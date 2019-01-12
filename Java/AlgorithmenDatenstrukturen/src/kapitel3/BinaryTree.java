package kapitel3;

public class BinaryTree {

	class TreeNode {
		TreeNode left = nullNode;
		TreeNode right = nullNode;
		String element;

		public TreeNode(String e) {
			element = e;
		}

		public String getElement() {
			return element;
		}

		public TreeNode getLeft() {
			return left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setLeft(TreeNode n) {
			left = n;
		}

		public void setRight(TreeNode n) {
			right = n;
		}
	}

	private TreeNode head, nullNode;

	public BinaryTree() {
		head = new TreeNode(null);
		nullNode = new TreeNode(null);
		head.setRight(nullNode);
		nullNode.setLeft(nullNode);
		nullNode.setRight(nullNode);

	}

	public void traversePreorder() {
		printPreorder(head.getRight());
	}

	private void printPreorder(TreeNode n) {
		if (n != nullNode) {
			System.out.print(n.getElement());
			printPreorder(n.getLeft());
			printPreorder(n.getRight());
		}
	}

	public void traverseInorder() {
		printInorder(head.getRight());
	}

	private void printInorder(TreeNode n) {
		if (n != nullNode) {
			printInorder(n.getLeft());
			System.out.print(n.getElement());
			printInorder(n.getRight());
		}
	}

	public void traversePostorder() {
		printPostorder(head.getRight());
	}

	private void printPostorder(TreeNode n) {
		if (n != nullNode) {
			printPostorder(n.getLeft());

			printPostorder(n.getRight());
			System.out.print(n.getElement());
		}
	}

	public void buildTest() {
		TreeNode a = new TreeNode("a");
		TreeNode b = new TreeNode("b");
		TreeNode c = new TreeNode("c");
		TreeNode d = new TreeNode("d");
		TreeNode e = new TreeNode("e");
		TreeNode f = new TreeNode("f");
		TreeNode g = new TreeNode("g");
		head.right = a;
		a.left = b;
		a.right = c;
		b.left = d;
		b.right = e;
		c.left = f;
		c.right = g;
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.buildTest();
		System.out.println("Preorder:");
		tree.traversePreorder();
		System.out.println("\n" + "Inorder:");
		tree.traverseInorder();
		System.out.println("\n" + "Postorder:");
		tree.traversePostorder();
	}

}
