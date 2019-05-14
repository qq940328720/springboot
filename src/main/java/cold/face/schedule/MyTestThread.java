package cold.face.schedule;

public class MyTestThread extends Thread {

    @Override
    public void run() {
        for (int i = 1; i > 0; i++) {
            try {
                sleep(1000);
                System.out.println(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
