package bufferManager;

import java.util.HashMap;


public class BufferManagerClock {
	private  HashMap<String,Boolean> bufferPool = new HashMap<>();
	private  HashMap<Integer,String> flagHandler = new HashMap<>();
	private int clockCursor = 0;
	
	private static int nbOperation = 0;
	private static int fetch = 0;
	private static long start;
	private static long stop;
    
	public void insertFrameinBufferPool(String [] workload) {
		
		start = System.currentTimeMillis();
		
		int j=0;
		
		for (String page : workload) {
			
			if(bufferPool.size() < 4 ) {
				if(!bufferPool.containsKey(page)) {
					bufferPool.put(page,false);
					flagHandler.put(j++,page);
					fetch++;
				}else{
					bufferPool.put(page,true);
				}
			}else{
				if(!bufferPool.containsKey(page)) {
					
					while(bufferPool.get(flagHandler.get(clockCursor)) != false){
						
						bufferPool.put(flagHandler.get(clockCursor),false);
						clockCursor++;
						if(clockCursor % 4 == 0)
							clockCursor = 0;
					}
						bufferPool.remove(flagHandler.get(clockCursor));
						flagHandler.put(clockCursor,page);
						bufferPool.put(page,false);
						clockCursor++;
						if(clockCursor % 4 == 0)
							clockCursor = 0;
						fetch++;
				}else {
					bufferPool.put(page,true);
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
			s += "["+ bufferPool.get(flagHandler.get(i))+ "]"+"["+flagHandler.get(i)+"]";
		}
		return s;
	}
	
	public void getNbOperation(){
		System.out.println("Nombre d'opérations : " + BufferManagerClock.nbOperation);
	}
	
	public void getExecutionTime(){
		long duration = BufferManagerClock.stop -BufferManagerClock.start;
		System.out.println("Temps d'execution : " + duration);
	}
	
	public void getFetch(){
		System.out.println("Nombre de fetch : " + BufferManagerClock.fetch);
	}
}
