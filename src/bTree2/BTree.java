package bTree2;

import java.util.Arrays;
import java.util.HashMap;

public class BTree {
	
	 private int order;
	 private int[] node;
	 private BTree[] subTree;
	 private int height = 0;
	 private static int heightMax = 0;
	 private static HashMap<String,String> printTab = new HashMap<>();

	  public BTree (int order,int height) {
		this.node = new int[order-1];
	    this.subTree = new BTree[order]; //recursif a chaque niveau on recrée un tableau de Btree vide pour le prochain niveau
	    this.order = order;
	    this.height = height;
	    if(height > heightMax) // récupération de la hauteur de l'arbre
	    	heightMax = height;
	  }

	  private boolean isLeaf() {
	   for (int i=0;i<order;i++)
	      if (subTree[i] != null)
			return false;
	   return true;
	  }

	  private boolean isFull() {
	   for (int i=0;i<order;i++){
	      if (subTree[i] != null)
	    	  return true;
	   }
	   return false;
	  } 
	  
	  public boolean search(int key){
		  int niveau = -1;
		  int index = -1;
		  if(!this.isLeaf()){
			  int j = 0;
			  while(j < order -1){
				  if(key == this.node[j]){
					  niveau = this.height;
					  index = j;
					  System.out.println("La clé se trouve en hauteur : "+niveau+" à l'index : "+index);
					  return true;
				  }else if (key < this.node[j])
					  return this.subTree[j].search(key);
				  else if(this.node[j] == 0)
					  return this.subTree[j].search(key);
				  else
					j++;
			  }  
				  return false;     
		  }else{
			  int i = 0;
			  while(i < order -1){
				  if(key < this.node[i]){
					  System.out.println("La clé n'est pas présente dans l'arbre");
					  return false;
				  }else if (key == this.node[i]){
					  niveau = this.height;
					  index = i;
					  System.out.println("La clé se trouve en hauteur : "+niveau+" à l'index : "+index);
					  return true;
				  }else{
					  i++;
				  }
			  }
			  return false;
		  }
	  }
	  
	  @Override
		public String toString() { //pour 1 BTree
			String s = "[";
			for(int i = 0; i < order-1; i++){
				s += this.node[i] +", ";
			}
			s = s.substring(0, s.length()-2) + "] ";
			return s;
		}
	  /**
	   * On récupère dans un hashmap le to String de chaque noeud d'un même niveau
	   * Ainsi on stocke la racine, chaque niveau et les feuilles sur des lignes séparées
	   */
	 public void print(){
		  if(!this.isLeaf()){
			  if(this.height == 1){
				  printTab.put("root", this.toString());
				  for(int i =0; i < order; i++){
					  if(this.subTree[i] != null)
						  this.subTree[i].print();
				  }
			  }else{
				 printTab.put("h"+String.valueOf(this.height), printTab.get("h"+String.valueOf(this.height)) + this.toString()); 
				 for(int i =0; i < order; i++){
					  if(this.subTree[i] != null)
						  this.subTree[i].print();
				  }
			  }  
		  }else{
			     printTab.put("leaves",printTab.get("leaves") + this.toString()); 
		  } 
	  }
	 
	 public void display(){
		 //On instancie chaque ligne de chaque étage du hashmap
		 printTab.put("root", "");
		 //Si la hauteur est > 3
		 if(heightMax > 2){
			 for(int i = 2; i <= heightMax-1; i++){
				 printTab.put("h"+i, "");
			 } 
		 }
		 //stockage des feuilles
		 printTab.put("leaves", "");
		 //Appel de la fonction de stockage
		 this.print();
		 //Affichage
		 System.out.println("---------Root-----------");
		 System.out.println(printTab.get("root"));
		 if(heightMax > 2){
			 for(int i = 2; i <= heightMax-1; i++){
				 System.out.println("---------h"+i+"-----------");
				 System.out.println(printTab.get("h"+i));
			 }
		 }
		 System.out.println("---------Leaves-----------");
		 System.out.println(printTab.get("leaves")+"\n");
	 }
	 
	 public void insert(int[] tabKey){
		 for(int key : tabKey){
			 insertionStep(key);
		 }	 
	 }
	 
