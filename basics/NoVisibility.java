package basics;

public class NoVisibility {
	
   
	
	public static void main(String[] args) {
		ReaderThread r = new ReaderThread(true, 3);
		r.start();
		
		new ReaderThread(false, 5).start();
		
	}

}

class ReaderThread extends Thread{
	private static boolean ready;
	private static int number;
	public ReaderThread(boolean ready, int number) {
		this.ready=ready;
		this.number =number;
	}
	@Override
	public void run() {
		while (!ready)
			Thread.yield();
			System.out.println(number);
	}
}
