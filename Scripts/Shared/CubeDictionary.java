/**
 * Pacote padrão dos módulos da ITsMagic Engine.
 *
 * @version vST2025.05f12
 * @author Lucas Leandro - O criador original do motor.
 * @since JDK 7.0 (Note que o compilador não é compatível com versões acima como JDK 8.0 por ser inviável).
 */
package JAVARuntime;

/**
 * Dicionario de blocos para se colocar no mundo.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public final class CubeDictionary
{
    // Constantes públicas de acesso estático.
    
    /**
     * Bloco de ar (representa o espaço vazio).
     */
    public static final byte AIR_BLOCK = 0;
    
    /**
     * Bloco de teto do pedaço.
     */
    public static final byte CEILING_BLOCK = 1;
    
    /**
     * Bloco de carpete normal (representa o carpete limpo).
     */
    public static final byte NORMAL_CARPET_BLOCK = 2;
    
    /**
     * Bloco de parede amarela (representa a parede monocromática do pedaço).
     */
    public static final byte YELLOW_WALL_BLOCK = 3;
    
    // Construtores privados.
    
    /**
     * Inibe a possibilidade de criar instancias desta classe.
     *
     * @throws IllegalStateException Não pode-se intanciar esta classe, use os campos estáticos.
     */
    private CubeDictionary()
    {
        throw new IllegalStateException("Não deve-se instanciar a classe CubeDictionary, use os campos estáticos");
    }
}
