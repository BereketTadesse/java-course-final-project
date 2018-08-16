import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

interface MouseClickListener extends MouseListener {
    @Override
    void mouseClicked(MouseEvent e);
}