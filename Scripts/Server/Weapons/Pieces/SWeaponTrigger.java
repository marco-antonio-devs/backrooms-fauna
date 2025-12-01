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
 * @version vST2025.06f7
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

// Importações como "java.util.*" já são adicionadas no ato da compilação autimaticamente.

/**
 * Classe instanciável de base de gatilho.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SWeaponTrigger extends SWeaponPieceBase
{
    // Campos privados.
    
    /**
     * Rigidez do gatilho (quanto menor o valor, mais leve o gatilho).
     */
    private float rigidLevel;
    
    // Construtores públicos.
    
    /**
     * Crie uma instância de gatilho (para previnir erros de compilador, que exige pelo menos um construtor sem argumentos e público).
     */
    public SWeaponTrigger()
    {
        // Não precisará de implementação.
    }
    
    /**
     * Crie uma nova instância padrão de gatilho.
     *
     * @param rigidLevel O nível de rigidez do gatilho.
     * @param dirtResistance A resistência contra sujeira do gatilho.
     * @param heatResistance A resistência contra superaquecimento do gatilho.
     * @param coldResistance A resistência contra congelamento do gatilho.
     */
    public SWeaponTrigger(float rigidLevel, float dirtResistance, float heatResistance, float coldResistance)
    {
        super("Trigger", 100f, dirtResistance, heatResistance, coldResistance);
        
        this.rigidLevel = rigidLevel;
    }
    
    // Métodos públicos.
    
    /**
     * Obtém a rigidez do gatilho.
     *
     * @return A rigidez atual.
     */
    public final float getRigidLevel()
    {
        return rigidLevel;
    }
    
    /**
     * Altere a rigidez do gatilho.
     *
     * @param value O novo valor.
     */
    public final void setRigidLevel(float value)
    {
        this.rigidLevel = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém o marcador de capacidade de funcionamento do gatilho.
     *
     * @return {@code true} caso possa funcionar, {@code false} caso contrario.
     */
    @Override
    public boolean canWork()
    {
        if(this.getHealth() < 25f)
        {
            return false;
        }
        
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
     * Ative o mecânismo do gatilho.
     */
    @Override
    public void activate()
    {
        if(canWork())
        {
            super.activate();
        }
    }
}
