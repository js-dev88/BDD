package btree3;

import java.util.TreeMap;

public class Node {
	private TreeMap<Integer, Node> node;
	private int order;
	private int h ;
	
	public Node(int m, int h) {
		order = m;
		node = new TreeMap<>();
		node.put(-1, null);
		this.h = h;
	}
	
	public boolean isLeaf(){
		for(Node n : node.values()){
			if(n != null){
				return false;
			}
		}
		return true;
	}
	
	public Node addReassemble() {
//		this.getNode().put(-1,null);
		int tmp = -1;
		for(Integer n : this.getNode().keySet()){
			if(n != -1){
				this.getNode().put(tmp, this.getNode().get(n));
				tmp = n;
			}
		}
		return this;
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(Integer n : node.keySet()){
			s += n + " ";
		}
		s += "]";
		return s;
	}
	
	public boolean searchInNode(int k){
		for(Integer n : node.keySet()){
			if (n == k){
				return true;
			}
		}
		return false;
	}

	public TreeMap<Integer, Node> getNode() {
		return node;
	}

	public void setNode(TreeMap<Integer, Node> node) {
		this.node = node;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	public int getH() {
		return h;
	}
	
	public void setH(int h) {
		this.h = h;
	}
}
