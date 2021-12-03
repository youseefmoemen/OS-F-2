public class Semaphore{
    int value = 0;
    Semaphore(){value = 0;}
    Semaphore(int init){value = init;}
    public synchronized void P(){ //Wait
        value--;
        if(value < 0) try{wait();} catch(InterruptedException e){}
    }

    public synchronized void V(){ //Signal
        value++;
        if(value >= 0) notify();
    }
}
