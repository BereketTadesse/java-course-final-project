import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DarkMenuBar extends JMenuBar {
    public static final Font DEFAULT_FONT = new Font("Sans Serif", Font.PLAIN, 12);

    DarkMenuBar() {
        adjustColors();
    }

    DarkMenuBar(JMenu menu) {
        this();
        add(menu);
    }

    private void adjustColors() {
        // setBackground(DarkButton.DEFAULT_COLOR);
        setBackground(Color.DARK_GRAY);
        setBorderPainted(false);
        // UIManager.put("Menu.selectionBackground", Color.LIGHT_GRAY);
        setFont(DEFAULT_FONT);
        // setBorder(new LineBorder(DarkTextField.DEFAULT_BORDER_COLOR));
        // setForeground(Color.RED);
    }
}

class DarkMenu extends JMenu {

    DarkMenu() {
        adjustColors();
    }

    DarkMenu(String text) {
        this();
        setText(text);
    }

    DarkMenu(JMenu menu) {
        this();
        add(menu);
    }

    private void adjustColors() {
        // setBackground(DarkButton.DEFAULT_COLOR);
        setBorderPainted(false);
        UIManager.put("PopupMenu.background", DarkButton.DEFAULT_COLOR);
        UIManager.put("PopupMenu.border", new LineBorder(Color.GRAY));
        // UIManager.put("PopupMenu.selectionBackground",Color.LIGHT_GRAY);
        setFont(DarkMenuBar.DEFAULT_FONT);
        setForeground(Color.LIGHT_GRAY);
    }

}

class DarkMenuItem extends JMenuItem {

    DarkMenuItem() {
        adjustColors();
    }

    DarkMenuItem(String text) {
        this();
        setText(text);
    }

    private void adjustColors() {
        setBorderPainted(false);
        setBackground(DarkButton.DEFAULT_COLOR);
        // UIManager.put("menuText.background", DarkButton.DEFAULT_COLOR);
        // UIManager.put("PopupMenu.border", new LineBorder(Color.GRAY));
        // UIManager.put("menuText",Color.LIGHT_GRAY);
        setFont(DarkMenuBar.DEFAULT_FONT);
        setForeground(Color.LIGHT_GRAY);
    }
/*
    public void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            setForeground(Color.BLACK);
        }
        super.paintComponent(g);
    }
*/
}
