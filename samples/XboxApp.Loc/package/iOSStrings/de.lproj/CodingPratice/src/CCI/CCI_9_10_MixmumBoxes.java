package CCI;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

//Maximum height of the the boxes.
public class CCI_9_10_MixmumBoxes extends TestCase {	
    
	public ArrayList<Integer> createStackDP(int[] boxes, int bottom, HashMap<Integer, ArrayList<Integer>> stackMap){
		Integer bt = new Integer(bottom);
		if (stackMap.containsKey(bt))
			return stackMap.get(bt);
		
		ArrayList<Integer> max_stack = null;
		int max_height = 0;
		for (int i = 0; i < boxes.length; i++){
			if(boxes[i] < bottom){
				ArrayList<Integer> stack = createStackDP(boxes, boxes[i], stackMap);
				int new_height = 0;
				for(Integer v: stack)
					new_height += v.intValue();
				if (new_height > max_height){
					max_height = new_height;
					max_stack = stack;
				}
			}
		}
		if (max_stack == null)
			max_stack = new ArrayList<Integer>();
		max_stack.add(0, bottom);
		stackMap.put(bottom, max_stack);
		
		return max_stack;                                                         
	}
	
    public void testFun(){
    	int [] boxes = { 4, 6, 7, 9, 5, 3, 2, 8, 1, 5};
    	HashMap<Integer, ArrayList<Integer>> stackMap = new HashMap<Integer, ArrayList<Integer>> ();
    	ArrayList<Integer> list = createStackDP(boxes, Integer.MAX_VALUE, stackMap);
    	if (list != null && list.size() > 0)
    		list.remove(0);
    	for(Integer v : list){
    		System.out.print(v.intValue() + " ");    		
    	}
	}
}
