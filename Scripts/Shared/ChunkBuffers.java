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
 * @version v2.2026.02f102025.12f6
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe controladora do sistema de reservas globais.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public final class ChunkBuffers
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
                    int blockType = (
                        y == 0 ?
                        CubeDictionary.NORMAL_CARPET_BLOCK : (
                            y == GlobalChunkData.H - 1 ?
                            CubeDictionary.CEILING_BLOCK :
                            CubeDictionary.AIR_BLOCK
                        )
                    );
                    
                    voxels.set(x, y, z, blockType);
                    
                    if(blockType != CubeDictionary.AIR_BLOCK)
                    {
                        int visibleFaces = 0;
                        
                        for(int side = 0; side < 6; side++)
                        {
                            if(ChunkUtils.getSideBlockID(voxels, x, y, z, side) == CubeDictionary.AIR_BLOCK)
                            {
                                visibleFaces++;
                            }
                            
                            if(visibleFaces > 0)
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
        
        RoomRect roomRect = new RoomRect(0, 0, 4, 4);
        
        roomRect.applyToVoxelsMap(voxels);
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
                        verticeIndex = addCube(voxels, v, p, n, uv, x, y, z, verticeIndex);
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
    public static int addCube(OH3LevelIntArray voxels, Vector3Buffer vertices, Point3Buffer polygons, Vector3Buffer normals, Vector2Buffer uv, int x, int y, int z, int verticeIndex)
    {
        int cubeID = voxels.get(x, y, z);
        
        int visibleFaceCount = 0;
        int[] visibleSides = new int[6];
        
        if(cubeID == CubeDictionary.AIR_BLOCK)
        {
            return verticeIndex;
        }
        
        for(int side = 0; side < 6; side++)
        {
            if(ChunkUtils.getSideBlockID(voxels, x, y, z, side) == CubeDictionary.AIR_BLOCK)
            {
                visibleSides[visibleFaceCount++] = side;
            }
        }
        
        if(visibleFaceCount == 0)
        {
            return verticeIndex;
        }
        
        
        Vector2 point = ChunkUtils.determineBlockUVMapping(cubeID);
        
        for(int faceIndex = 0; faceIndex < visibleFaceCount; faceIndex++)
        {
            int side = visibleSides[faceIndex];
            
            for(int verticeID = 0; verticeID < 4; verticeID++)
            {
                int[] positionOffset = VERTICE_OFFSETS[side][verticeID];
                int[] normalOffset = NORMAL_OFFSETS[side];
                
                final float u = ((verticeID == 0 || verticeID == 3) ? 0 : 1);
                final float v = ((verticeID < 2) ? 0 : 1);
                
                float uvX = (point.getX() * TILE_SIZE) + (u * TILE_SIZE) / ATLAS_BLOCK_TEXTURES;
                float uvY = (point.getY() * TILE_SIZE) + (v * TILE_SIZE) / ATLAS_BLOCK_TEXTURES;
                
                addVertice(
                    positionOffset,
                    vertices,
                    normals,
                    uv,
                    normalOffset,
                    x,
                    y,
                    z,
                    uvX,
                    uvY
                );
            }
            
            int currentIndex = verticeIndex;
            
            polygons.put(
                POLY_ORDERS[0][0] + currentIndex,
                POLY_ORDERS[0][1] + currentIndex,
                POLY_ORDERS[0][2] + currentIndex
            );
            polygons.put(
                POLY_ORDERS[1][0] + currentIndex,
                POLY_ORDERS[1][1] + currentIndex,
                POLY_ORDERS[1][2] + currentIndex
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
    private static void addVertice(int[] positions, Vector3Buffer v, Vector3Buffer n, Vector2Buffer uv, int[] offset, int x, int y, int z, float uvX, float uvY)
    {
        v.put(positions[0] + x, positions[1] + y, positions[2] + z);
        n.put(offset[0], offset[1], offset[2]);
        uv.put(uvX, uvY);
    }
}