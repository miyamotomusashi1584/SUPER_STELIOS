import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import java.awt.*;

class Movement implements Runnable{


    View v;
    KeyBinder k;
    boolean timeToJump=false;
    Newton2_0 n;
    


    public Movement(View view, KeyBinder key){
        this.v=view;
        this.k=key;
        
    }

    @Override
    public void run() {

        
        while(true){

            

            
            try{

            Thread.sleep(5);
            
            }catch(InterruptedException e){}



            HashMap<String, Boolean> keys = k.getKeys();
            boolean paused = k.getPauseStatus();



            while(paused){
                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {}
                paused = k.getPauseStatus();
            }

           
            

            if(keys.get("left")==true){
                moveLeft(v.stelios);
            }

            if(keys.get("right")==true){
                moveRight(v.stelios);
            }





        }

    }

    public void moveLeft(JPanel p){

        for(int i=0; i<2; i++){

            if(!(n.physics(v.stelios, n.grounds)[2])){

                p.setBounds(p.getX()-1,p.getY(), p.getWidth(), p.getHeight());
            }

        }
         
        
    }

    public void moveRight(JPanel p){

        for(int i=0; i<2; i++){

            if(!(n.physics(v.stelios, n.grounds)[3])){

                p.setBounds(p.getX()+1,p.getY(), p.getWidth(), p.getHeight());
            }
        }

    }

    public void giveNewtonToMovement (Newton2_0 n){

            this.n=n;
    }




}

class Newton2_0 implements Runnable {

    View v;
    KeyBinder k;
    Music m2;

    int g=10;
    int idx=0;
    double uo=0;
    double ujump;
    int t=0;
    int tground=0;
    double ajump;

    boolean jumped=false;

    ArrayList<JPanel> grounds = new ArrayList<JPanel>();


    public Newton2_0(View v, KeyBinder k, Music m2){
        this.v=v;
        this.k=k;
        this.m2=m2;
    }

    public void addCollisions(JPanel panel){

        grounds.add(panel);
    }

    @Override
    public void run() {

        m2.play();
        



        while(true){






        try{

            Thread.sleep(10);
            
            }catch(InterruptedException e){}


        boolean paused = k.getPauseStatus();

        while(paused){
            try{

            Thread.sleep(100);
            }catch(InterruptedException e){}
            paused = k.getPauseStatus();
        }


        HashMap<String, Boolean> keys = k.getKeys(); 

        if(jumped==false){

            if(keys.get("jump")==true && physics(v.stelios, grounds)[0]){
                jumped=true;
                ujump=40;
                tground=0;
            }
        }

            newton(v.stelios);
        }

        
    }

        public void newton(JPanel p){

            
        g=10;
        tground=0;
        if(ujump==0){

            ujump=0;
            ajump=0;
        }

        
        if(ujump<=0){

            double a=g-tground;

            double t = 0.2;

            

            double dy = uo * 0.2 + (1/2)*a*Math.pow(0.2,2);

            for(int i=0; i< (int)dy; i++){

                if(physics(p, grounds)[0]){
                    tground=g;
                    uo=0;

                    if(jumped==true){
                        jumped=false;
                    }
                    break;
                }
                
                p.setBounds(p.getX(), p.getY() + 1, p.getWidth(), p.getHeight());
            }
            
            if(a!=0){

                uo+=10*0.2;
                idx++;
            }
        }
        else if(ujump!=0){

            ajump = g-tground;
            double t =0.2;

            if(ajump!=0){

                ujump = ujump - ajump * t;
            }

            double dy = ujump*t - (1/2)*ajump*Math.pow(t, 2);

            for(int i=0; i<(int)dy; i++){

                if(physics(p, grounds)[1]){
                    ujump=0;
                    ajump=0;
                    break;
                }

            p.setBounds(p.getX(), p.getY() - 1 , p.getWidth(), p.getHeight());
            }
    
            // if(ajump!=0){

            //     ujump-=0.2*10;
            // }
        
        }       
        
    }

