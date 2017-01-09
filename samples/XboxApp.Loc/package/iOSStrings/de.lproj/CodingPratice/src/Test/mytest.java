package Test;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class mytest extends TestCase {
	
	class Result {
		int charsLeft;
		String parsedString = "";
		
		Result min(Result r2) {
			return this.charsLeft < r2.charsLeft ? this : r2;
		}
	}		
	
	public Result Parse(String str) {
		dict.add("this");
		dict.add("is");
		dict.add("the");
		dict.add("key");
		return parse(str, 0, str.length()- 1);
	}
	
	private HashMap<Integer, Result> cache = new HashMap<Integer, Result>();
	private Set<String> dict = new HashSet<String>();
	
	private Result parse(String str, int start, int end) {
		if (start >= end) {
			return new Result();
		}

		Result r = cache.get(start);
		if (r != null)
			return r;
				
		Result min = null;
		for (int i = start; i <= end; i ++ ){
			String word = str.substring(start, i+1);
			Result m = parse(str, i + 1, end);
			
			if (dict.contains(word)){
				m.parsedString += " " + word;
			}
			else {
				m.parsedString += " " + word.toUpperCase();
				m.charsLeft += word.length();
			}
			
			if (min == null)
				min = m;
			else 
				min = min.min(m);				
		}
		cache.put(start, min);
		return min;
	}
	
	public void testFun(){
		Result r = Parse("thisis");
		System.out.println(r.parsedString);
	}
}
