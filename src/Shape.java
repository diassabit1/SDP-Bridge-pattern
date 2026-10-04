public abstract class Shape {
    protected final int id;
    protected Renderer renderer;

    public Shape(int id, Renderer renderer) {
        this.id = id;
        this.renderer = renderer;
    }

    public void setImplementation(Renderer renderer) {
        this.renderer = renderer;
    }

    public int getId() {
        return id;
    }

    public abstract String execute();
}