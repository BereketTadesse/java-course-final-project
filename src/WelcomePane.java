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
}
