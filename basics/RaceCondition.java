package basics;

public class RaceCondition {
	public static void main(String[] args) {
		Counter c = new Counter();
		new MyThread1("a", c).start();
		new MyThread1("b", c).start();
	}	
}

class MyThread1 extends Thread {
	String name;
	Counter c ;
	public MyThread1(String name, Counter c) {
		super(name);
		this.name = name ;
		this.c =c;
		
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
				c.add(1);
				System.out.println(name +" "+c.count);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println();
		
	}
	
}


 class Counter {

    protected  long count = 0;

    public  synchronized void add(long value){
        this.count = this.count + value;
    }
 }