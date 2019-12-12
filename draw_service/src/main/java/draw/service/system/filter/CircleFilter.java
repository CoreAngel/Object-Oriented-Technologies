package draw.service.system.filter;

import draw.service.component.ColorComponent;
import draw.service.component.IComponent;
import draw.service.component.PointComponent;
import draw.service.component.RadiusComponent;
import draw.service.entity.AbstractEntity;
import draw.service.entity.Circle;
import draw.service.system.drawer.CircleDrawer;
import draw.service.system.drawer.Drawer;
import draw.service.utils.Point;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class CircleFilter implements IFilter {

    @Override
    public Optional<Drawer> filter(AbstractEntity entity) {
        List<IComponent> components = entity.getComponents();
        Optional<IComponent> optPoint = components.stream().filter(c -> c instanceof PointComponent).findFirst();
        Optional<IComponent> optRadius = components.stream().filter(c -> c instanceof RadiusComponent).findFirst();
        Optional<IComponent> optColor = components.stream().filter(c -> c instanceof ColorComponent).findFirst();

        if (optPoint.isPresent() && optRadius.isPresent() && optColor.isPresent()) {
            PointComponent point = (PointComponent) optPoint.get();
            RadiusComponent radius = (RadiusComponent) optRadius.get();
            ColorComponent color = (ColorComponent) optColor.get();

            return Optional.of(new CircleDrawer(new Point(point), radius.getRadius(), color.getColor()));
        }
        return Optional.empty();
    }
}