        public boolean[] physics(JPanel p, ArrayList<JPanel> floor){ 

            boolean[] physics = new boolean[4];

            boolean stand_floor0 = p.getY()+p.getHeight()==497;
            boolean wall0_rightside = p.getX()==0;;
            boolean wall0_leftside = p.getX()+p.getWidth()==900-28;

            boolean stand_floor=false;
            boolean tapped_floor=false;
            boolean wall_rightside=false;
            boolean wall_leftside=false;

            for(int i=0; i< floor.size(); i++){

                stand_floor = ((p.getY()+p.getHeight()==floor.get(i).getY() && p.getY()+p.getHeight()<floor.get(i).getY()+1) && ((p.getX()>=floor.get(i).getX() && p.getX()+p.getWidth()<=floor.get(i).getX()+floor.get(i).getWidth()) || (p.getX()+p.getWidth()>floor.get(i).getX() && p.getX()+p.getWidth()<floor.get(i).getX()+floor.get(i).getWidth()) || (p.getX()<floor.get(i).getX()+floor.get(i).getWidth() && p.getX()>floor.get(i).getX())));
                if(stand_floor){
                    stand_floor=true;
                    break;
                }
            }

            for(int i=0; i<floor.size(); i++){
                tapped_floor = ((p.getY() == floor.get(i).getY()+floor.get(i).getHeight() && p.getY() > floor.get(i).getY()+floor.get(i).getHeight() - 1) && ((p.getX()>=floor.get(i).getX() && p.getX()+p.getWidth()<=floor.get(i).getX()+floor.get(i).getWidth()) || (p.getX()+p.getWidth()>floor.get(i).getX() && p.getX()+p.getWidth()<floor.get(i).getX()+floor.get(i).getWidth()) || (p.getX()<floor.get(i).getX()+floor.get(i).getWidth() && p.getX()>floor.get(i).getX())));
                if(tapped_floor){
                    tapped_floor=true;
                    break;
                }
            }


            for(int i=0; i<floor.size(); i++){
                wall_rightside = ((p.getX()==floor.get(i).getX()+floor.get(i).getWidth() && p.getX()>floor.get(i).getX()+floor.get(i).getWidth() - 1) && ((p.getY()>=floor.get(i).getY() && p.getY()+p.getHeight() <= floor.get(i).getY()+floor.get(i).getHeight()) || (p.getY()+p.getHeight() > floor.get(i).getY() && p.getY()  + p.getHeight() <= floor.get(i).getY() + floor.get(i).getHeight()) || (p.getY()<floor.get(i).getY()+floor.get(i).getHeight() && p.getY() >= floor.get(i).getY()) || (p.getY()<floor.get(i).getY() && p.getY()+p.getHeight() > floor.get(i).getY()+floor.get(i).getHeight())  ));
                if(wall_rightside){
                    wall_rightside=true;
                    break;
                }
            }

            for(int i=0; i<floor.size(); i++){
                wall_leftside = ((p.getX()+p.getWidth()==floor.get(i).getX() && p.getX()+p.getWidth()<floor.get(i).getX() + 1) && ((p.getY()>=floor.get(i).getY() && p.getY()+p.getHeight() <= floor.get(i).getY()+floor.get(i).getHeight()) || (p.getY()+p.getHeight() > floor.get(i).getY() && p.getY() + p.getHeight() <= floor.get(i).getY() + floor.get(i).getHeight()) || (p.getY()<floor.get(i).getY()+floor.get(i).getHeight() && p.getY()>= floor.get(i).getY()) || (p.getY()<floor.get(i).getY() && p.getY()+p.getHeight() > floor.get(i).getY()+floor.get(i).getHeight()) ));
                if(wall_leftside){
                    wall_leftside=true;
                    break;
                }
            }

            boolean isStanding = stand_floor0 || stand_floor;

            boolean gotTapped = tapped_floor;

            boolean hitRightSide = wall0_rightside || wall_rightside;

            boolean hitLeftSide = wall0_leftside || wall_leftside;

            physics[0] = isStanding;
            physics[1] = gotTapped;
            physics[2] = hitRightSide;
            physics[3] = hitLeftSide;

            return physics;

        }




        


}

class VolumeManager implements Runnable{

    View v;
    Music m1, m2;


    public VolumeManager(View v, Music m1, Music m2){
        this.v=v;
        this.m1=m1;
        this.m2=m2;
    }

    @Override
    public void run() {


        m1.play();
        

        while(true){


            try{

            Thread.sleep(3);
            
            }catch(InterruptedException e){}



            int vol = v.volumeSlider.getValue();

            if(vol>=0 && vol<=10){
            m1.setVolume(0.1f);
            m2.setVolume(0.1f);

            }
            if(vol>10 && vol<=20){
            m1.setVolume(0.2f);
            m2.setVolume(0.2f);
            }
            if(vol>20 && vol<=30){

            m1.setVolume(0.3f);
            m2.setVolume(0.3f);
            }
            if(vol>30 && vol<=40){

            m1.setVolume(0.4f);
            m2.setVolume(0.4f);
            }
            if(vol>40 && vol<=50){

            m1.setVolume(0.5f);
            m2.setVolume(0.5f);
            }
            if(vol>50 && vol<=60){

            m1.setVolume(0.6f);
            m2.setVolume(0.6f);
            }
            if(vol>60 && vol<=70){

            m1.setVolume(0.7f);
            m2.setVolume(0.7f);
            }
            if(vol>70 && vol<=80){

            m1.setVolume(0.8f);
            m2.setVolume(0.8f);
            }
            if(vol>80 && vol<=90){

            m1.setVolume(0.9f);
            m2.setVolume(0.9f);
            }
            if(vol>90 && vol<=100){

            m1.setVolume(1.0f);
            m2.setVolume(1.0f);
            }
            


            v.volumeLabel.setText("Volume: "+ vol);

            }
        }
        
    }




