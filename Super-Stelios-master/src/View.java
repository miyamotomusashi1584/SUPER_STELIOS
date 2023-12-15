import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class View {

    int frameW = 900;
    int frameH = 600;
    int numOfCoins = 0;
    int volume;
    ArrayList<JPanel> collisions = new ArrayList<JPanel>();

    Newton2_0 n;

    //Start Menu
    JButton start = new JButton();
    JButton options = new JButton();
    JButton exit = new JButton();
    ImageIcon menuBack = new ImageIcon("src/images/sky.png");
    JLabel menuBackgound = new JLabel(menuBack);
    JPanel firstPanel = new JPanel();
    ImageIcon superStelios = new ImageIcon("src/images/SupStelios.png");
    JLabel supersuper = new JLabel(superStelios);
    File sound_track= new File("src/sounds/menu.wav");
    Music menu = new Music();
    
    //Options Menu
    JButton exitOptions = new JButton();
    JPanel optionPanel = new JPanel();
    JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL,0,100,100);
    JLabel volumeLabel = new JLabel();
    ImageIcon volBack = new ImageIcon("src/images/volumePanel.png");
    JLabel volBackground = new JLabel(volBack);
    JPanel volPanel = new JPanel();
    
    
    //Level1
    JPanel gamePanel = new JPanel();
    JPanel grassPanel = new JPanel();
    ImageIcon ouranos = new ImageIcon("src/images/level1.png");
    JLabel actualOuranos = new JLabel(ouranos);
    JPanel floor1 = new JPanel();

    //Pause Menu
    JPanel pauseMenu = new JPanel();
    JLabel paused = new JLabel();

    
    //game assets
    public JFrame frame = new JFrame("Mario");
    JPanel stelios = new JPanel();
    JLabel totalCoins = new JLabel();
    ImageIcon image = new ImageIcon("src/images/coin.png");
    JLabel coinImage = new JLabel(image);
    Music mainTheme = new Music();
    File theme = new File("src/sounds/mainTheme.wav");
    
    
    


    public View() {

        initFrame();
        initComponents();
        
        
        frame.add(pauseMenu);
        frame.add(volumeLabel);
        frame.add(volumeSlider);
        frame.add(exitOptions);
        frame.add(optionPanel);
        frame.add(exit);
        frame.add(options);
        frame.add(supersuper);
        frame.add(totalCoins);
        frame.add(coinImage);
        firstPanel.add(menuBackgound);
        frame.add(firstPanel);
        frame.add(stelios);
        frame.add(grassPanel);
        frame.add(gamePanel);
        frame.add(volPanel);

    }

    public void initFrame() {
        frame.setResizable(false);
        frame.setBounds(310, 100, 900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setFocusable(true);

    }

    public void initComponents() {

        start.setBounds(330, 200, 200, 80);
        start.setBackground(new Color(144, 248, 144));
        start.setText("Start");
        start.setFont(new Font("Verdana", Font.BOLD, 24));



        firstPanel.setBounds(0, 0, 900, 600);
        firstPanel.setLayout(null);
        firstPanel.setVisible(false);
        firstPanel.add(start);

        gamePanel.setBounds(0, -30, 900, 600);
        gamePanel.setLayout(null);
        gamePanel.setVisible(false);

        grassPanel.setLayout(null);
        grassPanel.setVisible(false);
        grassPanel.setBounds(0, 500, 900, 100);
        grassPanel.setBackground(new Color(5,20,27));

        stelios.setBounds(100, 100, 30, 35);
        stelios.setBackground(Color.white);
        stelios.setFocusable(true);
        stelios.requestFocus();

        coinImage.setBounds(50,50,56,55);
        coinImage.setVisible(false);

        totalCoins.setBounds(110,40,56,70);
        totalCoins.setText(String.valueOf(numOfCoins));
        totalCoins.setVerticalTextPosition(JLabel.CENTER);
        totalCoins.setHorizontalTextPosition(JLabel.CENTER);
        totalCoins.setVisible(false);
        totalCoins.setFont(new Font("Verdana", Font.BOLD, 24));

        supersuper.setBounds(160,100,553,53);

        options.setBounds(330,300,200,80);
        options.setBackground(new Color(144, 248, 144));
        options.setText("Options");
        options.setFont(new Font("Verdana", Font.BOLD, 24));
        options.setVisible(true);

        menu.setFile(sound_track);
        

        mainTheme.setFile(theme);

        exit.setBounds(330,400,200,80);
        exit.setBackground(new Color(144,248,144));
        exit.setText("Exit");
        exit.setFont(new Font("Verdana", Font.BOLD, 24));
        exit.setVisible(true);

        optionPanel.setBounds(0,0,700,600);
        optionPanel.setBackground(new Color(145,183,175));
        optionPanel.setVisible(false);

        exitOptions.setBounds(550, 500, 100, 50);
        exitOptions.setBackground(Color.gray.darker());
        exitOptions.setVisible(false);
        exitOptions.setText("Return");

        volumeSlider.setVisible(false);
        volumeSlider.setBounds(100,150,500,50);
        volumeSlider.setBackground(new Color(145,183,175));
        

        volumeLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        volumeLabel.setVisible(false);
        volumeLabel.setBounds(100,100,500,50);
        

        volBackground.setBounds(70,120,560,200);
        volBackground.setVisible(true);
        
        volPanel.setLayout(null);
        volPanel.setBounds(70,120,560,200);
        volPanel.add(volBackground);
        volPanel.setVisible(false);

        menuBackgound.setBounds(0,0,900,600);
        menuBackgound.setVisible(true);

        pauseMenu.setBounds(0,0,900,600);
        pauseMenu.setBackground(new Color(0,0,0,200));
        pauseMenu.setVisible(false);

        pauseMenu.add(paused);
        
        paused.setText("ZA WINDA...");
        paused.setBounds(0, 100, 400, 100);
        paused.setFont(new Font("Verdana", Font.BOLD, 50 ));
        paused.setVisible(true);
        paused.setForeground(Color.WHITE);
        paused.setHorizontalAlignment(SwingConstants.CENTER);

        actualOuranos.setBounds(0,0,900,600);
        actualOuranos.setVisible(true);
        gamePanel.add(actualOuranos);
        
        frame.add(floor1);
        floor1.setBounds(500-150,400+50-3,170,20+80-50);
        floor1.setBackground(Color.white);
        floor1.setVisible(true);
        collisions.add(floor1);
        
        

        
        


        

        

        



    }

    public void AddCollisions(ArrayList<JPanel> p){

        for(int i=0; i<p.size(); i++){
            n.addCollisions(p.get(i));
        }
    }

    public void giveNewtonToView(Newton2_0 n){
        this.n=n;
    }

    public void hideMenu() {
        start.setVisible(false);
        firstPanel.setVisible(false);
        options.setVisible(false);
        supersuper.setVisible(false);
        exit.setVisible(false);
        try {
            menu.stop();
        } catch (IOException e) {}


    }

    public void showGame() {
        gamePanel.setVisible(true);
        coinImage.setVisible(true);
        totalCoins.setVisible(true);
        
        

    }

    public void hideGame() {
        gamePanel.setVisible(false);
        grassPanel.setVisible(false);
        coinImage.setVisible(false);
        totalCoins.setVisible(false);
 
    }


    public void hideButtons(){

        start.setVisible(false);
        options.setVisible(false);
        exit.setVisible(false);
    }

    public void showButtons(){

        start.setVisible(true);
        options.setVisible(true);
        exit.setVisible(true);
    }


    public void showOption(){
        
        optionPanel.setVisible(true);
        exitOptions.setVisible(true);
        volumeSlider.setVisible(true);
        volumeLabel.setVisible(true);
        volPanel.setVisible(true);

    }
    public void hideOption(){

        optionPanel.setVisible(false);
        exitOptions.setVisible(false);
        volumeSlider.setVisible(false);
        volumeLabel.setVisible(false);
        volPanel.setVisible(false);

    }

    public void showPauseMenu(){
        pauseMenu.setVisible(true);
        exit.setVisible(true);
        exit.setBounds(600,450,200,80);
        
    }

    public void hidePauseMenu(){
        pauseMenu.setVisible(false);
        exit.setBounds(330,400,200,80);
        exit.setVisible(false);
    }


}