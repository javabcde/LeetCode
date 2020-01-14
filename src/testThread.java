/**
 * Created by TOM
 * On 2020/1/14 11:27
 */
public class testThread {

  public static void main(String[] args) {
    for (int i = 0; i < 3; i++) {
      Thread thread = new MyThread();
      thread.start();
    }

  }


  static class Sync {

    public void test() {

      synchronized (this) {
        System.out.println("test开始..");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("test结束..");
      }

    }
  }

  static class MyThread extends Thread {

    public void run() {
      Sync sync = new Sync();
      sync.test();
    }
  }
}
