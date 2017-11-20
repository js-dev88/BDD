package bufferManager;
import java.util.LinkedList;

public class BufferManagerLRU {
	
	private  LinkedList<String> bufferPool = new LinkedList<>(); //liste chaînée représentant la RAM, prenant au max 4 éléments
	
	private static int nbOperation = 0; // nombre de fois qu'une opération est effectuée 
	private static int fetch = 0; // nombre de fois qu'une nouvelle page est insérée dans la RAM (nb de page misses)
	private static long start;
    private static long stop;
    
	public void insertFrameinBufferPool(String [] workload) {
		
		start = System.currentTimeMillis(); // pour compter le temps d'exécution
		
		for (String page : workload) {
			if(bufferPool.size() < 4 ) { // s'il reste de la place dans la RAM
				if(!bufferPool.contains(page)) { //si la page à insérer n'est pas encore dans la RAM
					bufferPool.addFirst(page);
					fetch++;
				}else{
					bufferPool.remove(page);
					bufferPool.addFirst(page);
				} 
			}else{ // si la RAM est pleine
				if(!bufferPool.contains(page)) { // si la page à insérer n'est pas déjà dans la RAM
					bufferPool.removeLast();
					bufferPool.addFirst(page);
					fetch++;
				}else {
					bufferPool.remove(page);
					bufferPool.addFirst(page);
				}
			}
			nbOperation++;
			System.out.println(this.toString());
		}
		stop = System.currentTimeMillis();
		
	}
	
	@Override
	public String toString() {
		String s ="";
		for(int i=0; i< bufferPool.size(); i++) {
			s += "("+ bufferPool.get(i)+ ")";
		}
		return s;
	}
	
	public void getNbOperation(){
		System.out.println("Nombre d'operations : " + BufferManagerLRU.nbOperation);
	}
	
	public void getExecutionTime(){
		long duration = BufferManagerLRU.stop -BufferManagerLRU.start;
		System.out.println("Temps d'execution : " + duration);
	}
	
	public void getFetch(){
		System.out.println("Nombre de fetch : " + BufferManagerLRU.fetch);
	}
	

	
	
}
