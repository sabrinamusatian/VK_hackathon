import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UserChoose extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String user_id = request.getParameter("user_id");
        String route = request.getParameter("route");


        User user = UsersList.userChoose(user_id, route);
        out.println(user.getId().toString());
        if (user.getUserRoute() != null) {
            out.println(user.getUserRoute().getDescription());
        } else {
            out.println("ROUTE IS NULL");
        }
    }
} 