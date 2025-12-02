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
 * Classe instanciável de base de peça de arma.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public class SWeaponPieceBase extends Component
{
    // Campos privados.
    
    // Verificador de atividade da peça de arma.
    
    private boolean activated;
    
    // Nome de rótulo da peça de arma.
    
    private String pieceName;
    
    // Nível de saúde da peça de arma (quanto maior o valor, menor o desgaste).
    
    private float health;
    
    // Nível de resistência à poeira.
    
    private float dirtResistance;
    
    // Nível de resistência ao calor.
    
    private float heatResistance;
    
    // Nível de resistência ao frio.
    
    private float coldResistance;
    
    // Dependências da peça de arma.
    
    private final ArrayList<SWeaponPieceBase> dependencies = new ArrayList<SWeaponPieceBase>();
    
    // Construtores públicos.
    
    /**
     * Crie uma nova instância padrão de base de peça de arma.
     */
    public SWeaponPieceBase()
    {
        this("Default", 100f, 100f, 100f, 100f);
    }
    
    /**
     * Crie uma nova instância de base de peça de arma.
     *
     * @param pieceName O nome da peça de arma.
     * @param health A saúde da peça de arma.
     */
    public SWeaponPieceBase(String pieceName, float health, float dirtResistance, float heatResistance, float coldResistance, SWeaponPieceBase... dependencies)
    {
        this.pieceName = (pieceName == null || pieceName.trim().isEmpty()) ? "N/A" : pieceName;
        this.health = Math.min(Math.max(0f, health), 100f);
        this.dirtResistance = Math.min(Math.max(0f, dirtResistance), 100f);
        this.heatResistance = Math.min(Math.max(0f, heatResistance), 100f);
        this.coldResistance = Math.min(Math.max(0f, coldResistance), 100f);
        
        for(SWeaponPieceBase piece : dependencies)
        {
            this.dependencies.add(piece);
        }
    }
    
    // Métodos públicos.
    
    /**
     * Obtém o nome da peça de arma.
     *
     * @return O nome da peça atual.
     */
    public final String getPieceName()
    {
        return pieceName;
    }
    
    /**
     * Altere o nome da peça de arma.
     *
     * @param value O novo valor.
     */
    public final void setPieceName(String value)
    {
        this.pieceName = (value != null && !value.trim().isEmpty()) ? value : this.pieceName;
    }
    
    /**
     * Obtém a saúde da peça de arma.
     *
     * @return A saúde atual.
     */
    public final float getHealth()
    {
        return health;
    }
    
    /**
     * Altere a saúde da peça de arma.
     *
     * @param value O novo valor.
     */
    public final void setHealth(float value)
    {
        this.health = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém a resistência à poeira da peça de arma.
     *
     * @return A resistência atual contra poeira.
     */
    public final float getDirtResistance()
    {
        return dirtResistance;
    }
    
    /**
     * Altere a resistência à poeira da peça de arma.
     *
     * @param value O novo valor.
     */
    public final void setDirtResistance(float value)
    {
        this.dirtResistance = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém a resistência ao calor da peça de arma.
     *
     * @return A resistência atual contra calor.
     */
    public final float getHeatResistance()
    {
        return heatResistance;
    }
    
    /**
     * Altere a resistência ao calor da peça de arma.
     *
     * @param value O novo valor.
     */
    public final void setHeatResistance(float value)
    {
        this.heatResistance = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém a resistência ao frio da peça de arma.
     *
     * @return A resistência atual contra frio.
     */
    public final float getColdResistance()
    {
        return coldResistance;
    }
    
    /**
     * Altere a resistência ao frio da peça de arma.
     *
     * @param value O novo valor.
     */
    public final void setColdResistance(float value)
    {
        this.coldResistance = Math.min(Math.max(0f, value), 100f);
    }
    
    /**
     * Obtém o marcador de atividade da peça de arma.
     *
     * @return O marcador de atividade atual.
     */
    public final boolean isActivated()
    {
        return activated;
    }
    
    /**
     * Altere o marcador de atividade da peça de arma.
     *
     * @param value O novo valor.
     */
    public final void setActivated(boolean value)
    {
        this.activated = value;
    }
    
    /**
     * Obtém o marcador de capacidade de funcionamento.
     * Requer ser sobreposta para aplicar a verificação própria.
     *
     * @return {@code true} caso a peça possa funcionar, {@code false} caso contrario.
     */
    public boolean canWork()
    {
        return false;
    }
    
    /**
     * Obtém a lista de dependências da arma.
     *
     * @return Uma lista copiando a atual de dependências.
     */
    public final List<SWeaponPieceBase> getDependencies()
    {
        return new ArrayList<SWeaponPieceBase>(dependencies);
    }
    
    /**
     * Ative o mecânismo da peça de arma.
     */
    public void activate()
    {
        // Altere o marcador de atividade.
        
        this.activated = true;
    }
}
