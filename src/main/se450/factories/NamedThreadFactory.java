package main.se450.factories;

import java.util.concurrent.ThreadFactory;

/**
 *
 * The thread factory which takes a thread and gives it a name
 * 
 * @author Anthony Freund
 *
 */

public class NamedThreadFactory implements ThreadFactory {
	private String threadName = null;

	/**
	 *
	 * default constructor
	 *
	 * @param the
	 *            name for the thread
	 *
	 */

	public NamedThreadFactory(final String sThreadName) {
		threadName = sThreadName;
	}

	/**
	 *
	 * create a new thread given a runnable
	 *
	 * @param the
	 *            runnable for the thread
	 *
	 * @return the newly constructed thread
	 *
	 */

	public Thread newThread(Runnable runnable) {
		return new Thread(runnable, threadName);
	}

	/**
	 *
	 * gets the name of the thread
	 * 
	 * @author Anthony Freund
	 *
	 * @return the threads name
	 *
	 */

	public final String getThreadName() {
		return threadName;
	}
}