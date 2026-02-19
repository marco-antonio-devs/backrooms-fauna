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
 * @version v2.2026.02f10
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Enumerador contendo os tipos alimentares de entidade.
 *
 * @author Marco Antônio Pereira Júnior.
 */
public enum SEntityAlimentaryType
{
    /**
     * Herbívoros: Entidades cujo dependam de fibras tiradas dos produtores de clorofíla (incluindo plantas e afins).
     */
    HERBIVORE,
    
    /**
     * Carnívoros: Entidades cujo dependam de caçar outras e obter seus nutrientes diretamente da carne.
     */
    CARNIVORE,
    
    /**
     * Onívoros: Entidades que comem tanto fibras quanto carne, geralmente possuem uma diversidade alta de preferências.
     */
    OMNIVORE,
    
    /**
     * Detritívoros: Entidades cujo dependam de detrítos e dejetos, como minerais, fezes e afins.
     */
    DETRITIVORE,
    
    /**
     * Hematófogos: Entidades que consomem sangue, preferindo alvos vivos e podem ter um sistema de burlar a imunidade do seu alvo.
     */
    HEMATOPHAGE,
    
    /**
     * Quimiossintético: Entidades cujo dependam de compostos químicos ou radioativos, transformando-as em energia.
     */
    CHEMOSYNTHETIC,
    
    /**
     * Fotossintético: Entidades dependentes de luz, na maioria das vezes ultraviolêta para fotossíntese.
     */
    PHOTOSYNTHETIC,
}