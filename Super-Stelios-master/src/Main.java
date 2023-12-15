import java.util.HashMap;

public class Main{


    public static void main(String[] a){

        View v = new View();
        KeyBinder k = new KeyBinder(v, v.mainTheme);
        Controller c = new Controller(v, k);
        
        
        k.giveCon(c);
        v.frame.addKeyListener(k);
        
        Newton2_0 grav = new Newton2_0(v, k, v.mainTheme);
        Movement move = new Movement(v, k);
        VolumeManager vol = new VolumeManager(v, v.menu, v.mainTheme);

        v.giveNewtonToView(grav);
        v.AddCollisions(v.collisions);

        move.giveNewtonToMovement(grav);

        Thread movement = new Thread(move);
        Thread volume = new Thread(vol);
        Thread gravity = new Thread(grav);
        



        HashMap<String, Thread> threadMap = new HashMap<String, Thread>();
        threadMap.put("movement", movement);
        threadMap.put("volume", volume);
        threadMap.put("gravity", gravity);
        

        c.initView();
        c.initController();
        c.giveThreads(threadMap);


        movement.start();
        volume.start();
        

    }


}