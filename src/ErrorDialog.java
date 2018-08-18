import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class ErrorDialog extends JDialog {
    private Container cp = getContentPane();
    private static DarkButton ok = new DarkButton("OK");
    private ImageIcon errorIcon = new ImageIcon(getClass().getResource("files/images/error-icon.png"));
    private JLabel icon = new JLabel(errorIcon);
    private static ColoredLabel error = new ColoredLabel("Error!", new Color(200, 0, 0));
    private ColoredLabel failText = new ColoredLabel();
    private JPanel panel = new JPanel(new GridBagLayout());
    private GridBagConstraints constraints = new GridBagConstraints();

    ErrorDialog() {
        init();
        panel.setBackground(Color.DARK_GRAY);
        cp.add(panel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(533, 250);
        setUndecorated(true);
        setVisible(true);
    }

    private void init() {
        error.setFont(new Font("Sans Serif", Font.BOLD, 20));

        // Error icon
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 20, 20, 20);
        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(icon, constraints);

        // Error text
        constraints.gridx = 1;
        constraints.insets = new Insets(30, 0, 0, 20);
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.NONE;
        panel.add(error, constraints);

        // Button
        constraints.insets = new Insets(20, 20, 20, 20);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(ok, constraints);
        ok.addMouseListener(new closeDialog());
    }

    protected void writeFailText(String text) {
        failText.setText(text);
        failText.setFontSize(12);
        constraints.insets = new Insets(-25, 0, 0, 20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridwidth = 1;
        panel.add(failText, constraints);
        pack();
    }

    class closeDialog extends MouseClickListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            dispose();
        }
    }

}

class FileCreationError extends ErrorDialog {
    String errorText = "Could not create file.";// Please make sure you have the proper permissions.";

    FileCreationError() {
        writeFailText(errorText);
    }
}
