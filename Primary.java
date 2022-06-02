import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Primary implements Runnable{
    RunPlayer[] Runplayer;
    Player[] player= new Player[4];
    ArrayList<Integer[]> side=new ArrayList<>();
    private static ArrayList<Integer> randomList = new ArrayList<Integer>();
    private static HashMap<Integer,Integer> originCard = new HashMap<>();
    private JFrame f;

    public Primary(){
        Prepare start = new Prepare();
        ArrayList<Integer> character=start.getShuffleCharacter();
        ArrayList<Integer> role=start.getShuffleRole();
        start.originCards();
        this.randomList=start.getShuffleCard();
        this.originCard=start.getOriginCard();
        System.out.println("RandomList in primary:"+randomList);
        
        String s1="";
        String s2="";
        for(int i=0;i<4;i++){
            if(role.get(i)%2==0){
                s1+=i;
            }
            else{
                s2+=i;
            }
        }    
            Integer[] same={Integer.parseInt(s1.substring(0,1)),Integer.parseInt(s1.substring(1,2))};
            side.add(same);
            Integer[] same2={Integer.parseInt(s2.substring(0,1)),Integer.parseInt(s2.substring(1,2))};
            side.add(same2);
            System.out.println(side.get(0)[0]+" "+side.get(0)[1]);
        
        RunGameCard origin = new RunGameCard();
               Runplayer=new RunPlayer[4];
        // origin
        for(int i=0; i<4;i++){
            player[i]=new Player(i,role.get(i),character.get(i));
            
            Runplayer[i] = player[i].getRunPlayer();
            ArrayList<Integer> playerCard = new ArrayList<Integer>();
            
            playerCard.addAll(origin.drawCard(3));
            Runplayer[i].setHandCard(playerCard);
            System.out.println(i);
            System.out.println(Runplayer[i].getHandCard());
        }
    }
    
    public void run(){
        
        while(true){  // when game didn't end
            System.out.print("");

            if(Runplayer[side.get(0)[0]].getBlood()+Runplayer[side.get(0)[1]].getBlood()==0 ){
                System.out.println(side.get(1)[0]+" "+side.get(1)[1]+"WIN");
                int i = JOptionPane.showConfirmDialog(f,player[side.get(1)[0]].getIdentify()+" "+player[side.get(1)[1]].getIdentify()+"WIN","遊戲結束",
                                  JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(i==0){
                    System.exit(0);
                }
            }else if(Runplayer[side.get(1)[0]].getBlood()+Runplayer[side.get(1)[1]].getBlood()==0){
                System.out.println(side.get(0)[0]+" "+side.get(0)[1]+"WIN");
                int i = JOptionPane.showConfirmDialog(f,player[side.get(0)[0]].getIdentify()+" "+player[side.get(0)[1]].getIdentify()+"WIN","遊戲結束",
                                  JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(i==0){
                    System.exit(0);
                }
            }
        }

    }

    public ArrayList<Integer> getHandCard(int i){
        return this.Runplayer[i].getHandCard();
    }
    public Player getPlayer(int i){
        return this.player[i];
    }
}
