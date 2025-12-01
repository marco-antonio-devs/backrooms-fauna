/**
 * Pacote padrão dos módulos da ITsMagic Engine.
 *
 * <p>
 * Aviso: O compilador integrado do motor requer recursos do JDK 7.0 para baixo.
 * - Segundo meu contato com Lucas Leandro, o uso de compilador antigo é parte de uma limitação.
 * - Ele continua afirmando que compiladores modernos requerem do Gradle.
 * - Que por sua vez é quase impossível de funcionar no ambiente de desenvolvimente diretamente pelos dispositivos via-Android.
 * </p>
 *
 * @version vST2025.10f13
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe de componente do gerador de pedaços tridimensionais.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SChunkGenerator extends Component
{
    // Campos públicos (devem ser inicializados no painel de propriedades do componente).
    
    // A referência de material dos modelos.
    
    public MaterialFile material;
    
    // Tempo máximo para atualizar o estado de jogo atual.
    
    public static final float UPDATE_INTERVAL = 1.0f;
    
    // Quantidade de pedaços por cada quadro.
    
    public static final int CHUNKS_PER_FRAME = 1;
    
    // Tamanho máximo da fila de pedaços.
    
    public static final int MAX_QUEUED_CHUNKS = CHUNKS_PER_FRAME * CHUNKS_PER_FRAME;
    
    // Campos privados.
    
    // A distância de simulação em raio.
    
    private final int simulation = 1;
    
    // O mapa de pedaços e suas posições.
    
    private final Map<Long, SChunk> chunks = new HashMap<Long, SChunk>();
    
    // A definição de posições dos pedaços renderizados.
    
    private final Set<Long> renderedChunks = new HashSet<Long>();
    
    // O tempo em tiques para gerenciamento geral.
    
    private float ticks = 0f;
    
    // O gerenciador de jogadores em lista.
    
    private final Set<SPlayerController> players = new HashSet<SPlayerController>();
    
    // A definição de valores longos cujo representa as coordenadas onde precisa simular pedaços.
    
    private final Set<Long> neededChunks = new HashSet<Long>();
    
    // A fila de espera para pedaços.
    
    private final Queue<Long> queuedChunks = new LinkedList<Long>();
    
    // A referência de posição para o pedaço do mundo.
    
    private Vector2 chunkPosition = null;
    
    // A referência de posição para o jogador selecionado.
    
    private Vector3 playerPosition = new Vector3();
    
    // A referência de posições para os jogadores.
    
    private final Set<Long> playerChunkPositions = new HashSet<Long>();
    
    // As distâncias normais e quadradas.
    
    private float distanceSquare = -1f;
    private float minDistanceSquare = -1;
    
    // Métodos públicos.
    
    /**
     * Obtém o menu do componente.
     *
     * @return O caminho do menu atual do componente SChunkGenerator.
     */
    @Override
    public String getComponentMenu()
    {
        return "Backrooms/Server-Side";
    }
    
    /**
     * Repete esta função a cada quadro a ser renderizado.
     *
     * Este método pertence à classe Component, não chame-a (risco de sobrecarga da CPU e memória RAM).
     */
    @Override
    public void repeat()
    {
        ticks += Math.bySecond();
        
        if(ticks <= UPDATE_INTERVAL) return;
        
        neededChunks.clear();
        
        for(SPlayerController player : players)
        {
            playerPosition.set(player.getObject().getGlobalPosition());
            
            int px = (int)(playerPosition.getX() / GlobalChunkData.W);
            int pz = (int)(playerPosition.getZ() / GlobalChunkData.W);
            
            playerChunkPositions.add(CoordinatesUtils.createCoordinate(px, pz));
            
            for(int x = -simulation; x < simulation; x++)
            {
                for(int z = -simulation; z < simulation; z++)
                {
                    neededChunks.add(CoordinatesUtils.createCoordinate(x + px, z + pz));
                }
            }
        }
        
        Iterator<Map.Entry<Long, SChunk>> iterator = chunks.entrySet().iterator();
        
        while(iterator.hasNext())
        {
            Map.Entry<Long, SChunk> set = iterator.next();
            
            long key = set.getKey();
            
            SpatialObject value = set.getValue().getObject();
            
            if(value == null || !value.exists())
            {
                iterator.remove();
                
                continue;
            }
            
            SChunk sChunk = set.getValue();
            CChunk cChunk = value.findComponent(CChunk.class);
            
            if(sChunk == null || cChunk == null)
            {
                iterator.remove();
                
                continue;
            }
            
            chunkPosition = cChunk.getGridPosition();
            
            float cx = chunkPosition.getX();
            float cz = chunkPosition.getY();
            
            minDistanceSquare = Float.MAX_VALUE;
            
            for(long coord : playerChunkPositions)
            {
                float dx = cx - CoordinatesUtils.extractX(coord);
                float dz = cz - CoordinatesUtils.extractZ(coord);
                
                distanceSquare = ((dx * dx) + (dz * dz));
                
                if(distanceSquare < minDistanceSquare)
                {
                    minDistanceSquare = distanceSquare;
                }
                
                if(minDistanceSquare == 0) break;
            }
        }
        
        for(long coord : neededChunks)
        {
            if(!chunks.containsKey(coord) && !queuedChunks.contains(coord) && queuedChunks.size() <= MAX_QUEUED_CHUNKS) queuedChunks.add(coord);
        }
        
        for(int i = 0; i < CHUNKS_PER_FRAME && !queuedChunks.isEmpty(); i++)
        {
            long coord = queuedChunks.poll();
            
            generate(coord);
        }
        
        ticks = 0f;
    }
    
    /**
     * Gere os pedaços necessários para a renderização e simulação.
     *
     * @param chunkPosition A posição do pedaço.
     */
    public void generate(long chunkPosition)
    {
        int x = CoordinatesUtils.extractX(chunkPosition);
        int z = CoordinatesUtils.extractZ(chunkPosition);
        int worldX = x * GlobalChunkData.W;
        int worldZ = z * GlobalChunkData.W;
        
        SpatialObject object = new SpatialObject("Chunk", myObject);
        ModelRenderer model = new ModelRenderer();
        
        object.addComponent(model);
        object.addComponent(new Collider());
        object.addComponent(new CChunk());
        object.addComponent(new SChunk());
        model.setMaterialFile(material);
        
        object.setPosition(new Vector3(worldX, 0f, worldZ));
        object.setStatic(true);
        
        SChunk sChunk = object.findComponent(SChunk.class);
        
        chunks.put(chunkPosition, sChunk);
    }
    
    /**
     * Adicione um jogador à lista de referências.
     *
     * @param value A referência de jogador.
     */
    public void addPlayerToList(SPlayerController value)
    {
        if(players.contains(value)) return;
        
        players.add(value);
    }
}
