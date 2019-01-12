package kapitel2;

import java.util.NoSuchElementException;

public interface QueueIF {
	void enter(Object obj);
	Object leave() throws NoSuchElementException;
	Object front() throws NoSuchElementException;
	boolean isEmpty();
}
