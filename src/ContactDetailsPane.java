import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ContactDetailsPane extends JPanel {
    private static DarkButton deleteButton = new DarkButton("Delete", DarkButton.RED);
    DarkButton newContact = new DarkButton("New Contact");
    private GridBagConstraints constraints = new GridBagConstraints();
    // public static Color MAIN_BORDER_COLOR = new Color(64, 64, 64);


    ContactDetailsPane() {
        // setBackground(new Color(52, 52, 52));
        setBackground(MainScreen.MAIN_PANEL_COLOR);
        setForeground(Color.LIGHT_GRAY);
        // setBorder(new LineBorder(MainScreen.MAIN_PANEL_COLOR, 1));
        setBorder(new EmptyBorder(0, 10, 20, 20));
        setLayout(new GridBagLayout());
    }

    ContactDetailsPane(boolean contactsAvailable) {
        this();
        if (!contactsAvailable)
            initNoContactsPane();
    }

    private void initNoContactsPane() {
        ColoredLabel frown = new ColoredLabel(":( ", new Color(44, 44, 44));
        ColoredLabel nothingHere = new ColoredLabel("There seems to be nothing here", new Color(44, 44, 44));
        frown.setBold(true);
        frown.setFontSize(120);
        nothingHere.setBold(true);
        nothingHere.setFontSize(36);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets.top = 100;
        constraints.anchor = GridBagConstraints.CENTER;
        add(frown, constraints);
        constraints.insets.top = 30;
        constraints.gridy = 1;
        add(nothingHere, constraints);
        constraints.insets.top = 50;
        constraints.gridy = 2;
        add(newContact, constraints);
        newContact.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new NewContactDialog();
            }
        });
    }
}
