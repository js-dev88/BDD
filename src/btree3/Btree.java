package btree3;

import java.util.LinkedList;

public class Btree {
	private LinkedList<Node> tree;
	private int h = 0;
	private int order;
	
	public Btree(int m) {
		int h;
		order = m;
		tree = new LinkedList<>();
		tree.add(new Node(m));
	}
	
	@Override
	public String toString() {
		String s = "{";
		for (Node n : tree){
			s += n.toString();
		}
		s += "}";
		return s;
	}
	
	public boolean search(int k){

		Node n = tree.getFirst();
		if(n.searchInNode(k)){
			
			return true;}
		else{
			if(n.isLeaf()){return false;}
			else{
				return searchBis(n, k);
			}
		}
				

	}
	
	public boolean searchBis(Node n, int k){
		int j = n.getNode().firstKey();
		for(int i : n.getNode().keySet()){
			if(k < i){
				if (n.isLeaf()){return false;}
				else {return this.searchBis(n.getNode().get(j), k);}
			}
			if (k == i){ return true;}
			j = i;
		}
		if (n.isLeaf()){return false;}
		else {return this.searchBis(n.getNode().get(j), k);}
	}
	
	public boolean insert(int k){
		if(search(k)){
			System.out.println("element" + k + "already in the tree");
			return false;
		}
		Node n = findNode(k);
		
		if (n.getNode().size()<5){
			n.getNode().put(k, null);
			return true;
		}

		else{
			n.getNode().put(k, null);
			Node root = new Node(this.order);
			tree.addFirst(root);
			Node split = new Node(this.order);
			tree.add(tree.indexOf(n)+1,split);
			
			int compt = 0;
			for(Integer i: n.getNode().keySet()){
				if (compt == order/2+1){
					root.getNode().put(i, n.getNode().get(i));
				}
				if (compt > order/2+1){
					split.getNode().put(i, n.getNode().get(i));
				}
				compt++;
				
			}
			


			
			for (Integer i : root.getNode().keySet()){
				if (i != -1){
					n.getNode().remove(i);
				}
				
			}
			
			for (Integer i : split.getNode().keySet()){
				if (i != -1){
					n.getNode().remove(i);
				}
				
			}
			
			root.addReassemble();
			split.addReassemble();
			return true;
			
		}
		
		
	}
	
	
	
	public Node findNode(int k){
		Node n = tree.getFirst();
		if(n.isLeaf()){return n;}
		else{
			return findNodeBis(n, k);
		}
	}
	
	public Node findNodeBis(Node n, int k){
		int j = n.getNode().firstKey();
		for(int i : n.getNode().keySet()){
			if(k < i){
				if (n.isLeaf()){return n;}
				else {return this.findNodeBis(n.getNode().get(j), k);}
			}
			j = i;
		}
		if (n.isLeaf()){return n;}
		else {return this.findNodeBis(n.getNode().get(j), k);}
	}
	
	

	public LinkedList<Node> getTree() {
		return tree;
	}

	public void setTree(LinkedList<Node> tree) {
		this.tree = tree;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
