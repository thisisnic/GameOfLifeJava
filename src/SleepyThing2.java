import javax.swing.SwingUtilities;

class myThread extends Thread {

    public void run() {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Hello from a thread!");
    }

   

}


public class SleepyThing2 {
	
	public static void main(String[] argvs) throws InterruptedException{

		
		myThread m = new myThread();
		m.start();
		m.join(); // This line means that we wait for m to execute before we continue
		System.out.println("This should go last");
	}
	
}
