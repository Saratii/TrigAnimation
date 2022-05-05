import java.awt.Color;
import java.awt.EventQueue;
import java.awt.*;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Main extends JPanel {
    static Dimension screenSize = null;
    static float centerX, centerY;
    static List<Arc> arcies;
    private static final long serialVersionUID = 1L;
    private static int i = 0;

    public Main() {
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        try{
            for(Arc arc : arcies){
                arc.draw(g2);
            }
            g2.setColor(Color.red);
            g2.fillOval((int)centerX-100, (int)centerY-100, 200, 200);
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }   
    } 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new Main());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                centerX = screenSize.width/2;
                centerY = screenSize.height/2;
                frame.setSize(screenSize.width, screenSize.height);

                arcies = new ArrayList<>();
                arcies.add(new Arc(centerX, centerY, Color.orange, 1f, (float)Math.PI*5/6, 150f, 0f, 2*(float)Math.PI/3));
                arcies.add(new Arc(centerX, centerY, Color.blue, 1f, (float)Math.PI*5/6+2*(float)Math.PI/3, 150f, 0f, 2*(float)Math.PI/3));
                arcies.add(new Arc(centerX, centerY, Color.green, 1f, (float)Math.PI*5/6+4*(float)Math.PI/3, 150f, 0f, 2*(float)Math.PI/3));
                Timer timer = new Timer(5, new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        i++;
                        for(Arc arc : arcies){
                            arc.moob(i);
                        }
                        frame.repaint();
                    }
                    
                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                frame.repaint();
                frame.setVisible(true);
                timer.start();
            }
        });
    }
} 