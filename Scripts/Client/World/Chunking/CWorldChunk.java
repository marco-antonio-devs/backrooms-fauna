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
 * Classe de implementação para representar um pedaço do mundo tridimensional para o <b>cliente</b>.
 *  
 * <p>
 * Herda as funções para auxiliar em cálculos do <b>cliente</b> da interface {@code ISPlayerController}.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public final class CWorldChunk extends Component implements ICWorldChunk
{
    /// Declaração de atributos. ///
    
    /**
     * Referência do componente que implementa a interface da contra-parte do respectivo pedaço.
     * 
     * <p>
     * Usado internamente para não ser preciso o uso do método {@code getServerSidedCounterpart()}.
     * </p>
     * 
     * @note  Este campo é privado por questões de segurança. Utilize o método {@code getServerSidedCounterpart()} para obter-lo (requer que a instância seja préviamente inicializada no mundo).
     * @since v2.2026.03f13
     */
    private SWorldChunk serverWorldChunk = null;
    
    /**
     * Referência do nível de prioridade de atualização do respectivo pedaço.
     * 
     * <p>
     * Usado internamente para não ser preciso o uso do método {@code getUpdatePriorityLevel()}.
     * </p>
     * 
     * @note  Este campo é privado por questões de segurança. Utilize o método {@code getUpdatePriorityLevel()} para obter-lo (requer que a instância seja préviamente inicializada no mundo).
     * @since v2.2026.03f13
     */
    private int renderPriorityLevel = 0;
    
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
        serverWorldChunk = getObject().findComponent(SWorldChunk.class);
        renderPriorityLevel = 15;
        
        Objects.requireNonNull(serverWorldChunk, "Este objeto não contém ou não definiu uma referência válida para a contra-parte do lado do servidor. Por favor, defina a referência.");
    }
    
    /**
     * Obtém a contra-parte do lado do <b>servidor</b> do respectivo pedaço.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * <p>
     * Caso a contra-parte não seja encontrada, uma exceção de ponteiro nulo será disparada indicando o erro.
     * </p>
     * 
     * @return Uma instância da contra-parte do lado do <b>servidor</b> do respectivo pedaço (caso não haja um erro).
     * 
     * @since  v2.2026.03f13
     */
    public ISWorldChunk getServerSidedCounterpart()
    {
        Objects.requireNonNull(serverWorldChunk, "Este objeto não contém ou não definiu uma referência válida para a contra-parte do lado do servidor. Por favor, defina a referência.");
        
        return (ISWorldChunk)(serverWorldChunk);
    }
    
    /**
     * Obtém o nível de prioridade de renderização do respectivo pedaço.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * <p>
     * O nível de prioridade de renderização define se o respectivo pedaço precisa ser renderizado em uma faixa de zero (0) a quinze (15).
     * </p>
     * 
     * @return Um número inteiro representando o nível de prioridade de renderização do respectivo pedaço.
     * 
     * @since  v2.2026.03f13
     */
    public int getRenderPriority()
    {
        return Math.max(0, Math.min(renderPriorityLevel, GlobalChunkData.MAX_PRIORITY_LEVEL));
    }
}