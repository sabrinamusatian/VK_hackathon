import java.util.*;

public class User {
    private Integer id;
    private Route userRoute;
    private Integer pos = 0;

    public User(Integer id){
        this.id = id;
    }

    public void setUserRoute(Route userRoute) {
        this.userRoute = userRoute;
    }

    public VertexColor next(){
        List<ColorRoute> route = userRoute.getRoute();
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

    public Route getUserRoute() {
        return userRoute;
    }
}
