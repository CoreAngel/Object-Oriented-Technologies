package draw.service.component;

public class ColorComponent implements IComponent {
    private int color;

    public ColorComponent(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
