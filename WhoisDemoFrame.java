import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.*;

import java.util.*;
// import java.lang.*;
public class WhoisDemoFrame extends JFrame implements ActionListener,Runnable{
    private JFrame f;
    // the width of window.
    public static final int WIDTH = 1200;
    // the height of window.
    public static final int HEIGHT = 750;
    // set the max num of handed card.
    public static final int handcard=14;
    /* the setting of layout.
    frame
    --------------------------
    |       upPanel          |
    --------------------------
    |      downPanel         |
    --------------------------

    upPanel
    -----------------------------------------------------------------------------
    | up_leftPanel | player1Panel | player2Panel | player3Panel | up_rightPanel |
    -----------------------------------------------------------------------------

    up_leftPanel            up_rightPanel               up_right_upPanel
    -------------------        ----------------------      ----------------------
    | up_left_upPanel |        |  up_right_upPanel  |      | special1 |special2 |
    -------------------        ----------------------      ----------------------
    |up_left_downPanel|        | up_right_downPanel |
    -------------------        ----------------------             up_right_downPanel
                                                                  -------------
    up_left_upPanel             player1/2/3Panel                  | equiment1 |
    ----------------            ---------------------             -------------
    | clciked card |            | palyer1/2/3Blood  |             | equiment2 |
    ----------------            ---------------------             -------------
                                | player1/2/3Button |
                                ---------------------
    up_left_downPanel           
    ----------------------------------------------            
    |   yesButton  | cancelButton |   endButton  |
    ----------------------------------------------            
    
    downPanel                           cardPanel           personalPanel
    --------------------------------    -----------------   -----------------
    |   cardPanel  | personalPanel |    | cardButton... |   | player4Button |
    --------------------------------    -----------------   -----------------

    */
    private JPanel upPanel;
    private JPanel up_leftPanel;
    private UpdatePanel up_left_upPanel;
    private JPanel up_left_downPanel;
    private JPanel up_centerPanel;
    
    private JPanel up_rightPanel;
    private UpdateStatus status;
    private UpdateStatus cpu_attacked;
    private JPanel up_right_centerPanel;
    private UpdatePanel cpu_usedPanel;
    // private UpdatePanel cpu_attackedPanel;
    private JPanel up_right_downPanel;

    private JPanel downPanel;
    private JPanel cardPanel;
    private JPanel personalPanel;

    private static int roundState=0;
    private static String cardName = "";
    private static int roundPlayer=1;
    private Thread Run;
    private Round round;
    private Computer computer;
    private Prepare prepare = new Prepare();
    private RunGameCard originCard = new RunGameCard();
    // private ArrayList<Integer> character;
    
    private ArrayList<Integer> playerCard;
    private ArrayList<Integer> fold;
    private ArrayList<Card> foldcard;
    private Card[] cardButton;

    private Player player;
    private static RunPlayer runPlayer;
    private static String actionCommand="";
    private static int cardId=0;

    private JPanel player1Panel;
    private JPanel player2Panel;
    private JPanel player3Panel;

    private Player player0Button;
    private Player player1Button;
    private Player player2Button;
    private Player player3Button;
    
    private static Primary primary;
    private Thread start;
    private static Card tmp;

    private JButton special1;
    private JButton special2;

    private JButton endButton;
    private JButton yesButton;
    private JButton cancelButton;
    public WhoisDemoFrame()
    {
        super ();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        setBackground(new Color(197,224,180));  

        // bulid a primary class  to get the Player character and role.
        primary = new Primary();

        // add primary Runnable to execute the thread, and start the thread.
        start = new Thread(primary);
        start.start();

        add(this.drawUpPanel(),BorderLayout.NORTH);
        add(this.drawDownPanel(),BorderLayout.SOUTH);

    }
    
