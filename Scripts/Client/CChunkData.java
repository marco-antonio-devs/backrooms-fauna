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
 * @version vST2025.07f7
 * @author Lucas Leandro - O criador original do motor.
 */
package JAVARuntime;

/**
 * Classe instanciável para controle de dados do pedaço.
 *
 * @author Marco Antônio Pereira Júnior.
 * @see JAVARuntime.SChunk para a classe de pedaço no lado do servidor.
 */
public class CChunkData
{
    // Campos privados.
    
    /**
     * Quantidade de vértices do pedaço.
     *
     * Devem ser incrementados de 4 em 4.
     */
    private int verticesCount = 0;
    
    /**
     * Quantidade de polígonos do pedaço.
     *
     * Devem ser incrementados de 2 em 2.
     */
    private int polygonsCount = 0;
    
    /**
     * Quantidade de normais do pedaço.
     *
     * Devem ser incrementados de 4 em 4.
     */
    private int normalsCount = 0;
    
    /**
     * Quantidade de texturas mapeadas do pedaço.
     *
     * Devem ser incrementados de 4 em 4.
     */
    private int uvCount = 0;
    
    // Métodos públicos.
    
    /**
     * Obtém a quantidade de vértices do modelo.
     *
     * @return O total de vértices locais.
     */
    public int getVerticesCount()
    {
        return verticesCount;
    }
    
    /**
     * Obtém a quantidade de polígonos do modelo.
     *
     * @return O total de polígonos locais.
     */
    public int getPolygonsCount()
    {
        return polygonsCount;
    }
    
    /**
     * Obtém a quantidade de normais do modelo.
     *
     * @return O total de normais locais.
     */
    public int getNormalsCount()
    {
        return normalsCount;
    }
    
    /**
     * Obtém a quantidade de texturas mapeadas do modelo.
     *
     * @return O total de texturas mapeadas locais.
     */
    public int getUVCount()
    {
        return uvCount;
    }
    
    /**
     * Incremente a quantidade de vértices do modelo.
     */
    public void incVerticesCount()
    {
        verticesCount += 4;
    }
    
    /**
     * Incremente a quantidade de polígonos do modelo.
     */
    public void incPolygonsCount()
    {
        polygonsCount += 2;
    }
    
    /**
     * Incremente a quantidade de normais do modelo.
     */
    public void incNormalsCount()
    {
        normalsCount += 4;
    }
    
    /**
     * Incremente a quantidade de texturas mapeadas do modelo.
     */
    public void incUVCount()
    {
        uvCount += 4;
    }
}
