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
 * @version vST2025.10f17
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe controladora do sistema de reservas globais.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class ChunkBuffers
{
    // Constantes públicas de acesso estático.
    
    /**
     *Número de colunas e linhas da textura.
     */
    public static final int ATLAS_BLOCK_TEXTURES = 2;
    
    /**
     * Referência de deslocamentos das normais.
     */
    public static final int[][] NORMAL_OFFSETS =
    {
        { 1,  0,  0},
        {-1,  0,  0},
        { 0,  1,  0},
        { 0, -1,  0},
        { 0,  0,  1},
        { 0,  0, -1},
    };
    
    /**
     * Referência das ordens de polígonos.
     */
    public static final int[][] POLY_ORDERS =
    {
        {2, 1, 0}, {3, 2, 0} // Individual para cada polígono, está confirmado por mim.
    };
    
    /**
     * Deslocamentos relativos das posições dos vértices.
     */
    public static final int[][][] VERTICE_OFFSETS =
    {
        {{1, 0, 0},{1, 0, 1},{1, 1, 1},{1, 1, 0}},
        {{0, 0, 0},{0, 1, 0},{0, 1, 1},{0, 0, 1}},
        {{0, 1, 0},{1, 1, 0},{1, 1, 1},{0, 1, 1}},
        {{0, 0, 0},{0, 0, 1},{1, 0, 1},{1, 0, 0}},
        {{0, 0, 1},{0, 1, 1},{1, 1, 1},{1, 0, 1}},
        {{0, 0, 0},{1, 0, 0},{1, 1, 0},{0, 1, 0}},
    };
    
    /**
     * O tamanho de cada ladrilho da textura.
     */
    public static final float TILE_SIZE = 1f / ATLAS_BLOCK_TEXTURES;
    
    // Construtores privados.
    
    /**
     * Inibe a possibilidade de criar instâncias desta classe.
     *
     * @throws IllegalStateException Não pode-se intanciar esta classe, use os métodos estáticos.
     */
    private ChunkBuffers()
    {
        throw new IllegalStateException("Não deve-se instanciar a classe ChunkBuffers, use os métodos estáticos");
    }
    
    // Métodos públicos.
    
    /**
     * Simule um pedaço dentro do mundo.
     *
     * @param voxels Os cubos do pedaço.
     * @param data Os dados de renderização do pedaço.
     */
    public static void simulate(OH3LevelIntArray voxels, CChunkData data)
    {
        for(int x = 0; x < GlobalChunkData.W; x++)
        {
            for(int z = 0; z < GlobalChunkData.W; z++)
            {
                for(int y = 0; y < GlobalChunkData.H; y++)
                {
                    voxels.set(x, y, z, (y == 0) ? CubeDictionary.NORMAL_CARPET_BLOCK : (y == GlobalChunkData.H - 1) ? CubeDictionary.CEILING_BLOCK : CubeDictionary.AIR_BLOCK);
                    
                    for(int i = 0; i < 6; i++)
                    {
                        int neighborID = ChunkUtils.getSideBlockID(voxels, x, y, z, i);
                        
                        if(neighborID == CubeDictionary.AIR_BLOCK)
                        {
                            data.incVerticesCount();
                            data.incPolygonsCount();
                            data.incNormalsCount();
                            data.incUVCount();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Crie uma malha com base nos dados recebidos.
     *
     * @param voxels A matriz tridimensional de blocos.
     * @param v A reserva de vértices do pedaço.
     * @param n A reserva de normais do pedaço.
     * @param uv A reserva de mapeamento tipo UV do pedaço.
     * @param verticeIndex O índice de vértice.
     */
    public static void generateMesh(OH3LevelIntArray voxels, Vector3Buffer v, Point3Buffer p, Vector3Buffer n, Vector2Buffer uv, int verticeIndex)
    {
        for(int x = 0; x < GlobalChunkData.W; x++)
        {
            for(int z = 0; z < GlobalChunkData.W; z++)
            {
                for(int y = 0; y < GlobalChunkData.H; y++)
                {
                    if(voxels.get(x, y, z) != CubeDictionary.AIR_BLOCK)
                    {
                        verticeIndex = addCube(VERTICE_OFFSETS, voxels, v, p, n, uv, x, y, z, verticeIndex);
                    }
                }
            }
        }
    }
    
    /**
     * Crie um cubo tridimensional para o ambiente.
     * <p>
     * Devido aos problemas técnicos, o método de adicionar face foi completamente removido.
     * </p>
     *
     * @param positions As posições do deslocamento.
     * @param voxels Os blocos do pedaço em questão.
     * @param v A reserva de vértices do pedaço.
     * @param n A reserva de normais do pedaço.
     * @param uv A reserva de mapeamento tipo UV do pedaço.
     * @param verticeIndex O índice de vértice do bloco.
     */
    public static int addCube(int[][][] positions, OH3LevelIntArray voxels, Vector3Buffer vertices, Point3Buffer polygons, Vector3Buffer normals, Vector2Buffer uv, int x, int y, int z, int verticeIndex)
    {
        for(int s = 0; s < 6; s++)
        {
            int neighborID = ChunkUtils.getSideBlockID(voxels, x, y, z, s);
            
            if(neighborID == CubeDictionary.AIR_BLOCK)
            {
                Vector2 point = ChunkUtils.determineBlockUVMapping(voxels.get(x, y, z));
                
                for(int i = 0; i < 4; i++)
                {
                    float u = (i == 0 || i == 3) ? 0 : 1;
                    float v = (i < 2) ? 0 : 1;
                    float uvX = (point.getX() * TILE_SIZE) + (u * TILE_SIZE) / ATLAS_BLOCK_TEXTURES;
                    float uvY = (point.getY() * TILE_SIZE) + (v * TILE_SIZE) / ATLAS_BLOCK_TEXTURES;
                    
                    addVertice(
                        verticeIndex,
                        VERTICE_OFFSETS[s][i],
                        vertices,
                        normals,
                        uv,
                        NORMAL_OFFSETS[s],
                        x,
                        y,
                        z,
                        uvX,
                        uvY
                    );
                    
                    verticeIndex++;
                }
                
                for(int i = 0; i < 2; i++)
                {
                    int px = POLY_ORDERS[i][0] + (verticeIndex - 4);
                    int py = POLY_ORDERS[i][1] + (verticeIndex - 4);
                    int pz = POLY_ORDERS[i][2] + (verticeIndex - 4);
                    
                    polygons.put(px, py, pz);
                }
            }
        }
        
        return verticeIndex;
    }
    
    /**
     * Adicione um vértice na reserva.
     * Cada vértice adicionará uma normal por sua vez.
     *
     * @param positions As posições disponíveis para o vetor.
     * @param v A reserva de vértices do pedaço.
     * @param n A reserva de normais do pedaço.
     * @param uv A reserva de mapeamento tipo UV do pedaço.
     * @param offset O deslocamento da face para as normais.
     * @param x A coordenada X da face.
     * @param y A coordenada Y da face.
     * @param z A coordenada Z da face.
     * @param uvX Coordenada U do mapeamento de textura.
     * @param uvY Coordenada V do mapeamento de textura.
     */
    private static void addVertice(int verticeIndex, int[] positions, Vector3Buffer v, Vector3Buffer n, Vector2Buffer uv, int[] offset, int x, int y, int z, float uvX, float uvY)
    {
        int vx = positions[0] + x;
        int vy = positions[1] + y;
        int vz = positions[2] + z;
        
        v.put(vx, vy, vz);
        n.put(offset[0], offset[1], offset[2]);
        uv.put(uvX, uvY);
    }
}
