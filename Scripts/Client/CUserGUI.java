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
 * @version v2.2026.02f102025.12f6
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe utilitária da interface de usuário.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class CUserGUI extends Component
{
    // Campos privados.
    
    /**
     * Referência ao analógico do jogador.
     */
    private UIJoystick joystick = null;
    
    /**
     * Referência da área de deslize do jogador.
     */
    private UISlideArea slideArea = null;
    
    // Métodos públicos.
    
    /**
     * Obtém o menu do componente.
     *
     * @return O caminho do menu atual do componente CUserGUI.
     */
    @Override
    public String getComponentMenu()
    {
        return "Backrooms/Client-Side";
    }
    
    /**
     * Atualize as referências de interface do jogador.
     */
    public void refreshGUI()
    {
        // Verifique se tem um analógico e área de deslize disponível disponível.
        
        SpatialObject joystickObject = myObject.findChildObject("Joystick");
        SpatialObject slideAreaObject = myObject.findChildObject("Slide Area");
        
        if(joystickObject != null)
        {
            joystick = joystickObject.findComponent(UIJoystick.class);
        }
        else
        {
            Terminal.log("O analógico está nulo.");
        }
        
        if(slideAreaObject != null)
        {
            slideArea = slideAreaObject.findComponent(UISlideArea.class);
        }
        else
        {
            Terminal.log("A área de deslize está nula.");
        }
    }
    
    /**
     * Obtém a referência atual do analógico do jogador.
     *
     * @return O analógico atual.
     */
    public UIJoystick getJoystick()
    {
        return joystick;
    }
    
    /**
     * Obtém a referência atual da área de deslize do jogador.
     *
     * @return A área de deslize atual.
     */
    public UISlideArea getSlideArea()
    {
        return slideArea;
    }
}