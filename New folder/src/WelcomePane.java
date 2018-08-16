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
}
