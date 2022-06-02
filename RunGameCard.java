import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

// Reset originCard(key,2)->(key,0)
public class RunGameCard {
    // cardNum,cardStatus 0->origin,1->handcard,2->used
    Prepare start = new Prepare();
    private JFrame f;
    private HashMap<Integer,Integer> originCard = start.getOriginCard();
    private ArrayList<Integer> randomList=start.getShuffleCard();
    private static int location=0;
    private static int useState=0;
    public RunGameCard(){
    }
    
    public ArrayList<Integer> drawCard(int number){
        ArrayList<Integer> cardList = new ArrayList<>();

        for(int i=0;i<number;i++){
            cardList.add(randomList.get(location));
            start.changeHashmap(randomList.get(location),1);
            location++;
            if(location>40){
                location=0;
            }
        }
        return cardList;
    }
    public void fold(ArrayList<Integer> fold,RunPlayer User){
        ArrayList<Integer> handcard = User.getHandCard();
        for(int i = 0;i<fold.size();i++){
            int cardId=fold.get(i);
            handcard.remove(Integer.valueOf(cardId));
            User.setHandCard(handcard);
            start.changeHashmap(cardId, 2);
        }
    }
    public int use(RunPlayer User,Player chooseplayer,Integer cardId,String cardName,int useState){
        switch(cardName) {
            case "Fire!"://good
                //usestate->
                if(useState==0){
                    int roundState=2;

                    System.out.println("Use Fire!");
                    System.out.println("Please choose the player you want to fire!");
                    return roundState;
                }else if(useState==1){
                    int roundState=1;
                    RunPlayer test=chooseplayer.getRunPlayer();
                    if(test.getBlood()>0){
                        test.setBlood(test.getBlood()-1);
                        if(test.getBlood()==0){
                            JOptionPane.showMessageDialog(f, "玩家"+chooseplayer.getPlayerId()+"已被玩家"+User.getPlayerId()+"攻擊死亡，他的身分為"+chooseplayer.getIdentify(), "有玩家死亡", JOptionPane.WARNING_MESSAGE);
                        }
                        ArrayList<Integer> handcard = User.getHandCard();
                        System.out.println("old handcard"+handcard);
                        handcard.remove(Integer.valueOf(cardId));
                        User.setHandCard(handcard);
                        System.out.println("new handcard"+User.getHandCard());
                        start.changeHashmap(cardId, 2);
                        start.shuffleCard();
                        return roundState;
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "目標已經死亡囉", "無效", JOptionPane.WARNING_MESSAGE);
                        return roundState;
                    }
                    
                }else if(useState==2){
                    int roundState=0;
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                //return 1;
                break;
            case "Miss"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Miss");
                    ArrayList<Integer> handcard = chooseplayer.getRunPlayer().getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    chooseplayer.getRunPlayer().setHandCard(handcard);
                    System.out.println("new handcard"+chooseplayer.getRunPlayer().getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Financial Crisis"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Financial Crisis");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Oil Shocks"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Oil Shocks");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "beibigennosuke"://good
                if(useState==0){
                    int roundState=2;
                    System.out.println("Use beibigennosuke");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    return roundState;
                }else if(useState==1){
                    int roundState=1;
                    RunPlayer test=chooseplayer.getRunPlayer();
                    if(test.getBlood()>0){
                        ArrayList<Integer> handcard = User.getHandCard();
                        System.out.println("old handcard"+User.getHandCard());
                        handcard.remove(Integer.valueOf(cardId));
                        User.setHandCard(handcard);
                        ArrayList<Integer> handcard2 = test.getHandCard();
                        if(handcard2.size()!=0){
                            System.out.println("choose player old handcard"+handcard2);
                            int removecardId = handcard2.get(0);
                            handcard2.remove(0);
                            test.setHandCard(handcard2);
                            handcard.add(removecardId);
                            User.setHandCard(handcard);
                            System.out.println("choose player new handcard"+handcard2);
                        }
                        System.out.println("new handcard"+User.getHandCard());
                        start.changeHashmap(cardId,2);
                        start.shuffleCard();
                        return roundState;
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "目標已經死亡囉", "無效", JOptionPane.WARNING_MESSAGE);
                        return roundState;
                    }
                }
                break;
            case "awpjopabemarrrr"://good
                if(useState==0){
                    int roundState=2;
                    System.out.println("Use awpjopabemarrrr");
                    return roundState;
                }else if(useState==1){
                    int roundState=1;
                    RunPlayer test=chooseplayer.getRunPlayer();
                    if(test.getBlood()>0){
                        ArrayList<Integer> handcard = User.getHandCard();
                        System.out.println("old handcard"+User.getHandCard());
                        handcard.remove(Integer.valueOf(cardId));
                        User.setHandCard(handcard);
                        ArrayList<Integer> handcard2 = test.getHandCard();
                        if(handcard2.size()!=0){
                            System.out.println("choose player old handcard"+handcard2);
                            int removecardId = handcard2.get(0);
                            handcard2.remove(0);
                            test.setHandCard(handcard2);
                            start.changeHashmap(removecardId, 2);
                            System.out.println("choose player new handcard"+handcard2);
                        }
                        
                        System.out.println("new handcard"+User.getHandCard());
                        start.changeHashmap(cardId,2);
                        start.shuffleCard();
                        return roundState;
                    }else{
                        JOptionPane.showMessageDialog(f, "目標已經死亡囉", "無效", JOptionPane.WARNING_MESSAGE);
                        return roundState;
                    }
                    
                }
                break;
            case "Tonight I Want Some"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Tonight I Want Some");
                    // System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    handcard.addAll(this.drawCard(2));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Please Support Counter"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Please Support Counter");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    handcard.addAll(this.drawCard(3));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Different Pay For Equal Work"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Different Pay For Equal Work");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    handcard.addAll(this.drawCard(1));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Gig Economy"://good
                if(useState==0){
                    System.out.println("Use Gig Economy");
                    if(User.getBlood()<4){
                        User.setBlood(User.getBlood()+1);
                        System.out.println("Now your blood:"+User.getBlood());
                    }else{
                        System.out.println("U have 4 blood already, but u have used the card haha.");
                    }
                    int roundState=1;
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    System.out.println("new handcard"+handcard);
                    return roundState;
                }
                break;
            case "Bar"://good
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Bar");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Colosseum"://不要now 
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Colosseeum");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Shield Warrior"://不要now
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Shield Warrior");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    User.setShield(1);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
                
