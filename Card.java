import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// create card button.

public class Card extends JButton{
    // this card name.
    private String cardName;
    // this card id.
    private int cardId;
    // this card path.
    private String cardPath;
    private Font font = new Font("Dialog", Font.PLAIN, 16);
    // initialize the card through the construtor.
    public Card(int cardId){
        super();
        this.setBackground(Color.WHITE);
        this.cardId=cardId;
        this.cardName=getCardName(cardId);
        this.cardPath=getCardPath(cardId);
        Image img=new ImageIcon(cardPath).getImage();
        Image dimg = img.getScaledInstance(90, 120,java.awt.Image.SCALE_SMOOTH);
        ImageIcon cardIcon= new ImageIcon(dimg);
        setIcon(cardIcon);
        setFont(font);
        // set actioncommand of card with card name. 
        this.setActionCommand(cardName);
        // this will change to puta image icon instead setting text.
        addMouseListener(new MouseAdapter(){
            // if user move the mouse into card, he/she can see the card introduction. When the mouse exited the card, he/she will see origin card image.
            @Override
            public void mouseEntered(MouseEvent e){
                super.mouseEntered(e);
                setText(getCardIntroduction(cardName));
                ImageIcon cardIcon= new ImageIcon();
                setIcon(cardIcon);
            }
            @Override
            public void mouseExited(MouseEvent e){
                super.mouseExited(e);
                setIcon(cardIcon);
                setText("");
            }
        });
    }
    // Through the card id, it will return the card name by invoking this method.
    public String getCardName(int cardId){
        String s="";
        switch(cardId) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25:
                s="Fire!";
                break;
            case 26: case 27: case 28: case 29: case 30: case 31: case 32: case 33: case 34: case 35: case 36: case 37: case 38: case 39:
                s="Miss";
                break;
            case 40: case 41:
                s="Financial Crisis";
                break;
            case 42: case 43:
                s="Oil Shocks";
                break;
            case 44: case 45: case 46: case 47:
                s="beibigennosuke";
                break;
            case 48: case 49: case 50: case 51:
                s="awpjopabemarrrr";
                break;
            case 52: case 53:
                s="Tonight I Want Some";
                break;
            case 54:
                s="Please Support Counter";
                break;
            case 55: case 56:
                s="Different Pay For Equal Work";
                break;
            case 57: case 58: case 59: case 60: case 61: case 62:
                s="Gig Economy";
                break;
            case 63:
                s="Bar";
                break;
            case 64: case 65: case 66:
                s="Colosseum";
                break;
            case 67: case 68:
                s="Shield Warrior";
                break;
            case 69: case 70: case 71:
                s="COVID-19";
                break;
            case 72:
                s="Stock Market Crash";
                break;
            case 73:
                s="Lottery Jackpot";
                break;
            case 74: case 75:
                s="Slaughter";
                break;
            case 76: case 77:
                s="Let Bullet Fly";
                break;
            case 78: case 79:
                s="You Know It";
                break;
            default:
                break;
        }
        return(s);
    }
    
    public String getCardColors(int cardId){
        String s="";
        switch(cardId){
            case 0: case 1: case 2: case 3: case 4: case 5: case 26: case 27: case 28: case 29: case 44: case 49: case 51: case 54: case 58: case 64: case 65: case 67: case 72: case 76:
                s="0";
                break;
            case 6: case 7: case 8: case 9: case 10: case 11: case 30: case 31: case 32: case 33: case 40: case 45: case 48: case 53: case 59: case 60: case 66: case 68: case 77: case 79:
                s="1";
                break;
            case 12: case 13: case 14: case 15: case 16: case 17: case 34: case 35: case 36: case 37: case 41: case 43: case 52: case 55: case 56: case 61: case 63: case 70: case 73: case 75:
                s="2";
                break;
            case 18: case 19: case 20: case 21: case 22: case 23: case 24: case 25: case 38: case 39: case 42: case 46: case 47: case 50: case 57: case 62: case 69: case 71: case 74: case 78:
                s="3";
                break;
            default:
                break;
        }
        return(s);
    }

    public String getCardPath(int cardId){
        String s="";
        switch(cardId) {
            case 0: 
                s="fire_0_a.png";
                break;
            case 1: 
                s="fire_0_2.png";
                break;
            case 2: 
                s="fire_0_3.png";
                break;
            case 3: 
                s="fire_0_4.png";
                break;
            case 4: 
                s="fire_0_5.png";
                break;
            case 5: 
                s="fire_0_6.png";
                break;
            case 6: 
                s="fire_1_7.png";
                break;
            case 7: 
                s="fire_1_8.png";
                break;
            case 8: 
                s="fire_1_9.png";
                break;
            case 9: 
                s="fire_1_10.png";
                break;
            case 10: 
                s="fire_1_j.png";
                break;
            case 11: 
                s="fire_1_q.png";
                break;
            case 12: 
                s="fire_2_k.png";
                break;
            case 13: 
                s="fire_2_a.png";
                break;
            case 14: 
                s="fire_2_2.png";
                break;
            case 15: 
                s="fire_2_3.png";
                break;
            case 16: 
                s="fire_2_4.png";
                break;
            case 17: 
                s="fire_2_5.png";
                break;
            case 18: 
                s="fire_3_6.png";
                break;
            case 19: 
                s="fire_3_7.png";
                break;
            case 20: 
                s="fire_3_8.png";
                break;
            case 21: 
                s="fire_3_9.png";
                break;
            case 22: 
                s="fire_3_10.png";
                break;
            case 23: 
                s="fire_3_j.png";
                break;
            case 24: 
                s="fire_3_q.png";
                break;
            case 25:
                s="fire_3_k.png";
                break;
            case 26: 
                s="miss_0_7.png";
                break;
            case 27: 
                s="miss_0_8.png";
                break;
            case 28: 
                s="miss_0_9.png";
                break;
            case 29: 
                s="miss_0_10.png";
                break;
            case 30: 
                s="miss_1_A.png";
                break;
            case 31: 
                s="miss_1_2.png";
                break;
            case 32: 
                s="miss_1_3.png";
                break;
            case 33: 
                s="miss_1_4.png";
                break;
            case 34: 
                s="miss_2_6.png";
                break;
            case 35: 
                s="miss_2_7.png";
                break;
            case 36: 
                s="miss_2_8.png";
                break;
            case 37: 
                s="miss_2_9.png";
                break;
            case 38: 
                s="miss_3_A.png";
                break;
            case 39:
                s="miss_3_2.png";
                break;
            case 40: 
                s="financialcrisis_1_5.png";
                break;
            case 41:
                s="financialcrisis_2_10.png";
                break;
            case 42: 
                s="oilcrisis_3_3.png";
                break;
            case 43:
                s="oilcrisis_2_j.png";
                break;
            case 44: 
                s="beibigennosuke_0_j.png";
                break;
            case 45: 
                s="beibigennosuke_1_k.png";
                break;
            case 46: 
                s="beibigennosuke_3_4.png";
                break;
            case 47:
                s="beibigennosuke_3_5.png";
                break;
            case 48: 
                s="awojopabemarrrr_1_3.png";
                break;
            case 49: 
                s="awojopabemarrrr_0_j.png";
                break;
            case 50: 
                s="awojopabemarrrr_3_2.png";
                break;
            case 51:
                s="awojopabemarrrr_0_a.png";
                break;
            case 52: 
                s="tonightiwantsome_2_a.png";
                break;
            case 53:
                s="tonightiwantsome_1_k.png";
                break;
            case 54:
                s="plzsupportcounter_0_q.png";
                break;
            case 55: 
                s="differentpay_2_3.png";
                break;
            case 56:
                s="differentpay_2_6.png";
                break;
            case 57: 
                s="gigeconomy_3_9.png";
                break;
            case 58: 
                s="gigeconomy_0_6.png";
                break;
            case 59: 
                s="gigeconomy_1_7.png";
                break;
            case 60: 
                s="gigeconomy_1_10.png";
                break;
            case 61: 
                s="gigeconomy_2_5.png";
                break;
            case 62:
                s="gigeconomy_3_8.png";
                break;
            case 63:
                s="bar_2_9.png";
                break;
            case 64: 
                s="colosseum_0_q.png";
                break;
            case 65: 
                s="colosseum_0_k.png";
                break;
            case 66:
                s="colosseum_1_6.png";
                break;
            case 67: 
                s="shieldwarrior_0_k.png";
                break;
            case 68:
                s="shieldwarrior_1_j.png";
                break;
            case 69: 
                s="covid-19_3_10.png";
                break;
            case 70:
                s="covid-19_2_q.png";
                break;
            case 71:
                s="covid-19_3_6.png";
                break;
            case 72:
                s="stockmarketcrash_0_8.png";
                break;
            case 73:
                s="lotteryjackpot_2_k.png";
                break;
            case 74: 
                s="slaughter_3_4.png";
                break;
            case 75:
                s="slaughter_2_q.png";
                break;
            case 76: 
                s="letbulletfly_0_j.png";
                break;
            case 77:
                s="letbulletfly_1_a.png";
                break;
            case 78: 
                s="youknowit_3_5.png";
                break;
            case 79:
                s="youknowit_1_q.png";
                break;
            default:
                break;
        }
        return("Resource/Card/"+s);
    }
    
    public String getCardIntroduction(String cardName){
        String cardIntroduction="";
        switch(cardName) {
            case "Fire!":
                cardIntroduction="<html> 攻擊一名玩家一次<html/>";
                break;
            case "Miss":
                cardIntroduction="<html>抵銷一名玩家的攻擊<html/>";
                break;
            case "Financial Crisis":
                cardIntroduction="<html>其他玩家皆出一張Miss<html/>";
                break;
            case "Oil Shocks":
                cardIntroduction="<html>其他玩家皆出一張Fire!<html/>";
                break;
            case "beibigennosuke":
                cardIntroduction="<html>選一名玩家抽一張其手牌<html/>";
                break;
            case "awpjopabemarrrr":
                cardIntroduction="<html>指定一名玩家棄一張牌<html/>";
                break;
            case "Tonight I Want Some":
                cardIntroduction="<html>自己抽兩張牌<html/>";
                break;
            case "Please Support Counter":
                cardIntroduction="<html>自己抽三張牌<html/>";
                break;
            case "Different Pay For Equal Work":
                cardIntroduction="<html>倖存玩家依序抽取一張牌<html/>";
                break;
            case "Gig Economy":
                cardIntroduction="<html>自己加一滴血<html/>";
                break;
            case "Bar":
                cardIntroduction="<html>所有玩家皆加一滴血<html/>";
                break;
            case "Colosseum":
                cardIntroduction="<html>指定一名玩家輪流出Fire!<html/>";
                break;
            case "Shield Warrior":
                cardIntroduction="<html>抽一張牌判定，若為紅心則等同Miss<html/>";
                break;
            case "COVID-19":
                cardIntroduction="<html>抽一張牌判定，若為紅心，則此回合可以使用牌<html/>";
                break;
            case "Stock Market Crash":
                cardIntroduction="<html>抽一張牌判定，若為黑桃2~9則減三滴血<html/>";
                break;
            case "Lottery Jackpot":
                cardIntroduction="<html>抽一張牌判定，若為紅心2~9則加三滴血<html>";
                break;
            case "Slaughter":
                cardIntroduction="<html>在你的回合可以使用任意張Fire!<html/>";
                break;
            case "Let Bullet Fly":
                cardIntroduction="<html>攻擊距離為2<html/>";
                break;
            case "You Know It":
                cardIntroduction="<html>查看一名指定玩家身分<html/>";
                break;
            default:
                break;
        }
        return cardIntroduction;
    }

    // return this id of card.
    public int getCardId(){
        return this.cardId;
    }
}
