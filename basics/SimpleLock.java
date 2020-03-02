package basics;

public class SimpleLock {
	private boolean isLocked = false;
	private Thread currThread = null;
	
	public void lock() {
		while(isLocked) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isLocked =true;
		currThread = Thread.currentThread();
		
	}  
	public void unlock() {
		if (currThread!=Thread.currentThread()) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		isLocked =false;
		currThread =null;
		notify();
	}
	

}
