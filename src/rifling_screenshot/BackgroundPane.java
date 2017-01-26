package rifling_screenshot;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class BackgroundPane extends JPanel {

    private BufferedImage background;
    private Point mouseAnchor;
    private Point dragPoint;

    private SelectionPane selectionPane;

    public BackgroundPane() {
//        selectionPane = new SelectionPane(background);
        try {
            Robot bot = new Robot();
            background = bot.createScreenCapture(SelectionRectangle.getScreenViewableBounds());
        } catch (AWTException ex) {
            Logger.getLogger(SelectionRectangle.class.getName()).log(Level.SEVERE, null, ex);
        }
        selectionPane = new SelectionPane(background);
        
        setLayout(null);
        add(selectionPane);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseAnchor = e.getPoint();
                dragPoint = null;
                selectionPane.setLocation(mouseAnchor);
                selectionPane.setSize(0, 0);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                dragPoint = e.getPoint();
                int width = dragPoint.x - mouseAnchor.x;
                int height = dragPoint.y - mouseAnchor.y;

                int x = mouseAnchor.x;
                int y = mouseAnchor.y;

                if (width < 0) {
                    x = dragPoint.x;
                    width *= -1;
                }
                if (height < 0) {
                    y = dragPoint.y;
                    height *= -1;
                }
                selectionPane.setBounds(x, y, width, height);
                selectionPane.revalidate();
                repaint();
            }

        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(background, 0, 0, this);
        g2d.dispose();
    }

}
