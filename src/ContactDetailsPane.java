import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ContactDetailsPane extends JPanel {
    private DarkButton deleteButton = new DarkButton("Delete", DarkButton.RED);
    DarkButton newContact = new DarkButton("New Contact");
    // public static Color MAIN_BORDER_COLOR = new Color(64, 64, 64);

    private JPanel noContactsContainer = new JPanel(new GridBagLayout());
    private GridBagConstraints noContactConstraints = new GridBagConstraints();

    private JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel contactDetailsContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel contactDetailsPanel = new JPanel(new GridBagLayout());
    private GridBagConstraints contactDetailConstraints = new GridBagConstraints();

    private ColoredLabel name;
    private ColoredLabel id;
    private ColoredLabel[] phoneNumbers;


    ContactDetailsPane() {
        // setBackground(new Color(52, 52, 52));
        setBackground(MainScreen.MAIN_PANEL_COLOR);
        setForeground(Color.LIGHT_GRAY);
        // setBorder(new LineBorder(MainScreen.MAIN_PANEL_COLOR, 1));
        setBorder(new EmptyBorder(0, 20, 20, 20));
        setLayout(new BorderLayout());
    }

    ContactDetailsPane(boolean contactsAvailable) {
        this();
        if (!contactsAvailable)
            initNoContactsPane();
    }

    ContactDetailsPane(Contact contact) {
        this();
        initOptionsPanel(contact);
        initContactDetailsPanel(contact);
        add(contactDetailsContainer, BorderLayout.CENTER);
        add(optionsPanel, BorderLayout.NORTH);
    }

    private void initOptionsPanel(Contact contact) {
        optionsPanel.setBackground(MainScreen.MAIN_PANEL_COLOR);
        optionsPanel.setForeground(Color.LIGHT_GRAY);
        name = new ColoredLabel(contact.getFname() + " " + contact.getLname());
        id = new ColoredLabel("#" + Integer.toString(contact.getId()), Color.GRAY);

        optionsPanel.add(name);
        name.setFontSize(20);
        name.setBorder(new EmptyBorder(0, 0, 0, 50));
        name.setBold(true);
        optionsPanel.add(id);
        id.setBorder(new EmptyBorder(0, 0, 0, 20));
        deleteButton.addMouseListener(new DeleteAction());
        optionsPanel.add(deleteButton);
        optionsPanel.setBorder(new EmptyBorder(10, 20, 20, -10));
    }

    private void initDetailComponents(Contact contact) {
        String[] nums = contact.getPhoneNumbers();
        phoneNumbers = new ColoredLabel[nums.length];
        name = new ColoredLabel(contact.getFname() + " " + contact.getLname());
        for (int i = 0; i < nums.length; i++) {
            phoneNumbers[i] = new ColoredLabel(nums[i]);
            phoneNumbers[i].setFontSize(20);
        }
    }

    private void initContactDetailsPanel(Contact contact) {
        String phone = new String("Phone");
        ColoredLabel numberId;

        contactDetailsPanel.setBackground(MainScreen.DARKER_GRAY);
        contactDetailsPanel.setForeground(Color.LIGHT_GRAY);

        initDetailComponents(contact);

        contactDetailConstraints.insets = new Insets(20, 40, 0, 10);
        contactDetailConstraints.anchor = GridBagConstraints.WEST;

        for (int i = 0; i < phoneNumbers.length; i++) {
            contactDetailConstraints.gridy = i;
            contactDetailConstraints.gridx = 0;
                if (!phoneNumbers[i].equals("+")) {
                    if (phoneNumbers.length == 1)
                        numberId = new ColoredLabel(phone + ":");
                    else
                        numberId = new ColoredLabel(phone + " " + Integer.toString(i+ 1 ) + ":");
                    contactDetailsPanel.add(numberId, contactDetailConstraints);
                    contactDetailConstraints.gridx = 1;
                    contactDetailsPanel.add(phoneNumbers[i], contactDetailConstraints);
                }
        }
        contactDetailsContainer.setBackground(MainScreen.DARKER_GRAY);
        contactDetailsContainer.setForeground(Color.LIGHT_GRAY);
        contactDetailsContainer.add(contactDetailsPanel);
    }

    private void initNoContactsPane() {
        // ColoredLabel frown = new ColoredLabel(":( ", new Color(44, 44, 44));
        // ColoredLabel nothingHere = new ColoredLabel("There seems to be nothing here", new Color(44, 44, 44));
        ColoredLabel frown = new ColoredLabel(":( ", new Color(72, 72, 72));
        ColoredLabel nothingHere = new ColoredLabel("There seems to be nothing here", new Color(72, 72, 72));
        frown.setBold(true);
        frown.setFontSize(120);
        nothingHere.setBold(true);
        nothingHere.setFontSize(36);
        setBackground(MainScreen.DARKER_GRAY);
        noContactsContainer.setBackground(MainScreen.DARKER_GRAY);

        noContactConstraints.gridx = 0;
        noContactConstraints.gridy = 0;
        noContactConstraints.anchor = GridBagConstraints.CENTER;
        noContactsContainer.add(frown, noContactConstraints);
        noContactConstraints.insets.top = 20;
        noContactConstraints.gridy = 1;
        noContactsContainer.add(nothingHere, noContactConstraints);
        noContactConstraints.insets.top = 50;
        noContactConstraints.gridy = 2;
        noContactsContainer.add(newContact, noContactConstraints);
        newContact.addMouseListener(new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new NewContactDialog();
            }
        });
        add(noContactsContainer);
    }

    class DeleteAction extends MouseClickListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            String deleteIdString = id.getText().substring(1);
            int deleteId = Integer.parseInt(deleteIdString);
            for (int i = 0; i < MainScreen.allContacts.length; i++)
                if (MainScreen.allContacts[i].getId() == deleteId)
                    MainScreen.updateContactList(MainScreen.allContacts[i], MainScreen.DELETE);
            FileHandler.deleteContactFile(deleteId);
        }
    }
}
