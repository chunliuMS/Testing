package LeetCode;

import junit.framework.TestCase;

//Determine whether an integer is a palindrome. Do it without extra space..
public class LC011_PalindromeNumber extends TestCase {
	
	boolean isPalindrome(int n){
		if (n < 0)
			return false;
		int div = 1;
		while(n/div > 10)
			div *= 10;
			
		while(n > 0){
			if (n / div != n % 10)
				return false;
			
			n = (n % div)/10;
			div /= 100;
		}		
		return true;
	}
	
	public void testFun(){
		assertTrue(isPalindrome(12321));
		assertFalse(isPalindrome(123210));
	}	
}
