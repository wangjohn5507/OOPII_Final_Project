import java.awt.*;
import javax.swing.*;
public class UpdateStatus extends JPanel {
    private JLabel textLabel;
    // private int playerId;
    private String text ="自己的回合";
    private Font font = new Font("Dialog", Font.PLAIN, 16);
   
    public UpdateStatus(){
        
        setBackground(new Color(197,224,180));
        this.text = getStatus();
        textLabel = new JLabel(this.text,JLabel.CENTER);
        add(textLabel);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        textLabel.setText(this.text);
        textLabel.setFont(font);
    }
    public String getStatus(){
        return this.text;
    }
    public void setStatus(String s){
        this.text=s;
    }
}
