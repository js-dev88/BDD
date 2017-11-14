import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> workload = new ArrayList<>();
		workload.add("A");
		workload.add("B");
		workload.add("C");
		workload.add("D");
		workload.add("E");
		workload.add("A");
		workload.add("B");
		workload.add("C");
		workload.add("D");
		workload.add("E");
		BufferManagerLRU bflru = new BufferManagerLRU();
		for (String s : workload) {
			bflru.insertFrameinBufferPool(s);
		}
		


	}

}
