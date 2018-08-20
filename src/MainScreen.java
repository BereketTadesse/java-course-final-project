import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

// TODO: Build dynamically updating list
// TODO: Build contact details panel
// TODO: Organize code
// TODO: Address warnings

public class MainScreen {
    private static JFrame frame = new JFrame("Contacts");
    private static Container contentPane = frame.getContentPane();

    public static final Color DARKER_GRAY = new Color(48, 48, 48);
    private static final Color MAIN_PANEL_COLOR = new Color(56, 56, 56);

    // private JPanel titlePanel = new JPanel(new GridBagLayout());
    private JPanel titleContainer = new JPanel();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    private static final String EMPTY_SPACE = "                ";

    DarkMenuBar menuBar = new DarkMenuBar();
    DarkMenu file = new DarkMenu("File");
    DarkMenu edit = new DarkMenu("Edit");
    DarkMenuItem addContact = new DarkMenuItem("Add contact" + EMPTY_SPACE);
    DarkMenuItem exit = new DarkMenuItem("Exit" + EMPTY_SPACE);
    // DarkMenuItem contact = new DarkMenuItem("Contact" + EMPTY_SPACE);

    public static Color LINK_COLOR = new Color(49, 115, 175);

    User user = new User();
    private static DarkList contactsList;
    public static Contact[] allContacts;
    private static String[] allContactNames;
    private static DefaultListModel listModel = new DefaultListModel();


    MainScreen() {
        // addContact.add(contact);
        init();
        file.add(addContact);
        file.add(exit);
        panel.setBackground(MAIN_PANEL_COLOR);
        panel.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));

        titleContainer.setBackground(DARKER_GRAY);
        titleContainer.setLayout(new FlowLayout(FlowLayout.LEFT));

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(titleContainer, BorderLayout.NORTH);

        menuBar.add(file);
        menuBar.add(edit);
        frame.setJMenuBar(menuBar);

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
        initWelcomeMessage();
        initMenuActionListeners();
        initContactList();
    }

    private static void initContactList() {
        if (!FileHandler.maxFileExists()) {
            contactsList = new DarkList();
            return;
        }
        Contact.availableContacts = FileHandler.readMaxFile();
        allContacts = FileHandler.readAllContacts();
        generateContactNames();
        generateContactList();
    }

    private static void generateContactNames() {
        allContactNames = new String[allContacts.length];
        for (int i = 0; i < allContacts.length; i++) {
            allContactNames[i] = allContacts[i].getFname();
            if (!allContacts[i].getLname().isEmpty())
                allContactNames[i] += " " + allContacts[i].getLname();
            System.out.println(allContactNames[i]);
        }
    }

    private static void generateContactList() {
        for (int i = 0; i < allContactNames.length; i++)
            listModel.addElement(allContactNames[i]);
        contactsList = new DarkList<>(allContactNames);
        contactsList.setModel(listModel);
        contentPane.setVisible(false);
        contentPane.add(contactsList, BorderLayout.LINE_START);
        contentPane.setVisible(true);
    }

/*
    private static void generateContactList() {
        allContactNames = new String[allContacts.length];
        for (int i = 0; i < allContacts.length; i++) {
            allContactNames[i] = allContacts[i].getFname();
            if (!allContacts[i].getLname().isEmpty())
                allContactNames[i] += " " + allContacts[i].getLname();
            System.out.println(allContactNames[i]);
        }
        contactsList = new DarkList<>(allContactNames);
    }
    */
    /*
    private void sortContactList() {
        for (int i = 0; i < allContactNames.length; i++)
    }
    public static void updateContactList(Contact newContact) {
        Contact[] newContactsList = new Contact[allContacts.length + 1];
        for (int i = 0; i < allContacts.length; i++)
            newContactsList[i] = allContacts[i];
        newContactsList[newContactsList.length - 1] = newContact;
        allContacts = newContactsList;
        contentPane.remove(contactsList);
        generateContactList();
        contentPane.add(contactsList, BorderLayout.LINE_START);
    }
*/

    public static void updateContactList(Contact newContact) {
        Contact[] newContactsList;
        if (Contact.getAvailableContacts() > 1) {
            newContactsList = new Contact[allContacts.length + 1];
            for (int i = 0; i < allContacts.length; i++)
                newContactsList[i] = allContacts[i];
            newContactsList[newContactsList.length - 1] = newContact;
            allContacts = newContactsList;
            generateContactNames();
        }
        else
            initContactList();
    }

    private void initMenuActionListeners() {
        addContact.addActionListener(new MenuItemActions());
        exit.addActionListener(new MenuItemActions());
    }

    private void initWelcomeMessage() {
        if (FileHandler.userFileExists())
            FileHandler.readUserFile(user);
        else {
            new UserFileError();
            frame.dispose();
            new WelcomePane();
            return;
        }
        // ColoredLabel welcomeText = new ColoredLabel("Hi, " + user.getFname() + "!");
        ColoredLabel welcomeText = new ColoredLabel(user.getFname() + "'s contacts");
        welcomeText.setFontSize(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 20, 20, 20);
        constraints.anchor = GridBagConstraints.WEST;
        titleContainer.add(welcomeText, constraints);
    }

    private void addElements() {
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 10;
        constraints.anchor = GridBagConstraints.WEST;

    }

    class MenuItemActions implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals(addContact.getText())) {
                new NewContactDialog();
            }
            else if (actionCommand.equals(exit.getText()))
                frame.dispose();
        }
    }
}
