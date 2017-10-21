import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class SelectRoute extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String user_id = request.getParameter("user_id");
        String pos = request.getParameter("pos");

        // TODO: comment html
        List<Route> routes = UsersList.getPopularRoutes();

        String json = "[";

        List<Route> popularRoutes = UsersList.getPopularRoutes();
        for (int i = 0; i < popularRoutes.size(); i++){
            Route route = popularRoutes.get(i);
            json += "{\"description\": \"" + route.getDescription() +
                    "\", \"route\": [";

            List<ColorRoute> colorRoutes = route.getRoute();
            for (int j = 0; j < colorRoutes.size(); j++){
                ColorRoute cr = colorRoutes.get(j);
                json += "{\"color\": ";
                json += cr.getColor().getId().toString();
                json += ", \"vertexes\": [";

                List<Vertex> vertexes = cr.getRoute();
                for (int k = 0; k < vertexes.size(); k++){
                    json += vertexes.get(k).getId().toString();
                    if (k != vertexes.size() - 1) {
                        json += ", ";
                    }
                }
                json += "]}";
                if (j != colorRoutes.size() - 1) {
                    json += ", ";
                }
            }
            json += "]}";

            if (i != routes.size() - 1) {
                json += ", ";
            }
        }
        json += "]";
        out.println(json);
    }
} 