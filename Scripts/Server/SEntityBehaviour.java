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
 * @version v2.2026.02f4
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe instanciável do estado de comportamento de entidades.
 *
 * @author Marco Antrônio Pereira Júnior.
 */
strictfp public final class SEntityBehaviour
{
    // Campos privados.
    
    // Define se a entidade é herbívora, carnívora, onívora, detritívora ou afins.
    
    private SEntityAlimentaryType alimentaryType;
    
    // Contador de nutrientes da entidade em questão.
    // Geralmente, para diminuir o medidor, deverão alimentar-se do alimento cujo pertence à preferência alimentar.
     
    private Map<Nutrient, Float> hunger = new EnumMap<Nutrient, Float>(Nutrient.class);
    
    // Taxa de tiques do metabolismo.
    
    private float metabolism;
    
    // Construtores públicos.
    
    /**
     * Crie uma instância de comportamento desta entidade.
     */
    public SEntityBehaviour()
    {
        
    }
    
    // Métodos públicos.
    
    /**
     * Atualize o metabolísmo da entidade.
     */
    public void updateMetabolism()
    {
        Set<Nutrient> nutrientMapCopy = new HashSet<Nutrient>(hunger.keySet());
        
        for(Nutrient nutrient : nutrientMapCopy)
        {
            Float currentValue = hunger.get(nutrient);
            
            if(currentValue != null)
            {
                hunger.put(nutrient, Math.min(100f, currentValue + metabolism));
            }
        }
    }
    
    /**
     * Concede uma certa quantidade de nutrição para esta entidade.
     *
     * @param nutrient O nutriente a selecionar.
     * @param percentage A porcentagem de nutriente.
     */
    public void applyNutrient(Nutrient nutrient, float percent)
    {
        hunger.put(nutrient, Math.min(100f, hunger.get(nutrient) + percent));
    }
    
    /**
     * Obtém o tipo de definição da preferência alimentar desta entidade.
     *
     * @return O tipo de definição da preferência alimentar.
     */
    public SEntityAlimentaryType getAlimentaryType()
    {
        return alimentaryType;
    }
    
    /**
     * Altere o tipo de definição da preferência alimentar desta entidade.
     *
     * @param value O valor a ser alterado.
     */
    public void setAlimentaryType(SEntityAlimentaryType value)
    {
        alimentaryType = value;
    }
}