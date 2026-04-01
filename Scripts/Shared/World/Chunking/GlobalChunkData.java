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
 * Classe utilitária de dados globais para pedaços do mundo.
 * 
 * <p>
 * Contém informações técnicas como diferentes níveis de prioridades e afins.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public final class GlobalChunkData
{
    /// Declaração de métodos. ///
    
    /**
     * Nível máximo de prioridades.
     * 
     * @note Este campo é acessível de forma estática.
     */
    public static final int MAX_PRIORITY_LEVEL = 15;
    
    /**
     * Nível médio de prioridades.
     * 
     * @note Este campo é acessível de forma estática.
     */
    public static final int MID_PRIORITY_LEVEL = 7;
    
    /// Declaração de construtores. ///
    
    /**
     * Crie uma instância da respectiva classe.
     * 
     * @note Este construtor não pode ser acessado por questões de segurança.
     */
    private GlobalChunkData()
    {
        throw new UnsupportedOperationException("Não está autorizado a instanciação desta classe. Use seus métodos estáticos.");
    }
}