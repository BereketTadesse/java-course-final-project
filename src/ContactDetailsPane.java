import javax.swing.*;
import java.awt.*;

public class ContactDetailsPane extends JPanel {
    private static DarkButton deleteButton = new DarkButton("Delete", DarkButton.RED);
    // public static Color MAIN_BORDER_COLOR = new Color(64, 64, 64);


    ContactDetailsPane() {
        setBackground(new Color(52, 52, 52));
        setForeground(Color.LIGHT_GRAY);
        // setBorder(new LineBorder(MainScreen.MAIN_PANEL_COLOR, 1));
    }
}