    private JPanel drawUpPanel(){
        // use BorderLayout with upPanel.
        upPanel = new JPanel();
        upPanel.setBackground(new Color(197,224,180));
        upPanel.setLayout(new GridLayout(1,5,10,10));
        upPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // use BorderLayout with up_leftPanel.
        up_leftPanel = new JPanel();
        up_leftPanel.setBackground(new Color(197,224,180));
        
        up_leftPanel.setLayout(new BorderLayout(10,10));
        // set UpdatePanel to see which Card the user clicked.
        up_left_upPanel=new UpdatePanel();
        up_left_upPanel.setBackground(new Color(197,224,180));
        up_leftPanel.add(up_left_upPanel,BorderLayout.CENTER);

        // use GridLayout with up_leftPanel to put three button.
        up_left_downPanel=new JPanel();
        up_left_downPanel.setBackground(new Color(197,224,180));
        up_left_downPanel.setLayout(new GridLayout(1,3,10,10));

        // add three button into up_left_downPanel.
        yesButton=new JButton();
        yesButton.setIcon(new ImageIcon(new ImageIcon("Resource/yesbutton.png").getImage().getScaledInstance(60, 30,java.awt.Image.SCALE_SMOOTH)));
        yesButton.addActionListener(this);
        yesButton.setActionCommand("Yes");
        yesButton.setOpaque(false);
        yesButton.setContentAreaFilled(false);
        yesButton.setBorderPainted(false);
        up_left_downPanel.add(yesButton);

        cancelButton=new JButton();
        cancelButton.setIcon(new ImageIcon(new ImageIcon("Resource/cancel.png").getImage().getScaledInstance(60, 30,java.awt.Image.SCALE_SMOOTH)));
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");
        cancelButton.setOpaque(false);
        cancelButton.setContentAreaFilled(false);
        cancelButton.setBorderPainted(false);
        up_left_downPanel.add(cancelButton);

        endButton= new JButton(new ImageIcon(new ImageIcon("Resource/end.png").getImage().getScaledInstance(60, 30,java.awt.Image.SCALE_SMOOTH)));
        endButton.addActionListener(this);
        endButton.setActionCommand("end");
        endButton.setOpaque(false);
        endButton.setContentAreaFilled(false);
        endButton.setBorderPainted(false);
        up_left_downPanel.add(endButton);
        up_leftPanel.add(up_left_downPanel,BorderLayout.SOUTH);
        // add up_leftPanel into upPanel.
        upPanel.add(up_leftPanel);

        // use GridLayout with up_centerPanel.
        up_centerPanel = new JPanel();
        up_centerPanel.setBackground(new Color(197,224,180));
        up_centerPanel.setLayout(new GridLayout(1,3,50,10));
        up_centerPanel.setBorder(BorderFactory.createEmptyBorder(20,30,0,30));

        // add three playerButton into upPanel.
        player1Panel = new JPanel(new BorderLayout());
        player1Panel.setBackground(new Color(197,224,180));

        // because in primary Class, we have already shuffle the role & character. thus, we can use it directly.
        player1Button = primary.getPlayer(1);
        // add actionlistener to know which button is clicked.
        player1Button.addActionListener(this);
        // build this to set/get information of Player.
        RunPlayer runplayer1 = player1Button.getRunPlayer();

        // additionally build a JPanel to update Player blood.
        player1Panel.add(player1Button,BorderLayout.CENTER);
        upPanel.add(player1Panel);
        
        player2Panel = new JPanel(new BorderLayout());
        player2Panel.setBackground(new Color(197,224,180));

        player2Button = primary.getPlayer(2);
	    player2Button.addActionListener(this);
        RunPlayer runplayer2 = player2Button.getRunPlayer();

        player2Panel.add(player2Button,BorderLayout.CENTER);
        upPanel.add(player2Panel);


        player3Panel = new JPanel(new BorderLayout());
        player3Panel.setBackground(new Color(197,224,180));

        player3Button = primary.getPlayer(3);
	    player3Button.addActionListener(this);
        RunPlayer runplayer3 = player3Button.getRunPlayer();

        player3Panel.add(player3Button,BorderLayout.CENTER);
        upPanel.add(player3Panel);


        // use GridLayout with up_rightPanel.
        up_rightPanel = new JPanel();
        up_rightPanel.setBackground(new Color(197,224,180));
        up_rightPanel.setLayout(new BorderLayout(0,10));

        // use GridLayout with up_right_upPanel.
        status = new UpdateStatus();
        status.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        up_rightPanel.add(status,BorderLayout.NORTH);
        
        up_right_centerPanel = new JPanel();
        up_right_centerPanel.setBackground(new Color(197,224,180));
        up_right_centerPanel.setLayout(new BorderLayout(0,10));

        cpu_usedPanel = new UpdatePanel();
        cpu_usedPanel.setBackground(new Color(197,224,180));
        cpu_attacked=new UpdateStatus();
        cpu_attacked.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        cpu_attacked.setStatus(" ");
        up_right_centerPanel.add(cpu_attacked,BorderLayout.NORTH);
        up_right_centerPanel.add(cpu_usedPanel,BorderLayout.CENTER);
        up_rightPanel.add(up_right_centerPanel,BorderLayout.CENTER);
        
        up_right_downPanel = new JPanel();
        up_right_downPanel.setBackground(new Color(197,224,180));
        up_right_downPanel.setLayout(new GridLayout(1,2,10,10));
        // I guess the equiment card and special card also need to use another JPanel to repaint if get this kind of card.
        // add two button that is special card into up_right_upPanel.
        special1= new JButton();
        special1.setBackground(new Color(197,224,180));
        special1.setVisible(false);
        special1.setFocusable(false);
        special1.setContentAreaFilled(false);
        special1.setBorderPainted(false);
        up_right_downPanel.add(special1);

        special2=new JButton();
        special2.setBackground(new Color(197,224,180));
        special2.setVisible(false);
        special2.setFocusable(false);
        special2.setOpaque(false);
        special2.setContentAreaFilled(false);
        special2.setBorderPainted(false);
        up_right_downPanel.add(special2);

        // add up_right_upPanel into up_rightPanel.
        up_rightPanel.add(up_right_downPanel,BorderLayout.SOUTH);
        upPanel.add(up_rightPanel);
        
        return upPanel;
    }
    
