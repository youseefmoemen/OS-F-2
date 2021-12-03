public class Semaphore{
    int value = 0;
    Semaphore(){value = 0;}
    Semaphore(int init){value = init;}
    public synchronized void P(Device d){ //Wait
        value--;
        if(value < 0){
            try{
                System.out.println(d.name + " " + d.type + " arrived and waiting");
                wait();
            } catch(InterruptedException e){}
        }else{
            System.out.println(d.name + " " + d.type + "Arrived");
        }
    }

    public synchronized void V(){ //Signal
        value++;
        if(value >= 0) notify();
    }
}
