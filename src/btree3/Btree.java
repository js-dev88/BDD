package btree3;

import java.util.LinkedList;

public class Btree {
	private LinkedList<Node> tree;
//	private int h = 0;
	private int order;
	private static Node var;
	
	public Btree(int m) {
		order = m;
		tree = new LinkedList<>();
		tree.add(new Node(m, 1));
	}
	
	@Override
	public String toString() {
		String s = "{";
		int h = 1;
		for (Node n : tree){
			if(n.getH() > h){
				s += "\n";
				h++;
			}
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
				else { 
					return this.searchBis(n.getNode().get(j), k);}
			}
			if (k == i){ return true;}
			j = i;
		}
		if (n.isLeaf()){return false;}
		else {return this.searchBis(n.getNode().get(j), k);}
	}
	

	public void insertAll(int[] tab){
		for(int i =0; i<tab.length; i++){
			this.insert(tab[i]);
		}
	}
	
	public boolean insert(int k){
		if(search(k)){
			System.out.println("element" + k + "already in the tree");
			return false;
		}
		Node n = findNode(k); // trouver le noeud où il faut placer l'élement
		var = n;
		
		if (n.getNode().size()<5){
			n.getNode().put(k, null);
			return true;
		}

		else{
			insertBis(k, n);
			
			
		}
		return false;
		
		
	}
	
	public boolean insertBis (int k, Node n){
		if(n == tree.getFirst()){ // si le noeud dans lequel il faut placer l'élément est la racine de l'arbre.
			n.getNode().put(k, null);
			Node root = new Node(this.order, 0); // nouvelle racine.
			tree.addFirst(root);
			Node split = new Node(this.order, n.getH()); // deuxième noeud, au même niveau que n.
			tree.add(tree.indexOf(n)+1,split);
			for (Node node : tree){
				node.setH(node.getH()+1);
			}
			
			int compt = 0;
			for(Integer i: n.getNode().keySet()){
				if (compt == order/2+1){
					root.getNode().put(i, split);
					
				}
				if (compt > order/2+1){
					split.getNode().put(i, null);
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
			
			root.getNode().put(-1, n);

			
			
//			root.addReassemble();
//			split.addReassemble();
			return true;
		}
		
		else{ // si le noeux n'est pas racine de l'arbre
			insertRec(n, k);
		}
		return false;
	}
	
	public boolean insertRec(Node n, int k){
		if(n == tree.getFirst()){
			Node root = new Node(this.order, 0); // nouvelle racine.
			tree.addFirst(root);
			Node split = new Node(this.order, n.getH()); // deuxième noeud, au même niveau que n.
			tree.add(tree.indexOf(n)+1,split);
			for (Node node : tree){
				node.setH(node.getH()+1);
			}
			
			int compt = 0;
			for(Integer i: n.getNode().keySet()){
				if (compt == order/2+1){
					root.getNode().put(i, split);
					
				}
				if (compt > order/2+1){
//					if(n.getNode().lastKey() == i){
//						split.getNode().put(i, var);
//					}
					split.getNode().put(i, var);
					System.out.println(var);
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
			return true;
		}
		Node ancetre = searchAncestor(n);
		n.getNode().put(k, null);
		
		Node split = new Node(this.order, n.getH()); // deuxième noeud, au même niveau que n.
		tree.add(tree.indexOf(n)+1,split);
		
		int compt = 0;
		
		for(Integer i: n.getNode().keySet()){
			if (compt == order/2+1){
				ancetre.getNode().put(i, split);
				var = split;
			}
			if (compt > order/2+1){
				split.getNode().put(i, null);
			}
			compt++;	
		}
		
		for (Integer i : ancetre.getNode().keySet()){
			if (i != -1){
				n.getNode().remove(i);
			}
		}
		
		for (Integer i : split.getNode().keySet()){
			if (i != -1){
				n.getNode().remove(i);
			}
		}
		
		if (ancetre.getNode().size()>5){
			split(k, ancetre);
		}
		return true;
	}
	
	public boolean split(int k, Node n){
		Node ancetre = searchAncestor(n);
		if(ancetre == null){
			return insertRec(n, k);
		}
		if(ancetre.getNode().size()>5){
			
			Node split = new Node(this.order, n.getH()); // deuxième noeud, au même niveau que n.
			tree.add(tree.indexOf(n)+1,split);
			
			int compt = 0;
			
			for(Integer i: n.getNode().keySet()){
				if (compt == order/2+1){
					ancetre.getNode().put(i, split);
					
				}
				if (compt > order/2+1){
					split.getNode().put(i, null);
				}
				compt++;	
			}
			
			for (Integer i : ancetre.getNode().keySet()){
				if (i != -1){
					n.getNode().remove(i);
				}
			}
			
			for (Integer i : split.getNode().keySet()){
				if (i != -1){
					n.getNode().remove(i);
				}
			}
		}
		else{
			return insertRec(ancetre, k);
		}
		return true;
	}
	
	
	
	
	
	public Node searchAncestor(Node n){
		for(Node node : tree){
			for(Node subnode : node.getNode().values()){
				if(subnode == n){
					return node;
				}
			}
		}
		return null;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	
}
