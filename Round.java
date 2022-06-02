import java.util.*;

public class Round implements Runnable{
    private int playerId;
    private ArrayList<Integer> handCard = new ArrayList<Integer>();
    private int roundState=1;
    private RunGameCard originCard;
    private RunPlayer runPlayer;
    public Round(RunPlayer runPlayer,int roundState,RunGameCard originCard){
        super ();
        this.playerId = runPlayer.getPlayerId();
        this.runPlayer = runPlayer;
        this.roundState = roundState;
        this.originCard = originCard;
        this.roundState = 1;
        this.handCard = runPlayer.getHandCard();
        ArrayList<Integer> temp=runPlayer.getHandCard();
        temp.addAll(originCard.drawCard(2));
        this.runPlayer.setHandCard(temp);        
    }
    public void run(){
        while(roundState!=0){
            
        }
    }
    public int getRoundState(){
        return this.roundState;
    }
    public RunPlayer getPlayer(){
        return this.runPlayer;
    }
}
