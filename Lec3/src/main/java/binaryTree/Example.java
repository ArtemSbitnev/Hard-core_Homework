package binaryTree;

public class Example {

	public static void main(String[] args) {

		BinaryTree<Integer> firstTree = new BinaryTree<Integer>();
		Integer a = 5;
		firstTree.add(a);
		for (int i = 1; i < 8; i ++) {
			a = i;
			firstTree.add(i);			
		}
		System.out.println(firstTree.exists(10));
		firstTree.printByDepth();
		firstTree.remove(5);
		firstTree.printByDepth();
		firstTree.printByWidth();
	}
}
