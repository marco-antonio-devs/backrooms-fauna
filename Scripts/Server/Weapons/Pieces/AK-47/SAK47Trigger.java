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

// Importações como "java.util.*" já são adicionadas no ato da compilação autimaticamente.

/**
 * Classe instanciável de gatilho padrão da AK-47 (Kalashnikov).
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SAK47Trigger extends SWeaponTrigger
{
    // Construtores públicos.
    
    /**
     * Crie uma nova instância padrão de gatilho da AK-47.
     */
    public SAK47Trigger()
    {
        super(0.18f, 96.3f, 88.7f, 60.7f);
    }
    
    // Métodos públicos.
    
    /**
     * Obtém o título do componente (note que é Tittle, não Title, confirmado por mim).
     *
     * @return O título atual do componente em questão.
     */
    @Override
    public String getComponentTittle()
    {
        return "SAK47Trigger";
    }
    
    /**
     * Obtém o menu do componente.
     *
     * @return O caminho do menu atual do componente em questão.
     */
    @Override
    public String getComponentMenu()
    {
        return "Modular-Guns/Server-Side";
    }
    
    /**
     * Método chamado automaticamente no primeiro quadro de execução.
     *
     * <p><b>Aviso:</b> este método é parte do ciclo de vida da classe Component e não deve ser invocado manualmente.</p>
     */
    @Override
    public void start()
    {
        // Imprima mensagens de depuração.
        
        Console.log("Nome: " + this.getPieceName());
        Console.log("Saúde: " + this.getHealth());
        Console.log("Resistência à poeira: " + this.getDirtResistance());
        Console.log("Resistência ao calor: " + this.getHeatResistance());
        Console.log("Resistência ao frio: " + this.getColdResistance());
        Console.log("Nível de rigidez: " + this.getRigidLevel());
    }
}
