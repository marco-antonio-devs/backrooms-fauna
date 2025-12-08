/**
 * Pacote padrão dos módulos da ITsMagic Engine.
 *
 * <p>
 * Aviso: O compilador integrado do motor requer recursos do JDK 7.0 para baixo.
 * - Segundo meu contato com Lucas Leandro, o uso de compilador antigo é parte de uma limitação.
 * - Ele continua afirmando que compiladores modernos requerem do Gradle.
 * - Que por sua vez é quase impossível de funcionar no ambiente de desenvolvimente diretamente pelos dispositivos via-Android.
 * </p>
 *
 * @version vST2025.10f17
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe do manipulador de cliente.
 * <p>
 * Permite que o usuário controle o jogador.
 * </p>
 *
 * @author Marco Antônio Pereira Júnior.
 */
public final class CHandler
{
    // Campos públicos.
    
    /**
     * Instância do soquete de conexão do cliente.
     */
    private Socket socket;
    
    /**
     * Controlador de jogador anexado ao objeto associado.
     */
    private SPlayerController player;
    
    // Construtores públicos.
    
    /**
     * Crie uma nova instância do manipulador de cliente.
     *
     * @throws IOException Uma exceção de entrada e saída, indicando alguma falha na conexão.
     */
    public CHandler()
    {
        try
        {
            this.socket = new Socket("localhost", 8080);
        }
        catch(IOException exception)
        {
            Console.log("Houve um problema ao tentar lhe conectar ao servidor.");
            exception.printStackTrace();
        }
    }
    
    // Métodos públicos.
    
    /**
     * Inicialize este manipulador de cliente de maneira assíncrona.
     *
     * @return Um código de estado de conexão (0 para "nenhum problema de conexão", 255 para "houve um problema interno" e 64 para "cliente não definido").
     */
    public int start()
    {
        if(player == null)
        {
            return 64;
        }
        
        int code = 0;
        
        Console.log("Atualmente inicializando o manipulador de cliente...");
        
        new AsyncTask(new AsyncRunnable()
        {
            public Object onBackground(Object input)
            {
                return 65536;
            }
            
            public void onEngine(Object result)
            {
                if(result instanceof Integer)
                {
                    Console.log(result);
                }
            }
        });
        
        return code;
    }
    
    /**
     * Anexe o controlador de jogador ao manipulador de cliente.
     *
     * @param player O controlador solicitado para esta operação.
     */
    public void attachSPlayerController(SPlayerController player)
    {
        if(player == null)
        {
            throw new IllegalArgumentException("O controlador de jogador cujo era para ser anexado encontra-se nulo. Por favor, defina-o.");
        }
        
        if(this.player == null)
        {
            throw new IllegalArgumentException("Já está presente um controlador de jogador neste manipulador de cliente.");
        }
        
        this.player = player;
    }
}
