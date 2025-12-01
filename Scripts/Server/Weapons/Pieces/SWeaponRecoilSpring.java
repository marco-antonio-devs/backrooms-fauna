/**
 * Pacote padrão dos módulos da ITsMagic Engine.
 *
 * <p>
 * Aviso: O compilador requer recursos do JDK 7.0 para baixo.
 * - Segundo meu contato com Lucas Leandro, o uso de compilador antigo é parte de uma limitação.
 * - Ele continua afirmando que compiladores modernos requerem do Gradle.
 * - Que por sua vez é quase impossível de funcionar no ambiente de desenvolvimente diretamente pelos dispositivos via-Android.
 * </p>
 *
 * @version vST2025.06f7
 * @author Lucas Leandro - O criador original do motor.
 
 // Importações como "java.util.*" já são adicionadas no ato da compilação autimaticamente.
 */
package JAVARuntime;

/**
 * Classe instanciável da mola de recuo padrão.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SWeaponRecoilSpring extends SWeaponPieceBase
{
    // Campos públicos.
    
    // Velocidade da mola.
    
    private float springSpeed;
    
    // Construtores públicos.
    
    /**
     * Crie uma instância de mola de recuo (para previnir erros de compilador, que exige pelo menos um construtor sem argumentos e público).
     */
    public SWeaponRecoilSpring()
    {
        // Não precisará de implementação.
    }
    
    /**
     * Crie uma nova instância padrão de mola de recuo.
     */
    public SWeaponRecoilSpring(float springSpeed, float dirtResistance, float heatResistance, float coldResistance)
    {
        super("Recoil Spring", 100f, dirtResistance, heatResistance, coldResistance);
        
        this.springSpeed = springSpeed;
    }
    
    // Métodos públicos.
    
    /**
     * Obtém o título do componente.
     *
     * @return O título atual do componente em questão.
     */
    @Override
    public String getComponentTittle()
    {
        return "SWeaponRecoilSpring";
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
     * Obtém a velocidade da mola de recuo.
     *
     * @return A velocidade atual.
     */
    public final float getSpringSpeed()
    {
        return springSpeed;
    }
    
    /**
     * Altere a velocidade da mola de recuo.
     *
     * @param value O novo valor.
     */
    public final void setSpringSpeed(float value)
    {
        this.springSpeed = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém o marcador de capacidade de funcionamento da mola de recuo.
     *
     * @return {@code true} caso possa funcionar, {@code false} caso contrario.
     */
    public boolean canWork()
    {
        // Verifique a saúde da peça.
        
        if(this.getHealth() < 25f)
        {
            return false;
        }
        
        // Execute um laço para iterar sobre as dependências.
        
        for(SWeaponPieceBase piece : this.getDependencies())
        {
            if(!piece.canWork())
            {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Ative o mecânismo da mola de recuo.
     */
    @Override
    public void activate()
    {
        // Verifique se a peça poderá inicializar sua atividade.
        
        if(canWork())
        {
            super.activate();
        }
    }
}
