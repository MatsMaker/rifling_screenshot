package rifling_screenshot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;


public class SelectionPane extends JPanel {
	private JButton buttonClose;
    private JButton buttonRifling;
    private JLabel label;

    public SelectionPane(BufferedImage screenshot) {
    	buttonClose = new JButton("Close");
        buttonRifling = new JButton("Rifling");
        setOpaque(false);

        label = new JLabel("Rectangle");
        label.setOpaque(true);
        label.setBorder(new EmptyBorder(4, 4, 4, 4));
        label.setBackground(Color.GRAY);
        label.setForeground(Color.WHITE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        gbc.gridy++;
        add(buttonRifling, gbc);
        
        gbc.gridy++;
        add(buttonClose, gbc);

        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(SelectionPane.this).dispose();
            }
        });
        
        buttonRifling.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Rifling rifling = new Rifling(screenshot, getX(), getY(), getWidth(), getHeight());
            	boolean result = rifling.saveTenderloinImage();
            	if(result){
            		label.setText("Images had saved");
            	}
            	else{
            		label.setText("Error to saving image");
            	}
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                label.setText("Rectangle " + getX() + "x" + getY() + "x" + getWidth() + "x" + getHeight());
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(128, 128, 128, 64));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        float dash1[] = {10.0f};
        BasicStroke dashed =
                        new BasicStroke(3.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(dashed);
        g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
        g2d.dispose();
    }

}