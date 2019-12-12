package draw.service.window;

import draw.service.entity.AbstractEntity;
import draw.service.entity.EntityRepository;
import draw.service.system.EntityManager;
import draw.service.system.drawer.Drawer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class Canvas extends JPanel {
    public static int WIDTH = 1000;
    public static int HEIGHT = 600;

    private EntityRepository entityRepository;
    private EntityManager em;

    public Canvas(EntityRepository entityRepository, EntityManager em) {
        this.entityRepository = entityRepository;
        this.em = em;

        this.setPreferredSize(new Dimension(Canvas.WIDTH ,Canvas.HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        List<AbstractEntity> entityList = this.entityRepository.getEntities();
        for (AbstractEntity abstractEntity : entityList) {
            Optional<Drawer> optionalDrawer = this.em.process(abstractEntity);
            optionalDrawer.ifPresent(d -> d.draw(g));
        }
    }
}
