package scRT.tracker;

import scRT.tracker.exception.PropagationException;

public abstract class Propagation {
	protected String id;
	protected int type;

	public static final int IMPERATIVE = 1;
	public static final int CONDUCTIVE = 2;
	public static final int SELECTIVE = 3;

	public abstract void fire() throws PropagationException;

	public abstract void checkPropagation(ConfigurationValue input)
			throws PropagationException;
}