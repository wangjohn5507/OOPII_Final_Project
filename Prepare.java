import java.util.*;
public class Prepare{
    private static ArrayList<Integer> role;    
    private static ArrayList<Integer> character;
    private static ArrayList<Integer> randomList = new ArrayList<Integer>();
    private static HashMap<Integer,Integer> originCard = new HashMap<>();
    public Prepare(){
      role  = new ArrayList<Integer>(4);
      character = new ArrayList<Integer>(10);
      shuffleRole();
      shuffleCharacter();
    }
    public void shuffleCard(){
      randomList.clear();
       for(int i=0;i<80;i++){
          if(originCard.get(i)==2){
              randomList.add(i);
          }
      }
      Collections.shuffle(randomList);
  }
    public void originCards(){
      for(int i=0;i<80;i++){
        originCard.put(i,2);
      }
      for(int i=0;i<80;i++){
         if(originCard.get(i)==2){
              randomList.add(i);
         }
    }
      Collections.shuffle(randomList);
    }
    private void shuffleRole(){
      role.add(0);
      role.add(1);
      role.add(2);
      role.add(3);
      Collections.shuffle(role);
    }
    private void shuffleCharacter(){
       for(int i=0;i<10;i++){
           character.add(i);
       }
       Collections.shuffle(character);
    }
    public ArrayList<Integer> getShuffleCharacter(){
      return character;
    }
    public ArrayList<Integer> getShuffleRole(){
      return role;
    }
    public ArrayList<Integer> getShuffleCard(){
      return randomList;
    }
    public HashMap<Integer,Integer> getOriginCard(){
        return originCard;
    }
    public void changeHashmap(int CardId,int state){
      originCard.put(CardId,state);
    }
}
