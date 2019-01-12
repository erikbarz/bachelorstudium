package kapitel2;

public class ListQueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListQueue queue=new ListQueue();
		queue.enter("eins");
		queue.enter("2");
		queue.enter("drei");
		queue.enter("4");
		queue.enter("fünf");
		
		while(!queue.isEmpty()){
			System.out.println(queue.leave());
		}

	}

}
