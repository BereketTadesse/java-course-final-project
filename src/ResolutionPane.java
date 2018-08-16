import javax.swing.*;
import java.awt.*;

@Deprecated
class ResolutionPane extends JDialog {
    private static JLabel prompt = new JLabel("Preferred size:");
    private static String[] resolutionOptions = { "640 x 360", "896 x 504", "1280 x 720", "1600 x 900", "1920 x 1080" };
    private static DarkComboBox<String> resolutions = new DarkComboBox<>(resolutionOptions);
    // private static JPanel panel = new JPanel(new GridBagLayout());
    // private static Container buttonContainer = new Container();
    // private static Container optionsContainer = new Container();
    private static GridBagConstraints content = new GridBagConstraints();
    private static DarkButton exit = new DarkButton("Exit", DarkButton.RED);
    private static DarkButton ok = new DarkButton("OK");

    ResolutionPane() {
        // setUndecorated(true);
        // setSize(320, 240);
        getContentPane().setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        init();
        setVisible(true);
    }

    private void init() {
        prompt.setForeground(Color.LIGHT_GRAY);

        // ok.setBorderPainted(false);
        // ok.setBackground(Color.GRAY);
        // ok.setForeground(Color.WHITE);

        // exit.setBorderPainted(false);
        // exit.setBackground(Color.DARK_GRAY);
        // exit.setForeground(Color.WHITE);

        addComponents();

    }

    private void addComponents() {
        content.insets = new Insets(10, 10, 10, 10);

        content.gridx = 0;
        content.gridy = 0;
        content.gridwidth = 2;
        content.anchor = GridBagConstraints.WEST;
        getContentPane().add(prompt, content);

        content.gridy = 1;
        content.anchor = GridBagConstraints.CENTER;
        content.fill = GridBagConstraints.BOTH;
        getContentPane().add(resolutions, content);

        content.gridy = 2;
        content.anchor = GridBagConstraints.EAST;
        content.gridwidth = 1;
        // content.fill = GridBagConstraints.NONE;
        getContentPane().add(ok, content);

        content.gridx = 1;
        content.anchor = GridBagConstraints.WEST;
        getContentPane().add(exit, content);
        this.pack();
        // initButtonContainer();

        ok.setEnabled(false);
    }
}
