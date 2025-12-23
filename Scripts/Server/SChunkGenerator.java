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
 * @version v2.2025.12f14
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
    
    // Constantes públicas.
    
    /**
     * Tempo máximo em tiques para atualizar o estado de jogo atual.
     */
    public static final float UPDATE_INTERVAL = 0.2f;
    
    /**
     * Quantidade de pedaços por cada quadro.
     */
    public static final int CHUNKS_PER_FRAME = 1;
    
    /**
     * Quantidade de descargas por cada quadro.
     */
    public static final int UNLOADS_PER_FRAME = 2;
    
    // Campos privados.
    
    // A distância de simulação em raio.
    
    private final int simulation = 1;
    
    // O mapa de pedaços e suas posições.
    
    private final Map<Long, SChunk> chunks = new HashMap<Long, SChunk>();
    
    // O tempo em tiques para gerenciamento geral.
    
    private float ticks = 0;
    
    // O gerenciador de jogadores em lista.
    
    private final Set<SPlayerController> players = new HashSet<SPlayerController>();
    
    // A definição de valores longos cujo representa as coordenadas onde precisa simular pedaços.
    
    private final Set<Long> neededChunks = new HashSet<Long>();
    
    // A fila de espera para pedaços.
    
    private final Queue<Long> queuedChunks = new LinkedList<Long>();
    
    // A referência de posição para o jogador selecionado.
    
    private final Vector3 playerPosition = new Vector3();
    
    // A referência de posições para os jogadores.
    
    private final Set<Long> playerChunkPositions = new HashSet<Long>();
    
    // Uma referência temporária de vetor tridimensional.
    
    private final Vector3 temporaryVector3 = new Vector3();
    
    // Uma referência temporária de jogador.
    
    @Singleton
    private SPlayerController player;
    
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
     * Método chamado automaticamente no primeiro quadro de execução.
     *
     * <p><b>Aviso:</b> este método é parte do ciclo de vida da classe Component e não deve ser invocado manualmente.</p>
     */
    @Override
    public void start()
    {
        playerPosition.set(player.getObject().getTransform().getGlobalPosition());
    }
    
    /**
     * Método chamado automaticamente durante cada quadro de execução.
     *
     * <p><b>Aviso:</b> este método é parte do ciclo de vida da classe Component e não deve ser invocado manualmente.</p>
     */
    @Override
    public void repeat()
    {
        ticks += Math.bySecond();
        
        if(ticks > UPDATE_INTERVAL)
        {
            update();
            
            ticks = 0;
        }
    }
    
    /**
     * Crie pedaços em gatilhos, permitindo várias chamadas agendadas.
     */
    public void update()
    {
        neededChunks.clear();
        playerChunkPositions.clear();
        
        int px = (int)(playerPosition.getX() / GlobalChunkData.W);
        int pz = (int)(playerPosition.getZ() / GlobalChunkData.W);
        
        playerChunkPositions.add(CoordinatesUtils.createCoordinate(px, pz));
        
        for(int x = -simulation; x <= simulation; x++)
        {
            for(int z = -simulation; z <= simulation; z++)
            {
                neededChunks.add(CoordinatesUtils.createCoordinate(x + px, z + pz));
            }
        }
        
        for(long coord : neededChunks)
        {
            if(!chunks.containsKey(coord) && !queuedChunks.contains(coord) && queuedChunks.size() <= CHUNKS_PER_FRAME)
                queuedChunks.add(coord);
        }
        
        for(int i = 0; i < CHUNKS_PER_FRAME && !queuedChunks.isEmpty(); i++)
        {
            long coord = queuedChunks.poll();
            
            generate(coord);
        }
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
        int worldX = x * GlobalChunkData.W - (GlobalChunkData.W / 2);
        int worldZ = z * GlobalChunkData.W - (GlobalChunkData.W / 2);
        
        SpatialObject object = new SpatialObject("Chunk", myObject);
        ModelRenderer model = new ModelRenderer();
        
        object.addComponent(model);
        object.addComponent(new Collider());
        object.addComponent(new CChunk());
        object.addComponent(new SChunk());
        model.setMaterialFile(material);
        
        temporaryVector3.set(worldX, 0f, worldZ);
        
        object.setPosition(temporaryVector3);
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
        if(players.contains(value))
        {
            return;
        }
        
        players.add(value);
    }
}