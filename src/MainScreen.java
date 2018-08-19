import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainScreen {
    JFrame frame = new JFrame("Contacts");
    private JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    Container contentPane = frame.getContentPane();
    DarkMenuBar menuBar = new DarkMenuBar();
    DarkMenu file = new DarkMenu("File");
    DarkMenu edit = new DarkMenu("Edit");
    DarkMenu newMenu = new DarkMenu("New");
    DarkMenuItem contact = new DarkMenuItem("Contact");

    User user = new User();

    MainScreen() {
        newMenu.add(contact);
        file.add(newMenu);
        panel.setBackground(Color.DARK_GRAY);
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
}
