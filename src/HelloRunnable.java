public class HelloRunnable implements Runnable {

    public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
    	 System.out.println("Hello from a main thread!");
        (new Thread(new HelloRunnable())).start();
        System.out.println("Hello from a main thread!");
    }

}