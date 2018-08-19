import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;

public class NewContactDialog extends JDialog {
    private Container contentPane = getContentPane();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    private ColoredLabel title = new ColoredLabel("Contact info");

    private ColoredLabel fname = new ColoredLabel("First name:");
    private ColoredLabel lname = new ColoredLabel("Last name:");
    private ColoredLabel phone[] = new ColoredLabel[Contact.MAX_PHONES];
    private DarkTextField fnameField = new DarkTextField();
    private DarkTextField lnameField = new DarkTextField();
    private DarkTextField[] phoneFields = new DarkTextField[Contact.MAX_PHONES];

    private int phoneInputsCount = 1;

    private ColoredLink addPhoneLink = new ColoredLink("Add another phone number");

    private DarkButton addContact = new DarkButton("Add contact", DarkButton.BLUE);
    private DarkButton cancel = new DarkButton("Cancel");

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
        initButtons();
        addPhoneInput();
        addLink();
    }

    private void addLink() {
        constraints.gridx = 2;
        constraints.gridy = 8;
        constraints.insets = new Insets(5, 0, 10, 30);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridwidth = 1;
        panel.add(addPhoneLink, constraints);
        addPhoneLink.addMouseListener(new PhoneLinkAction());
    }

    private void addPhoneInput() {
        for (int i = 0; i < Contact.MAX_PHONES; i++) {
            constraints.gridx = 0;
            constraints.gridy = 3 + i;
            constraints.insets = new Insets(0, 30, 10, 10);
            constraints.anchor = GridBagConstraints.EAST;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0;
            if (i > 0)
                phone[i] = new ColoredLabel("Phone " + Integer.toString(i + 1) + ":");
            else
                phone[i] = new ColoredLabel("Phone:");
            panel.add(phone[i], constraints);
            if (i > 0)
                phone[i].setVisible(false);

            constraints.gridx = 1;
            constraints.insets = new Insets(0, 10, 10, 30);
            constraints.anchor = GridBagConstraints.WEST;
            constraints.gridwidth = 2;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            phoneFields[i] = new DarkTextField();
            panel.add(phoneFields[i], constraints);
            if (i > 0)
                phoneFields[i].setVisible(false);
        }

    }

    private void initButtons() {
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(70, 50, 30, 10);
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        // constraints.fill = GridBagConstraints.NONE;
        panel.add(addContact, constraints);

        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(70, 10, 30, 30);
        panel.add(cancel, constraints);
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
    }

    private void initTitle() {
        title.setFontSize(36);
        title.setBold(true);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 30, 40, 30);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        panel.add(title, constraints);
    }

    private class PhoneLinkAction extends MouseClickListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (phoneInputsCount < Contact.MAX_PHONES) {
                phone[phoneInputsCount].setVisible(true);
                phoneFields[phoneInputsCount].setVisible(true);
                phoneInputsCount++;
            }
        }
    }
}
