package draw.service.window;

import draw.service.entity.Circle;
import draw.service.entity.EntityRepository;
import draw.service.entity.Rectangle;
import draw.service.entity.Triangle;
import draw.service.provider.Loader;
import draw.service.provider.Writer;
import draw.service.utils.Point;
import draw.service.utils.Size;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class ButtonBar extends JPanel {
    private JButton addRectangle = new JButton("Add rectangle");
    private JButton addCircle = new JButton("Add circle");
    private JButton addTriangle = new JButton("Add triangle");
    private JButton save = new JButton("Save");
    private JButton load = new JButton("Load");
    private JButton clear = new JButton("Clear");

    private EntityRepository repository;
    private Canvas canvas;

    public ButtonBar(EntityRepository entityRepository, Canvas canvas) {
        this.repository = entityRepository;
        this.canvas = canvas;

        this.add(this.addRectangle);
        this.add(this.addCircle);
        this.add(this.addTriangle);
        this.add(this.save);
        this.add(this.load);
        this.add(this.clear);

        this.setActions();
    }

    private void setActions() {
        this.addRectangleAction();
        this.addCircleAction();
        this.addTriangleAction();
        this.addLoadAction();
        this.addSaveAction();
        this.addClearAction();
    }

    private void addRectangleAction() {
        this.addRectangle.addActionListener(a -> {
            Random random = new Random();

            int width = random.nextInt(200) + 100;
            int height = random.nextInt(100) + 100;
            int x = random.nextInt(Canvas.WIDTH - width);
            int y = random.nextInt(Canvas.HEIGHT - height);


            Rectangle rectangle = new Rectangle(new Point(x, y), new Size(width, height));
            this.repository.add(rectangle);
            this.canvas.repaint();
        });
    }

    private void addCircleAction() {
        this.addCircle.addActionListener(a -> {
            Random random = new Random();

            int radius = random.nextInt(60) + 20;
            int x = random.nextInt(Canvas.WIDTH - radius * 2) + radius;
            int y = random.nextInt(Canvas.HEIGHT - radius * 2) + radius;

            Circle circle = new Circle(new Point(x, y), radius);
            this.repository.add(circle);
            this.canvas.repaint();
        });
    }

    private void addTriangleAction() {
        this.addTriangle.addActionListener(a -> {
            Random random = new Random();

            int x1 = random.nextInt(Canvas.WIDTH);
            int y1 = random.nextInt(Canvas.HEIGHT);
            int x2 = random.nextInt(Canvas.WIDTH);
            int y2 = random.nextInt(Canvas.HEIGHT);
            int x3 = random.nextInt(Canvas.WIDTH);
            int y3 = random.nextInt(Canvas.HEIGHT);

            Triangle triangle = new Triangle(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3));
            this.repository.add(triangle);
            this.canvas.repaint();
        });
    }

    private void addSaveAction() {
        this.save.addActionListener(a -> {
            Writer writer = new Writer(this.repository);
            try {
                writer.writeToFile("data.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void addLoadAction() {
        this.load.addActionListener(a -> {
            Loader loader = new Loader(this.repository);

            this.repository.clear();
            loader.load("data.json");
            this.canvas.repaint();
        });
    }

    private void addClearAction() {
        this.clear.addActionListener(s -> {
            this.repository.clear();
            this.canvas.repaint();
        });
    }

}
