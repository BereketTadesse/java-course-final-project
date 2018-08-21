import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    public static final Color MAIN_PANEL_COLOR = new Color(56, 56, 56);
    public static final Color LINK_COLOR = new Color(49, 115, 175);

    // private JPanel titlePanel = new JPanel(new GridBagLayout());
    private JPanel titleContainer = new JPanel();
    private JPanel panel = new ContactDetailsPane();
    private GridBagConstraints constraints = new GridBagConstraints();

    private static final String EMPTY_SPACE = "                ";

    DarkMenuBar menuBar = new DarkMenuBar();
    DarkMenu file = new DarkMenu("File");
    DarkMenu edit = new DarkMenu("Edit");
    DarkMenuItem addContact = new DarkMenuItem("Add contact" + EMPTY_SPACE);
    DarkMenuItem exit = new DarkMenuItem("Exit" + EMPTY_SPACE);
    // DarkMenuItem contact = new DarkMenuItem("Contact" + EMPTY_SPACE);


    User user = new User();
    private static DarkList<String> contactsList;
    private static DarkScrollPane listScrollPane;
    public static Contact[] allContacts;
    private static String[] allContactNames;
    // private static ListModel listModel;
    private static DefaultListModel listModel = new DefaultListModel();


    MainScreen() {
        init();
        file.add(addContact);
        file.add(exit);
        panel.setBackground(MAIN_PANEL_COLOR);
        // panel.setBackground(DARKER_GRAY);

        panel.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));

        titleContainer.setBackground(DARKER_GRAY);
        titleContainer.setLayout(new GridBagLayout());
        titleContainer.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(new ContactDetailsPane());

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
        initListAttributes();
    }

    private static void initListAttributes() {
    }

    private static void initContactList() {
        String[] emptyArray = {" "};
        if (!FileHandler.maxFileExists()) {
            // contactsList = new DarkList<>(emptyArray);
            return;
        }
        Contact.availableContacts = FileHandler.readMaxFile();
        allContacts = FileHandler.readAllContacts();
        generateContactNames();
        generateContactList(false);
    }

    private static void generateContactNames() {
        allContactNames = new String[allContacts.length];
        for (int i = 0; i < allContacts.length; i++) {
            allContactNames[i] = allContacts[i].getFname();
            if (!allContacts[i].getLname().isEmpty())
                allContactNames[i] += " " + allContacts[i].getLname();
        }
    }

    private static void generateContactList(boolean isUpdate) {
        if (isUpdate)
            listModel.addElement(allContactNames[allContactNames.length - 1]);
        else {
            listModel = new DefaultListModel();
            sortContactList();
            for (int i = 0; i < allContactNames.length; i++)
                listModel.addElement(allContactNames[i]);
        }
        contactsList = new DarkList<>();
        contactsList.setModel(listModel);
        contentPane.setVisible(false);
        listScrollPane = new DarkScrollPane(contactsList);
        contactsList.ensureIndexIsVisible(contactsList.getSelectedIndex());
        // contentPane.add(contactsList, BorderLayout.LINE_START);
        contentPane.add(listScrollPane, BorderLayout.WEST);
        contentPane.setVisible(true);
    }

    private static void sortContactList() {
        String temp;
        for (int i = 0; i < allContactNames.length; i++) {
            for (int j = i + 1; j < allContactNames.length; j++) {
                if (allContactNames[i].compareTo(allContactNames[j]) > 0) {
                    temp = allContactNames[i];
                    allContactNames[i] = allContactNames[j];
                    allContactNames[j] = temp;
                }
            }
        }
    }

    private static int getIndex(Contact newContact) {
        int position = 0;
        for (int i = 0; i < allContactNames.length; i++) {
            if (allContactNames[i].equals(newContact.getFname() + " " + newContact.getLname())) {
                position = i;
                break;
            }
        }
        System.out.println(position);
        return position;
    }

    public static void updateContactList(Contact newContact) {
        Contact[] newContactsList;
        int position;
        if (Contact.getAvailableContacts() > 1) {
            newContactsList = new Contact[allContacts.length + 1];
            for (int i = 0; i < allContacts.length; i++)
                newContactsList[i] = allContacts[i];
            newContactsList[newContactsList.length - 1] = newContact;
            allContacts = newContactsList;
            generateContactNames();
            sortContactList();
            position = getIndex(newContact);
            listModel.add(position, allContactNames[position]);
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
        welcomeText.setBold(true);
        welcomeText.setFontSize(24);
        welcomeText.setBorder(new EmptyBorder(10, 13, 10, 0));
        titleContainer.add(welcomeText);
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
