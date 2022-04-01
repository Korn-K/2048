public class TimeThread extends Thread{
    public void run(){
        while(true){
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
            }
            ScorePanel.setTime(ScorePanel.getTime()+1);
        }
    }
}
