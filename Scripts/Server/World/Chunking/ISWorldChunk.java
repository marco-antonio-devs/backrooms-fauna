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
 * Interface básica de declaração para representar um pedaço de mundo tridimensional para o <b>servidor</b>.
 * 
 * <p>
 * Contém funções para auxiliar em cálculos sensíveis isolados do <b>cliente</b>.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public interface ISWorldChunk
{
    /// Declaração de métodos. ///
    
    /**
     * Obtém o identificador único universal do respectivo pedaço.
     * 
     * <p>
     * O uso padrão deste método consiste no seguinte código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * UUID localUUID = serverWorldChunk.getUUID();
     * }
     * </p>
     * 
     * @return O identificador único universal local do respectivo pedaço.
     * 
     * @since  v2.2026.03f13
     */
    public UUID getUUID();
    
    /**
     * Obtém a contra-parte do lado do <b>cliente</b> do respectivo pedaço.
     * 
     * <p>
     * O uso padrão deste método consiste no seguinte código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * ICWorldChunk clientWorldChunk = serverWorldChunk.getClientSidedCounterpart();
     * }
     * </p>
     * <p>
     * Caso a contra-parte não seja encontrada, uma exceção de estado inválido será disparada indicando o erro.
     * </p>
     * 
     * @return Uma instância da contra-parte do lado do <b>cliente</b> do respectivo pedaço (caso não haja um erro).
     * 
     * @since  v2.2026.03f13
     */
    public ICWorldChunk getClientSidedCounterpart();
    
    /**
     * Inicialize um procedimento de remoção do respectivo pedaço do mundo.
     * 
     * <p>
     * O uso padrão deste método consiste no código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * serverWorldChunk.removeFromWorld(true); // O marcador para verdadeiro (`true`) permite que removerá forçadamente o pedaço do mundo.
     * }
     * </p>
     * 
     * @param forceToRemove Define se o procedimento deve ser executado forçadamente ou não.
     * 
     * @since v2.2026.03f13
     */
    public void removeFromWorld(boolean forceToRemove);
    
    /**
     * Obtém o nível de prioridade de atualização do respectivo pedaço.
     * 
     * <p>
     * O uso padrão deste método consiste no código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * int serverWorldChunkUpdatePriority = serverWorldChunk.getUpdatePriority();
     * }
     * </p>
     * 
     * @return Um número inteiro representando o nível de prioridade de atualização do respectivo pedaço.
     * 
     * @since  v2.2026.03f13
     */
    public int getUpdatePriority();
}
