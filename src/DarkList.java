import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class DarkList extends JList { // Uses a generic type so as not to lose any JList functionality
    private static final Color DEFAULT_COLOR = new Color(160, 160,160);

    DarkList() {
        init();
    }

    private void init() {
        setBackground(MainScreen.DARKER_GRAY);
        setForeground(DEFAULT_COLOR);
        setSelectionBackground(MainScreen.MAIN_PANEL_COLOR);
        setSelectionForeground(Color.LIGHT_GRAY);
        UIManager.put("List.focusCellHighlightBorder", new EmptyBorder(0, 0, 0, 0));
        setFont(new Font("Sans Serif", Font.PLAIN, 14));
        setFixedCellWidth(200);
        setFixedCellHeight(23);
    }
}
