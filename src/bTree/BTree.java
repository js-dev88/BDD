package bTree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BTree {
	  private static int height;
	  private int order; // ordre du graphe
	  private int id;
	  private static int nodeIdentifier = 0; // identifiant du noeud
	  private TreeMap<Integer,BTree> node; // enregistrements et pointeurs vers autres nodes
	  private BTree parentNode;
	  
	  //constructeur
	  public BTree (int order) {
		this.order = order;
		this.id = BTree.createID();
	    this.node = new TreeMap<>();
	    this.parentNode = null;
	    height = 1;
	  }
	  
	public static int createID(){
		return nodeIdentifier++;
	}
	
	public int getId() {
		return id;
	}
	 // getters et setters
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getOrder() {
		return order;
	}

	public static int getNodeIdentifier() {
		return nodeIdentifier;
	}

	public TreeMap<Integer,BTree> getNode() {
		return node;
	}

	public BTree getParentNode() {
		return parentNode;
	}

	public void setParentNode(BTree parentNode) {
		this.parentNode = parentNode;
	}

	
	//Si le tous les child node sont vides, alors le noeud est une feuille
	  private boolean isLeaf() {
		  for(BTree nodeElement : node.values()){
			  if( nodeElement != null)
				  return false;
		  }
		  return true;
	  }

	  
	  //Si la taille du noeud  = order -1 => le noeud est plein
	  private boolean isFull() {
		  if(node.size() < order-1)
			     return false;
		  return true;
	  }
	  
	  //prend le tableau de données en entrée
	  public void insertInBTree(int[] dataLoad){
		  for(int key : dataLoad){
			  if(this.getHeight() == 1 ){ // si on est sur le root
				  if(!this.isFull()) // si le root n'est pas plein
					  this.getNode().put(key, null);
				  else{//sinon
					  this.getNode().put(key, null);
					  for(int i = 0; i < Math.round(order/2) -1; i++){
						  this.getNode().put(this.getNode().firstKey(), new BTree(5));
						  
						  this.getNode().remove(this.getNode().firstKey());
					  }
				  }
				  
			  }
		  }
		 
	  }
	  
	  @Override
	public String toString() {
		String s = "[";
		for(Entry<Integer, BTree> entry : this.getNode().entrySet()){
			s += entry.getKey()+" ,";
		}
		s = s.substring(0, s.length()-1) + "]";
		return s;
	}
	  
	  public static void main(String[] args) {
		 int[] dataLoad = {10,21,9,4};
		 BTree tree = new BTree(5);
		 tree.insertInBTree(dataLoad);
		 System.out.println(tree.toString());
	  }
	  
	  
	
	
}
