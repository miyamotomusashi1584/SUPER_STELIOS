import java.io.IOException;
import java.util.HashMap;

public class Controller {

    View v;
    KeyBinder k;
    HashMap<String, Thread> threadMap;
    
    //Processor proc = new Processor();

    public Controller(View v, KeyBinder k){
        this.v=v;
        this.k=k;
    }

    public void giveThreads(HashMap<String, Thread> threadMap){
        this.threadMap=threadMap;
    }

    
    public void initView(){

        
        v.firstPanel.setVisible(true);

        
    }

    public void initController(){

        v.start.addActionListener(evt -> startGame());
        v.exit.addActionListener(evt -> exitButton());
        v.options.addActionListener(evt -> enterOptionButton());
        v.exitOptions.addActionListener(evt -> exitOptionButton());
        
        
    }

    private void startGame(){
        v.hideMenu();
        v.showGame();
        threadMap.get("gravity").start();


    }

    private void exitButton(){
        v.frame.setVisible(false);
             
        v.frame.dispose();
        System.exit(0);

    }

    private void enterOptionButton(){
        v.hideButtons();
        v.showOption();
        
    }

    private void exitOptionButton(){
        v.showButtons();
        v.hideOption();

    }

    
    
    
}
