package kapitel2;

public class ListStackTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListStack stack=new ListStack();
		stack.push("eins");
		stack.push("2");
		stack.push("drei");
		stack.push("4");
		stack.push("fünf");
		
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}

}
