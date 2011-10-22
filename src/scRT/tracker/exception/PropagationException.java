package scRT.tracker.exception;

public abstract class PropagationException extends Exception {

	private static final long serialVersionUID = -5067240024259351125L;

	public PropagationException() {
	}

	public PropagationException(String message) {
		super(message);
	}

}
