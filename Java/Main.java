public class Main {

    public static Integer parseVertexColor(VertexColor vertexColor){
        if (vertexColor.getThere() == 2){
            System.out.println("Exit");
            return 2;
        } else if (vertexColor.getThere() == 1){
            System.out.println("Move to vertex " + vertexColor.getVertex().getId().toString());
            return 1;
        } else if (vertexColor.getThere() == 0){
            System.out.println("Move to color " + vertexColor.getColor().getId().toString());
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        Graph.initGraph();

        User user = new User(0);

        while (true){
            VertexColor vertexColor = user.next();
            Integer returnCode = parseVertexColor(vertexColor);
            if (returnCode == 2){
                break;
            }
        }
    }
}
