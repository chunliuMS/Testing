package Maths;

import junit.framework.TestCase;

//Shuffle cards.
public class ShuffleCards extends TestCase {
	
	public void shuffle(int[] cards){
		for (int i = 0; i < cards.length; i++){
			int temp = cards[i];
			int index = (int)(Math.random() * (cards.length - i)) + i;
			cards[i] = cards[index];
			cards[index] = temp;
		}
	}
	
		
	public void testFun(){
		int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
				12, 13, 14, 15, 16, 17, 18, 19, 20};
		shuffle(cards);
		
		for (int i = 0; i < cards.length; i++)
			System.out.print(Integer.toString(cards[i]) + " ");
	}
}
