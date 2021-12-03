public class Device extends Thread{
    String name;
    String type;
    Router connectRouter;
    Device(String a,String b, Router r){
        name = a;
        type = b;
        connectRouter = r;
    }
    public void run(){
        connectRouter.connect(this);
        connectRouter.action(10);
        connectRouter.disconnect(this);

    }
}
