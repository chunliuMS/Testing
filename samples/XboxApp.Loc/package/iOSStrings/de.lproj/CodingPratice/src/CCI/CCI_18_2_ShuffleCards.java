
package CCI;

import junit.framework.TestCase;

//Write a method to shuffle a deck of cards.
public class CCI_18_2_ShuffleCards extends TestCase {

	void shuffle(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int temp = a[i];
			int index = (int) (Math.random() * (a.length - i)) + i;
			a[i] = a[index];
			a[index] = temp;
		}
	}

	public void testFun() {
		int[] a = new int[52];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}

		shuffle(a);
		StringBuffer sb = new StringBuffer();
		for (int v : a) {
			sb.append(v).append(" ");
		}
		System.out.println(sb.toString());
	}
}