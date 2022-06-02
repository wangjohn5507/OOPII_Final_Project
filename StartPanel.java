import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class StartPanel extends JFrame {
    
    private JButton ruleButton;
    private JButton startButton;

    private DeskPanel desk;

    private Sdg gui;

    public StartPanel(){

        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLayout(new BorderLayout());
        JPanel button =new JPanel(new GridLayout(0,1,10,10));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(button, BorderLayout.LINE_END);

        bottomPanel.setOpaque(false);
        bottomPanel.setFocusable(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,50,10));

        button.setOpaque(false);
        button.setFocusable(false);
        ruleButton=new JButton(new ImageIcon("Resource/rule.png"));
        ruleButton.setOpaque(false);
        ruleButton.setContentAreaFilled(false);
        ruleButton.setBorderPainted(false);
        
        startButton = new JButton(new ImageIcon("Resource/startbutton.png"));
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        ruleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                Rule gui2=new Rule();
                gui2.setVisible(true);
            }
        });
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                gui=new Sdg();
                gui.setVisible(true);
                gui.setResizable(false);
            }
        });
        
        desk=new DeskPanel();
        desk.setLayout(new BorderLayout());
        desk.add(bottomPanel, BorderLayout.PAGE_END);
        add(desk,BorderLayout.CENTER);
        button.add(ruleButton);
        button.add(startButton);
    }

    public class DeskPanel extends JPanel{
        private Image deskTop = new ImageIcon("Resource/desktop.png").getImage();
        public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(deskTop,0,0,getWidth(),getHeight(),this);
        }
    }

    public JFrame getFrame(){
        return gui;
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StartPanel game=new StartPanel();
                // game.pack();
                game.setVisible(true);
            }
        });
    }
}
