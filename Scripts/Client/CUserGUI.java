/**
 * Pacote padrão dos módulos da ITsMagic Engine.
 *
 * @version vST2025.05f12
 * @author Lucas Leandro - O criador original do motor.
 * @since JDK 7.0 (Note que o compilador não é compatível com versões acima como JDK 8.0 por ser inviável).
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
    
    // Referência ao analógico do jogador.
    
    private SUIJoystick joystick = null;
    
    // Referência da área de deslize do jogador.
    
    private SUISlideArea slideArea = null;
    
    // Métodos públicos.
    
    /**
     * Obtém o título do componente.
     *
     * @return O título atual do componente CUserGUI.
     */
    @Override
    public String getComponentTittle()
    {
        return "CUserGUI";
    }
    
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
            joystick = joystickObject.findComponent(SUIJoystick.class);
        }
        else
        {
            Console.log("O analógico está nulo.");
        }
        
        if(slideAreaObject != null)
        {
            slideArea = slideAreaObject.findComponent(SUISlideArea.class);
        }
        else
        {
            Console.log("A área de deslize está nula.");
        }
    }
    
    /**
     * Obtém a referência atual do analógico do jogador.
     *
     * @return O analógico atual.
     */
    public SUIJoystick getJoystick()
    {
        return joystick;
    }
    
    /**
     * Obtém a referência atual da área de deslize do jogador.
     *
     * @return A área de deslize atual.
     */
    public SUISlideArea getSlideArea()
    {
        return slideArea;
    }
}
