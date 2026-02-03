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
 * @version v2.2026.02f1
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe do controlador de entidade (via-lado do servidor).
 * As entidades devem incluir comportamentos e necessidades fisiológicas
 * - incluindo tanto fome, sede, reprodução e afins como liberação de dejetos.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SEntity extends Component implements ISEntity
{
    // Campos protegidos.
    
    // Estado de comportamento atual da entidade.
    
    protected SEntityBehaviour behaviour = new SEntityBehaviour();
    
    // A física de movimento da entidade.
    
    @AutoWired
    protected Characterbody characterPhysics;
    
    // Código genético da entidade.
    
    protected double[] genes = new double[5120];
    
    // Escala de tamanho base da entidade.
    
    protected float baseSizeScaleFactor = 1f;
    
    // Velocidade de locomoção relativa ao tamanho.
    
    protected float baseLocomotionSpeed = 1f;
    
    // Métodos públicos.
    
    /**
     * Método chamado automaticamente no primeiro quadro de execução.
     *
     * <p><b>Aviso:</b> este método é parte do ciclo de vida da classe Component e não deve ser invocado manualmente.</p>
     */
    @Override
    public void start()
    {
        baseSizeScaleFactor *= Random.range(0.0001f, 3f);
        
        Terminal.log("Base do fator de escala do tamanho: " + baseSizeScaleFactor);
        myObject.getTransform().setScale(baseSizeScaleFactor);
        
        float width = characterPhysics.getWidth() * baseSizeScaleFactor;
        float height = characterPhysics.getHeight() * baseSizeScaleFactor;
        
        characterPhysics.setWidth(width);
        characterPhysics.setHeight(height);
    }
    
    /**
     * Repete esta função a cada quadro a ser renderizado.
     *
     * <p><b>Aviso:</b> este método é parte do ciclo de vida da classe Component e não deve ser invocado manualmente.</p>
     */
    @Override
    public void repeat()
    {
        
    }
    
    /**
     * Obtém o estado de comportamento da entidade (herdado da interface ISEntity).
     *
     * @return O estado da entidade, que faz a mesma se comportar como um animal.
     */
    public SEntityBehaviour getBehaviour()
    {
        return behaviour;
    }
}