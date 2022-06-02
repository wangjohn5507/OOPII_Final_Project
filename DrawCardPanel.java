import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class DrawCardPanel extends JPanel{
    private JPanel bottomPanel;
    private Image card;

    public DrawCardPanel(){
        card = new ImageIcon("Resource/Rule/card.jpg").getImage();
        this.setBackground(new Color(19,51,76));
        this.setLayout(new BorderLayout());

        bottomPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(card,0,0,getWidth(),getHeight(),this);
            }
        };
        bottomPanel.setPreferredSize(new Dimension(card.getWidth(null), card.getHeight(null)));
        this.add(bottomPanel,BorderLayout.CENTER);

        
    }
}
