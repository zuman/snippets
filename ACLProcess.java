
public class ACLProcess {

    public static void main(String args[]){
        ACLProcess acl=new ACLProcess();
        acl.execute();
    }

    private void execute(){
        int threadCount=4;
        Thread threads[]=new Thread[threadCount];
        try {
            for (int t = 0; t < threadCount; t++) {
                System.out.println("Inside loop for thread #" + t);
                threads[t] = new Thread(new ACLThread("This is thread " + t));
                System.out.println("Thread #" + t + " starting");
                threads[t].start();
            }
            boolean allDead=false;
            while(! allDead){
                allDead=true;
                for (int t = 0; t < threadCount; t++)
                    if(threads[t].isAlive())    allDead=false;
                Thread.sleep(2000);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Exiting execute() !");
    }

    public class ACLThread implements Runnable{

        private String ctx;

        ACLThread(String ctx){
            this.ctx=ctx;
        }

        @Override
        public void run() {
            try{
                for(int i=0;i<10;i++){
                    System.out.println(status(ctx,i));
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String status(String ctx, int i){
        return ctx+". Index #"+i;
    }
}
