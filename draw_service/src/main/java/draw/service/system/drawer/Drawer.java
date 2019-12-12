package draw.service.system.drawer;

import java.awt.*;

public interface Drawer {
    int[] COLORS = new int[]{
            0xFFCF0D,
            0xE82A00,
            0x8E0DFF,
            0x00B7E8,
            0x36FF00,
    };

    void draw(Graphics g);

}
