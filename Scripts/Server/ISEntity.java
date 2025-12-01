/**
 * Pacote padrão dos módulos da ITsMagic Engine.
 *
 * @version vST2025.05f12
 * @author Lucas Leandro - O criador original do motor.
 * @since JDK 7.0 (Note que o compilador não é compatível com versões acima como JDK 8.0 por ser inviável).
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
