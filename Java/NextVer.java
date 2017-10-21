import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class NextVer extends HttpServlet {

    public String parseVertexColor(VertexColor vertexColor){
        if (vertexColor.getThere() == 2){
            return "{\"action\": \"exit\"}";
        } else if (vertexColor.getThere() == 1){
            return "{\"action\": \"vertex\", \"vertex\": " + vertexColor.getVertex().getId().toString() + "}";
        } else if (vertexColor.getThere() == 0){
            return"{\"action\": \"color\", \"color\": " + vertexColor.getColor().getId().toString() + "}";
        }
        return "{\"action\": \"error\"}";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try{
            String user_id = request.getParameter("user_id");

            User user = UsersList.getUserById(user_id);
            if (user.getUserRoute() != null) {
                out.println(parseVertexColor(user.next()));
            } else {
                out.println("ROUTE IS NULL");
            }
        } catch (Exception e){ // TODO: catch null
            out.println(e.getMessage());
        }
    }
} 