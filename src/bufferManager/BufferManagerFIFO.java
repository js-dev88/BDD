package bufferManager;
import java.util.LinkedList;

public class BufferManagerFIFO {
private  LinkedList<String> bufferPool = new LinkedList<>();


private static int nbOperation = 0;	
private static int fetch = 0;
private static long start;
private static long stop;  

	public void insertFrameinBufferPool(String [] workload) {
		start = System.currentTimeMillis();
		
			
		for (String page : workload) {
			if(bufferPool.size() < 4 ) { //Si le bufferPool n'est pas rempli
				if(!bufferPool.contains(page)) { //Si le bufferpool ne contient la page
					bufferPool.add(page);
					fetch++;
					nbOperation++;
				}//sinon on ne fait rien
			}else{
				if(!bufferPool.contains(page)) {//Si le bufferpool ne contient la page
					bufferPool.removeFirst();
					bufferPool.add(page);
					fetch++;
					nbOperation++;
				}//sinon on ne fait rien
			}
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
		System.out.println("Nombre d'operations : " + BufferManagerFIFO.nbOperation);
	}
	
	public void getExecutionTime(){
		long duration = BufferManagerFIFO.stop -BufferManagerFIFO.start;
		System.out.println("Temps d'execution : " + duration);
	}
	
	public void getFetch(){
		System.out.println("Nombre de fetch : " + BufferManagerFIFO.fetch);
	}
}
