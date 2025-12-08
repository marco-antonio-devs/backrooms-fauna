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
 * Classe utilitária de ruído.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class NoiseUtils
{
    // Constantes públicas.
    
    // Extensão da imagem de ruído.
    
    public static final int NOISE_S = 1024;
    
    // Imagem do ruído de Ken Perlin.
    
    public static PerlinNoise PERLIN_NOISE = new PerlinNoise(NOISE_S);
    
    // Métodos públicos de acesso estático.
    
    /**
     * Obtém um ponto da imagem de ruído de Ken Perlin.
     *
     * @param x A coordenada X da imagem de ruído.
     * @param y A coordenada Y da imagem de ruído.
     *
     * @return Um valor de ponto flutuante de 0.0 a 1.0, representando o nível de claridade desse ponto.
     */
    public static float getNoise(float x, float y)
    {
        return PERLIN_NOISE.noise(x, y);
    }
}
