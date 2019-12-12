package draw.service.system.filter;

import draw.service.entity.AbstractEntity;
import draw.service.system.drawer.Drawer;

import java.util.Optional;

public interface IFilter {

    Optional<Drawer> filter(AbstractEntity entity);

}
