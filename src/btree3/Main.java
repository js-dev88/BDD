package btree3;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		int order = 5;
		Btree t = new Btree(order);
		
//		t.insert(10);
//		t.insert(15);
//		t.insert(30);
//		t.insert(5);
//		System.out.println(t);
//		t.insert(12);
//		t.insert(8);
//		t.insert(15);
//		t.insert(13);
//		t.insert(7);
//		t.insert(17);
//		t.insert(18);
//		t.insert(14);
//		t.insert(20);
//		t.insert(22);
//		t.insert(30);
//		t.insert(25);
//		t.insert(27);
//		t.insert(33);
		
		//10, 15, 30, 27, 35, 40, 45, 37, 20, 50, 55, 46, 71, 66, 74, 85, 90, 79, 78, 95, 25, 81, 68, 60, 65.
		
		int[] tab = {10, 15, 30, 27, 35, 40, 45, 37, 20, 50, 55, 46, 71, 66, 74, 85, 90, 79};//, 78, 95, 81, 68, 60, 65};//
		
		t.insertAll(tab);
		
		System.out.println(t.search(90));
		
//		t.getTree().getFirst().addReassemble();
//
//		System.out.println(t);
//		
//		for (int i = 0; i< 10; i++){
//			System.out.println( i + " : " + t.search(i));
//		}
		
		
//		LinkedList<Node> l = t.getTree();
//		
//		Node n2 = new Node(order, 1);
//		l.add(n2);
//		n2.getNode().put(1, null);
//		n2.getNode().put(2, null);
//		
//		Node n3 = new Node(order, 1);
//		l.add(n3);
//		n3.getNode().put(4, null);
//		n3.getNode().put(5, null);
//		n3.getNode().put(8, null);
//		
//		
//		Node n1 = l.getFirst();
//		n1.getNode().put(3, n3);
//		n1.getNode().put(-1, n2);
//		
		System.out.println(t);
		


	}

}
