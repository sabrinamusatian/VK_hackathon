import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

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

    public static User userChoose(String id, String route){
        if (routes.size() == 0){
            initRoutes();
        }
        Integer _id = Integer.parseInt(id);

        Route curRoute = null;
        for (Route rt: routes){
            if (rt.getDescription().equals(route)){
                curRoute = rt;
                break;
            }
        }

        User user = UsersList.getUserById(id);

        if (user == null) {
            User newUser = new User(_id);
            newUser.setUserRoute(curRoute);
            users.add(newUser);
            return newUser;
        }

        return user;
    }

    public static User getUserById(String id){
        Integer _id = Integer.parseInt(id);
        for (User user: users){
            if (user.getId().equals(_id)){
                return user;
            }
        }
        return null;
    }
}
