import javax.swing.*;
import java.awt.*;

public class ColoredLabel extends JLabel {
    private Font currentFont = new Font("Sans Serif", Font.PLAIN, 12);

    ColoredLabel() {
        setForeground(Color.LIGHT_GRAY);
        this.setFont(currentFont);
    }

    ColoredLabel(String text) {
        this();
        setText(text);
    }

    ColoredLabel(Color color) {
        setForeground(color);
        this.setFont(currentFont);
    }

    ColoredLabel(String text, Color color) {
        this(color);
        setText(text);
    }
    public void setFontSize(int newSize) {
        Font newFont = new Font(currentFont.getFontName(), currentFont.getStyle(), newSize);
        currentFont = newFont;
        this.setFont(currentFont);
    }
}
