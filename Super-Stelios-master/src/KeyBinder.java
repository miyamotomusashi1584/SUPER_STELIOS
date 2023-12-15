import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyBinder implements KeyListener{

    Controller c;

    View v;
    Music m;
    public KeyBinder(View view, Music m){
        this.v=view;
        this.m=m;
    }

    boolean right=false;
    boolean left=false;
    boolean jump=false;
    boolean spawn=false;
    int menuTimes=0;

    boolean isPaused=false;


    public void giveCon(Controller c){

        this.c=c;

    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            right = true;
            //System.out.println("D is pressed");
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            left = true;
            //System.out.println("A is pressed");
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            jump = true;
            //System.out.println("jump");
        }
        if(e.getKeyCode() == KeyEvent.VK_H){
            v.stelios.setBounds(v.stelios.getX(), 50, 56, 70);
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
          
          if(v.firstPanel.isVisible()){
            System.out.println("are you suer u want to exit");

          }
          else if(v.gamePanel.isVisible()){
    

            if(menuTimes%2==0){
                v.showPauseMenu();
                m.pause();
                isPaused=true;
            
            }
            else {
                v.hidePauseMenu();
                m.resume();
                isPaused=false;
                }


            menuTimes++;
          }
        }

        

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_D){
            right = false;
            //System.out.println("D is released");
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            left = false;
            //System.out.println("A is released");
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            jump = false;
            //System.out.println("jump");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {



    }


    public HashMap<String, Boolean> getKeys (){

        HashMap<String, Boolean> keys= new HashMap<String, Boolean>();
        keys.put("left", left);
        keys.put("right", right);
        keys.put("jump", jump);

        return keys;


    }

    public boolean getPauseStatus(){

        return isPaused;

    }

}