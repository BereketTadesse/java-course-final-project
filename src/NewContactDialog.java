import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class NewContactDialog extends JDialog {
    private Container contentPane = getContentPane();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    private ColoredLabel title = new ColoredLabel("Add contact");

    private ColoredLabel fname = new ColoredLabel("First name:");
    private ColoredLabel lname = new ColoredLabel("Last name:");
    private ColoredLabel phone = new ColoredLabel("Phone:");
    private DarkTextField fnameField = new DarkTextField();
    private DarkTextField lnameField = new DarkTextField();
    private DarkTextField phoneField = new DarkTextField();

    NewContactDialog() {
        init();
        contentPane.add(panel);
        URL imgurl = getClass().getResource("files/images/icon(32x32_light).png");
        if (imgurl != null) {
            ImageIcon icon = new ImageIcon(imgurl);
            setIconImage(icon.getImage());
        }
        setTitle("New contact");
        setSize(360, 640);
        setLocation(503, 64);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void init() {
        panel.setBackground(MainScreen.DARKER_GRAY);
        initTitle();
        initInputs();
    }

    private void initInputs() {
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 30, 10, 10);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        panel.add(fname, constraints);

        constraints.gridy = 2;
        panel.add(lname, constraints);

        constraints.gridy = 3;
        panel.add(phone, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 10, 10, 30);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        panel.add(fnameField, constraints);

        constraints.gridy = 2;
        panel.add(lnameField, constraints);

        constraints.gridy = 3;
        panel.add(phoneField, constraints);
    }

    private void initTitle() {
        title.setFontSize(24);
        title.setBold(true);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(30, 30, 30, 30);
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        panel.add(title, constraints);
    }
}
