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
 * @version v2.2025.12f14
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Enumerador de nutrientes, incluindo minerais, vitaminas, aminoácidos e afins.
 *
 * <p>
 * Permite maior segurança ao manipular-los proceduralmente.
 * </p>
 *
 * @author Marco Antônio Pereira Júnior.
 */
public enum Nutrient
{
    // Constantes.
    
    VITAMIN,
    MINERAL,
    AMINO_ACID,
    OTHER;
    
    // Enumeradores internos.
    
    public enum Vitamin
    {
        VITAMIN_A,
        VITAMIN_B1,
        VITAMIN_B2,
        VITAMIN_B3,
        VITAMIN_B4,
        VITAMIN_B5,
        VITAMIN_B6,
        VITAMIN_B7,
        VITAMIN_B9,
        VITAMIN_B12,
        VITAMIN_B13,
        VITAMIN_B15,
        VITAMIN_B17,
        VITAMIN_C,
        VITAMIN_D,
        VITAMIN_E,
        VITAMIN_F,
        VITAMIN_K,
        VITAMIN_J,
        VITAMIN_T,
    }
    
    public enum Mineral
    {
        CALCIUM,
        PHOSPHORUS,
        POTASSIUM,
        SODIUM,
        MAGNESIUM,
        CHLORINE,
        SULFUR,
        IRON,
        ZINC,
        COPPER,
        MANGANESE,
        NICKEL,
        MOLYBDENUM,
        SELENIUM,
        IODINE,
        COBALT,
        SILICON,
        BROME,
        CHROME,
    }
    
    public enum AminoAcid
    {
        ALANINE,
        ARGININE,
        ASPARAGINE,
        ASPARTATE,
        CYSTEINE,
        PHENYLALANINE,
        GLYCINE,
        GLUTAMATE,
        GLUTAMINE,
        HYSTIDINE,
        ISOLEUCINE,
        LEUCINE,
        LYSINE,
        METHIONINE,
        PROLINE,
        SERINE,
        TYROSINE,
        THREONINE,
        TRYPTOPHAN,
        VALINE,
    }
    
    public enum Other
    {
        WATER,
    }
}