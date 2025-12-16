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
 * @version v2.2025.12f6
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe auxiliar instanciável para criação de retângulos de salas.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class RoomRect
{
    // Campos privados.
    
    // Posição X e Z do retângulo, largura e profundidade.
    
    private int x, z, width, depth;
    
    // Construtores públicos.
    
    /**
     * Crie uma nova instância de retângulo para salas.
     *
     * @param x A coordenada X do pivô.
     * @param z A coordenada Z do pivô.
     * @param width A largura do retângulo.
     * @param depth A profundidade do retângulo.
     */
    public RoomRect(int x, int z, int width, int depth)
    {
        this.x = x;
        this.z = z;
        this.width = width;
        this.depth = depth;
    }
    
    // Campos públicos.
    
    /**
     * Retorne um verificador de intersecção entre dois retângulos imaginários.
     * <p>
     * Utiliza uma margem de 1, tanto ao sentido negativo quanto ao positivo.
     * </p>
     *
     * @param other O outro retângulo para fins de medida.
     *
     * @return Um valor booleano ({@code true} para indicar que há uma intersecção entre este e o outro retângulo).
     */
    public boolean haveIntersectionWith(RoomRect other)
    {
        return (
            x - 1 <= other.x + other.width + 1 &&
            x + width + 1 >= other.x + 1 &&
            z - 1 <= other.z + other.depth + 1 &&
            z + depth + 1 >= other.z + 1
        );
    }
    
    /**
     * Obtém um vetor bidimensional indicando o centro do retângulo.
     *
     * @return Um vetor bidimensional contendo o centro.
     */
    public Vector2 getCenter()
    {
        return new Vector2(x + width / 2f, z + depth / 2f);
    }
    
    /**
     * Aplique as paredes ao mapa de blocos.
     *
     * @param voxels O mapa de blocos do pedaço.
     */
    public void applyToVoxelsMap(OH3LevelIntArray voxels)
    {
        for(int dx = 0; dx < width; dx++)
        {
            for(int dz = 0; dz < depth; dz++)
            {
                for(int y = 1; y < voxels.getSizeY() - 1; y++)
                {
                    boolean border = (
                        dx == 0 ||
                        dx == width - 1 ||
                        dz == 0 ||
                        dz == depth - 1
                    );
                    
                    voxels.set(
                        x + dx,
                        y,
                        z + dz, (border) ?
                        CubeDictionary.YELLOW_WALL_BLOCK : CubeDictionary.AIR_BLOCK
                    );
                }
            }
        }
    }
}