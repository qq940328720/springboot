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

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        String a = null;
        builder.append("123");
        builder.append(a);
        builder.toString();//132null

        StringBuffer buffer = new StringBuffer();
        buffer.append(123);
        buffer.append(a);//123null

        String b = "123";
        b = a + b;
    }

}
