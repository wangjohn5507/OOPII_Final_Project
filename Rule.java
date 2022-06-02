import java.awt.*;
import javax.swing.*;
//import javax.swing.event.MenuListener;
import javax.swing.event.*;
import java.awt.event.*;

public class Rule extends JFrame implements ActionListener,MenuListener{

    private JPanel bottomPanel; //底板
    private JPanel rulePanel; //規則板
    private JPanel identifyPanel; //身分板
    private JPanel characterPanel; //人物板
    private JPanel gameCardPanel; //遊戲卡板
    private JMenu ruleMenu; 
    private JMenu startMenu;
    private JMenu cardMenu;
    private Font font = new Font("Dialog", Font.PLAIN, 16);

    public Rule(){

        //底板
        setTitle("RuleExplain");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,750);
        bottomPanel = new JPanel(new CardLayout());
        this.add(bottomPanel);
        
        //規則板
        rulePanel = new JPanel();
        DrawRulePanel dPanel = new DrawRulePanel();
        rulePanel.add(dPanel);

        JScrollPane scroll1 = new JScrollPane(rulePanel);
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scroll1,"1");

        //身分板
        identifyPanel = new JPanel();
        DrawIdentifyPanel idPanel = new DrawIdentifyPanel();
        identifyPanel.add(idPanel);
        JScrollPane scroll2 = new JScrollPane(identifyPanel);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scroll2,"2");

        //人物板
        characterPanel= new JPanel();
        DrawCharacterPanel chPanel = new DrawCharacterPanel();
        characterPanel.add(chPanel);
        JScrollPane scroll3 = new JScrollPane(characterPanel);
        scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scroll3,"3");

        //遊戲卡板
        gameCardPanel = new JPanel();
        DrawCardPanel dcPanel = new DrawCardPanel();
        gameCardPanel.add(dcPanel);
        JScrollPane scroll4 = new JScrollPane(gameCardPanel);
        scroll4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bottomPanel.add(scroll4,"4");

        //設置Menu
        ruleMenu = new JMenu("遊戲規則");
        ruleMenu.setFont(font);
        ruleMenu.addMenuListener(this);

        startMenu = new JMenu("Back To Start");
        startMenu.setFont(font);
        startMenu.addMenuListener(this);

        cardMenu = new JMenu("卡牌介紹");
        cardMenu.setFont(font);
        JMenuItem identifyItem = new JMenuItem("身分卡");
        identifyItem.setFont(font);
        identifyItem.addActionListener(this);
        cardMenu.add(identifyItem);

        JMenuItem characterItem = new JMenuItem("人物卡");
        characterItem.setFont(font);
        characterItem.addActionListener(this);
        cardMenu.add(characterItem);
        
        JMenuItem gameCardItem = new JMenuItem("遊戲卡");
        gameCardItem.setFont(font);
        gameCardItem.addActionListener(this);
        cardMenu.add(gameCardItem);

        JMenuBar bar = new JMenuBar();
        bar.add(ruleMenu);
        bar.add(cardMenu);
        bar.add(startMenu);
        this.setJMenuBar(bar);
    }

    public void menuSelected(MenuEvent e) {
        CardLayout c = (CardLayout)(bottomPanel.getLayout());
        if(e.getSource()==ruleMenu){
            c.show(bottomPanel,"1");
        }
        else if(e.getSource()==startMenu){
            dispose();
            StartPanel game = new StartPanel();
            game.setVisible(true);
        }
    }
    public void menuDeselected(MenuEvent e) {}
    public void menuCanceled(MenuEvent e) {}

    public void actionPerformed(ActionEvent e){
        String buttonString = e.getActionCommand();
        CardLayout c = (CardLayout)(bottomPanel.getLayout());

        if(buttonString.equals("身分卡")){
            c.show(bottomPanel,"2");
        }
        else if(buttonString.equals("人物卡")){
            c.show(bottomPanel,"3");
        }
        else if(buttonString.equals("遊戲卡")){
            c.show(bottomPanel,"4");
        }
        else{
            System.out.println("Unexpected error.");
        }
    }
}
