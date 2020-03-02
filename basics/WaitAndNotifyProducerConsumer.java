package basics;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class WaitAndNotifyProducerConsumer {
	public static void main(String[] args) {
		Task task = new Task();
		int maxSize = 10;
		
		Thread producer = new Thread(new NotifyThreadProducer(task, maxSize), "Producer");
		Thread consumer = new Thread(new WaitThreadConsumer(task, maxSize), "Consumer");
		producer.start();
		consumer.start();
	}
}

class WaitThreadConsumer implements Runnable {
	private Task task;
	int maxSize;

	public WaitThreadConsumer(Task task, int maxSize) {
		this.task = task;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (task) {
				while (task.size() == 0) {
					System.out.println("Queue is empty waiting for consumer to consume");
					try {
						task.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				System.out.println("Consumer " + task.consume());
				task.notify();
			}

		}
	}

}

class NotifyThreadProducer implements Runnable {
	private Task task;
	int maxSize;
	Random r = new Random(6);

	public NotifyThreadProducer(Task task, int maxSize) {
		this.task = task;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (task) {
				while (task.size() == maxSize) {
					System.out.println("Queue is full waiting for consumer to consume");
					try {
						task.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				int i = r.nextInt();
				task.produce(i);
				System.out.println("Producer " + i);
				task.notify();
			}

		}

	}

}

class Task {
	Queue<Integer> q = new LinkedList<>();

	public void produce(int i) {
		q.add(i);
	}

	public int consume() {
		return q.poll();
	}

	public int size() {
		return q.size();
	}

	public int getValue() {
		return q.peek();
	}

}
