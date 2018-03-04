//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import org.json.*;


public class Main {

    public static Integer parseVertexColor(VertexColor vertexColor){
        if (vertexColor.getThere() == 2){
            System.out.println("Exit");
            return 2;
        } else if (vertexColor.getThere() == 1){
            System.out.println("Move to vertex " + vertexColor.getVertex().getId().toString() + " with name " + vertexColor.getVertex().getName());
            return 1;
        } else if (vertexColor.getThere() == 0){
            System.out.println("Move to color " + vertexColor.getColor().getId().toString());
            return 0;
        }
        return -1;
    }

    public static String onPost(String strJson){
        Graph.initGraph();
        try {
            JSONArray arr = new JSONArray(strJson);
            for (int i = 0; i < arr.length(); i++){
                JSONObject device = (JSONObject) arr.get(i);
                String mac = (String) device.get("mac");
                String power = (String) device.get("power");

                // TODO: parse info from raspberry
            }

            Color color = Graph.getColorById(0);
            if (color != null){
                color.setPeople(arr.length());
            }
        } catch (Exception e){
            e.printStackTrace();
            return "FAIL: " + strJson;
        }

        Color color = Graph.getColorById(0);
        if (color != null){
            return color.getPeople().toString();
        }
        return "FAILED";
    }

    public static void main(String[] args) {
        Graph.initGraph();
//
//        User user = new User(0);
//
//        while (true){
//            VertexColor vertexColor = user.next();
//            Integer returnCode = parseVertexColor(vertexColor);
//            if (returnCode == 2){
//                break;
//            }
//    }

    String json = "[{\"mac\" :  \"MACMACMAC\", \"power\" : \"-65\"}, {\"mac\" :  \"FUCKFUCKFUCK\", \"power\" : \"-63\"}]";
        onPost(json);
    }
}
