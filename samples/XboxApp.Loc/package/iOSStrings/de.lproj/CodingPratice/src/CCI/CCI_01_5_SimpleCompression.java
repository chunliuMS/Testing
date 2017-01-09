package CCI;

import junit.framework.TestCase;

//Implement a method to perform basic compresson aaabbccccc-->a3b2c5. If not better,
//return the original string.
public class CCI_01_5_SimpleCompression extends TestCase {
	String compress(String str){
		if (str == null )
			return str;
		
		if (str.length() < 3)
			return str;
		
		char c = str.charAt(0);
		int count = 1;
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < str.length(); i++)
		{
			char next = str.charAt(i);
			if (next != c)
			{
				sb.append(c).append(count);
				c = next;
				count = 1;
			}
			else
				count++;
		}
		sb.append(c).append(count);
		
		if (sb.length() < str.length())
			return sb.toString();
		else
			return str;
	}
	
	public void testFun(){
		System.out.println(compress("aaabbcccdd"));
		System.out.println(compress("aabc"));
		System.out.println(compress(null));
		System.out.println(compress("aaabbcccddeeeeeeeeeee"));
	}
}
