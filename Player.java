import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.String;
import java.util.*;
public class Player extends JButton implements ActionListener{
    /* the setting of panel.
    Player
    ---------------
    |  introself |
    ---------------

    introself           downself            downupself
    --------------      --------------      -------------------
    |   upself   |      | downupself |      | attack | defend |
    --------------      --------------      -------------------
    | middleself |      |   handed   |
    --------------      --------------
    |   downself |
    --------------

    upself            middleself
    -------------     -------------
    |  location |     | character |
    -------------     ------------- 
    |  identify |     
    -------------           

    */

    private JPanel introself;
    private JPanel upself;
    private JPanel middleself;
    private JPanel downself;
    private JPanel downupself;

    private JLabel location;
    private JLabel identify;
    private JPanel character;
    private JLabel attack;
    private JLabel defend;
    // private JLabel handed;
    private Font font = new Font("Dialog", Font.PLAIN, 16);

    private RunPlayer ownPlayer;
    private String role;
    private int ch;
    private ArrayList<Integer> handCard;
    private int blood;
    private int playerid;
    private int CardNum=3;
    private UpdateHandCard handed;
    private UpdateBlood playerBlood;

    private Color green = new Color(197,224,180);
    private Color rice = new Color(255,245,213);
    public Player(int playerid,int role,int ch){
        super();
        // build a RunPlayer class to get the receive variable.
        this.ownPlayer= new RunPlayer(playerid,role,ch);
        this.ch=ownPlayer.getCharacter();
        this.handCard=ownPlayer.getHandCard();
        this.CardNum=getCardNum();
        this.blood= ownPlayer.getBlood();
        this.playerid=playerid;
        if(playerid==0){
            setBackground(rice);
        }else{
            setBackground(green);
        }
        setOpaque(false);
        setContentAreaFilled(false);
        addActionListener(this);
        setBorderPainted(false);
        setLayout(new BorderLayout());
        // set actioncommand with a String and playerid.
        setActionCommand("player"+playerid);      

        // use BorderLayout with introself.
        introself = new JPanel();
        introself.setLayout(new BorderLayout());
        introself.setOpaque(false);
        if(playerid==0){
            introself.setBackground(rice);
        }else{
            introself.setBackground(green);
        }

        // use GridLayout with upself.
        upself = new JPanel();
        upself.setLayout(new GridLayout(3,1));
        upself.setOpaque(false);
        // upself.setBackground(new Color(151,142,67));
        if(playerid==0){
            upself.setBackground(rice);
        }else{
            upself.setBackground(green);
        }
        // add two label and one panel into upself.
        // display Player's location
        if(playerid==0) location=new JLabel("己方",JLabel.CENTER);
        else location=new JLabel("玩家 "+playerid,JLabel.CENTER);
        location.setFont(font);
        location.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // location.setBackground(new Color(151,142,67));
        if(playerid==0){
            location.setBackground(rice);
        }else{
            location.setBackground(green);
        }
        upself.add(location);

        playerBlood = new UpdateBlood();
        playerBlood.setOpaque(false);
        // playerBlood.setBackground(new Color(151,142,67));
        if(playerid==0){
            playerBlood.setBackground(rice);
        }else{
            playerBlood.setBackground(green);
        }
        upself.add(playerBlood);
        // display Player's role. If this Player not myself, do not display their role.
        if(playerid!=0){
            this.role="???";
        }else{
            this.role=getIdentifyName(ownPlayer.getRole());
        }
        // may we need to get the role name from its id, and display it instead of role id.
        identify=new JLabel("身分 : "+this.role,JLabel.CENTER);
        identify.setFont(font);
        if(playerid==0){
            identify.setBackground(rice);
        }else{
            identify.setBackground(green);
        }
        upself.add(identify);

        // add upself into introself.
        introself.add(upself,BorderLayout.NORTH);

        // use BorderLayout with middleself.
        middleself = new JPanel();
        if(playerid==0){
            middleself.setBackground(rice);
        }else{
            middleself.setBackground(green);
        }
        middleself.setLayout(new BorderLayout());
        middleself.setOpaque(false);
        // add character into middleself, and add middleself into introself.
        // get character file name from character id, and build a image with it.
        Image img=new ImageIcon(getCharacterFilePath(ch)).getImage();
        // override the pantComponent function of JPanel to let image meet the container size.
        character = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(img,0,0,getWidth(),getHeight(),this);
            }
        };
        character.setOpaque(false);
        middleself.add(character,BorderLayout.CENTER);
        introself.add(middleself,BorderLayout.CENTER);

        // use GridLayout with downself.
        downself = new JPanel();
        // downself.setBackground(new Color(151,142,67));
        if(playerid==0){
            downself.setBackground(rice);
        }else{
            downself.setBackground(green);
        }
        downself.setLayout(new BorderLayout());
        downself.setOpaque(false);

        handed = new UpdateHandCard();
        handed.setOpaque(false);
        if(playerid==0){
            handed.setBackground(rice);
        }else{
            handed.setBackground(green);
        }
        downself.add(handed,BorderLayout.CENTER);
        introself.add(downself,BorderLayout.SOUTH);
        // add introself into Player.
        add(introself,BorderLayout.CENTER);
    }
    // Actually, we are not likely need to implement the actionlistener in this class.
    public void actionPerformed(ActionEvent e)
    {

    }
    // switch the cardId to get its file path.
    public String getCharacterFilePath(int cardId){
        String s="";
        switch(cardId) {
            case 0:
                s="Resource/Character/glassceiling.png";
                break;
            case 1:
                s="Resource/Character/quantumentanglement.png";
                break;
            case 2:
                s="Resource/Character/masterofcardplayer.png";
                break;
            case 3:
                s="Resource/Character/kingpeterofseadragon.png";
                break;
            case 4:
                s="Resource/Character/wangjohn.png";
                break;
            case 5:
                s="Resource/Character/slacker.png";
                break;
            case 6:
                s="Resource/Character/rankssmusketeer.png";
                break;
            case 7:
                s="Resource/Character/slashyouth.png";
                break;
            case 8:
                s="Resource/Character/demoncatman.png";
                break;
            case 9:
                s="Resource/Character/romanticduke.png";
                break;
            default:
                break;
        }
        return(s);
    }
    // switch the cardId to get its file path.
    public String getCharacterName(int cardId){
        String s="";
        switch(cardId) {
            case 0:
                s="Glassceiling";
                break;
            case 1:
                s="Quantumentanglement";
                break;
            case 2:
                s="Masterofcardplayer";
                break;
            case 3:
                s="Kingpeterofseadragon";
                break;
            case 4:
                s="Wangjohn";
                break;
            case 5:
                s="Slacker";
                break;
            case 6:
                s="Rankssmusketeer";
                break;
            case 7:
                s="Slashyouth";
                break;
            case 8:
                s="Demoncatman";
                break;
            case 9:
                s="Romanticduke";
                break;
            default:
                break;
        }
        return(s);
    }
    public String getIdentifyName(int cardId){
        String s="";
        switch(cardId) {
            case 0:
                s="實習生";
                break;
            case 1:
                s="合夥人";
                break;
            case 2:
                s="社畜";
                break;
            case 3:
                s="慣老闆";
                break;
            default:
                break;
        }
        return(s);
    }
    // to get RunPlayer of this Player.
    public RunPlayer getRunPlayer(){
        return this.ownPlayer;
    }
    // to get handCard of this Player.
    public ArrayList<Integer> getHandCard(){
        return this.handCard;
    }
    // to get playerid of this Player.
    public int getPlayerId (){
        return this.playerid;
    }
    public int getCharacterId(){
        return this.ch;
    }
    public String getIdentify(){
        return getIdentifyName(ownPlayer.getRole());
    }
    public JLabel getIdentifyJLabel(){
        return this.identify;
    }
    public int getCardNum(){
        return this.CardNum;
    }
    public void setCardNum(int num){
        this.CardNum=num;
    }
    public UpdateHandCard getHadedPanel(){
        return this.handed;
    }
    public UpdateBlood getBloodPanel(){
        return this.playerBlood;
    }
}
