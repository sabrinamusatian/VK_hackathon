import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Route {
    private List<ColorRoute> route = new ArrayList<>();
    private String description;

    public Route(String description){
        this.description = description;
        List<Color> colors = Graph.getGraph();

        Collections.sort(colors, (lhs, rhs) -> lhs.getPeople() < rhs.getPeople() ? -1 : (lhs.getPeople() > rhs.getPeople()) ? 1 : 0);

        for (int i = 0; i < colors.size(); i++){
            ColorRoute colorRoute = new ColorRoute(colors.get(i).getId());
            route.add(colorRoute);
        }
    }

    public List<ColorRoute> getRoute() {
        return route;
    }

    public String getDescription() {
        return description;
    }
}
