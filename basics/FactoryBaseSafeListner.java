package basics;

import java.awt.Event;
import java.util.EventListener;


public class FactoryBaseSafeListner {
	
	private final EventListener listner;
	private FactoryBaseSafeListner() {
		listner = new EventListener() {
			public void onEvent(Event e) {
				
			}
		};
	}
	public static FactoryBaseSafeListner newInstance(EventSource source) {
		FactoryBaseSafeListner safe = new FactoryBaseSafeListner();
		source.registerListener(safe.listner);
		return safe;
	}

}
