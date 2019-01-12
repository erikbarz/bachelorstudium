package kapitel2;


public class SimpleListNode{

	private Object obj;
	private SimpleListNode next;
	
	
	
	public SimpleListNode(Object obj, SimpleListNode next) {
		this.obj = obj;
		this.next = next;
	}



	public Object getElement() {
		return obj;
	}



	public void setElement(Object obj) {
		this.obj = obj;
	}



	public SimpleListNode getNext() {
		return next;
	}



	public void setNext(SimpleListNode next) {
		this.next = next;
	}

	

}
