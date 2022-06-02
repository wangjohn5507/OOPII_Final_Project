import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class Computer{
    private JFrame f;
    private Player User;
    private Player playerUserone;
    private Player playerUsertwo;
    private Player playerUserThree;
    private ArrayList<Integer> handcard;
    private Card card;
    private int pos=-1;
    private int posself=-1;
    private RunGameCard rungamecard=new RunGameCard();
    private Prepare start= new Prepare();
    public Computer(Player User,Player playerUserone,Player playerUsertwo,Player playerUserthree){
        this.User=User;
        this.handcard=User.getRunPlayer().getHandCard();
        User.setCardNum(handcard.size());
        this.playerUserone=playerUserone;
        this.playerUsertwo=playerUsertwo;
        this.playerUserThree=playerUserthree;
    }

    public void fold(){
        int size=handcard.size();
        int blood = User.getRunPlayer().getBlood();
        if(size>blood){
            for(int i=0;i<size-blood;i++){
                handcard.remove(handcard.get(0));
                start.changeHashmap(handcard.get(0), 2);
            }
        }
    }
    public void useCard(Player chosen){
        int useFire=0;
        for(int i=0;i<handcard.size();i++){
            card=new Card(handcard.get(i));
            String cardName=card.getCardName(handcard.get(i));
            if(cardName=="Fire!"&&useFire==0){
                int miss=0;
                pos=handcard.get(i);
                if(chosen.getPlayerId()==0){
                    
                    JOptionPane.showMessageDialog(f, "你被電腦"+User.getPlayerId()+"攻擊了", "被攻擊", JOptionPane.WARNING_MESSAGE);

                    if(chosen.getRunPlayer().getShield()!=0){
                        JOptionPane.showMessageDialog(f, "Shield生效了，將會進行判定是否能擋下攻擊", "Shield生效", JOptionPane.WARNING_MESSAGE);
                        ArrayList<Integer> chancecardList=rungamecard.drawCard(1);
                        Card chancecard=new Card(chancecardList.get(0));
                        int chancecardId=chancecardList.get(0);
                        if(chancecard.getCardColors(chancecardId)=="1"){
                            JOptionPane.showMessageDialog(f, "判斷為愛心因此Shield生效，玩家免於攻擊", "Shield生效", JOptionPane.WARNING_MESSAGE);
                            chosen.getRunPlayer().setShield(0);
                            start.changeHashmap(chancecardId, 2);
                            start.shuffleCard();
                            rungamecard.use(User.getRunPlayer(), chosen, handcard.get(i), cardName, 2);
                            break;
                        }
                        else{
                            JOptionPane.showMessageDialog(f, "判斷不為愛心因此Shield失效", "Shield失效", JOptionPane.WARNING_MESSAGE);
                            chosen.getRunPlayer().setShield(0);
                            start.changeHashmap(chancecardId, 2);
                            start.shuffleCard();
                        }
                    }

                    ArrayList<Integer> myHandcard=chosen.getRunPlayer().getHandCard();
                    for(int j=0;j<myHandcard.size();j++){
                        Card mycard=new Card(myHandcard.get(j));
                        int mycardId = mycard.getCardId();
                        posself=mycardId;
                        String mycardName=mycard.getCardName(mycardId);
                        if(mycardName=="Miss"){
                            int answer=JOptionPane.showConfirmDialog(f,"你有Miss要使用嗎?","Miss?",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                            if(answer==0){
                                miss=1;
                                rungamecard.use(User.getRunPlayer(), chosen, mycardId, mycardName, 0);
                                break;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    if(miss==1){
                        rungamecard.use(User.getRunPlayer(), chosen, handcard.get(i), cardName, 2);
                    }
                    else{
                        rungamecard.use(User.getRunPlayer(), chosen, handcard.get(i), cardName, 1);
                    }
                }else{
                    rungamecard.use(User.getRunPlayer(), chosen, handcard.get(i), cardName, 1);
                    System.out.println("Cpu use fire!");
                }
                
                useFire++;
                System.out.println("useFire?"+useFire);
            
            }
        }
        doNothing(1000);
    }
    public Player shuffle(){
        ArrayList<Integer>alive = new ArrayList<Integer>();
        if(playerUserone.getRunPlayer().getBlood()>0){
            alive.add(0);
        }
        if(playerUsertwo.getRunPlayer().getBlood()>0){
            alive.add(1);
        }
        if(playerUserThree.getRunPlayer().getBlood()>0){
            alive.add(2);
        }
        Collections.shuffle(alive);
        if(alive.get(0)==0)return playerUserone;
        else if(alive.get(0)==1)return playerUsertwo;
        else return playerUserThree;
    }
    public Image getCardImg(){
        int cardId=pos;
        Card tmp=new Card(cardId);
        Image img = new ImageIcon(tmp.getCardPath(cardId)).getImage();
        return img;
    }
    public int getCardId(){
        return pos;
    }
    public Image getSelfCardImg(){
        int cardId=posself;
        Card tmp=new Card(cardId);
        Image img = new ImageIcon(tmp.getCardPath(cardId)).getImage();
        return img;
    }
    public int getSelfCardId(){
        return posself;
    }
    private static void doNothing(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Unexpected interruption");
            System.exit(0);
        }
    }
}
