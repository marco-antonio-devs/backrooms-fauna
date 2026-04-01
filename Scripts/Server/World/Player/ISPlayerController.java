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
 * Interface básica para representar o jogador.
 * 
 * <p>
 * Contém funções para auxiliar em cálculos sensíveis isolados do <b>cliente</b>.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public interface ISPlayerController
{
    /// Declaração de métodos. ///
    
    /**
     * Obtém o identificador único universal do respectivo jogador.
     * 
     * <p>
     * O uso padrão deste método consiste no seguinte código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * UUID localUUID = serverPlayer.getUUID();
     * }
     * </p>
     * 
     * @return O identificador único universal local do respectivo jogador.
     * 
     * @since  v2.2026.03f13
     */
    public UUID getUUID();
    
    /**
     * Obtém o identificador único universal do respectivo jogador.
     * 
     * <p>
     * O uso padrão deste método consiste no seguinte código de exemplo abaixo.
     * </p>
     * <p>
     * {@code
     * Characterbody localPhysics = serverPlayer.getCharacterPhysics();
     * }
     * </p>
     * <p>
     * Caso o corpo físico não seja encontrado, uma exceção de ponteiro nulo será disparada indicando o erro.
     * </p>
     * 
     * @return O corpo físico local do respectivo jogador (caso não haja um erro)..
     * 
     * @since  v2.2026.03f13
     */
    public Characterbody getCharacterPhysics();
}
