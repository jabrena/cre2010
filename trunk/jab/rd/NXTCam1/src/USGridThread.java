import lejos.nxt.*;

public class USGridThread extends Thread{
        private SensorMux sm1;

        private int[] northDistances = {0,0,0,0,0};
        private int[] southDistances = {0,0,0,0,0};
        private int[] eastDistances = {0,0,0,0,0};
        private int[] westDistances = {0,0,0,0,0};

        private int delay = 250;

        //private DataBridge db;

        //public USGridThread(DataBridge _db, SensorPort sp){
                //sm1 = new SensorMux(sp);
                //db  =_db;
        //}

        public void run(){
                //It is a infinite task
                while(true){
                        measure();
                        communicate();
                }
        }

        private void measure(){

        }

        private void communicate(){

        }

        private void sleep(int miliseconds){
                try {Thread.sleep(miliseconds);} catch (Exception e) {}
        }
}
