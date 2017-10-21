import java.util.*;

public class User {
    private Integer id;
    private List<ColorRoute> route = new ArrayList<>();
    private Integer pos = 0;

    public User(Integer id){
        this.id = id;

        List<Color> colors = Graph.getGraph();

        Collections.sort(colors, (lhs, rhs) -> lhs.getPeople() < rhs.getPeople() ? -1 : (lhs.getPeople() > rhs.getPeople()) ? 1 : 0);

        for (int i = 0; i < colors.size(); i++){
            ColorRoute colorRoute = new ColorRoute(colors.get(i).getId());
            route.add(colorRoute);
        }
    }

    public VertexColor next(){
        if (pos < route.size()){
            Vertex nextVer = route.get(pos).next();
            if (nextVer == null){
                pos++;
                if (pos == route.size()){
                    return new VertexColor();
                } else {
                    return new VertexColor(route.get(pos).getColor());
                }
            } else {
                return new VertexColor(nextVer);
            }
        }
        return new VertexColor();
    }

    public Integer getId() {
        return id;
    }
}
