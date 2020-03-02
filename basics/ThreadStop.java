package basics;

public class ThreadStop {

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			MyThread a = new MyThread(i);
			new Thread(a).start();
			if (i==5) {
				a.doStop();
			}
		}
	
	}

}

class MyThread implements Runnable {
	private int i;

	private boolean doStop = false;

	public synchronized void doStop() {
		this.doStop = true;
	}
	public MyThread(int i ) {
		this.i = i;
	}

	private synchronized boolean keepRunning() {
		return this.doStop == false;
	}

	@Override
	public void run() { 
		while (keepRunning()) {
			System.out.println("Thread running "+ i);

			try {
				Thread.sleep(3L * 1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread stoped "+i );

	}

}
