import javax.swing.JPanel;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;

public class DrawCharacterPanel extends JPanel{

    private JPanel bottomPanel;
    private Image character;

    public DrawCharacterPanel(){
        character = new ImageIcon("Resource/Rule/character.jpg").getImage();
        this.setBackground(new Color(19,51,76));
        this.setLayout(new BorderLayout());

        bottomPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(character,0,0,getWidth(),getHeight(),this);
            }
        };
        bottomPanel.setPreferredSize(new Dimension(character.getWidth(null), character.getHeight(null)));
        this.add(bottomPanel,BorderLayout.CENTER);

        
    }
    
}
