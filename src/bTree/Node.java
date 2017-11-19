package bTree;

import java.util.HashMap;

public class Node {
	private int mOrder;
	private int height = 0;
	private HashMap<Integer,Node> nodeContent;
	private Node fatherNode;
	
	// if node is root
	public Node(int order) {
		this.mOrder = order;
		this.height ++;
		this.nodeContent = new HashMap<>();
		this.fatherNode = null;
	}
	
	//if node is not root (leaf or sub node)
	public Node(int order, Node fatherNode) {
		this.mOrder = order;
		height = fatherNode.getHeight() + 1;
		this.nodeContent = new HashMap<>();
		this.fatherNode = fatherNode;
	}
	
	public HashMap<Integer, Node> getNodeContent() {
		return nodeContent;
	}

	public Node getFatherNode() {
		return fatherNode;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Boolean isLeaf(Node node){
		Boolean rez = true;
		for(Node nodeElement : nodeContent.values()){
			if(nodeElement != null)
				rez = false;
			return rez;
		}
		return rez;
	}
	
	public Boolean isFull(Node node){
		if(nodeContent.size() == mOrder-1)
			return true;
		return false;
		
	}

	
	
}
