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
 * @version v2.2026.02f1
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe responsável pela criação e renderização de pedaços no <b>lado do cliente</b>.
 * 
 * Sua principal função é gerar a malha visual do ambiente tridimensional com base nos dados enviados pelo servidor.
 * 
 * @author Marco Antônio Pereira Júnior.
 */
public class CChunk extends Component
{
    // Campos privados.
    
    // Reserva local dos vértices.
    
    private Vector3Buffer localVerticesBuffer;
    
    // Reserva local dos polígonos.
    
    private Point3Buffer localPolygonsBuffer;
    
    // Reserva local das normais.
    
    private Vector3Buffer localNormalsBuffer;
    
    // Reserva local de mapeamento de textura.
    
    private Vector2Buffer localUVBuffer;
    
    // Dados locais do pedaço.
    
    private CChunkData chunkData = new CChunkData();
    
    // Índice de vértice localizada no objeto.
    
    private int verticeIndex = 0;
    
    @AutoWired
    private ModelRenderer model = null;
    
    @AutoWired
    private Collider collider = null;
    
    // Componente correspondente no lado do servidor.
    
    @AutoWired
    private SChunk parentSChunk = null;
    
    // Métodos públicos.
    
    /**
     * Obtém o menu onde o componente será exibido na interface da engine.
     *
     * @return O caminho hierárquico do menu.
     */
    @Override
    public String getComponentMenu()
    {
        return "Backrooms/Client-Side";
    }
    
    /**
     * Método chamado automaticamente no primeiro quadro de execução.
     *
     * <p><b>Aviso:</b> este método é parte do ciclo de vida da classe Component e não deve ser invocado manualmente.</p>
     */
    @Override
    public void start()
    {
        final OH3LevelIntArray voxels = parentSChunk.getVoxelArray();
        
        ChunkBuffers.simulate(voxels, chunkData);
        generateBuffers(chunkData);
        
        new AsyncTask(new AsyncRunnable()
        {
            public Object onBackground(Object input)
            {
                ChunkBuffers.generateMesh(
                    voxels,
                    localVerticesBuffer,
                    localPolygonsBuffer,
                    localNormalsBuffer,
                    localUVBuffer,
                    verticeIndex
                );
                
                return null;
            }
            
            public void onEngine(Object result)
            {
                Vertex v = apply();
                
                model.setVertex(v);
                collider.setVertex(v);
            }
        });
    }
    
    /**
     * Defina as reservas para a malha.
     */
    public void generateBuffers(CChunkData d)
    {
        localVerticesBuffer = BufferUtils.createVector3Buffer(d.getVerticesCount());
        localPolygonsBuffer = BufferUtils.createPoint3Buffer(d.getPolygonsCount());
        localNormalsBuffer = BufferUtils.createVector3Buffer(d.getNormalsCount());
        localUVBuffer = BufferUtils.createVector2Buffer(d.getUVCount());
    }
    
    /**
     * Define a visibilidade do modelo do pedaço.
     *
     * @param value {@code true} para exibir; {@code false} para ocultar.
     */
    public void setVisibility(boolean value)
    {
        if(model != null)
        {
            model.setEnabled(value);
        }
    }
    
    /**
     * Retorna a posição relativa do pedaço na grade global.
     *
     * @return Vetor bidimensional contendo as coordenadas X e Z.
     */
    public Vector2 getGridPosition()
    {
        return myObject.getGlobalPosition().getXZ();
    }
    
    /**
     * Retorna uma representação textual do pedaço.
     *
     * @return Um texto formatado com as coordenadas do pedaço.
     */
    @Override
    public String toString()
    {
        return String.format(
            "Chunk [%d:%d]",
            (int)(getGridPosition().getX()),
            (int)(getGridPosition().getY())
        );
    }
    
    /**
     * Aplique as alterações no modelo do pedaço local.
     *
     * @return A malha criada a partir da aplicação.
     */
    public Vertex apply()
    {
        Vertex vertex = new Vertex();
        
        vertex.setVertices(localVerticesBuffer);
        vertex.setTriangles(localPolygonsBuffer);
        vertex.setNormals(localNormalsBuffer);
        vertex.setUVs(localUVBuffer);
        
        vertex.apply();
        
        return vertex;
    }
}