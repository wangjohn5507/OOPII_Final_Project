import java.awt.*;
import javax.swing.*;

public class UpdateBlood extends JPanel{

    // private JPanel bloodPanel;
    private JLabel bloodLabel;
    private int playerId;
    private String blood="4";
    private Font font = new Font("Dialog", Font.PLAIN, 16);
    public UpdateBlood(){
        this.blood = getBlood();
        bloodLabel = new JLabel(this.blood,JLabel.CENTER);
        bloodLabel.setBackground(new Color(151,142,67));
        add(bloodLabel);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        bloodLabel.setText("血量 : "+this.blood);
        bloodLabel.setFont(font);
        bloodLabel.setBackground(new Color(151,142,67));
        
    }
    public void setBlood(int blood){
        this.blood=(String.format("%d",blood));
    }
    public String getBlood(){
        return this.blood;
    }
}
