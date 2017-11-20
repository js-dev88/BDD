package bufferManager;

public class Main {

	public static void main(String[] args) {
		//https://sites.google.com/site/coursparisdauphine/home/bd-m1
		System.out.println("----------------------------------");
		System.out.println("-----------TESTS LRU--------------");
		System.out.println("----------------------------------\n");
		
		//Ex1
		//String workload[] = {"A","B","C","D","E","A","B","C","D","E"};
		//Ex 2
		//String workload[] = {"A","B","C","D","A","G","A","F","A","D","B","C","D","A","A","G","A","A","F","A","G","A","F","A","D","B","B","C","D","A"};
		//Ex 3 test 200 acc�s
		//EX 4
		String workload[] = {"A","B","C","D","A","E","A"};
		//String workload[] = {"A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D","A","B","C","D","A","E","A","B","C","D"};
		//
		BufferManagerLRU bfmLRU = new BufferManagerLRU();
		
		bfmLRU.insertFrameinBufferPool(workload);

		
		System.out.println("----------------------------------");
		System.out.println("-----------TESTS FIFO-------------");
		System.out.println("----------------------------------\n");
		
		BufferManagerFIFO bfmFIFO = new BufferManagerFIFO();
		bfmFIFO.insertFrameinBufferPool(workload);
		bfmFIFO.getNbOperation();
		bfmFIFO.getExecutionTime();
		bfmFIFO.getFetch();
		
		//ex 1 / 2 Nb op�rations => LRU plus co�teux en op�rations
		//ex 3 200 rez temps d'execution => pas coh�rent / les algos ne sont pas les m�mes
		//ex 2 FIFO nombre de fetch > LRU => plus pertinant si on acc�de plusieurs fois � la m�me page => justifie "une page peu consult�e est susceptible d'�tre appel�e moins souvent"
		//ex 2 illustration du "It will be written back to disk, only to be reread shortly thereafter into another buffer."
		
		System.out.println("----------------------------------");
		System.out.println("-----------TESTS CLOCK-------------");
		System.out.println("----------------------------------\n");
		
		//test clock ok PENSER A CHANGER 4 => 3 !!!
		//String workloadClock[] = {"2","3","2","1","5","2","4","5","3","2","4","5","3","2","4","5","3","2","5","2"};
		BufferManagerClock bfmClock = new BufferManagerClock();
		bfmClock.insertFrameinBufferPool(workload);
		
		
		System.out.println("-----------TESTS LRU--------------");
		bfmLRU.getNbOperation();
		bfmLRU.getExecutionTime();
		bfmLRU.getFetch();
		
		System.out.println("-----------TESTS FIFO-------------");
		bfmFIFO.getNbOperation();
		bfmFIFO.getExecutionTime();
		bfmFIFO.getFetch();
		
		System.out.println("-----------TESTS CLOCK-------------");
		bfmClock.getNbOperation();
		bfmClock.getExecutionTime();
		bfmClock.getFetch();
	
	}

}
