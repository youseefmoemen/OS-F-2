import java.util.Random;

public class Device extends Thread{
    public String name;
    public String type;
    Router connectRouter;
    Device(String a,String b, Router r){
        name = a;
        type = b;
        connectRouter = r;
    }
    public void run(){
        Random r = new Random();
        connectRouter.connect(this);
        connectRouter.action(r.nextInt(2));
        connectRouter.disconnect(this);
    }
}
