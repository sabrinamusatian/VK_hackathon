import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
    private static List<Color> graph = new ArrayList<>();
    private static List<Vertex> vertexes = new ArrayList<>();

    private static Random random = new Random();
    private static boolean isInited = false;

    public static final int numberColors = 4;

    public static void initGraph() {
        if (!isInited) {
            for (int i = 0; i < numberColors; i++) {
                List<Vertex> colorVertexes = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    Vertex ver = new Vertex(Math.abs(random.nextInt()), "Rembrant", "");
                    colorVertexes.add(ver);
                    vertexes.add(ver);
                }
                Color color = new Color(i, colorVertexes);
                color.setPeople(100 - i);
                graph.add(color);
            }
            isInited = true;
        }
    }

    public static Color getColorById(int id){
        if (!isInited){
            Graph.initGraph();
        }
        for (Color color: graph){
            if (color.getId().equals(id)){
                return color;
            }
        }
        return null;
    }

    public static Color getColorByName(String name){
        if (!isInited){
            Graph.initGraph();
        }
        for (Color color: graph){
            List<Vertex> vertexes = color.getVertexes();
            for (Vertex ver: vertexes){
                if (ver.getName().equals(name)){
                    return color;
                }
            }
        }
        return null;
    }

    public static List<Color> getGraph() {
        if (!isInited){
            Graph.initGraph();
        }
        return graph;
    }

    public static List<Vertex> getVertexes() {
        return vertexes;
    }
}
