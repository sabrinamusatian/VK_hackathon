import java.util.List;

/**
 * Created by lkolt on 20.10.2017.
 */
public class Vertex {
    private Integer id;
    private String name;
    private String url;

    public Vertex(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Integer getVerNumber() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}