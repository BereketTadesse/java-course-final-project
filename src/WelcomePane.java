import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class WelcomePane extends JFrame {
    void initImages() throws IOException {
        // ImageIcon closebtnHover = new ImageIcon(getClass().getResource("./files/img/closebtn_hover.png"));
        ImageIcon button = new ImageIcon("files/images/closebtn.png");
        JLabel closebtn = new JLabel(button);
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.gridy = 0;
        g.gridx = 1;
        g.anchor = GridBagConstraints.NORTHEAST;
        getContentPane().add(closebtn);
        g.gridx = 0;
        g.gridy = 1;
        g.anchor = GridBagConstraints.WEST;
        getContentPane().add(new JLabel("Close btn Try"), g);
        // super.setUndecorated(true);
        super.setSize(1280, 720);
        setBackground(Color.darkGray);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setVisible(true);
    }

    private void initPanel() {
        ColoredLabel welcome = new ColoredLabel("Welcome!");
        ColoredLabel tellus = new ColoredLabel("Tell us a bit about yourself");
        ColoredLabel fname = new ColoredLabel("First Name:");
        ColoredLabel lname = new ColoredLabel("Last Name:");
        DarkButton proceed = new DarkButton("Proceed", DarkButton.GREEN);
        DarkButton exit = new DarkButton("Exit", DarkButton.RED);
        GridBagConstraints constraints = new GridBagConstraints();
        DarkTextField fnameField = new DarkTextField();
        DarkTextField lnameField = new DarkTextField();

        panel.setBackground(Color.DARK_GRAY);
        welcome.setFont(new Font("Sans Serif", Font.BOLD, 70));
        tellus.setFont(new Font("Sans Serif", Font.PLAIN, 16));
        fname.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        lname.setFont(new Font("Sans Serif", Font.PLAIN, 12));
        proceed.setFont(new Font("Sans Serif", Font.BOLD, 13));
        exit.setFont(new Font("Sans Serif", Font.PLAIN, 12));

        constraints.insets = new Insets(10, 10, 2, 10);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        panel.add(welcome, constraints);

        constraints.insets.top = 0;
        constraints.insets.bottom = 30;
        constraints.gridy = 3;
        panel.add(tellus, constraints);

        constraints.insets.bottom = 10;
        constraints.insets.right = 10;
        constraints.insets.left = 150;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(fname, constraints);

        constraints.gridy = 6;
        panel.add(lname, constraints);

        constraints.insets.right = 150;
        constraints.insets.left = 10;
        constraints.gridx = 3;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        panel.add(fnameField, constraints);

        constraints.gridy = 6;
        panel.add(lnameField, constraints);

        constraints.insets.left = 10;
        constraints.insets.right = 10;
        constraints.insets.top = 18;
        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(proceed, constraints);

        constraints.insets.top = 5;
        constraints.insets.bottom = 10;
        constraints.gridy = 8;
        panel.add(exit, constraints);
    }
}
