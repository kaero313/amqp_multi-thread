package thread;


public class threadTester {

    public static void main(String args[]){

        //Thread thread = new Thread(new thread(1, 0, 1000));
        //thread.start();

        threadPool threadPool = new threadPool(5, 10, 3000, 100);

        for(int i=0; i<=1000; i++){
            Thread thread = new Thread(new thread(1, 0, 1000));
            threadPool.execute(thread);
        }




    }



}
