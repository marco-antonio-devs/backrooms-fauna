/**
 * Pacote de módulos internos do mecanismo <b>ITsMagic Engine</b>.
 * 
 * <p>
 * <ul>
 * <li>O compilador interno usa a sétima versão do <b>Conjunto de Desenvolvimento Java</b> (também conhecido como "<b>J.D.K. 7</b>").</li>
 * <li>Isso não afeta a compatibilidade no dispositivo final — trata-se de uma limitação estrutural do <b>compilador</b>.</li>
 * <li>Segundo o <b>Lucas</b> (criador oficial do mecanismo), ele optou por um compilador antigo por ser a <b>única</b> versão possível de usar como compilador direto.</li>
 * </ul>
 * </p>
 * 
 * @version v2.2026.03f13
 */
package JAVARuntime;

/// Importações implícitas (comentadas por não serem necessárias explicitar).

/// import java.util.*;
/// import java.io.*;
/// import java.net.*;
/// 
/// import JAVARuntime.*;

/**
 * Interface básica de declaração para representar um pedaço do mundo tridimensional para o <b>cliente</b>.
 * 
 * <p>
 * Contém funções para auxiliar na renderização, bem como a simplificação de malha.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public interface ICWorldChunk
{
    /// Declaração de métodos. ///
    
    /**
     * Obtém a contra-parte do lado do <b>servidor</b> do respectivo pedaço.
     * 
     * <p>
     * O uso padrão deste método consiste no seguinte código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * ISWorldChunk serverWorldChunk = clientWorldChunk.getServerSidedCounterpart();
     * }
     * </p>
     * <p>
     * Caso a contra-parte não seja encontrada, uma exceção de estado inválido será disparada indicando o erro.
     * </p>
     * 
     * @return Uma instância da contra-parte do lado do <b>servidor</b> do respectivo pedaço (caso não haja um erro).
     * 
     * @since  v2.2026.03f13
     */
    public ISWorldChunk getServerSidedCounterpart();
    
    /**
     * Obtém o nível de prioridade de renderização do respectivo pedaço.
     * 
     * <p>
     * O uso padrão deste método consiste no seguinte código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * int clientWorldChunkRenderPriority = clientWorldChunk.getRenderPriority();
     * }
     * </p>
     * <p>
     * O nível de prioridade de renderização define se o respectivo pedaço precisa ser renderizado em uma faixa de zero (0) a quinze (15).
     * </p>
     * 
     * @return Um número inteiro representando o nível de prioridade de renderização do respectivo pedaço.
     * 
     * @since  v2.2026.03f13
     */
    public int getRenderPriority();
}