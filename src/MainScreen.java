import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainScreen {
    JFrame frame = new JFrame("Contacts");
    Container contentPane = frame.getContentPane();

    public static final Color DARKER_GRAY = new Color(48, 48, 48);

    private JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    JList contacts = new JList();

    private static final String EMPTY_SPACE = "                ";

    DarkMenuBar menuBar = new DarkMenuBar();
    DarkMenu file = new DarkMenu("File");
    DarkMenu edit = new DarkMenu("Edit");
    DarkMenuItem addContact = new DarkMenuItem("Add contact" + EMPTY_SPACE);
    DarkMenuItem exit = new DarkMenuItem("Exit" + EMPTY_SPACE);
    // DarkMenuItem contact = new DarkMenuItem("Contact" + EMPTY_SPACE);

    User user = new User();

    public static Color LINK_COLOR = new Color(49, 115, 175);

    MainScreen() {
        // addContact.add(contact);
        file.add(addContact);
        file.add(exit);
        panel.setBackground(DARKER_GRAY);
        contentPane.add(panel);
        menuBar.add(file);
        menuBar.add(edit);
        // frame.add(menuBar, BorderLayout.NORTH);
        frame.setJMenuBar(menuBar);
        init();

        URL imgurl = getClass().getResource("files/images/icon(32x32_light).png");
        if (imgurl != null) {
            ImageIcon icon = new ImageIcon(imgurl);
            frame.setIconImage(icon.getImage());
        }
        frame.setSize(1280, 720);
        frame.setLocation(43, 24);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        new NewContactDialog();
    }

    private void init() {
        displayWelcomeMessage();
    }

    private void displayWelcomeMessage() {
        if (FileHandler.userFileExists())
            FileHandler.readUserFile(user);
        else {
            new UserFileError();
            frame.dispose();
            new WelcomePane();
            return;
        }
        ColoredLabel welcomeText = new ColoredLabel("Hi, " + user.getFname() + "!");
        welcomeText.setFontSize(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 20, 20, 20);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 4;
        panel.add(welcomeText, constraints);
    }

    private void addElements() {
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;

    }
}
