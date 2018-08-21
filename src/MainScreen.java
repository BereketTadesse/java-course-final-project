import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

// TODO: Organize code
// TODO: Address warnings

public class MainScreen {
    private static JFrame frame = new JFrame("Contacts");
    private static Container contentPane = frame.getContentPane();

    public static final Color DARKER_GRAY = new Color(48, 48, 48);
    public static final Color MAIN_PANEL_COLOR = new Color(56, 56, 56);
    public static final Color LINK_COLOR = new Color(49, 115, 175);

    private static JPanel titleContainer = new JPanel();
    static JPanel panel = new JPanel(new BorderLayout());;

    private static final String EMPTY_SPACE = "                ";

    DarkMenuBar menuBar = new DarkMenuBar();
    DarkMenu file = new DarkMenu("File");
    DarkMenu edit = new DarkMenu("Edit");
    DarkMenuItem addContact = new DarkMenuItem("Add contact" + EMPTY_SPACE);
    DarkMenuItem exit = new DarkMenuItem("Exit" + EMPTY_SPACE);

    User user = new User();
    static DarkList<String> contactsList;
    private static DarkScrollPane listScrollPane;
    public static Contact[] allContacts;
    static String[] allContactNames;
    private static DefaultListModel listModel = new DefaultListModel();

    public static final int DELETE = 0;
    public static final int ADD = 1;


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

        // panel.add(new ContactDetailsPane(true));

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
        frame.setSize(720, 720);
        frame.setLocation(323, 24);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    private void init() {
        initWelcomeMessage();
        initMenuActionListeners();
        initContactList();
    }

    private static void addListListener() {
        contactsList.addListSelectionListener(new ListSelectionAction());
    }

    private static void initContactList() {
        if (!FileHandler.maxFileExists()) {
            panel.add(new ContactDetailsPane(false), BorderLayout.CENTER);
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
        addListListener();
        panel.removeAll();
        panel.add(new ContactDetailsPane(allContacts[0]));
    }

    private static void sortContactList() {
        String temp;
        String currentContactName, nextContactName;
        Contact tempContact;
        for (int i = 0; i < allContactNames.length; i++) {
            for (int j = i + 1; j < allContactNames.length; j++) {
                if (allContactNames[i].compareTo(allContactNames[j]) > 0) {
                    temp = allContactNames[i];
                    allContactNames[i] = allContactNames[j];
                    allContactNames[j] = temp;
                }
            }
        }
        for (int i = 0; i < allContacts.length; i++) {
            for (int j = i + 1; j < allContacts.length; j++) {
                currentContactName = allContacts[i].getFname() + " " + allContacts[i].getLname();
                nextContactName = allContacts[j].getFname() + " " + allContacts[j].getLname();
                if (currentContactName.compareTo(nextContactName) > 0) {
                    tempContact = allContacts[i];
                    allContacts[i] = allContacts[j];
                    allContacts[j] = tempContact;
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

    public static void updateContactList(Contact contact, int action) {
        Contact[] newContactsList;
        int position;
        if (action == ADD) {
            if (Contact.getAvailableContacts() > 1) {
                newContactsList = new Contact[allContacts.length + 1];
                for (int i = 0; i < allContacts.length; i++)
                    newContactsList[i] = allContacts[i];
                newContactsList[newContactsList.length - 1] = contact;
                allContacts = newContactsList;
                generateContactNames();
                sortContactList();
                position = getIndex(contact);
                listModel.add(position, allContactNames[position]);
            } else
                initContactList();
        }
        else if (action == DELETE) {
            // panel.removeAll();
            newContactsList = new Contact[allContacts.length - 1];
            position = getIndex(contact);
            // System.out.println(listModel.get(0));
            for (int i = 0; i < allContacts.length; i++) {
                if (i < position)
                    newContactsList[i] = allContacts[i];
                else if (i == position)
                    continue;
                else
                    newContactsList[i - 1] = allContacts[i];
            }
            contactsList.setSelectedIndex(position + 1);
            listModel.remove(position);
            allContacts = newContactsList;
            System.out.println(allContacts[0].getFname());
            generateContactNames();
            sortContactList();
            Contact.availableContacts--;
            panel.removeAll();
        }
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
        ColoredLabel welcomeText = new ColoredLabel("Hi, " + user.getFname() + "!");
        // ColoredLabel welcomeText = new ColoredLabel(user.getFname() + "'s contacts");
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

class ListSelectionAction implements ListSelectionListener {
    int selection;
    static int previousSelection = -1;
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            selection = MainScreen.contactsList.getSelectedIndex();
            MainScreen.panel.setVisible(false);
            MainScreen.panel.removeAll();
            if (selection != previousSelection && selection < MainScreen.allContacts.length)
                MainScreen.panel.add(new ContactDetailsPane(MainScreen.allContacts[selection]));
            MainScreen.panel.setVisible(true);

        }
     }

}