package draw.service.entity;

import draw.service.entity.memento.CircleState;
import draw.service.entity.memento.IState;
import draw.service.entity.memento.RectangleState;
import draw.service.entity.memento.TriangleState;

import java.util.ArrayList;
import java.util.List;

public class EntityRepository {

    private List<AbstractEntity> entities = new ArrayList<>();

    public void add(AbstractEntity entity) {
        this.entities.add(entity);
    }

    public List<AbstractEntity> getEntities() {
        return entities;
    }

    public void clear() {
        this.entities.clear();
    }

    public List<IState> getStates() {
        List<IState> states = new ArrayList<>();

        for (AbstractEntity entity: this.entities) {
            states.add(entity.getState());
        }
        return states;
    }

    public void loadFromStates(List<IState> states) {
        for (IState state: states) {
            if (state instanceof CircleState) {
                CircleState circleState = (CircleState)state;
                this.entities.add(Circle.loadFromState(circleState));
            } else if (state instanceof RectangleState) {
                RectangleState rectangleState = (RectangleState)state;
                this.entities.add(Rectangle.loadFromState(rectangleState));
            } else if (state instanceof TriangleState) {
                TriangleState triangleState = (TriangleState)state;
                this.entities.add(Triangle.loadFromState(triangleState));
            }

        }
    }
}
