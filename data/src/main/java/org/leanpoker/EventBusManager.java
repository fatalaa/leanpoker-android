package org.leanpoker;

/**
 * Created by tbalogh on 12/09/15.
 */
public class EventBusManager {

	private static final EventBusManager mInstance = new EventBusManager();

	private EventBusManager() {
		// singleton
	}

	public static EventBusManager getInstance() {
		return mInstance;
	}

	
}
