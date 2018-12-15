package banking.common.application.query.exception;

public class EmptyResultException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyResultException(String message) {
		super(message);
	}
}
