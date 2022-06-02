import java.awt.*;
import javax.swing.*;

public class UpdateHandCard extends JPanel{
    
    // private JPanel bloodPanel;
    private JLabel cardNumLabel;
    // private int playerId;
    private int cardNum=3;
    private Font font = new Font("Dialog", Font.PLAIN, 16);
    public UpdateHandCard(){
        this.cardNum = getCardNum();
        cardNumLabel = new JLabel("手牌 : "+String.format("%d",this.cardNum),JLabel.CENTER);
        cardNumLabel.setBackground(new Color(151,142,67));
        add(cardNumLabel);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        cardNumLabel.setText("手牌 : "+this.cardNum);
        cardNumLabel.setFont(font);
        cardNumLabel.setBackground(new Color(151,142,67));
        
    }
    public int getCardNum(){
        return this.cardNum;
    }
    public void setCardNum(int num){
        this.cardNum=num;
    }
}

