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
 * O sistema de DNA, uma maneira de gerar criaturas proceduralmente.
 *
 * @Author Marco Antônio Pereira Júnior.
 */
public final class DNASystem
{
    // Construtores privados.
    
    /**
     * Inibe a possibilidade de criar instancias desta classe.
     *
     * @throws IllegalStateException Não pode-se intanciar esta classe, use os métodos estáticos.
     */
    private DNASystem()
    {
        throw new IllegalStateException("Não deve-se instanciar a classe DNASystem, use os métodos estáticos");
    }
}
