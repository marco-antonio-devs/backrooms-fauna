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

/**
 * Classe responsável por gerenciar física e propriedades do pedaço.
 * Diferente da CChunk, esta classe prioriza controlar as tarefas para o servidor.
 *
 * @author Marco Antônio Pereira Júnior.
 * @see JAVARuntime.CChunk para obter informações da classe do pedaço ao lado do cliente.
 */
public class SChunk extends Component
{
    // Campos privados.
    
    // Matriz tridimensional dos cubos do pedaço (fora da memória de tipo Heap).
    // Para mais informações, veja JAVARuntime.CubeDictionary para obter referência dos tipos.
    
    private OH3LevelIntArray voxels = new OH3LevelIntArray(GlobalChunkData.W, GlobalChunkData.H, GlobalChunkData.W);
    
    // Métodos públicos
    
    /**
     * Obtém o menu do componente.
     *
     * @return O caminho do menu atual do componente em questão.
     */
    @Override
    public String getComponentMenu()
    {
        return "Backrooms/Server-Side";
    }
    
    /**
     * Obtém o bloco localizado neste pedaço.
     *
     * @param x A coordenada X do bloco.
     * @param y A coordenada Y do bloco.
     * @param z A coordenada Z do bloco.
     */
    public int getBlock(int x, int y, int z)
    {
        return voxels.get(x, y, z);
    }
    
    /**
     * Obtém a matriz de blocos deste pedaço.
     *
     * @return A matriz local.
     */
    public OH3LevelIntArray getVoxelArray()
    {
        return voxels;
    }
    
    /**
     * Obtém o código de tabela correspondente a posição do pedaço.
     *
     * @return Um código inicializado em número primo, multiplicando-o pelas posições X e Z.
     */
    @Override
    public int hashCode()
    {
        int code = 17;
        
        code = 31 * code + (int)(myObject.getGlobalPosition().getX());
        code = 31 * code + (int)(myObject.getGlobalPosition().getZ());
        
        return code;
    }
    
    /**
     * Verifique se este objeto equivale ao outro.
     *
     * @param object O objeto a ser comparado.
     *
     * @return Um valor booleano entre {@code true} (para "igual") e {@code false} (para "diferente").
     */
    @Override
    public boolean equals(Object object)
    {
        if(object == null || object.getClass() != getClass()) return false;
        if(object == this) return true;
        
        SChunk otherSChunk = (SChunk)(object);
        
        return (
            otherSChunk.myObject.getGlobalPosition().getX() == myObject.getGlobalPosition().getX() &&
            otherSChunk.myObject.getGlobalPosition().getZ() == myObject.getGlobalPosition().getZ()
        );
    }
}