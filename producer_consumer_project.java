class company{
    int n;
   boolean f = false;
   synchronized public void produced_item(int n) throws InterruptedException{
    if(f)
    {
          wait();
    }
        this.n = n;
        System.out.println("Produced : "+ this.n);
        f = true;
        notify();
    }

  synchronized  public int consume_item() throws InterruptedException{
    if(!f){
        wait();
    }
        System.out.println("Consumed: " + this.n);
        f = false;
        notify();
        return this.n;
    }
}
class producer extends Thread{
    company c;
    producer(company c){
        this.c = c;

    }
    public void run(){
        int i=1;
        while(i<1000){
            try {
                this.c.produced_item(i);
            }catch(Exception e){

            }
            try{
            Thread.sleep(1000);
            i++;
        }catch(Exception e){
                
        }
    }
}
}
class Consumer extends Thread{
    company c;
    Consumer(company c){
        this.c = c;
    }
    public void run(){
        int i=1;
        while(i<1000){
            try {
                this.c.consume_item();
            } catch (InterruptedException e)
             {

            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){

            }
        }
    }
}
public class producer_consumer_project {
    public static void main(String[] args) {

        company comp = new company();
        producer p = new producer(comp);
        Consumer c = new Consumer(comp);

        p.start();
        c.start();
    } 
}