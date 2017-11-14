import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class BufferManagerLRU {
	private  LinkedList<String> bufferPool = new LinkedList<>();
	
    
	public void insertFrameinBufferPool(String page) {
		if(bufferPool.size() < 4 ) {
			if(!bufferPool.contains(page)) {
				bufferPool.addFirst(page);
			}else{
				bufferPool.remove(page);
				bufferPool.addFirst(page);
			}
		}else{
			if(!bufferPool.contains(page)) {
				bufferPool.removeLast();
				bufferPool.addFirst(page);
			}else {
				bufferPool.remove(page);
				bufferPool.addFirst(page);
			}
		}
		System.out.println(this.toString());
	}
	
	@Override
	public String toString() {
		String s ="";
		for(int i=0; i< bufferPool.size(); i++) {
			s += "("+ bufferPool.get(i)+ ")";
		}
		return s;
	}
	

	
	
}
