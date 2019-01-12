package kapitel2;

import java.util.NoSuchElementException;

public interface StackIF {
	void push(Object obj);

	Object pop() throws NoSuchElementException;

	Object top() throws NoSuchElementException;

	boolean isEmpty();
}
