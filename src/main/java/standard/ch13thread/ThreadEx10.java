package standard.ch13thread;

public class ThreadEx10 implements Runnable {
    static protected boolean autoSave = false;
    @Override
    public void run() {
        while (true) {
            try{
                Thread.sleep(3 * 1000); //3 seconds
            }catch (InterruptedException e){}
            if(autoSave) {
                autoSave();
            }
        }
    }
    public void autoSave() {
        System.out.println("작업파일이 자동 저장 되었습니다.");
    }
}
