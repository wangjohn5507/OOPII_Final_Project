import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Sdg extends JFrame implements ActionListener,Runnable{
    private JPanel bottomPanel;
    private JPanel buttonPanel;
    private JButton prev;
    private JButton next;
    private JButton go;
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panel;
    private JPanel[] pages = new JPanel[20];
    private int current = 1;

    public Sdg() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1200, 750);
        this.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 1200, 750);

        panel = new JPanel(new CardLayout());
        panel.setBounds(0,0,1200,750);

        bottomPanel=new JPanel(new GridLayout(1,0));
        bottomPanel.setBounds(0,450,1000,150);
        bottomPanel.setBackground(new Color(255,248,229,0));

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255,248,229));
        buttonPanel.setLayout(new GridLayout(1,0));
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0,450,1000,150);
        
        prev = new JButton(new ImageIcon(new ImageIcon("Resource/SDG/prev.png").getImage().getScaledInstance(70, 70,java.awt.Image.SCALE_SMOOTH)));
        prev.addActionListener(this);
        prev.setActionCommand("prev");
        prev.setOpaque(false);
        prev.setContentAreaFilled(false);
        prev.setBorderPainted(false);
        prev.setBounds(50, 600, 70, 70);

        if(getCurrent()==1){
            prev.setVisible(false);
        }else{
            prev.setVisible(true);
        }
        next = new JButton(new ImageIcon(new ImageIcon("Resource/SDG/next.png").getImage().getScaledInstance(70, 70,java.awt.Image.SCALE_SMOOTH)));
        next.addActionListener(this);
        next.setActionCommand("next");
        next.setOpaque(false);
        next.setContentAreaFilled(false);
        next.setBorderPainted(false);
        next.setBounds(1070, 600, 70, 70);

        go = new JButton(new ImageIcon(new ImageIcon("Resource/SDG/go.png").getImage().getScaledInstance(70, 70,java.awt.Image.SCALE_SMOOTH)));
        go.addActionListener(this);
        go.setActionCommand("Go");
        go.setOpaque(false);
        go.setContentAreaFilled(false);
        go.setBorderPainted(false);
        go.setBounds(1070, 600, 70, 70);
        go.setVisible(false);

        if(getCurrent()==20){
            next.setVisible(false);
            go.setVisible(true);
        }else{
            next.setVisible(true);
            go.setVisible(false);
        }
        flipJPanel();
        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(go);
        add(panel);
        add(buttonPanel);
        
        
    }
    public void run(){
        if(getCurrent()==1){
            prev.setVisible(false);
        }
        else{
            prev.setVisible(true);
        }
        if(getCurrent()==14){
            next.setVisible(false);
            go.setVisible(true);
        }
        else{
            next.setVisible(true);
            go.setVisible(false);
        }
        panel.revalidate();
        panel.repaint();
    }
    public void actionPerformed(ActionEvent e){
        String buttonString = e.getActionCommand();
        System.out.println(buttonString);
        CardLayout c = (CardLayout)(panel.getLayout());
        Thread th=new Thread(this);
        th.start();
        // System.out.println(getCurrent());
        if(buttonString.equals("prev")){
            setCurrent(Math.max(1,getCurrent() - 1));
            System.out.println(getCurrent());
            c.show(panel, toString(getCurrent()));
        }
        else if(buttonString.equals("next")){
            setCurrent(getCurrent() + 1);
            System.out.println(getCurrent());
            c.show(panel, toString(getCurrent()));
        }
        else if(buttonString.equals("Go")){
            dispose();
            WhoisDemoFrame gui=new WhoisDemoFrame();
            gui.setVisible(true);
            gui.setResizable(false);
        }
        else{
            System.out.println("Unexpected error.");
        }
        
    }
    public void flipJPanel(){
        for(int i=0;i<14;i++){
            panel.add(content(i),toString(i+1));
        }
    }
    public JPanel content(int i){
        String[] path={"Resource/SDG/0.png","Resource/SDG/1.png","Resource/SDG/2.png","Resource/SDG/3.png","Resource/SDG/4.png","Resource/SDG/5.png","Resource/SDG/6.png","Resource/SDG/7.png","Resource/SDG/8.png","Resource/SDG/9.png","Resource/SDG/10.png","Resource/SDG/11.png","Resource/SDG/12.png","Resource/SDG/13.png"};
        JPanel page=new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(new ImageIcon(path[i]).getImage(),0,0,1200,750,this);
            }
        };
        page.setBounds(0, 0, 1200, 750);
        return page;
    }
    public int getCurrent(){
        return this.current;
    }
    public void setCurrent(int now){
        this.current=now;
    }
    public String toString(int num){
        String s="";
        s=String.format("%d", num);
        return s;
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Sdg animate=new Sdg();
                // game.pack();
                animate.setVisible(true);
            }
        });
    }
}