    private JPanel drawDownPanel(){
        // use BorderLayout with downPanel.
        downPanel= new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
            }
        };
        downPanel.setLayout(new FlowLayout());
        downPanel.setPreferredSize(new Dimension(1200, 350));
        downPanel.setBackground(new Color(255,245,213));
        // use BorderLayout with personalPanel.
        personalPanel= new JPanel();
        personalPanel.setLayout(new BorderLayout(15,10));
        personalPanel.setBackground(new Color(255,245,213));
        personalPanel.setPreferredSize(new Dimension(200, 300));
        // here need to set player blood too.
        // add player4Button into personalPanel.
        player0Button = primary.getPlayer(0);
        // use GridLayout with cardPanel.
        cardPanel= new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
            }
        };
        cardPanel.setPreferredSize(new Dimension(950, 300));
        // depend on max handed number of card, set the column number. 
        cardPanel.setLayout(new GridLayout(2,handcard/2,10,10));
        cardPanel.setBackground(new Color(255,245,213));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10,30,10,30));
        cardPanel.setBorder(new MatteBorder(0,0,0,1,Color.WHITE));
        // I'm not sure that meaning. But I guess it is that starting a round for myself.
        RunPlayer runplayer0 = player0Button.getRunPlayer();
        round = new Round(runplayer0,roundState,originCard);
        roundState = round.getRoundState();
            
        // add cardPanel into downPanel.
        downPanel.add(drawCardPanel(),BorderLayout.CENTER);
        // add player0Button into personalPanel.
        personalPanel.add(player0Button,BorderLayout.CENTER);

        // add personalPanel into downPanel.
        downPanel.add(personalPanel,BorderLayout.EAST);
        
        /* Hint: setBorder(BorderFactory.createEmptyBorder(top,left,bottom,right));
           This function can set border between component and container.
        */
        
        downPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        return downPanel;
    }
    private JPanel drawCardPanel(){
        // Hint: change handedcard to other variable that player own card number.
        // Create Card object, and add it into cardPanel.
        cardPanel.removeAll();
        cardButton=new Card[handcard];
        playerCard = primary.getHandCard(0);
        
        // cardPanel.repaint();
        for (Integer i=0;i<playerCard.size();i++){
            cardButton[i] = new Card(playerCard.get(i));
            cardButton[i].addActionListener(this);
            cardPanel.add(cardButton[i]);
        }
        for(int j=0;j<handcard-playerCard.size();j++){
            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(new Color(255,245,213));
            cardPanel.add(emptyPanel);
        }
        System.out.println("player Card"+primary.getHandCard(0));
        player0Button.getHadedPanel().setCardNum(playerCard.size());
        cardPanel.updateUI();
        return cardPanel;
    }
    private void clearPlayerBorder(){
        
        player0Button.setBorder(null);
        player1Button.setBorder(null);
        player2Button.setBorder(null);
        player3Button.setBorder(null);
    }
    private void setPlayerBorder(int playerId){
        clearPlayerBorder();
        switch(playerId){
            case 0:
                player0Button.setBorder(BorderFactory.createLineBorder(Color.red,3));
                break;
            case 1:
                player1Button.setBorder(BorderFactory.createLineBorder(Color.red,3));
                break;
            case 2:
                player2Button.setBorder(BorderFactory.createLineBorder(Color.red,3));
                break;
            case 3:
                player3Button.setBorder(BorderFactory.createLineBorder(Color.red,3));
                break;
        }
    }
    public void run(){
        int i =0;
        int roundPlayer=1;
        while(i==0 && roundPlayer<4){
            
            if(primary.getPlayer(roundPlayer).getRunPlayer().getBlood()!=0){
                
                status.setStatus("玩家 "+roundPlayer+" 的回合");
                status.repaint();
                System.out.println("Now is "+roundPlayer+"playing");
                changeBlood();
                changeHandCard();

                if(primary.getPlayer(roundPlayer).getRunPlayer().getCovid()!=0){
                    JOptionPane.showMessageDialog(f, "玩家"+primary.getPlayer(roundPlayer).getPlayerId()+"受到Covid19功能卡限制，需進行判定才能進行回合", "Covid19生效", JOptionPane.WARNING_MESSAGE);
                    ArrayList<Integer> chancecardList=originCard.drawCard(1);
                    Card chancecard=new Card(chancecardList.get(0));
                    int chancecardId=chancecardList.get(0);
                    if(chancecard.getCardColors(chancecardId)=="1"){
                        JOptionPane.showMessageDialog(f, "判斷為愛心因此Covid19失效，玩家"+primary.getPlayer(roundPlayer).getPlayerId()+"可進行回合", "Covid19失效", JOptionPane.WARNING_MESSAGE);
                        primary.getPlayer(roundPlayer).getRunPlayer().setCovid(0);
                        prepare.changeHashmap(chancecardId, 2);
                        prepare.shuffleCard();
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "判斷不為愛心因此Covid19生效，玩家"+primary.getPlayer(roundPlayer).getPlayerId()+"不可進行回合", "Covid19生效", JOptionPane.WARNING_MESSAGE);
                        primary.getPlayer(roundPlayer).getRunPlayer().setCovid(0);
                        prepare.changeHashmap(chancecardId, 2);
                        prepare.shuffleCard();
                        roundPlayer++;
                        continue;
                    }
                    
                    
                }

                System.out.println("attacker : "+primary.getPlayer(roundPlayer).getActionCommand());
                roundState=0;
                round = new Round(primary.getPlayer(roundPlayer).getRunPlayer(),roundState,originCard);

                primary.getPlayer(roundPlayer).getHadedPanel().setCardNum(primary.getHandCard(roundPlayer).size());
                primary.getPlayer(roundPlayer).getHadedPanel().repaint();

                computer=new Computer(primary.getPlayer(roundPlayer),primary.getPlayer((roundPlayer+1)%4),primary.getPlayer((roundPlayer+2)%4),primary.getPlayer((roundPlayer+3)%4));
                System.out.println("attacker blood:"+primary.getPlayer(roundPlayer).getRunPlayer().getBlood());
                Player chosen = computer.shuffle();
                computer.useCard(chosen);

                if(chosen.getRunPlayer().getBlood()==0){
                    chosen.getIdentifyJLabel().setText(chosen.getIdentifyName(chosen.getRunPlayer().getRole()));
                }
                

                // changeHandCard();
                Image img2 = new ImageIcon(chosen.getCharacterFilePath(chosen.getCharacterId())).getImage();
                Card temp=new Card(computer.getCardId());
                System.out.println("cpu use card id :　"+computer.getCardId());
                if(computer.getCardId()>=0){
                    Image img1 = computer.getCardImg();
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                    if(chosen.getPlayerId()==0){
                        cpu_attacked.setStatus("玩家 "+primary.getPlayer(roundPlayer).getPlayerId()+" 對你使用了 "+temp.getCardName(computer.getCardId()));
                        cpu_attacked.repaint();
                    }else{
                        cpu_attacked.setStatus("玩家 "+primary.getPlayer(roundPlayer).getPlayerId()+" 對玩家 "+chosen.getPlayerId()+" 使用了 "+temp.getCardName(computer.getCardId()));
                        cpu_attacked.repaint();
                    }
                }
                if(computer.getSelfCardId()!=-1){
                    System.out.println("we use miss card id :　"+computer.getSelfCardId());
                    drawCardPanel().updateUI();
                }
                System.out.println("be attacker : "+chosen.getPlayerId());
                System.out.println("be attacker blood:"+chosen.getRunPlayer().getBlood());
                
                computer.fold();

                roundPlayer++;
                changeBlood();
                changeHandCard();

            }
            else{
                roundPlayer++;
                changeBlood();
                changeHandCard();
            }
            doNothing(1500);

        }
        
        if(primary.getPlayer(0).getRunPlayer().getBlood()>0){
            round = new Round(primary.getPlayer(0).getRunPlayer(),roundState,originCard);
            cpu_attacked.setStatus(" ");
            cpu_attacked.repaint();
            status.setStatus("自己的回合");
            status.repaint();
            drawCardPanel().repaint();
            roundState = round.getRoundState();
            System.out.println("After quit roundState:"+roundState);
            actionCommand="";
            endButton.setActionCommand("end");
            changeBlood();
            changeHandCard();
            
        }else{
            roundState=0;
            int j =0;
            int roundPlayer1=1;
            while(j==0){
                if(roundPlayer1%4==0){
                    roundPlayer1++;
                    roundPlayer1=roundPlayer1%4;
                    System.out.println("roundPlayer1"+roundPlayer1);
                }else{
                    roundPlayer1=roundPlayer1%4;
                    System.out.println("roundPlayer1"+roundPlayer1);
                }
                
                if(primary.getPlayer(roundPlayer1).getRunPlayer().getBlood()!=0){
                    status.setStatus("玩家 "+roundPlayer1+" 的回合");
                    status.repaint();
                    JOptionPane.showMessageDialog(f, "輪到"+primary.getPlayer(roundPlayer1).getPlayerId()+"回合", "換回合", JOptionPane.WARNING_MESSAGE);

                    if(primary.getPlayer(roundPlayer1).getRunPlayer().getCovid()!=0){
                        JOptionPane.showMessageDialog(f, "玩家"+primary.getPlayer(roundPlayer).getPlayerId()+"受到Covid19功能卡限制，需進行判定才能進行回合", "Covid19生效", JOptionPane.WARNING_MESSAGE);
                        ArrayList<Integer> chancecardList=originCard.drawCard(1);
                        Card chancecard=new Card(chancecardList.get(0));
                        int chancecardId=chancecardList.get(0);
                        if(chancecard.getCardColors(chancecardId)=="1"){
                            JOptionPane.showMessageDialog(f, "判斷為愛心因此Covid19失效，玩家"+primary.getPlayer(roundPlayer).getPlayerId()+"可進行回合", "Covid19失效", JOptionPane.WARNING_MESSAGE);
                            primary.getPlayer(roundPlayer).getRunPlayer().setCovid(0);
                            prepare.changeHashmap(chancecardId, 2);
                            prepare.shuffleCard();
                        }
                        else{
                            JOptionPane.showMessageDialog(f, "判斷不為愛心因此Covid19生效，玩家"+primary.getPlayer(roundPlayer).getPlayerId()+"不可進行回合", "Covid19生效", JOptionPane.WARNING_MESSAGE);
                            primary.getPlayer(roundPlayer).getRunPlayer().setCovid(0);
                            prepare.changeHashmap(chancecardId, 2);
                            prepare.shuffleCard();
                            roundPlayer++;
                            continue;
                        }
                        
                        
                    }

                    System.out.println("attacker : "+primary.getPlayer(roundPlayer1).getActionCommand());
                    roundState=0;
                    round = new Round(primary.getPlayer(roundPlayer1).getRunPlayer(),roundState,originCard);

                    computer=new Computer(primary.getPlayer(roundPlayer1),primary.getPlayer((roundPlayer1+1)%4),primary.getPlayer((roundPlayer1+2)%4),primary.getPlayer((roundPlayer1+3)%4));
                    System.out.println("attacker blood:"+primary.getPlayer(roundPlayer1).getRunPlayer().getBlood());
                    Player chosen = computer.shuffle();
                    computer.useCard(chosen);

                    if(chosen.getRunPlayer().getBlood()==0){
                        chosen.getIdentifyJLabel().setText(chosen.getIdentifyName(chosen.getRunPlayer().getRole()));
                    }


                    Image img2 = new ImageIcon(chosen.getCharacterFilePath(chosen.getCharacterId())).getImage();
                                        
                    Card temp=new Card(computer.getCardId());
                    System.out.println("cpu use card id :　"+computer.getCardId());
                    if(computer.getCardId()>=0){
                        Image img1 = computer.getCardImg();
                        cpu_usedPanel.setCardImg(img1);
                        cpu_usedPanel.repaint();
                        if(chosen.getPlayerId()==0){
                            cpu_attacked.setStatus("玩家 "+primary.getPlayer(roundPlayer1).getPlayerId()+" 對你使用了 "+temp.getCardName(computer.getCardId()));
                            cpu_attacked.repaint();
                        }else{
                            cpu_attacked.setStatus("玩家 "+primary.getPlayer(roundPlayer1).getPlayerId()+" 對玩家 "+chosen.getPlayerId()+" 使用了 "+temp.getCardName(computer.getCardId()));
                            cpu_attacked.repaint();
                        }
                    }
                    if(computer.getSelfCardId()!=-1){
                        System.out.println("we use miss card id :　"+computer.getSelfCardId());
                        drawCardPanel().updateUI();
                    }
                    System.out.println("be attacker : "+chosen.getPlayerId());
                    System.out.println("be attacker blood:"+chosen.getRunPlayer().getBlood());

                    computer.fold();

                    roundPlayer1++;
                    changeBlood();
                    changeHandCard();
                }
                else{
                    roundPlayer1++;
                    changeBlood();
                    changeHandCard();
                }
                doNothing(1000);
            }
        }
    }
    public void changeBlood(){
        primary.getPlayer(0).getBloodPanel().setBlood(primary.getPlayer(0).getRunPlayer().getBlood());
        primary.getPlayer(1).getBloodPanel().setBlood(primary.getPlayer(1).getRunPlayer().getBlood());
        primary.getPlayer(2).getBloodPanel().setBlood(primary.getPlayer(2).getRunPlayer().getBlood());
        primary.getPlayer(3).getBloodPanel().setBlood(primary.getPlayer(3).getRunPlayer().getBlood());
        primary.getPlayer(0).getBloodPanel().repaint();
        primary.getPlayer(1).getBloodPanel().repaint();
        primary.getPlayer(2).getBloodPanel().repaint();
        primary.getPlayer(3).getBloodPanel().repaint();
    }
    public void changeHandCard(){
        // change the blood of player on gui.
        primary.getPlayer(0).getHadedPanel().setCardNum(primary.getHandCard(0).size());
        primary.getPlayer(1).getHadedPanel().setCardNum(primary.getHandCard(1).size());
        primary.getPlayer(2).getHadedPanel().setCardNum(primary.getHandCard(2).size());
        primary.getPlayer(3).getHadedPanel().setCardNum(primary.getHandCard(3).size());
        primary.getPlayer(0).getHadedPanel().repaint();
        primary.getPlayer(1).getHadedPanel().repaint();
        primary.getPlayer(2).getHadedPanel().repaint();
        primary.getPlayer(3).getHadedPanel().repaint();
    }
    public void changeStatus(int roundPlayer){
        status.setStatus("玩家 "+roundPlayer+" 的回合");
        status.repaint();
    }
    public void actionPerformed(ActionEvent e)
    {        
        String action=e.getActionCommand();
        System.out.println("The actioncommand we get : "+action);

        String className=e.getSource().getClass().getName();
        // check the click button whether a card or not.
        // if is is a card, then display this card we clicked in upper left corner.
        if(className.equals("Card")){
            tmp=(Card)e.getSource();
            cardId=tmp.getCardId();
            cardName=tmp.getCardName(cardId);
            up_left_upPanel.setCardImg(new ImageIcon(tmp.getCardPath(cardId)).getImage());
            up_left_upPanel.repaint();
        }else if(className.equals("Player")){
            player=(Player)e.getSource();
            setPlayerBorder(player.getPlayerId());
        }
        // In our turn, if we click end, interruped the thread of this round.
        if(action.equals("end")){
            if(primary.getPlayer(0).getRunPlayer().getHandCard().size()>primary.getPlayer(0).getRunPlayer().getBlood()){
                int answer=JOptionPane.showConfirmDialog(f,"手牌多於血量，因此要進到棄牌階段!","棄牌",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                
                if(answer==0){
                    roundState=3;
                    status.setStatus("己方棄牌階段");
                    status.repaint();
                    endButton.setActionCommand("exit");;
                }
                fold = new ArrayList<Integer>(); 
                foldcard = new ArrayList<Card>();
                System.out.println("After quit roundState:"+roundState);
            }
            else{
                endButton.setActionCommand("exit");
                Thread changeBlood = new Thread(this);
                changeBlood.start();
            }            
        }
        // the round for myself
        if(roundState==1){
            // When we click card, change the card actionCommand by cardEffect().
            actionCommand=cardEffect(action, tmp, roundState);
            System.out.println("Card actionCommand : "+actionCommand);
            // If we click Yes, check whether we want to use card.
            if(action.equals("Yes")){
                if(actionCommand.equals("2")){
                    roundState=originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    // reset card actionCommand.
                    actionCommand="";
                    System.out.println("roundState after using "+cardName+" and click Yes : "+roundState);
                    Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();;
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                    // remove used card.
                    removeUsedCard(tmp);
                    drawCardPanel().repaint();
                    changeBlood();
                    changeHandCard();
                }
                else if(actionCommand.equals("1")){
                    // change roundState if we use Fire! to attack others.
                    status.setStatus("選取目標人物");
                    status.repaint();

                    player0Button.setBorderPainted(true);
                    player1Button.setBorderPainted(true);
                    player2Button.setBorderPainted(true);
                    player3Button.setBorderPainted(true);

                    roundState = originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    System.out.println("roundState : "+roundState);
                } 
                else if(actionCommand.equals("4")){
                    if(cardName.equals("Bar")){
                        for(int i = 0;i<4;i++){
                            int blood = primary.getPlayer(i).getRunPlayer().getBlood();
                            if(blood!=0&&blood<4){
                                primary.getPlayer(i).getRunPlayer().setBlood(blood+1);
                            }
                            changeBlood();
                            changeHandCard();
                        }
                    }
                    else if(cardName.equals("Oil Shocks")){
                        JOptionPane.showMessageDialog(f, "你已發動Oil Shocks，玩家若無Fire則扣一滴血", "Oil Shocks", JOptionPane.WARNING_MESSAGE);
                        for(int i = 1;i<4;i++){
                            int blood = primary.getPlayer(i).getRunPlayer().getBlood();
                            ArrayList<Integer> handcard = primary.getPlayer(i).getRunPlayer().getHandCard();
                            int size = handcard.size();
                            int miss=0;
                            if(blood!=0){
                                System.out.println("Miss?"+primary.getPlayer(i).getPlayerId());
                                for(int j = 0;j<size;j++){
                                    Card Miss=new Card(handcard.get(j));
                                    int Id=Miss.getCardId();
                                    if(Miss.getCardName(handcard.get(j)).equals("Fire!")){
                                        System.out.println("miss player old handcard"+handcard);
                                        handcard.remove(j);
                                        primary.getPlayer(i).getRunPlayer().setHandCard(handcard);
                                        prepare.changeHashmap(Id, 2);
                                        System.out.println("miss player new handcard"+handcard);
                                        miss=1;
                                        break;
                                    }
                                }
                                if(miss==0){
                                    blood=blood-1;
                                    primary.getPlayer(i).getRunPlayer().setBlood(blood);
                                    JOptionPane.showMessageDialog(f, "由於玩家"+primary.getPlayer(i).getPlayerId()+"沒有Fire，因此扣一滴血", "Oil Shocks", JOptionPane.WARNING_MESSAGE);
                                    if(blood<=0){
                                        JOptionPane.showMessageDialog(f, "玩家"+primary.getPlayer(i).getPlayerId()+"已死亡，他的身分為"+primary.getPlayer(i).getIdentify(), "有玩家死亡", JOptionPane.WARNING_MESSAGE);
                                        primary.getPlayer(i).getIdentifyJLabel().setText(primary.getPlayer(i).getIdentifyName(primary.getPlayer(i).getRunPlayer().getRole()));
                                    }
                                }
                                changeBlood();
                                changeHandCard();
                            }
                        }
                    }
                    else if(cardName.equals("Financial Crisis")){
                        JOptionPane.showMessageDialog(f, "你已發動Financial Crisis，玩家若無Miss則扣一滴血", "Financial Crisis", JOptionPane.WARNING_MESSAGE);
                        for(int i = 1;i<4;i++){
                            int blood = primary.getPlayer(i).getRunPlayer().getBlood();
                            ArrayList<Integer> handcard = primary.getPlayer(i).getRunPlayer().getHandCard();
                            int size = handcard.size();
                            int miss=0;
                            if(blood!=0){
                                System.out.println("Miss?"+primary.getPlayer(i).getPlayerId());
                                for(int j = 0;j<size;j++){
                                    Card Miss=new Card(handcard.get(j));
                                    int Id=Miss.getCardId();
                                    if(Miss.getCardName(handcard.get(j)).equals("Miss")){
                                        System.out.println("miss player old handcard"+handcard);
                                        handcard.remove(j);
                                        primary.getPlayer(i).getRunPlayer().setHandCard(handcard);
                                        prepare.changeHashmap(Id, 2);
                                        System.out.println("miss player new handcard"+handcard);
                                        miss=1;
                                        break;
                                    }
                                }
                                if(miss==0){
                                    blood=blood-1;
                                    primary.getPlayer(i).getRunPlayer().setBlood(blood);
                                    JOptionPane.showMessageDialog(f, "由於玩家"+primary.getPlayer(i).getPlayerId()+"沒有Miss，因此扣一滴血", "Financial Crisis", JOptionPane.WARNING_MESSAGE);
                                    if(blood<=0){
                                        JOptionPane.showMessageDialog(f, "玩家"+primary.getPlayer(i).getPlayerId()+"已死亡，他的身分為"+primary.getPlayer(i).getIdentify(), "有玩家死亡", JOptionPane.WARNING_MESSAGE);
                                        primary.getPlayer(i).getIdentifyJLabel().setText(primary.getPlayer(i).getIdentifyName(primary.getPlayer(i).getRunPlayer().getRole()));
                                    }
                                }
                                changeBlood();
                                changeHandCard();
                            }
                        }
                    }
                    else if(cardName.equals("Different Pay For Equal Work")){
                        for(int i = 1;i<4;i++){
                            int blood = primary.getPlayer(i).getRunPlayer().getBlood();
                            ArrayList<Integer> handcard = primary.getPlayer(i).getRunPlayer().getHandCard();
                            if(blood!=0){
                                System.out.println("handcard before"+handcard);
                                handcard.addAll(originCard.drawCard(1));
                                System.out.println("handcard after"+handcard);
                                primary.getPlayer(i).getRunPlayer().setHandCard(handcard);
                                changeBlood();
                                changeHandCard();
                            }
                        }
                    }
                    roundState = originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                    removeUsedCard(tmp);
                    drawCardPanel().repaint();

                }
                else if(actionCommand.equals("5")){
                    roundState = originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    actionCommand="";
                    Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();;
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                    
                    System.out.println("roundState after using "+cardName+" and click Yes : "+roundState);
                    // remove used card.
                    removeUsedCard(tmp);
                    drawCardPanel().repaint();
                    changeBlood();
                    changeHandCard();
                }
                else if(actionCommand.equals("6")){
                    roundState = originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    if(cardName.equals("Stock Market Crash")){
                        int heart=0;
                        int roundheartplayer=1;
                        while(heart!=1){
                            int blood = primary.getPlayer(roundheartplayer%4).getRunPlayer().getBlood();
                            ArrayList<Integer> handcard = primary.getPlayer(roundheartplayer%4).getRunPlayer().getHandCard();
                            if(blood!=0){
                                JOptionPane.showMessageDialog(f, roundheartplayer%4+"判定中", "Lottery Jackpot", JOptionPane.WARNING_MESSAGE);
                                ArrayList<Integer> chancecardList=originCard.drawCard(1);
                                Card chancecard=new Card(chancecardList.get(0));
                                int chancecardId=chancecardList.get(0);
                                if(chancecard.getCardColors(chancecardId)=="0"){
                                    JOptionPane.showMessageDialog(f, roundheartplayer%4+"判定為黑桃，將扣去三點血", "Lottery Jackpot", JOptionPane.WARNING_MESSAGE);
                                    heart=heart+1;
                                    blood=blood-3;
                                    primary.getPlayer(roundheartplayer%4).getRunPlayer().setBlood(blood);
                                    if(blood<=0){
                                        primary.getPlayer(roundheartplayer%4).getRunPlayer().setBlood(0);
                                        JOptionPane.showMessageDialog(f, "玩家"+primary.getPlayer(roundheartplayer%4).getPlayerId()+"已死亡，他的身分為"+primary.getPlayer(roundheartplayer%4).getIdentify(), "有玩家死亡", JOptionPane.WARNING_MESSAGE);
                                        primary.getPlayer(roundheartplayer%4).getIdentifyJLabel().setText(primary.getPlayer(roundheartplayer%4).getIdentifyName(primary.getPlayer(roundheartplayer%4).getRunPlayer().getRole()));
                                    }
                                    System.out.println("lottery handcard"+handcard);
                                    break;
                                }
                                prepare.changeHashmap(chancecardId, 2);
                                prepare.shuffleCard();
                            }
                            roundheartplayer=roundheartplayer+1;
                        }
                    }
                    if(cardName.equals("Lottery Jackpot")){
                        int heart=0;
                        int roundheartplayer=1;
                        while(heart!=1){
                            int blood = primary.getPlayer(roundheartplayer%4).getRunPlayer().getBlood();
                            ArrayList<Integer> handcard = primary.getPlayer(roundheartplayer%4).getRunPlayer().getHandCard();
                            if(blood!=0){
                                JOptionPane.showMessageDialog(f, roundheartplayer%4+"判定中", "Lottery Jackpot", JOptionPane.WARNING_MESSAGE);
                                ArrayList<Integer> chancecardList=originCard.drawCard(1);
                                Card chancecard=new Card(chancecardList.get(0));
                                int chancecardId=chancecardList.get(0);
                                if(chancecard.getCardColors(chancecardId)=="1"){
                                    JOptionPane.showMessageDialog(f, roundheartplayer%4+"判定成功，將加三點血", "Lottery Jackpot", JOptionPane.WARNING_MESSAGE);
                                    heart=heart+1;
                                    blood=blood+3;
                                    primary.getPlayer(roundheartplayer%4).getRunPlayer().setBlood(blood);
                                    if(blood>=4){
                                        primary.getPlayer(roundheartplayer%4).getRunPlayer().setBlood(4);
                                        JOptionPane.showMessageDialog(f, "玩家已加超過四點血囉!", "血量超出", JOptionPane.WARNING_MESSAGE);
                                    }
                                    System.out.println("lottery handcard"+handcard);
                                    break;
                                }
                                prepare.changeHashmap(chancecardId, 2);
                                prepare.shuffleCard();
                            }
                            roundheartplayer=roundheartplayer+1;
                        }
                    }
                    changeBlood();
                    changeHandCard();
                    removeUsedCard(tmp);
                }
                else if(actionCommand.equals("7")){
                    roundState = originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();;
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                    removeUsedCard(tmp);
                }
                else if(actionCommand.equals("8")){
                    roundState = originCard.use(round.getPlayer(),player,cardId,cardName,0);
                    Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();;
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                    removeUsedCard(tmp);
                }
            }
            // If we click cancel, let roundState back to 1.
            else if(action.equals("cancel")){
                // reset card actionCommand.
                actionCommand="";
                // reset roundState.
                roundState=1;
                System.out.println("roundState after click cancel : "+roundState);
                up_left_upPanel.setCardImg(new ImageIcon("").getImage());
                up_left_upPanel.repaint();
                clearPlayerBorder();
            }
        }
        // If this card need to choose the others used, turn to 2nd roundState.
        else if(roundState==2){
            //choose the others to attack
            if(actionCommand.equals("1")&&action.equals("Yes")){
                if(player.getRunPlayer().getBlood()>0){
                   removeUsedCard(tmp);  
                }
                // actaully use this card.
                status.setStatus("自己的回合");
                status.repaint();
                roundState=originCard.use(round.getPlayer(),player,cardId,cardName,1);
                // reset card actionCommand.
                actionCommand="";
                // the role and blood for player who be attacked.
                if(cardName.equals("Fire!")){
                    System.out.println("Player location : "+player.getRunPlayer().getPlayerId()+" blood : "+player.getRunPlayer().getBlood());
                    System.out.println("roundState after using Fire! and click Yes : "+roundState);
                    cpu_attacked.setStatus("你對玩家 "+player.getPlayerId()+" 使用了 Fire!");
                    cpu_attacked.repaint();
                    // change the blood of player on gui.
                    if(player.getRunPlayer().getBlood()==0){
                        player.getIdentifyJLabel().setText(player.getIdentifyName(player.getRunPlayer().getRole()));
                    }
                    if(player.getRunPlayer().getBlood()>0){
                        Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();;
                        cpu_usedPanel.setCardImg(img1);
                        cpu_usedPanel.repaint();
                    }
                    changeBlood();
                    changeHandCard();
                    clearPlayerBorder();
                }else{
                    Image img1 = new ImageIcon(tmp.getCardPath(cardId)).getImage();;
                    cpu_usedPanel.setCardImg(img1);
                    cpu_usedPanel.repaint();
                }
                // remove used card.
                drawCardPanel().repaint();
                changeBlood();
                changeHandCard();
            }
            // if we click cancel button, it will return to origin status.  
            else if(action.equals("cancel")){
                // reset card actionCommand.
                actionCommand="";
                // reset roundState.
                roundState=1;
                System.out.println("roundState after click cancel : "+roundState);
                up_left_upPanel.setCardImg(new ImageIcon("").getImage());
                up_left_upPanel.repaint();
                clearPlayerBorder();
            }
        }
        else if(roundState==3){
            if(className.equals("Card")){
                tmp.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        super.mouseClicked(e);
                        if(e.getClickCount()%2!=0){
                            tmp.setBorder(BorderFactory.createLineBorder(Color.red,3));
                            fold.add(cardId);
                            foldcard.add(tmp);
                        }
                    }
                });
            }
            else if(action.equals("Yes")){
                if(round.getPlayer().HandCardNum()-fold.size()>round.getPlayer().getBlood()){
                    JOptionPane.showMessageDialog(f, "手牌多於血量，請棄牌", "棄牌", JOptionPane.WARNING_MESSAGE);
                }else{
                    originCard.fold(fold,round.getPlayer());
                    for(int i = 0;i<fold.size();i++){
                        tmp=foldcard.get(i);
                        removeUsedCard(tmp);
                    }
                    Thread changeBlood = new Thread(this);
                    changeBlood.start();
                }
            }
            else if(action.equals("cancel")){
                for(int i = 0;i<fold.size();i++){
                    tmp=foldcard.get(i);
                    tmp.setBorder(null);
                }
                fold.clear();
                foldcard.clear();
            }
            
        }
    }

    public String cardEffect(String action, Card card, int roundState){
        
        runPlayer=round.getPlayer();
        switch(action){
            // need to choose one player for using
            case "Fire!": case "awpjopabemarrrr": case "beibigennosuke":  case "You Know It": case "COVID-19":
                actionCommand="";
                actionCommand=actionCommand+"1";
                break;
            // use on myself
            case "Gig Economy": case "Miss": case "Tonight I Want Some": case "Please Support Counter":
                actionCommand="";
                actionCommand=actionCommand+"2";
                break;
            // all player need to do
            case "Financial Crisis": case "Oil Shocks": case "Different Pay For Equal Work": case "Bar": case "Colosseum": 
                actionCommand="";
                actionCommand=actionCommand+"4";
                break;
            // need to decide the result with card suit for own drawing
            case "Shield Warrior": 
                actionCommand="";
                actionCommand=actionCommand+"5";
                break;
            // will exist on playboard at least a round.
            case "Stock Market Crash": case "Lottery Jackpot":
                actionCommand="";
                actionCommand=actionCommand+"6";
                break;
            // the equipment 1
            case "Slaughter":
                actionCommand="";
                actionCommand=actionCommand+"7";
                break;
            // the equipment 2
            case "Let Bullet Fly":
                actionCommand="";
                actionCommand=actionCommand+"8";
                break;
            default:
                break;
        }
        return actionCommand;
    }

    public void removeUsedCard(Card tmp){
        clearPlayerBorder();
        for (Card i:cardButton){
            if(tmp.equals(i)){
                cardPanel.remove(i);
                cardPanel.repaint();
                up_left_upPanel.setCardImg(new ImageIcon("").getImage());  
                up_left_upPanel.repaint();
            }
        }
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
