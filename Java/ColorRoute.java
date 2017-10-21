import java.util.List;

public class ColorRoute {
    private List<Vertex> route;
    private Color color;
    private int pos = 0;

    ColorRoute(Integer id){
        Color color = Graph.getColorById(id);

        route = color.generateRoute();
        this.color = color;
    }

    public List<Vertex> getRoute() {
        return route;
    }

    public Vertex next(){
        if (pos < route.size()){
            return route.get(pos++);
        }
        return null;
    }

    public Color getColor() {
        return color;
    }
}
