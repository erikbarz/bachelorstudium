package kapitel2;

import java.util.NoSuchElementException;

public class SimpleList implements ListIF {

	private SimpleListNode head = null;

	public SimpleList() {
		head = new SimpleListNode(null, null);
	}

	@Override
	public void addFirst(Object o) {
		SimpleListNode n = new SimpleListNode(o, head.getNext());
		head.setNext(n);
	}

	@Override
	public void addLast(Object o) {
		SimpleListNode m = head;
		while (m.getNext() != null) {
			m = m.getNext();
		}
		SimpleListNode n = new SimpleListNode(o, null);
		m.setNext(n);

	}

	@Override
	public Object getFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.getNext().getElement();
	}

	@Override
	public Object getLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		SimpleListNode m = head;
		while (m.getNext() != null) {
			m = m.getNext();
		}
		return m.getElement();
	}

	@Override
	public Object removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Object o = head.getNext().getElement();
		head.setNext(head.getNext().getNext());
		return o;
	}

	@Override
	public Object removeLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		SimpleListNode m = head;
		while (m.getNext().getNext() != null) {
			m = m.getNext();
		}
		Object o = m.getNext().getElement();
		m.setNext(null);
		return o;
	}

	@Override
	public int size() {
		int s = 0;
		SimpleListNode n = head;
		while(n.getNext()!=null){
			s++;
			n=n.getNext();
		}
		return s;
	}

	@Override
	public boolean isEmpty() {
		return head.getNext()==null;
	}
	
	class SimpleListIterator implements IteratorIF{
		private SimpleListNode node=null;

		public SimpleListIterator() {
			node=head.getNext();
		}

		
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return node!=null;
		}

		@Override
		public Object next() throws NoSuchElementException {
			if(node==null){
				throw new NoSuchElementException();
			}
			Object o=node.getElement();
			node=node.getNext();
			return o;
		}
		
	}
	
	public IteratorIF iterator(){
		return new SimpleListIterator();
	}

}
