import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class DrawRulePanel extends JPanel {

    private JPanel bottomPanel;
    private Image rule;

    public DrawRulePanel(){
        rule = new ImageIcon("Resource/Rule/rule.jpg").getImage();
        this.setBackground(new Color(19,51,76));
        this.setLayout(new BorderLayout());

        bottomPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(rule,0,0,getWidth(),getHeight(),this);
            }
        };
        bottomPanel.setPreferredSize(new Dimension(rule.getWidth(null), rule.getHeight(null)));
        this.add(bottomPanel,BorderLayout.CENTER);
    }
   
}
