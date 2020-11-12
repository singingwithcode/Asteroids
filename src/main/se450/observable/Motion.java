package main.se450.observable;

/*
 * Name     : 
 * Depaul#  : 
 * Class    : SE 450
 * Project  : Final
 * Due Date : xx/xx/2017
 *
 * class Motion
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import main.se450.factories.NamedThreadFactory;
import main.se450.interfaces.IObservable;
import main.se450.singletons.ConfigManager;

public class Motion implements Runnable {
	private static Motion motion = new Motion();

	private boolean inMotion = false;
	private ScheduledThreadPoolExecutor scheduler = null;

	private final static int FRAMES_PER_SECOND = ConfigManager.getConfigManager().getFPS();//30;

	private final static int NANO_SECONDS_PER_SECOND = 1000000000;

	private ArrayList<IObservable> observables = new ArrayList<IObservable>();

	private Motion() {
	}
 
	private static Motion getMotion() {
		return motion;
	}

	public static void startObserving(final IObservable iObservable) {
		Motion motion = getMotion();
		if (motion != null)
			motion.addObserver(iObservable);
	}

	private void addObserver(final IObservable iObservable) {
		if (iObservable != null) {
			if (!isObserving(iObservable)) {
				observables.add(iObservable);

				if (!getIsInMotion())
					startMotion();
			}
		}
	}

	public static void stopObserving(final IObservable iObservable) {
		Motion motion = getMotion();
		if (motion != null)
			motion.removeObserver(iObservable);
	}

	private void removeObserver(final IObservable iObservable) {
		observables.remove(iObservable);

		if (observables.isEmpty())
			stopMotion();
	}

	public static void toggleObserving() {
		Motion motion = getMotion();
		if (motion != null)
			motion.toggleMotion();
	}

	private void toggleMotion() {
		if (getIsInMotion()) {
			stopMotion();
		} else {
			startMotion();
		}
	}

	private boolean isObserving(final IObservable iObservable) {
		boolean bIsObserving = false;

		if (iObservable != null) {
			bIsObserving = observables.contains(iObservable);
		}

		return bIsObserving;
	}

	private void startMotion() {
		setIsInMotion(true);

		if (FRAMES_PER_SECOND > 0) {
			if (scheduler == null) {
				NamedThreadFactory motionThread = new NamedThreadFactory("Motion");

				scheduler = new ScheduledThreadPoolExecutor(1, motionThread);
				if (scheduler != null)
					scheduler.scheduleAtFixedRate(this, 0, NANO_SECONDS_PER_SECOND / FRAMES_PER_SECOND,
							TimeUnit.NANOSECONDS);// ~20 frames per second
			}
		}
	}

	private void stopMotion() {
		setIsInMotion(false);

		if (scheduler != null) {
			scheduler.shutdown();

			scheduler = null;
		}
	}

	public static final boolean isInMotion() {
		Motion motion = getMotion();
		return (motion != null ? motion.getIsInMotion() : false);
	}

	public final boolean getIsInMotion() {
		return inMotion;
	}

	private final void setIsInMotion(final boolean bIsInMotion) {
		inMotion = bIsInMotion;
	}

	public void run() {
		if ((observables != null) && (isInMotion())) {
			Iterator<IObservable> iiObservables = observables.iterator();
			while (iiObservables.hasNext()) {
				IObservable iObservable = iiObservables.next();
				if (iObservable != null) {
					iObservable.update();
				}
			}
		}
	}
}
