package draw.service.component;

public class RadiusComponent implements IComponent {
    private double radius;

    public RadiusComponent(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
