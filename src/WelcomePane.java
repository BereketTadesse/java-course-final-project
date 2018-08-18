import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;

class WelcomePane extends JFrame {
    private JPanel panel = new JPanel(new GridBagLayout());
    private ColoredLabel welcome = new ColoredLabel("Welcome!");
    private ColoredLabel tellus = new ColoredLabel("Tell us a bit about yourself");
    private ColoredLabel fname = new ColoredLabel("First Name:");
    private ColoredLabel lname = new ColoredLabel("Last Name:");
    private DarkButton proceed = new DarkButton("Proceed", DarkButton.GREEN);
    private DarkButton exit = new DarkButton("Exit", DarkButton.RED);
    private GridBagConstraints constraints = new GridBagConstraints();
    private DarkTextField fnameField = new DarkTextField();
    private DarkTextField lnameField = new DarkTextField();

    WelcomePane() {
        // ImageIcon icon = new ImageIcon("./files/images/icon(32x32).png");
        Container contentPane = getContentPane();
        initPanel();
        contentPane.add(panel);
        setTitle("Welcome");
        setSize(640, 360);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        URL imgurl = getClass().getResource("files/images/icon(32x32_light).png");
        if (imgurl != null) {
            ImageIcon icon = new ImageIcon(imgurl);
            setIconImage(icon.getImage());
        }
        setLocation(363, 204);
        setVisible(true);
    }

    private void initPanel() {
        panel.setBackground(Color.DARK_GRAY);
        welcome.setFont(new Font("Sans Serif", Font.BOLD, 70));
        tellus.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        fname.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        lname.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        proceed.setFont(new Font("Sans Serif", Font.BOLD, 13));
        exit.setFont(new Font("Sans Serif", Font.PLAIN, 12));

        adjustTitleConstraints();
        adjustSubtitleConstraints();
        adjustLabelConstraints();
        adjustTextFieldConstraints();
        adjustButtonConstraints();
        addEventHandlers();
    }

    private void adjustTitleConstraints() {
        constraints.insets = new Insets(10, 10, 2, 10);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        panel.add(welcome, constraints);
    }

    private void adjustSubtitleConstraints() {
        constraints.insets = new Insets(0, 10, 30, 10);
        constraints.gridy = 3;
        panel.add(tellus, constraints);
    }

    private void adjustLabelConstraints() {
        constraints.insets = new Insets(0, 150, 10, 10);
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(fname, constraints);

        constraints.gridy = 6;
        panel.add(lname, constraints);
    }

    private void adjustTextFieldConstraints() {
        constraints.insets = new Insets(0, 10, 10, 150);
        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        panel.add(fnameField, constraints);

        constraints.gridy = 6;
        panel.add(lnameField, constraints);
    }

    private void adjustButtonConstraints(){
        constraints.insets = new Insets(20, 10, 10, 10);
        // constraints.insets.top = 18;
        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(proceed, constraints);

        constraints.insets.top = 5;
        constraints.gridy = 8;
        panel.add(exit, constraints);
    }

    private void addEventHandlers() {
        proceed.addMouseListener(new ProceedButtonAction());
        exit.addMouseListener(new ExitButtonAction());
    }


    class ProceedButtonAction extends MouseClickListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // fnameField.getText();
            new FileCreationError();
            setVisible(false);
            dispose();
        }
    }

    class ExitButtonAction extends MouseClickListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
    }
}