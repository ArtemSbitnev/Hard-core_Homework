package lecture2;

public class Example {

	public static void main(String[] args) {

		binaryTree<Integer> firstTree = new binaryTree<Integer>();
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
		/*
		BinTree<Integer> firstTree = new BinTree<Integer>();
		
		Integer a = 5;
		firstTree.add(a);
		for (int i = 1; i < 8; i ++) {
			a = i;
			firstTree.add(i);			
		}
		System.out.println(firstTree.exists(16));
		System.out.println(firstTree.exists(7));
		
		firstTree.printByDepth();
		System.out.println(firstTree.remove(5));

		firstTree.printByDepth();
		System.out.println(firstTree.remove(4));
		firstTree.printByDepth();
		System.out.println(firstTree.remove(10));
		firstTree.printByDepth();
		System.out.println(firstTree.remove(12));
		firstTree.printByDepth();
		System.out.println(firstTree.remove(7));
		firstTree.printByDepth();
		System.out.println(firstTree.remove(8));
		firstTree.printByWidth();		
		firstTree.add(8);
		firstTree.add(7);
		firstTree.printByDepth();
		System.out.println(firstTree.remove(7));
		firstTree.printByDepth();
		*/
	}
}
