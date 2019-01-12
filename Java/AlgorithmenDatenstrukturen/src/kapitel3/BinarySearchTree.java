package kapitel3;

public class BinarySearchTree {

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

	public BinarySearchTree() {
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

	public TreeNode findNode(String s) {
		TreeNode t = findNodeRekursiv(s, head.getRight());

		return t;
	}

	public TreeNode findNodeRekursiv(String s, TreeNode n) {
		if (n == nullNode)
			return null;
		if (s.compareTo(n.getElement()) < 0)
			return findNodeRekursiv(s, n.getLeft());
		if (s.compareTo(n.getElement()) > 0)
			return findNodeRekursiv(s, n.getRight());
		return n;
	}

//	public boolean insert(String s) {
//		TreeNode parent = head;
//		TreeNode n = head.getRight();
//
//		while (n != nullNode) {
//			parent = n;
//			if (s.compareTo(n.getElement()) < 0) {
//				n = n.getLeft();
//			} else {
//				if (s.compareTo(n.getElement()) > 0) {
//					n = n.getRight();
//				} else
//					return false;
//			}
//		}
//
//		TreeNode newNode = new TreeNode(s);
//		newNode.setLeft(nullNode);
//		newNode.setRight(nullNode);
//		if (parent != head && s.compareTo(n.getElement()) < 0) {
//			parent.setLeft(newNode);
//		} else {
//			parent.setRight(newNode);
//		}
//		return true;
//
//	}

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

		System.out.println("Preorder:");
		tree.traversePreorder();
		System.out.println("\n" + "Inorder:");
		tree.traverseInorder();
		System.out.println("\n" + "Postorder:");
		tree.traversePostorder();
	}

}
