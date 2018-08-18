import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Since there are other ways of handling mouse inputs that are not clicks,
// it's better to override every method from MouseListener that's not "mouseClicked" once through an abstract class
// instead of doing it every time a listener is added
public abstract class MouseClickListener implements MouseListener {
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