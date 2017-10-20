import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private Integer id;
    private List<ColorRoute> route = new ArrayList<>();
    private Integer pos = 0;

    public User(Integer id){
        this.id = id;

        for (int i = 0; i < Graph.numberColors; i++){
            ColorRoute colorRoute = new ColorRoute(i);
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
}
