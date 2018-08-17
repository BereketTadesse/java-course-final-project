import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Deprecated
public interface CustomMouseListener extends MouseListener {
    @Override
    void mouseClicked(MouseEvent e);

    @Override
    void mouseEntered(MouseEvent e);

    @Override
    void mouseExited(MouseEvent e);

    @Override
    void mousePressed(MouseEvent e);
}

abstract class MouseClickListener implements MouseListener {
    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}