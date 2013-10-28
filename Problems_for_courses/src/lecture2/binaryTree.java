package lecture2;

public class binaryTree <T extends Comparable> {

	public binaryTree() {
		root = null;
		left = null;
		right = null;
		number = null;
		amount = 0;
	}
	
	public void add(T value) {
		if (root == null) {
			root = new binaryTree();
			root.setValue(value);
			amount++;
		} else {
			binaryTree node = root;
			while (true) {
				if (root == null) {
					root = new binaryTree();
					root.setValue(value);
					amount++;
					break;
				} else {
					if (value.compareTo(root.getValue()) < 0) {
						if (root.left != null)
							root = root.left;
						else {
							root.left = new binaryTree();
							root.left.setValue(value);
							amount++;
							break;
						}
					} else {
						if (value.compareTo(root.getValue()) > 0) {
							if (root.right != null)
								root = root.right;
							else {
								root.right = new binaryTree();
								root.right.setValue(value);
								amount++;
								break;
							}
						} else {
							System.out.println("this value already exist");
							break;
						}
					}
				}
			}
			root = node;
		}
}

	public boolean exists(T value) {
		return search(value, root);
	}

	public <T extends Comparable> boolean remove(Comparable value) {
		boolean result = false;
		if (root == null)
			result = false;
		else {
			binaryTree<T> n = new binaryTree <T>();
			n.right = root;
			binaryTree<T> node = n;
			boolean direction = true;
			while (true) {
				if (root == null) {
					result = false;
					break;
				}
				if (value.compareTo(root.getValue()) > 0) {
					n = root;
					root = root.right;
					direction = true;
				} else if (value.compareTo(root.getValue()) < 0) {
					n = root;
					root = root.left;
					direction = false;
				} else {
					if (root.left == null && root.right == null) {
						if (direction)
							n.right = null;
						else
							n.left = null;
						amount--;
						result = true;
						break;
					} else if (root.left == null) {
						if (direction)
							n.right = root.right;
						else
							n.left = root.right;
						root = null;
						amount--;
						result = true;
						break;
					} else if (root.right == null) {
						if (direction)
							n.right = root.left;
						else
							n.left = root.left;
						root = null;
						amount--;
						result = true;
						break;
					} else {
						if (root.right.left == null) {
							root.right.left = root.left;
							if (direction)
								n.right = root.right;
							else
								n.left = root.right;
							root = null;
							amount--;
							result = true;
							break;
						} else {
							n = root.right;
							while (n.left != null) n = n.left;
							root.setValue(n.getValue());
							n = root.right;
							value = root.getValue();
							root = root.right.left;
							direction = false;
						}
					}
				}
			}
			root = node.right;
			n = null;
		}

		return result;
	}

	public void printByDepth() {
		if (root == null)
			System.out.println("Tree is empty");
		else
			byDepth(root);
		System.out.println();
	}

	public void printByWidth() {
		if (root == null)
			System.out.println("Tree is empty");
		else {
			binaryTree[] mas = new binaryTree[amount];
			int counter = 0;
			mas[counter] = root;
			byWidth(mas, counter, counter);
			System.out.println();
		}
	}

	private <T extends Comparable> boolean search(T value, binaryTree root) {
		boolean result = false;
		if (root == null) {
			result = false;
		} else {
			if (value == root.getValue())
				result = true;
			else {
				if (value.compareTo(root.getValue()) < 0) {
					result = search(value, root.left);
				} else {
					result = search(value, root.right);
				}
			}
		}
		return result;
	}

	private void byDepth(binaryTree node) {
		System.out.print(node.getValue() + " ");
		if (node.left != null)
			byDepth(node.left);
		if (node.right != null)
			byDepth(node.right);
	}

	private void byWidth(binaryTree[] mas, int counter, int counterMas) {
		System.out.print(mas[counter].getValue() + " ");
		if (mas[counter].left != null) {
			counterMas++;
			mas[counterMas] = mas[counter].left;
		}
		if (mas[counter].right != null) {
			counterMas++;
			mas[counterMas] = mas[counter].right;
		}
		counter++;
		if (counter < amount)
			byWidth(mas, counter, counterMas);
	}
	
	public void setValue(T nubmer) {
		this.number = nubmer;
	}
	
	public T getValue() {
		return number;
	}
	
	private int amount;
	private T number;
	private binaryTree root;
	private binaryTree right;
	private binaryTree left;
}
/*
class Node {

	public Node(int number) {
		this.number = number;
		left = null;
		right = null;
	}

	public void setLeft(Node n) {
		left = n;
	}

	public void setRight(Node n) {
		right = n;
	}

	public void setValue(int nubmer) {
		this.number = nubmer;
	}

	public Node getRight() {
		return right;
	}

	public Node getLeft() {
		return left;
	}

	public int getValue() {
		return number;
	}

	private int number;
	private Node left;
	private Node right;
}
*/