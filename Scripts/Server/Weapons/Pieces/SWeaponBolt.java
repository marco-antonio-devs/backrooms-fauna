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
 * Classe instanciável de base de ferrolho.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SWeaponBolt extends SWeaponPieceBase
{
    // Campos privados.
    
    /**
     * Velocidade de movimento da mola em segundos (quanto menor o valor, maior a velocidade).
     */
    private float boltSpeed;
    
    // Construtores públicos.
    
    /**
     * Crie uma instância de ferrolho (para previnir erros de compilador, que exige pelo menos um construtor sem argumentos e público).
     */
    public SWeaponBolt()
    {
        // Não precisará de implementação.
    }
    
    /**
     * Crie uma nova instância padrão de ferrolho.
     */
    public SWeaponBolt(float boltSpeed, float dirtResistance, float heatResistance, float coldResistance)
    {
        super("Bolt", 100f, dirtResistance, heatResistance, coldResistance);
        
        this.boltSpeed = boltSpeed;
    }
    
    // Métodos públicos.
    
    /**
     * Obtém a velocidade do ferrolho.
     *
     * @return A velocidade atual.
     */
    public final float getBoltSpeed()
    {
        return boltSpeed;
    }
    
    /**
     * Altere a velocidade do ferrolho.
     *
     * @param value O novo valor.
     */
    public final void setBoltSpeed(float value)
    {
        this.boltSpeed = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém o marcador de capacidade de funcionamento do ferrolho.
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
     * Ative o mecânismo do ferrolho.
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
