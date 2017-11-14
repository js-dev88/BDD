package bufferManager;
import java.util.LinkedList;

public class BufferManagerLRU {
	
	private  LinkedList<String> bufferPool = new LinkedList<>();
	
	private static int nbOperation = 0;
	private static int fetch = 0;
	private static long start;
    private static long stop;
    
	public void insertFrameinBufferPool(String [] workload) {
		
		start = System.currentTimeMillis();
		
		for (String page : workload) {
			if(bufferPool.size() < 4 ) {
				if(!bufferPool.contains(page)) {
					bufferPool.addFirst(page);
					fetch++;
				}else{
					bufferPool.remove(page);
					bufferPool.addFirst(page);
				}
			}else{
				if(!bufferPool.contains(page)) {
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
		System.out.println("Nombre d'opérations : " + BufferManagerLRU.nbOperation);
	}
	
	public void getExecutionTime(){
		long duration = BufferManagerLRU.stop -BufferManagerLRU.start;
		System.out.println("Temps d'execution : " + duration);
	}
	
	public void getFetch(){
		System.out.println("Nombre de fetch : " + BufferManagerLRU.fetch);
	}
	

	
	
}
