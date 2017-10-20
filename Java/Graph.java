import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private static List<Color> graph = new ArrayList<>();
    private static List<Vertex> vertexes = new ArrayList<>();

    private static Random random = new Random();

    public static final int numberColors = 4;

    public static void initGraph() {
        for (int i = 0; i < numberColors; i++){
            List<Vertex> colorVertexes = new ArrayList<>();
            for (int j = 0; j < 5; j++){
                Vertex ver = new Vertex(Math.abs(random.nextInt()), "Rembrant", "");
                colorVertexes.add(ver);
                vertexes.add(ver);
            }
            Color color = new Color(i, colorVertexes);
            graph.add(color);
        }
    }

    public static Color getColor(int pos){
        return graph.get(pos);
    }
}
