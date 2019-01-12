package kapitel2;

import java.util.NoSuchElementException;

public interface IteratorIF {
	
	boolean hasNext();
	
	Object next() throws NoSuchElementException;
}
