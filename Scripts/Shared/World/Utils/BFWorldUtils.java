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
 * Classe utilitária para o gerenciamento de mundo.
 * 
 * <p>
 * Esta classe é específica do projeto <b>Backrooms: Fauna</b> e serve como uma extensão da classe {@code WorldController}.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public final class BFWorldUtils
{
    /// Declaração de atributos. ///
    
    /**
     * Tabela de pedaços do lado do <b>servidor</b>.
     * 
     * @note  Este atributo é privado por questões de segurança. Utilize o método {@code getServerWorldChunks()} para obter-lo.
     * @since v2.2026.03f13
     */
    private static final Map<Long, SWorldChunk> serverWorldChunks = Collections.synchronizedMap(new HashMap<Long, SWorldChunk>());
    
    /// Declaração de construtores. ///
    
    /**
     * Crie uma instância da respectiva classe.
     * 
     * @note Este construtor não pode ser acessado por questões de segurança.
     */
    private BFWorldUtils()
    {
        throw new UnsupportedOperationException("Não está autorizado a instanciação desta classe. Use seus métodos e atributos estáticos.");
    }
    
    /// Implementação de métodos. ///
    
    /**
     * Adicione um pedaço do mundo à tabela específica aos pertencentes do <b>servidor</b>.
     * 
     * @param  coordinate A coordenada empacotada da sua instanciação no mundo.
     * @param  worldChunk O pedaço do mundo, especificamente do lado do <b>servidor</b>, solicitado para a adição na tabela.
     * 
     * @throws UnsafeWorldManipulationException Uma exceção específica do projeto para fins de segurança durante a manipulação do mundo.
     */
    public static void addServerWorldChunk(long coordinate, SWorldChunk worldChunk) throws UnsafeWorldManipulationException
    {
        Objects.requireNonNull(worldChunk, "A chamada para este método não introduziu uma referência válida de pedaço do mundo específico do servidor. Por favor, defina a referência.");
        
        if(serverWorldChunks.containsKey(coordinate))
        {
            Log warning = new Log();
            
            warning.setTittle("Aviso de pedaço existente do mundo.");
            warning.setMessage("Houve uma tentativa de adicionar um pedaço existente do mundo para a tabela.\nNote que por existir préviamente, está acessível por meio de `getServerWorldChunk(long coordinate)`.");
            
            Terminal.log(warning);
            
            return;
        }
        
        serverWorldChunks.put(coordinate, worldChunk);
    }
    
    /**
     * Obtém uma cópia imutável da tabela de pedaços do mundo.
     * 
     * <p>
     * Por motivos de segurança, o uso deste método é <b>obrigatório</b> ao obter a tabela.
     * </p>
     * 
     * @return Uma cópia da tabela, cujo não pode ser manipulada.
     */
    public static Map getServerWorldChunks()
    {
        return Collections.unmodifiableMap(serverWorldChunks);
    }
    
    /**
     * Obtém um pedaço do mundo, especificamente do lado do <b>servidor</b>.
     * 
     * @param  coordinate A coordenada empacotada do pedaço a ser consultado.
     * 
     * @return Uma instância de pedaço do mundo específico do lado do <b>servidor</b> (caso não haja um erro).
     */
    public static SWorldChunk getServerWorldChunk(long coordinate)
    {
        if(!serverWorldChunks.containsKey(coordinate))
        {
            throw new InvalidArgumentException("A coordenada solicitada para a obtenção do pedaço do mundo não está definida na tabela. Por favor, certifique-se de que a cordenada esteja correta.");
        }
        
        return serverWorldChunks.get(coordinate);
    }
}