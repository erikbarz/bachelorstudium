package kapitel2;

import java.util.NoSuchElementException;

public class ListStack implements StackIF {

	private SimpleList list;
	
		
	public ListStack() {
		list=new SimpleList();
	}

	@Override
	public void push(Object obj) {
		list.addFirst(obj);
	}

	@Override
	public Object pop() throws NoSuchElementException {
		return list.removeFirst();
	}

	@Override
	public Object top() throws NoSuchElementException {
		return list.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
