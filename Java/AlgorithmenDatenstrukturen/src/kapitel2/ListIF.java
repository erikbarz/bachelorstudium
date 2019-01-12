package kapitel2;

import java.util.NoSuchElementException;

public interface ListIF {
	void addFirst(Object o);
	
	void addLast(Object o);
	
	Object getFirst() throws NoSuchElementException;
	
	Object getLast() throws NoSuchElementException;
	
	Object removeFirst() throws NoSuchElementException;
	
	Object removeLast() throws NoSuchElementException;
	
	int size();
	
	boolean isEmpty();
}
