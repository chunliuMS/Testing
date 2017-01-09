package LinkedList;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Test;


public class IterWrapper {	
	
	Iterator<Integer> iter;	
	private Integer peekedValue = null;
		
	public IterWrapper(){			
	}
	
	public void setIter(Iterator<Integer> iter){
		this.iter = iter;
	}
	
	public boolean hasNext(){
		return peekedValue != null || iter.hasNext();
	}
	
	public Integer next(){
		Integer value = null;
		if (peekedValue != null)
			 value = peekedValue;
		else{
			if (iter.hasNext())
				value = iter.next();		
		}	
		peekedValue = null;
		return value;
	}
	
	public Integer peek(){
		if (peekedValue != null)
			return peekedValue;
		else{
			if(iter.hasNext()){
				peekedValue = iter.next();
			}
			
			return peekedValue;
		}
	}
		
	@Test
	public void test() {		
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < 10; i++){
			list.add(i);
		}
		
		IterWrapper wrapper = new IterWrapper();
		wrapper.setIter(list.iterator());
		assertTrue(wrapper.peek().intValue() == 0);
		assertTrue(wrapper.next().intValue() == 0);		
		assertTrue(wrapper.next().intValue() == 1);
		assertTrue(wrapper.next().intValue() == 2);
		assertTrue(wrapper.peek().intValue() == 3);
		assertTrue(wrapper.peek().intValue() == 3);
		
	}
}
