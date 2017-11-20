package bufferManager;

import java.util.HashMap;


public class BufferManagerClock {
	private  HashMap<String,Boolean> bufferPool = new HashMap<>(); // récupère l'info A=>false
	private  HashMap<Integer,String> flagHandler = new HashMap<>();//récupère la position : 1=>A
	private int clockCursor = 0; //aiguille
	
	private static int nbOperation = 0;
	private static int fetch = 0;
	private static long start;
	private static long stop;
    
	public void insertFrameinBufferPool(String [] workload) {
		
		start = System.currentTimeMillis();
		
		int j=0;
		
		for (String page : workload) {
			
			if(bufferPool.size() < 4 ) { //Au départ on insère la lettre dans les espaces disponbiles
				if(!bufferPool.containsKey(page)) {
					bufferPool.put(page,false);
					flagHandler.put(j++,page);
					fetch++;
				}else{
					bufferPool.put(page,true);//Si la lettre est présente, on passe le flag à true
				}
			}else{
				if(!bufferPool.containsKey(page)) {// Si le bufferpOOL EST PLEIN,
					
					while(bufferPool.get(flagHandler.get(clockCursor)) != false){ //Le curseur cherche le premier slot avec un flag à flase, et passe les flags true à false
						
						bufferPool.put(flagHandler.get(clockCursor),false);
						clockCursor++;
						if(clockCursor % 4 == 0)
							clockCursor = 0;
					}
						bufferPool.remove(flagHandler.get(clockCursor));// lorsque le curseur a trouver le slot il remplace l'élément
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
		System.out.println("Nombre d'operations : " + BufferManagerClock.nbOperation);
	}
	
	public void getExecutionTime(){
		long duration = BufferManagerClock.stop -BufferManagerClock.start;
		System.out.println("Temps d'execution : " + duration);
	}
	
	public void getFetch(){
		System.out.println("Nombre de fetch : " + BufferManagerClock.fetch);
	}
}
