package basics;

public class DeadLockExample {
	public static void main(String[] args) {
		TestDeadLock t1 = new TestDeadLock();
		TestDeadLock t2 = new TestDeadLock();
		new Thread (new Thread1(t1,t2)).start();
		new Thread (new Thread2(t1,t2)).start();
	}
	
}

class Thread1 implements Runnable{
	TestDeadLock t1;
	TestDeadLock t2;
	public Thread1(TestDeadLock t1, TestDeadLock t2) {
		super();
		this.t1 = t1;
		this.t2 = t2;
	}
	 @Override
	public void run() {
		t1.test2(t2);
		
	}
}

class Thread2 implements Runnable{
	TestDeadLock t1;
	TestDeadLock t2;
	public Thread2(TestDeadLock t1, TestDeadLock t2) {
		super();
		this.t1 = t1;
		this.t2 = t2;
	}
	 @Override
	public void run() {
		t2.test2(t1);
		
	}
}
class TestDeadLock{
	 synchronized void test1(TestDeadLock s2){
		 System.out.println("Test 1 begin");
		 Util.sleep(1000);
		 s2.test2(this);
		 System.out.println("Test 1 end");
	 }
	 
	 synchronized void test2(TestDeadLock s1){
		 System.out.println("Test 2 begin");
		 Util.sleep(1000);
		// s1.test1(this);
		 System.out.println("Test 2 end");
	 }
}

class Util{
	
	static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		}catch(InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
