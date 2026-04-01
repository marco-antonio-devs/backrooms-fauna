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
 * Classe de implementação para representar um pedaço de mundo tridimensional para o <b>servidor</b>.
 * 
 * <p>
 * Herda as funções para auxiliar em cálculos sensíveis isolados do <b>cliente</b> da interface {@code ISPlayerController}.
 * </p>
 * 
 * @author Marco Antônio Pereira Júnior
 * @since  v2.2026.03f13
 */
public final class SWorldChunk extends Component implements ISWorldChunk
{
    /// Declaração de atributos. ///
    
    /**
     * Identificador único universal do respectivo pedaço.
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
     * Referência do componente que implementa a interface da contra-parte do respectivo pedaço.
     * 
     * <p>
     * Usado internamente para não ser preciso o uso do método {@code getClientSidedCounterpart()}.
     * </p>
     * 
     * @note  Este campo é privado por questões de segurança. Utilize o método {@code getClientSidedCounterpart()} para obter-lo (requer que a instância seja préviamente inicializada no mundo).
     * @since v2.2026.03f13
     */
    private CWorldChunk clientWorldChunk = null;
    
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
    private int updatePriorityLevel = 0;
    
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
    public void start()
    {
        localUUID = UUID.randomUUID();
        clientWorldChunk = getObject().findComponent(CWorldChunk.class);
        updatePriorityLevel = 15;
        
        Objects.requireNonNull(clientWorldChunk, "Este objeto não contém ou não definiu uma referência válida para a contra-parte do lado do cliente. Por favor, defina a referência.");
    }
    
    /**
     * Obtém o identificador único universal do respectivo jogador.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * 
     * @return O identificador único universal local do respectivo pedaço do mundo.
     * 
     * @since  v2.2026.03f13
     */
    public UUID getUUID()
    {
        return localUUID;
    }
    
    /**
     * Obtém a contra-parte do lado do <b>cliente</b> do respectivo pedaço.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * <p>
     * Caso a contra-parte não seja encontrada, uma exceção de ponteiro nulo será disparada indicando o erro.
     * </p>
     * 
     * @return Uma instância da contra-parte do lado do <b>cliente</b> do respectivo pedaço (caso não haja um erro).
     * 
     * @since  v2.2026.03f13
     */
    public ICWorldChunk getClientSidedCounterpart()
    {
        Objects.requireNonNull(clientWorldChunk, "Este objeto não contém ou não definiu uma referência válida para a contra-parte do lado do cliente. Por favor, defina a referência.");
        
        return (ICWorldChunk)(clientWorldChunk);
    }
    
    /**
     * Inicialize um procedimento de remoção do respectivo pedaço do mundo.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * 
     * @param forceToRemove Define se o procedimento deve ser executado forçadamente ou não.
     * 
     * @since v2.2026.03f13
     */
    public void removeFromWorld(boolean forceToRemove)
    {
        if(!forceToRemove && updatePriorityLevel >= GlobalChunkData.MID_PRIORITY_LEVEL)
        {
            return;
        }
        
        getObject().destroy();
    }
    
    /**
     * Obtém o nível de prioridade de atualização do respectivo pedaço.
     * 
     * <p>
     * Este método é uma implementação do método de mesmo nome da interface <b>ISWorldChunk<b>.
     * </p>
     * 
     * @return Um número inteiro representando o nível de prioridade de atualização do respectivo pedaço.
     * 
     * @since  v2.2026.03f13
     */
    public int getUpdatePriority()
    {
        return Math.max(0, Math.min(updatePriorityLevel, GlobalChunkData.MAX_PRIORITY_LEVEL));
    }
}