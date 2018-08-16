import javax.swing.*;
import java.awt.*;

public class DarkComboBox <E> extends JComboBox { // Uses a generic type so as not to lose any JComboBox functionality
    private static final Color FONT_COLOR = new Color(200, 200, 200);
    private static final Color DEFAULT_COLOR = new Color(80, 80,80);
    private static final Color DEFAULT_SELECTION_COLOR = new Color(96, 96, 96);

    DarkComboBox() {
        setColors();
    }

    DarkComboBox (E[] items) {
        super(items);
        setColors();
    }

    private void setColors() {
        setBackground(DEFAULT_COLOR);
    }

    public void paintComponent(Graphics comboGraphics) {
        comboGraphics.setColor(DEFAULT_COLOR);
        comboGraphics.fillRect(0, 0, getWidth(), getHeight());
        setForeground(FONT_COLOR);
    }


}
