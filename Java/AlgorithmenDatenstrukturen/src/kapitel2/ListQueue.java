package kapitel2;

import java.util.NoSuchElementException;

public class ListQueue implements QueueIF {

	private SimpleList list;

	public ListQueue() {
		list = new SimpleList();
	}

	@Override
	public void enter(Object obj) {
		list.addFirst(obj);
	}

	@Override
	public Object leave() throws NoSuchElementException {
		return list.removeLast();
	}

	@Override
	public Object front() throws NoSuchElementException {
		return list.getLast();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
