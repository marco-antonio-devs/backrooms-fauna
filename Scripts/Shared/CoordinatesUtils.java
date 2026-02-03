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
 * Classe utilitária para coordenadas em tipo primitivo.
 *
 * @author Marco Antonio Pereira Junior.
 */
public final class CoordinatesUtils
{
    // Construtores privados.
    
    /**
     * Inibe a possibilidade de criar instancias desta classe.
     *
     * @throws IllegalStateException Não pode-se instanciar esta classe, use os métodos estáticos.
     */
    private CoordinatesUtils()
    {
        throw new IllegalStateException("Não deve-se instanciar a classe CoordinatesUtils, use os métodos estáticos");
    }
    
    // Métodos públicos de acesso estático.
    
    /**
     * Crie uma nova coordenada baseada em tipo primitivo.
     *
     * @param x A posição em X solicitada.
     * @param z A posição em Z solicitada.
     *
     * @return Um número em <b>long</b> feito para representar uma coordenada bidimensional.
     */
    public static long createCoordinate(int x, int z)
    {
        return (((long)(x) << 32) | (z & 0xFFFFFFFFL));
    }
    
    /**
     * Extraia a posição X da coordenada primitiva.
     *
     * @param coord A coordenada primitiva solicitada.
     *
     * @return A posição X solicitada.
     */
    public static int extractX(long coord)
    {
        return (int)(coord >> 32);
    }
    
    /**
     * Extraia a posição Z da coordenada primitiva.
     *
     * @param coord A coordenada primitiva solicitada.
     *
     * @return A posição Z solicitada.
     */
    public static int extractZ(long coord)
    {
        return (int)(coord);
    }
}