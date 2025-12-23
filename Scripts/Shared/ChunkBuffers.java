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
 * Classe controladora do sistema de reservas globais.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class ChunkBuffers
{
    // Constantes públicas de acesso estático.
    
    /**
     * Semente de geração do mundo.
     */
    public static final long SEED = 128L;
    
    /**
     * Número de colunas e linhas da textura.
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
                    voxels.set(
                        x,
                        y,
                        z,
                        (
                            y == 0 ?
                            CubeDictionary.NORMAL_CARPET_BLOCK :
                            (
                                y == GlobalChunkData.H - 1 ?
                                CubeDictionary.CEILING_BLOCK :
                                CubeDictionary.AIR_BLOCK
                            )
                        )
                    );
                    
                    for(int i = 0; i < 6; i++)
                    {
                        if(ChunkUtils.getSideBlockID(voxels, x, y, z, i) == CubeDictionary.AIR_BLOCK)
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
     * Crie uma nova modificação, adicionando um cubo tridimensional.
     *
     * @param positions As posições do deslocamento.
     * @param voxels Os blocos do pedaço em questão.
     * @param v A reserva de vértices do pedaço.
     * @param n A reserva de normais do pedaço.
     * @param uv A reserva de mapeamento tipo UV do pedaço.
     * @param verticeIndex O índice de vértice do bloco.
     *
     * @return O novo índice de vértice.
     */
    public static int addCube(int[][][] positions, OH3LevelIntArray voxels, Vector3Buffer vertices, Point3Buffer polygons, Vector3Buffer normals, Vector2Buffer uv, int x, int y, int z, int verticeIndex)
    {
        int cubeID = voxels.get(x, y, z);
        
        if(cubeID == CubeDictionary.AIR_BLOCK)
        {
            return verticeIndex;
        }
        
        Vector2 uvPoint = ChunkUtils.determineBlockUVMapping(cubeID);
        
        byte visibleFaces = 0;
        
        for(int side = 0; side < 6; side++)
        {
            if(ChunkUtils.getSideBlockID(voxels, x, y, z, side) == CubeDictionary.AIR_BLOCK)
            {
                visibleFaces |= (1 << side);
            }
        }
        
        if(visibleFaces == 0)
        {
            return verticeIndex;
        }
        
        for(int side = 0; side < 6; side++)
        {
            if((visibleFaces & (1 << side)) == 0)
            {
                continue;
            }
            
            for(int verticeID = 0; verticeID < 4; verticeID++)
            {
                int[] positionOffset = VERTICE_OFFSETS[side][verticeID];
                int[] normalOffset = NORMAL_OFFSETS[side];
                
                float uCoordinate = uvPoint.getX() * TILE_SIZE + ((verticeID == 0 || verticeID == 3) ? 0 : TILE_SIZE);
                float vCoordinate = uvPoint.getY() * TILE_SIZE + ((verticeID < 2) ? TILE_SIZE : 0);
                
                addVertice(
                    verticeIndex,
                    positionOffset,
                    vertices,
                    normals,
                    uv,
                    normalOffset,
                    x,
                    y,
                    z,
                    uCoordinate,
                    vCoordinate
                );
            }
            
            polygons.put(
                POLY_ORDERS[0][0] + verticeIndex,
                POLY_ORDERS[0][1] + verticeIndex,
                POLY_ORDERS[0][2] + verticeIndex
            );
            polygons.put(
                POLY_ORDERS[1][0] + verticeIndex,
                POLY_ORDERS[1][1] + verticeIndex,
                POLY_ORDERS[1][2] + verticeIndex
            );
            
            verticeIndex += 4;
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
        v.put(positions[0] + x, positions[1] + y, positions[2] + z);
        n.put(offset[0], offset[1], offset[2]);
        uv.put(uvX, uvY);
    }
}