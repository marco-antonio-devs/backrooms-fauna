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
 * @version v2.2025.12f6
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

import java.util.UUID;

/**
 * Controlador de jogador no lado do servidor.
 * Lida com a movimentação, posição e animações globais.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SPlayerController extends Component
{
    // Campos privados.
    
    // Corpo físico do jogador em questão.
    
    @AutoWired
    private Characterbody physics = null;
    
    // A cabeça do personagem.
    
    private SpatialObject head = null;
    
    // Controlador de interface gráfica do jogador.
    
    private CUserGUI userInterface = null;
    
    // Ângulo de inclinação do jogador.
    
    private float angleV = 0f;
    private float angleH = 0f;
    
    // Sensibilidade de rotação.
    
    private float sensibility = 20f;
    
    // Identidade do jogador.
    
    private final UUID playerID = UUID.randomUUID();
    
    // Métodos públicos.
    
    /**
     * Obtém o título do componente.
     *
     * @return O título atual do componente SPlayerController.
     */
    @Override
    public String getComponentTittle()
    {
        return "SPlayerController";
    }
    
    /**
     * Obtém o menu do componente.
     *
     * @return O caminho do menu atual do componente SPlayerController.
     */
    @Override
    public String getComponentMenu()
    {
        return "Backrooms/Server-Side";
    }
    
    /**
     * Inicialize esta função no primeiro quadro a ser renderizado.
     *
     * Este método pertence à classe Component, não deve-se chamar tal, sugeito ao risco de inconsistências de estado.
     */
    @Override
    public void start()
    {
        SChunkGenerator generator = WorldController.findObject("Chunk Storage").findComponent("SChunkGenerator");
        
        head = myObject.findChildObject("Head");
        userInterface = head.findComponent(CUserGUI.class);
        
        generator.addPlayerToList(this);
        
        userInterface.refreshGUI();
    }
    
    /**
     * Repete esta função a cada quadro a ser renderizado.
     *
     * Este método pertence à classe Component, não deve-se chamar tal, sugeito ao risco de sobrecarga da CPU e memória RAM.
     */
    @Override
    public void repeat()
    {
        movePlayer(userInterface.getJoystick().getValue(), 2f);
        handleRotation();
    }
    
    /**
     * Move o jogador para o eixo desejado com uma velocidade de aceleração variada.
     *
     * @param axis O eixo solicitado para mover o jogador.
     * @param speed O multiplicador de aceleração do mesmo.
     */
    public void movePlayer(Vector2 axis, float speed)
    {
        physics.setSpeed(axis.getX() * -speed, axis.getY() * speed);
    }
    
    /**
     * Rotacione o jogador, desde o eixo X até o Y.
     */
    public void handleRotation()
    {
        float deltaTime = Time.getDeltaTime();
        
        Vector2 slideArea = userInterface.getSlideArea().getValue();
        
        float slideFactorV = slideArea.getY();
        float slideFactorH = slideArea.getX();
        float targetAngleV = slideFactorV * sensibility * deltaTime;
        float targetAngleH = slideFactorH * sensibility * deltaTime;
        
        angleV += targetAngleV;
        angleH += targetAngleH;
        
        angleV = Math.clamp(-75f, angleV, 80f);
        
        head.getRotation().selfLookTo(new Vector3(0f, Math.sin(-angleV), Math.cos(-angleV)));
        myTransform.getRotation().selfLookTo(new Vector3(Math.sin(-angleH), 0f, Math.cos(-angleH)));
    }
    
    /**
     * Obtém um código de identificação nomeado de Hash.
     *
     * @return O código de Hash.
     */
    @Override
    public int hashCode()
    {
        return playerID.hashCode();
    }
    
    /**
     * Compare a igualdade entre este objeto Java e outro.
     *
     * @param obj O objeto solicitado para a comparação.
     *
     * @return Desde {@code true} caso haja igualdade e {@code false} caso não.
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj == this) return true;
        if(obj == null || obj.getClass() != getClass()) return false;
        
        SPlayerController other = (SPlayerController)(obj);
        
        return (other.myObject == myObject && other.playerID.equals(playerID));
    }
}