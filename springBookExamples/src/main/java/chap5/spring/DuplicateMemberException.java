package chap5.spring;

public class DuplicateMemberException extends RuntimeException{
	public DuplicateMemberException(String message) {
		super(message);
	}
}