	 public void insertionStep(int key){
		 if(!this.isArrayFull(node)){
			 insertAndSort(this.node, key);
		 }else{
			 int[] temp = copyTempArray(this.node, key);
			 this.subTree[0] = new BTree(order,heightMax+1);
			 this.subTree[1] = new BTree(order,heightMax+1);
			 for(int i = 0; i < order; i++){
				 if(i < 2){
					 this.subTree[0].node[i] = temp[i];
				 }else if( i == 2){
					 this.node[0] = temp[i];
					 emptyArray(this.node);
			 	 }else{
			 		this.subTree[1].node[i - 3] = temp[i];
			 	 }
				 
			 }
			 
		 }
	 }
	 public boolean isArrayFull(int[] node){
		 for(int i = 0; i < this.node.length; i++){
			 if(this.node[i] == 0){
				return false;
			 }
		 }
		 return true;
	 }
	 public void insertAndSort(int[] node, int key){
		 for(int i = 0; i < this.node.length; i++){
			 if(this.node[i] == 0){
				 this.node[i] = key;
				 Arrays.sort(this.node);
				 break;
			 }
		 }
	 }
	 
	 public void emptyArray(int[] tab){
		 for(int i = 1; i < tab.length; i++){
			 tab[i] = 0;
		 }
	 }
	 
	 public int[] copyTempArray(int[] node, int key){
		 int[] temp = new int[order];
		 for(int i = 0; i < this.node.length; i++){
			 temp[i] = node[i];
		 }
		 temp[order-1] = key;
		 Arrays.sort(temp);
		 return temp;
	 }
	 
	  
	  public static void main(String[] args) {
		//Creation d'un b-tree en dur pour search
		//10, 15, 30, 27, 35, 40, 45, 37, 20, 50, 55, 46, 71, 66, 74, 85, 90, 79, 78, 95, 25, 81, 68, 60, 65.
		 BTree btree = new BTree(5,1);
		 int[] tabKey = {10,15,30,27,35,40,45};
		 btree.insert(tabKey);
		 /*btree.node[0] = 46;
		 
		 btree.subTree[0] = new BTree(5,2);
		 btree.subTree[1] = new BTree(5,2);
		 
		 btree.subTree[0].subTree[0] = new BTree(5,3);
		 btree.subTree[0].subTree[1] = new BTree(5,3);
		 btree.subTree[0].subTree[2] = new BTree(5,3);
		 btree.subTree[1].subTree[0] = new BTree(5,3);
		 btree.subTree[1].subTree[1] = new BTree(5,3);
		 btree.subTree[1].subTree[2] = new BTree(5,3);
		 
		 btree.subTree[0].node[0] = 27;
		 btree.subTree[0].subTree[0].node[0] = 10;
		 btree.subTree[0].subTree[0].node[1] = 15;
		 btree.subTree[0].subTree[0].node[2] = 20;
		 btree.subTree[0].subTree[0].node[3] = 25;
		 
		 btree.subTree[0].node[1] = 37;
		 btree.subTree[0].subTree[1].node[0] = 30;
		 btree.subTree[0].subTree[1].node[1] = 35;
		 
		 btree.subTree[0].subTree[2].node[0] = 40;
		 btree.subTree[0].subTree[2].node[1] = 45;
		 
		 btree.subTree[1].node[0] = 66;
		 btree.subTree[1].subTree[0].node[0] = 50;
		 btree.subTree[1].subTree[0].node[1] = 55;
		 btree.subTree[1].subTree[0].node[2] = 60;
		 btree.subTree[1].subTree[0].node[3] = 65;
		 
		 btree.subTree[1].node[1] = 79;
		 btree.subTree[1].subTree[1].node[0] = 68;
		 btree.subTree[1].subTree[1].node[1] = 71;
		 btree.subTree[1].subTree[1].node[2] = 74;
		 btree.subTree[1].subTree[1].node[3] = 78;
		 
		 btree.subTree[1].subTree[2].node[0] = 81;
		 btree.subTree[1].subTree[2].node[1] = 85;
		 btree.subTree[1].subTree[2].node[2] = 90;
		 btree.subTree[1].subTree[2].node[3] = 95;*/
		 
		 //Test du print
		 btree.display();
		 
		 //Test search
		 System.out.println(btree.search(46));
		 System.out.println(btree.search(45));
		 System.out.println(btree.search(95));
		 System.out.println(btree.search(71));
		 System.out.println(btree.search(50));
		 System.out.println(btree.search(69));
	}
}
