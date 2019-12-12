package draw.service.window;

import draw.service.entity.EntityRepository;
import draw.service.system.EntityManager;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame frame = new JFrame();
    private Canvas canvas;
    private ButtonBar buttonBar;

    public Window(EntityRepository entityRepository, EntityManager em) {
        this.frame.setTitle("Draw service");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.canvas = new Canvas(entityRepository, em);
        this.buttonBar = new ButtonBar(entityRepository, this.canvas);

        this.frame.add(this.buttonBar, BorderLayout.NORTH);
        this.frame.add(this.canvas, BorderLayout.CENTER);

        this.frame.pack();
        this.frame.setVisible(true);
    }
}
