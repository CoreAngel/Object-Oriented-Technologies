package draw.service.system.filter;

import draw.service.component.ColorComponent;
import draw.service.component.IComponent;
import draw.service.component.PointComponent;
import draw.service.entity.AbstractEntity;
import draw.service.system.drawer.Drawer;
import draw.service.system.drawer.PolygonDrawer;
import draw.service.utils.Point;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PolygonFilter implements IFilter {

    @Override
    public Optional<Drawer> filter(AbstractEntity entity) {
        List<IComponent> components = entity.getComponents();
        List<Point> optPoints = components.stream()
                .filter(c -> c instanceof PointComponent)
                .map(c -> {
                    PointComponent pointComponent = (PointComponent)c;
                    return new Point(pointComponent);
                })
                .collect(Collectors.toList());
        Optional<IComponent> optColor = components.stream().filter(c -> c instanceof ColorComponent).findFirst();

        if (optPoints.size() >= 3 && optColor.isPresent()) {
            ColorComponent color = (ColorComponent) optColor.get();
            return Optional.of(new PolygonDrawer(optPoints, color.getColor()));
        }
        return Optional.empty();
    }

}
