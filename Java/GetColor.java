import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class GetColor extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String color = request.getParameter("color");

        // TODO: comment html
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<TITLE>Servlet Testing</TITLE>");
        out.println("</HEAD>");
        out.println("<BODY>");

        Color col = Graph.getColorByName(color);
        String json;
        if (col != null) {
            json = "{\"type\": \"color\", \"color\": " + col.getId().toString() + "}";
        } else {
            json = "{\"type\": \"error\"}";
        }

        out.println(json);

        out.println("</BODY>");
        out.println("</HTML>");
    }
} 