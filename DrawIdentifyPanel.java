import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

public class DrawIdentifyPanel extends JPanel {

    private JPanel bottomPanel;
    private Image id;

    public DrawIdentifyPanel(){
        id = new ImageIcon("Resource/Rule/id.jpg").getImage();
        this.setLayout(new BorderLayout());

        bottomPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(id,0,0,getWidth(),getHeight(),this);
            }
        };
        bottomPanel.setPreferredSize(new Dimension(id.getWidth(null), id.getHeight(null)));
        this.add(bottomPanel,BorderLayout.CENTER);
    }  
}
