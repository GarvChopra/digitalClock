
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Mywindow extends JFrame {

    private JLabel heading;
    private JLabel timeLabel;
    private JLabel dateLabel;

    private Font timeFont = new Font("Segoe UI", Font.BOLD, 32);
    private Font dateFont = new Font("Segoe UI", Font.PLAIN, 16);
    private Font headingFont = new Font("Segoe UI", Font.BOLD, 18);

    Mywindow() {
        setTitle("My Clock");
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Transparent circular window
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        ((JComponent) getContentPane()).setOpaque(false);

        // Drag feature
        MouseAdapter ma = new MouseAdapter() {
            int x, y;

            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);

        // Circular shape
        setShape(new Ellipse2D.Double(0, 0, 400, 400));

        createUI();
        startClock();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUI() {
        setLayout(new GridLayout(3, 1));

        heading = new JLabel("My Clock", SwingConstants.CENTER);
        timeLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel = new JLabel("", SwingConstants.CENTER);

        heading.setFont(headingFont);
        timeLabel.setFont(timeFont);
        dateLabel.setFont(dateFont);

        heading.setForeground(Color.WHITE);
        timeLabel.setForeground(new Color(0, 255, 255)); // neon cyan
        dateLabel.setForeground(new Color(180, 180, 180));

        add(heading);
        add(timeLabel);
        add(dateLabel);
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            Date now = new Date();

            String time = new SimpleDateFormat("HH:mm:ss").format(now);
            String date = new SimpleDateFormat("EEE, dd MMM yyyy").format(now);

            timeLabel.setText(time);
            dateLabel.setText(date);
        });

        timer.start();
    }
}