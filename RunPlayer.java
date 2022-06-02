import java.util.*;
public class RunPlayer{
    private int blood=4;
    private int lastblood=4;
    private int PLAYERID;
    private int ROLE;//0,2,4 good  1,3,5 bad
    private int CHARACTER;
    private ArrayList<Integer> handCard = new ArrayList<Integer>();
    private Thread playerThread;
    private int Covid=0;
    private int Shield=0;
    public RunPlayer(int playerId ,int role,int character){
        this.ROLE=role;
        this.CHARACTER=character;
        this.PLAYERID=playerId;
        
    }
    
    public int getPlayerId(){
        return this.PLAYERID;
    }
    public int getRole(){
        return this.ROLE;
    }
    public void setRole(int role){
        this.ROLE=role;
    }
    public int getCharacter(){
        return this.CHARACTER; 
    }
    public void setCharacter(int character){
        this.CHARACTER=character;
    }
    public int getBlood(){
        return this.blood;
    }
    public void setBlood(int b){
        this.blood=b;
    }
    public ArrayList<Integer> getHandCard(){
        return this.handCard;
    }
    public void setHandCard(ArrayList<Integer> handcard){
        this.handCard=handcard;
    }
    public int HandCardNum(){
        return this.handCard.size();
    }
    public int getCovid(){
        return this.Covid;
    }
    public void setCovid(int i){
        this.Covid=i;
    }
    public int getShield(){
        return this.Shield;
    }
    public void setShield(int i){
        this.Shield=i;
    }
    //test
    public static void main(String[] args){
       Primary primary = new Primary();
       Player player1 = primary.getPlayer(1);
       System.out.println(primary.getHandCard(1));
    }
}
