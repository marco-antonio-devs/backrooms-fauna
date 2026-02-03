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
 * Interface do controlador de entidade (via-lado do servidor).
 *
 * @author Marco Antônio Pereira Júnior.
 */
public interface ISEntity
{
    // Métodos públicos.
    
    /**
     * Obtém o estado de comportamento da entidade.
     *
     * @return O estado da entidade, que faz a mesma se comportar como um animal.
     */
    public SEntityBehaviour getBehaviour();
}