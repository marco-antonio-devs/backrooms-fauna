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
 * Classe utilitária de pedaços do mundo.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class ChunkUtils
{
    // Métodos públicos de acesso estático.
    
    /**
     * Verifique o bloco ao lado deste.
     *
     * @param voxels A matriz tridimensional de blocos.
     * @param position A posição relativa para checagem.
     * @param sideIndex O índice do lado solicitado.
     *
     * @return O identificador do bloco solicitado ao lado.
     */
    public static int getSideBlockID(OH3LevelIntArray voxels, int x, int y, int z, int sideIndex)
    {
        int rx = x;
        int ry = y;
        int rz = z;
        
        switch(sideIndex)
        {
            case 0: rx++; break;
            case 1: rx--; break;
            case 2: ry++; break;
            case 3: ry--; break;
            case 4: rz++; break;
            case 5: rz--; break;
        }
        
        if(rx < 0 || rx >= GlobalChunkData.W || ry < 0 || ry >= GlobalChunkData.H || rz < 0 || rz >= GlobalChunkData.W)
        {
            return CubeDictionary.AIR_BLOCK;
        }
        else
        {
            return voxels.get(rx, ry, rz);
        }
    }
    
    /**
     * Determine a textura do bloco em questão.
     *
     * @param block O bloco do pedaço (sem coordenadas).
     *
     * @return O tipo de textura segundo o deslocamento do plano cartesiano.
     */
    public static Vector2 determineBlockUVMapping(int block)
    {
        float division = block / 2f;
        int lines = (int)(division);
        float offset = division - lines;
        
        return new Vector2(offset, lines);
    }
}