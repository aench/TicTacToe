import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;

public class TicTacToe extends JFrame  implements ActionListener {

    JButton buttons[];
    JMenuItem menuItem;
    JLabel screen;
    AIPlayer AIpl;
    GameSituation gameSitua;
    int lastmove = 10;
    boolean isFinish = false;

    int player = 1;

    public TicTacToe(String s){

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");//submenu;
        //menu.setMnemonic(KeyEvent.VK_A);
        //menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);


        //JRadioButtonMenuItem rbMenuItem;
        //JCheckBoxMenuItem cbMenuItem;


        //a group of JMenuItems
        menuItem = new JMenuItem("New",KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
        menu.add(menuItem);

    /*
    menuItem = new JMenuItem("Both text and icon",
    new ImageIcon("images/middle.gif"));
    menuItem.setMnemonic(KeyEvent.VK_B);
    menu.add(menuItem);

    menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
    menuItem.setMnemonic(KeyEvent.VK_D);
    menu.add(menuItem);*/


        menu.addSeparator();

        //a group of radio button menu items
    /*ButtonGroup group = new ButtonGroup();
    rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
    rbMenuItem.setSelected(true);
    rbMenuItem.setMnemonic(KeyEvent.VK_R);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);

    rbMenuItem = new JRadioButtonMenuItem("Another one");
    rbMenuItem.setMnemonic(KeyEvent.VK_O);
    group.add(rbMenuItem);
    menu.add(rbMenuItem);*/

        //a group of check box menu items
    /*menu.addSeparator();
    cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
    cbMenuItem.setMnemonic(KeyEvent.VK_C);
    menu.add(cbMenuItem);

    cbMenuItem = new JCheckBoxMenuItem("Another one");
    cbMenuItem.setMnemonic(KeyEvent.VK_H);
    menu.add(cbMenuItem);*/

        //a submenu
    /*
    menu.addSeparator();
    submenu = new JMenu("A submenu");
    submenu.setMnemonic(KeyEvent.VK_S);

    menuItem = new JMenuItem("An item in the submenu");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
    submenu.add(menuItem);

    menuItem = new JMenuItem("Another item");
    submenu.add(menuItem);
    menu.add(submenu);
    */

        this.setJMenuBar(menuBar);

        menuItem.addActionListener(this);

        /*---*/



        gameSitua = new GameSituation();

        setTitle(s); // frame title (written on the window)
        setSize(300,300); // frame size 300 width and 300 height
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close program when close window
        setLocationRelativeTo(null);

        AIpl = new AIPlayer();

        /* Layout */
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints(); // 'constraints' per creare la tabella
        setLayout(gridbag);

        /* Buttons */
        buttons = new JButton [9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton (" ");
        }

        for (int i = 0 ; i < 9;i++) {
            buttons[i].addActionListener(this);
        }

        /* Screen */
        screen = new JLabel(" ");

        /* Inizializza i 'constraints' delle celle */
        c.fill = GridBagConstraints.BOTH;          // Espande in entrambe le direzioni
        c.weightx = 1.0;                           // Allarga l'elemento per riempire l'area
        c.weighty = 1.0;                           // Alza l'elemento per riempire l'area
        c.gridheight = 1;                          // Occupa una sola riga


        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(screen, c);
        add(screen);

        /* First row */
        c.gridwidth = 1;                           // Elementi che occupano una sola cella (non ultimi)
        gridbag.setConstraints(buttons[0], c);    // Applica i 'constraints'
        add(buttons[0]);                          // Ed inserisce
        gridbag.setConstraints(buttons[1], c);    // Applica i 'constraints'
        add(buttons[1]);                          // Ed inserisce                          // Ed inserisce
        c.gridwidth = GridBagConstraints.REMAINDER;// Ultimo elemento
        gridbag.setConstraints(buttons[2], c);    // Applica i 'constraints'
        add(buttons[2]);

        /* Second row */
        c.gridwidth = 1;                           // Elementi che occupano una sola cella (non ultimi)
        gridbag.setConstraints(buttons[3], c);    // Applica i 'constraints'
        add(buttons[3]);                          // Ed inserisce
        gridbag.setConstraints(buttons[4], c);    // Applica i 'constraints'
        add(buttons[4]);                          // Ed inserisce
        c.gridwidth = GridBagConstraints.REMAINDER;// Ultimo elemento (largo due celle)
        gridbag.setConstraints(buttons[5], c);// Applica i 'constraints'
        add(buttons[5]);                     // Ed inserisce

        /* Third row */
        c.gridwidth = 1;                           // Elementi che occupano una sola cella (non ultimi)
        gridbag.setConstraints(buttons[6], c);    // Applica i 'constraints'
        add(buttons[6]);                          // Ed inserisce
        gridbag.setConstraints(buttons[7], c);    // Applica i 'constraints'
        add(buttons[7]);                          // Ed inserisce
        c.gridwidth = GridBagConstraints.REMAINDER;// Ultimo elemento
        gridbag.setConstraints(buttons[8], c); // Applica i 'constraints'
        add(buttons[8]);                       // Ed inserisce

        setVisible(true);//now frame will be visible, by default not visible

    }

    public void reset(){
        System.out.println("Reset the game situation:");
        isFinish = false;
        for (int i=0;i<9;i++){
            buttons[i].setText(" ");
            update();
        }
    }

    public boolean checkWin(){
        String row1 = buttons[0].getText()+buttons[1].getText()+buttons[2].getText();
        String row2 = buttons[3].getText()+buttons[4].getText()+buttons[5].getText();
        String row3 = buttons[6].getText()+buttons[7].getText()+buttons[8].getText();
        String col1 = buttons[0].getText()+buttons[3].getText()+buttons[6].getText();
        String col2 = buttons[1].getText()+buttons[4].getText()+buttons[7].getText();
        String col3 = buttons[2].getText()+buttons[5].getText()+buttons[8].getText();
        String diag1 =buttons[0].getText()+buttons[4].getText()+buttons[8].getText();
        String diag2 =buttons[2].getText()+buttons[4].getText()+buttons[6].getText();

        if (diag2.equals("XXX") || diag2.equals("OOO") || diag1.equals("OOO") || diag1.equals("XXX") || row1.equals("XXX") || row2.equals("XXX") || row3.equals("XXX") || col1.equals("XXX") || col2.equals("XXX") || col3.equals("XXX") || row1.equals("OOO") || row2.equals("OOO") || row3.equals("OOO") || col1.equals("OOO") || col2.equals("OOO") || col3.equals("OOO")) {
            screen.setText("Ha vinto il giocatore " + player);
            System.out.println("Ha vinto il giocatore " + player);
            if (player == 1) {
                AIpl.deleteGame(gameSitua.unsetMove(lastmove));
            }
            System.out.println("Number of games in memory: " + AIpl.numberOfGames());
            isFinish = true;
            //reset();
            return false;
        }

        int counter = 0;
        for (int i =0;i<9;i++){
            if(buttons[i].getText() == "X" || buttons[i].getText() == "O"){
                counter = counter + 1;
            }
        }
        if(counter == 9) {
            screen.setText("Pareggio!");
            System.out.println("Pareggio!");
            isFinish = true;
            //reset();
            return false;
        }

        return true;
    }

    public void update(){
        String[] string = new String[9];
        for (int i=0;i<9;i++){
            string[i]=buttons[i].getText();
        }
        gameSitua.setSituation(string);
        System.out.print("Situazione gioco: ");
        gameSitua.print();
    }

    public void quit(){
        System.out.println("Ha vinto il giocatore 1");
        AIpl.deleteGame(gameSitua);
        System.out.println(AIpl.numberOfGames());
        reset();
    }

    /* Gestisce tutti i tasti */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem) {
            System.out.println("New game!");
            reset();
        }
        else {
            if (isFinish){
            }
            else {
                lastmove = 10;
                if (player == 1) {
                    for (int i = 0; i < 9; i++) {
                        if (e.getSource() == buttons[i]){
                            if (buttons[i].getText() == "X" || buttons[i].getText() == "O") {
                                System.out.println("Già occupato!");
                            }
                            else if (buttons[i].getText() == " ") {
                                lastmove = i;
                                buttons[i].setText("X");
                                update();
                                if (checkWin()){
                                    player = 2;
                                    int move = 11;
                                    move = AIpl.move(gameSitua);
                                    System.out.print("gameSitua dopo la mossa: ");
                                    gameSitua.print();
                                    if (move !=10){
                                        buttons[move].setText("O");
                                        checkWin();
                                        player = 1;
                                    }
                                    else if (move == 10) {
                                        System.out.println("Quit!");
                                        quit();
                                    }
                                    else if (move == 11) {
                                        System.out.println("Error!");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    /*else if (player == 2){
      for (int i = 0; i < 9; i++) {
        if (e.getSource() == buttons[i]){
          if (buttons[i].getText() == " "){
            buttons[i].setText("O");
            checkWin();
            player =1;
          }
          else if (buttons[i].getText() == "X" || buttons[i].getText() == "O") {
            System.out.println("Già occupato!");
          }
        }
      }
    }*/
    }


    public static void main(String[] args) throws Exception {
        TicTacToe game = new TicTacToe("Tic Tac Toe");
        //AIPlayer player = new AIPlayer();
    }

}
