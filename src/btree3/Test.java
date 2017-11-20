package btree3;

public class Test {

	/*public class Btree {
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
			
		
		public void split(int k, Node n){
			n.getNode().put(k, null);
			Node root;
			if(n == tree.getFirst()){
				root = new Node(this.order);
				tree.addFirst(root);
			}else{
				root = searchAncestor(n);
			}
				Node split = new Node(this.order);
				tree.add(tree.indexOf(n)+1,split);
				
				int compt = 0;
				for(Integer i: n.getNode().keySet()){
					if (compt == order/2+1){
						if(root == tree.getFirst()){
							
							if(root.getNode().get(-1) == null){
								root.getNode().put(-1,n);
							}
							root.getNode().put(i,split);
							if(root.getNode().size() > 5){
								split(k,root);
							}
						}else{
		
							root.getNode().put(i,split);
							if(searchAncestor(n).getNode().size() > 5){
								split(k,searchAncestor(n));
							}
						}
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


		}
		public void insert(int k){
			if(search(k)){
				System.out.println("element" + k + "already in the tree");
			}
			Node n = findNode(k);
			
			if (n.getNode().size()<5){
				n.getNode().put(k, null);
			}
			else{
				split(k,n);
			}	
		}
		
		
		public void insertAll(int[] tab){
			for(int i =0; i<tab.length; i++){
				this.insert(tab[i]);
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
		
		
		
		
	}*/

}
