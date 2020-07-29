public class TE implements Runnable{

    private Thread thread;
    private String threadName;
    public String deneme;

    public TE(){
        this.threadName = "Default-Thread";
        System.out.println("Thread oluşturuluyor >>> " + threadName + " <<<");
    }

    public TE (String threadName){
        this.threadName = threadName;
        System.out.println("Thread oluşturuluyor >>> " + threadName + " <<<");
    }

    @Override
    public void run() {

        System.out.println("Thread çalışmaya başladı >>> " + this.threadName + " <<<");

        try {
            for (int i=0; i<=50; i++){
                Thread.sleep(1000);
                System.out.println(this.threadName + " : " + i);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }

        System.out.println("Thread çalışmayı sonlandırdı >>> " + this.threadName + " <<<");
    }

    public void start(){
        System.out.println("Thread başlatılıyor... >>> " + this.threadName + " <<<");
        if (thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

}