            case "COVID-19"://不要now
                if(useState==0){
                    int roundState=2;
                    System.out.println("Use Covid");
                    return roundState;
                }else if(useState==1){
                    int roundState=1;
                    RunPlayer test=chooseplayer.getRunPlayer();
                    if(test.getBlood()>0){
                        ArrayList<Integer> handcard = User.getHandCard();
                        System.out.println("old handcard"+User.getHandCard());
                        handcard.remove(Integer.valueOf(cardId));
                        User.setHandCard(handcard);
                        chooseplayer.getRunPlayer().setCovid(1);
                        System.out.println("new handcard"+User.getHandCard());
                        start.changeHashmap(cardId,2);
                        start.shuffleCard();
                        return roundState;
                    }else{
                        JOptionPane.showMessageDialog(f, "目標已經死亡囉", "無效", JOptionPane.WARNING_MESSAGE);
                        return roundState;
                    }
                    
                }
                break;
            case "Stock Market Crash": // special1
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Stock Market Crash");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Lottery Jackpot": // speicial2
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Lottery Jackpot");
                    // System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Slaughter": // equipment1
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Slaughter");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "Let Bullet Fly": // equipemt2
                if(useState==0){
                    int roundState=1;
                    System.out.println("Use Let Bullet Fly");
                    System.out.println("U CAN'T USE IT QAQ !!");
                    ArrayList<Integer> handcard = User.getHandCard();
                    System.out.println("old handcard"+handcard);
                    handcard.remove(Integer.valueOf(cardId));
                    User.setHandCard(handcard);
                    System.out.println("new handcard"+User.getHandCard());
                    start.changeHashmap(cardId, 2);
                    start.shuffleCard();
                    return roundState;
                }
                break;
            case "You Know It"://good
                if(useState==0){
                    int roundState=2;
                    System.out.println("Use You Know It");
                    return roundState;
                }else if(useState==1){
                    int roundState=1;
                    RunPlayer test=chooseplayer.getRunPlayer();
                    if(test.getBlood()>0){
                        ArrayList<Integer> handcard = User.getHandCard();
                        System.out.println("old handcard"+User.getHandCard());
                        handcard.remove(Integer.valueOf(cardId));
                        User.setHandCard(handcard);
                        System.out.println("new handcard"+User.getHandCard());
                        String role=chooseplayer.getIdentify();
                        JOptionPane.showMessageDialog(f, "玩家"+chooseplayer.getPlayerId()+"的身分為"+role, "你知道的",JOptionPane.WARNING_MESSAGE);
                        start.changeHashmap(cardId, 2);
                        start.shuffleCard();
                        return roundState;
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "目標已經死亡囉", "無效", JOptionPane.WARNING_MESSAGE);
                        return roundState;
                    }
                }
                break;
        }
        return 0;
    }
}
