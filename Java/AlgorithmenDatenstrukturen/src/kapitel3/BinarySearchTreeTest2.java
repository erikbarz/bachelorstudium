package kapitel3;

public class BinarySearchTreeTest2 {

	public static void main(String[] args) {
		BinarySearchTree2 tree = new BinarySearchTree2();
		
		tree.insert("F");
		tree.insert("C");
		tree.insert("I");
		tree.insert("A");
		tree.insert("E");
		tree.insert("G");
		tree.insert("J");
		tree.insert("D");

		tree.transversePreorder();
		
		System.out.println("\n" + tree.findNode("D"));
		System.out.println(tree.findNode("J"));
		System.out.println(tree.findNode("B"));
	}

}
