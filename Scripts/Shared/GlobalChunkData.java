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
 * @version v2.2026.02f102025.10f17
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe utilitária de constantes globais.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class GlobalChunkData
{
    // Constantes públicas de acesso estático.
    
    /**
     * Tamanho horizontal dos pedaços.
     */
    public static final int W = 16;
    
    /**
     * Tamanho vertical dos pedaços (incluindo teto e piso).
     */
    public static final int H = 5;
}