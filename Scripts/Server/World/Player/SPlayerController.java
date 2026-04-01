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
 * Classe de implementação para representar o jogador.
 * 
 * <p>
 * Herda funções para auxiliar em cálculos sensíveis isolados do <b>cliente</b> da interface {@code ISPlayerController}.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public final class SPlayerController extends Component implements ISPlayerController
{
    /// Declaração de atributos. ///
    
    /**
     * Identificador único universal do respectivo jogador.
     * 
     * <p>
     * Usado para armazenar-lo em uma tabela baseada em <b>hash</b>.
     * </p>
     * 
     * @note  Este atributo é privado por questões de segurança. Utilize o método {@code getUUID()} para obter-lo (requer que a instância seja préviamente inicializada no mundo).
     * @since v2.2026.03f13
     */
    private UUID localUUID = null;
    
    /**
     * Referência ao corpo físico do objeto.
     * 
     * @note  Este atributo é privado por questões de segurança. Utilize o método {@code getCharacterPhysics()} para obter-lo (requer que a instância seja préviamente inicializada no mundo).
     * @since v2.2026.03f13
     */
    private Characterbody localPhysics = null;
    
    /// Implementação de métodos. ///
    
    /**
     * Inicialize este componente no primeiro tique após sua instanciação.
     * 
     * <p>
     * Usado principalmente para a configuração deste componente.
     * </p>
     * 
     * @note  Este método é chamado automaticamente e não precisa de chamada posterior.
     * @since v2.2026.03f13
     */
    @Override
    public void start()
    {
		localUUID = UUID.randomUUID();
		localPhysics = (Characterbody)(getObject().findComponent(Characterbody.class));
		
		Objects.requireNonNull(localPhysics, "Este objeto não contém ou não definiu uma referência válida para o corpo físico. Por favor, defina a referência.");
	}
    
    /**
     * Obtém o identificador único universal do respectivo jogador.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * 
     * @return O identificador único universal local do respectivo jogador.
     * 
     * @since  v2.2026.03f13
     */
    public UUID getUUID()
    {
        return localUUID;
    }
    
    /**
     * Obtém o identificador único universal do respectivo jogador.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface {@code ISPlayerController}.
     * </p>
     * <p>
     * Caso o corpo físico não seja encontrado, uma exceção de ponteiro nulo será disparada indicando o erro.
     * </p>
     * 
     * @return O corpo físico local do respectivo jogador (caso não haja um erro).
     * 
     * @since  v2.2026.03f13
     */
    public Characterbody getCharacterPhysics()
    {
		Objects.requireNonNull(localPhysics, "Este objeto não contém ou não definiu uma referência válida para a contra-parte do lado do cliente. Por favor, defina a referência.");
		
        return localPhysics;
    }
}
