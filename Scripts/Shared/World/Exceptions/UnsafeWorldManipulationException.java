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
 * Classe de exceção customizada que indica uma manipulação insegura rejeitada.
 * 
 * <p>
 * Esta classe é específica do projeto <b>Backrooms: Fauna</b>.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public final class UnsafeWorldManipulationException extends Exception
{
	/// Declaração de construtores. ///
	
	/**
	 * Crie uma nova instância da classe de exceção customizada.
	 * 
	 * @param message A mensagem a ser exibida no ato do disparo da exceção.
	 */
	public UnsafeWorldManipulationException(String message)
	{
		super(message);
	}
}
