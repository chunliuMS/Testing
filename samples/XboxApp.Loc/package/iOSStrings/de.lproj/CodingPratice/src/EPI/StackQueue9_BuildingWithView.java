package EPI;
import java.util.Stack;

import junit.framework.TestCase;
/* 
 * Given a series of building in a straight line and facing south, the house has view only if it's taller than the any one in south. If the array contains heights of house from
 * north to south, write a function to return all the houses with views. 
*/
				
public class StackQueue9_BuildingWithView extends TestCase {	
		
	Stack<Integer> getHousesWithViews(int[] houses) {
		if (houses == null || houses.length == 0)
			return null;
		
		Stack<Integer> housesWithViews = new Stack<Integer> ();
		housesWithViews.add(houses[0]);
		
		for (int i = 1; i < houses.length; i++) {
			while (!housesWithViews.isEmpty()) {
				int h = housesWithViews.peek();
				if (h < houses[i])
					housesWithViews.pop();
				else 
					break;
			}
			housesWithViews.push(houses[i]);			
		}
		
		return housesWithViews;
	}
	
		
	public void testFun(){	
		Stack<Integer> houses = getHousesWithViews(new int[]{2, 4, 3, 7, 5, 6, 1});
		for (int i : houses) {
			System.out.print(i);
			System.out.print("\t");
		}
	}		
}