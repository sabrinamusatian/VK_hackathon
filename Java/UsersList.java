import java.util.ArrayList;
import java.util.List;

public class UsersList {
    private static List<User> users = new ArrayList<>();
    private static List<Route> routes = new ArrayList<>();

    private static final int numberRoutes = 10;

    public static void initRoutes(){
        for (int i = 0; i < numberRoutes; i++){
            routes.add(new Route("Desc"));
        }
    }

    public static List<Route> getPopularRoutes(){
        if (routes.size() == 0){
            initRoutes();
        }
        return routes;
    }
}
