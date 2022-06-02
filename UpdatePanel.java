import java.awt.*;
import javax.swing.*;

public class UpdatePanel extends JPanel{
    private Image cardImg;
    public UpdatePanel(){
        this.cardImg = getCardImg();
        this.setBackground(new Color(19,51,76));
        this.setLayout(new BorderLayout());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(cardImg,0,0,getWidth(),getHeight(),this);
    }
    public void setCardImg(Image card){
        this.cardImg=card;
    }
    public Image getCardImg(){
        return this.cardImg;
    }
}
