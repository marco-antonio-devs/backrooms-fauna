package JAVARuntime;

public class Test extends Component
{
    public ModelRenderer targetModel;
    
    public float delay = 1.7f;
    
    private float time = 0f;
    private int verticeIndex = 0;
    
    private Vertex vertex;
    private List<Vector3> vertices = new ArrayList<Vector3>();
    
    @Override
    public void start()
    {
        Console.log(String.format("Testes inicializados para '%s'!", myObject.getName()));
        Console.log("Teste de hoje: depuração de modelo tridimensional;");
        
        if(targetModel == null)
        {
            throw new IllegalStateException("Atualmente, o modelo-alvo do teste não estás definido. Por favor, defina.");
        }
        
        vertex = targetModel.getVertex();
        vertices = vertex.getVertices();
    }
    
    @Override
    public void repeat()
    {
        time += Time.getDeltaTime();
        
        if(time >= delay && verticeIndex < vertices.size())
        {
            myObject.setGlobalPosition(vertices.get(verticeIndex));
            
            verticeIndex++;
            time = 0f;
        }
    }
}
