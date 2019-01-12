package kapitel3;

public class BinarySearchTree2 {

	// Attributes
	private TreeNode head;
	private TreeNode nullNode;

	// Constructor
	public BinarySearchTree2() {
		head = new TreeNode(null);
		nullNode = new TreeNode(null);
		head.setRight(nullNode);
		nullNode.setLeft(nullNode);
		nullNode.setRight(nullNode);
	}

	// Methods
	public void transversePreorder() {
		printPreorder(head.getRight());
	}

	public void transverseInorder() {
		printInorder(head.getRight());
	}

	public void transversePostorder() {
		printPostorder(head.getRight());
	}

	private void printPreorder(TreeNode n) {
		if (n != nullNode) {
			System.out.print(n.getElement() + " ");
			printPreorder(n.getLeft());
			printPreorder(n.getRight());
		}
	}

	private void printInorder(TreeNode n) {
		if (n != nullNode) {
			printInorder(n.getLeft());
			System.out.print(n.getElement() + " ");
			printInorder(n.getRight());
		}
	}

	private void printPostorder(TreeNode n) {
		if (n != nullNode) {
			printPostorder(n.getLeft());
			printPostorder(n.getRight());
			System.out.print(n.getElement() + " ");
		}
	}

	public TreeNode findNode(String s) {
		return findNodeRecursive(s, head.getRight());
	}

	private TreeNode findNodeRecursive(String s, TreeNode n) {
		if (n == nullNode) {
			return null;
		}

		if (s.compareTo(n.getElement()) < 0) {
			return findNodeRecursive(s, n.getLeft());
		} 
		else if (s.compareTo(n.getElement()) > 0) {
			return findNodeRecursive(s, n.getRight());
		}
		else {
			return n;
		}
	}
	
	public boolean insert(String s) {
		boolean left = false;
		TreeNode parent = head;
		TreeNode n = head.getRight();
		
		while (n != nullNode) {
			parent = n;
			left = false;
			if (s.compareTo(n.getElement()) < 0) {
				n = n.getLeft();
				left = true;
			}
			else if (s.compareTo(n.getElement()) > 0) {
				n = n.getRight();
			}
			else {
				return false;
			}			
		}
		
		TreeNode m = new TreeNode(s);
		m.setLeft(nullNode);
		m.setRight(nullNode);
		
		if (left) {
			parent.setLeft(m);
		} else  {
			parent.setRight(m);
		}
		return true;
	}

	// Subclass
	class TreeNode {
		TreeNode left = null;
		TreeNode right = null;
		String element;

		public TreeNode(String e) {
			element = e;
		}

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode n) {
			this.left = n;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode m) {
			this.right = m;
		}

		public String getElement() {
			return element;
		}

		public void setElement(String e) {
			this.element = e;
		}

	}
}
