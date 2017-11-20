package btree3;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		int order = 5;
		Btree t = new Btree(order);
		t.setH(2);
		
		t.insert(3);
		t.insert(2);
		t.insert(5);
		t.insert(8);
		t.insert(1);
		t.insert(6);
		System.out.println(t);
		
//		LinkedList<Node> l = t.getTree();
		
//		Node n2 = new Node(order);
//		l.add(n2);
//		n2.getNode().put(1, null);
//		n2.getNode().put(2, null);
//		
//		Node n3 = new Node(order);
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
//		System.out.println(t);
//		
//		System.out.println(t.search(6));

	}

}
