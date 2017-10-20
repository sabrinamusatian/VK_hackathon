import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Color {
    private List<Vertex> vertexes;
    private Integer id;
    private Random random = new Random();

    public Color(Integer id, List<Vertex> vertexes) {
        this.id = id;
        this.vertexes = vertexes;
    }

    public List<Vertex> generateRoute(){
        List<Vertex> res = new ArrayList<>();

        for (Vertex ver: vertexes){
            if (random.nextBoolean()){
                res.add(ver);
            }
        }

        return res;
    }

    public Integer getId() {
        return id;
    }
}
