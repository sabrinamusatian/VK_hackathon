public class VertexColor {
    private Color color = null;
    private Vertex vertex = null;
    private Integer there;

    public VertexColor(Color color) {
        this.color = color;
        this.there = 0;
    }

    public VertexColor(Vertex vertex) {
        this.vertex = vertex;
        this.there = 1;
    }

    public VertexColor(){
        this.there = 2;
    }

    public Color getColor() {
        return color;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Integer getThere() {
        return there;
    }
}
