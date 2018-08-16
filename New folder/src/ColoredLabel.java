import javax.swing.*;
import java.awt.*;

public class ColoredLabel extends JLabel {
    ColoredLabel() {
        setForeground(Color.LIGHT_GRAY);
    }

    ColoredLabel(String text) {
        this();
        setText(text);
    }

    ColoredLabel(Color color) {
        setForeground(color);
    }

    ColoredLabel(String text, Color color) {
        this(color);
        setText(text);
    }
}
