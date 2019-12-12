package draw.service.entity;

import draw.service.component.IComponent;
import draw.service.entity.memento.IState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractEntity {

    private List<IComponent> components = new ArrayList<IComponent>();

    protected void addComponents(IComponent ...components) {
        if (components.length != 0) {
            this.components.addAll(Arrays.asList(components));
        }
    }

    public List<IComponent> getComponents() {
        return this.components;
    }
    public abstract IState getState();
}
