package draw.service.system;

import draw.service.entity.AbstractEntity;
import draw.service.system.drawer.Drawer;
import draw.service.system.filter.CircleFilter;
import draw.service.system.filter.IFilter;
import draw.service.system.filter.PolygonFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityManager {
    private static List<IFilter> filters = new ArrayList<>();

    static {
        EntityManager.filters.add(new CircleFilter());
        EntityManager.filters.add(new PolygonFilter());
    }

    public Optional<Drawer> process(AbstractEntity entity) {
        return EntityManager.filters.stream()
                .map(f -> f.filter(entity))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

}
