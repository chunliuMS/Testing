
package CCI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import junit.framework.TestCase;

//Given a dictionary, try to recover a sentence in which the spaces are removed accidently.
public class CCI_17_14_RecoverSentenceWithNoSpaces extends TestCase {
	
	public class Result{
		int invalid = Integer.MAX_VALUE;
		String parsed = "";
		
		Result(int invalid, String parsed){
			this.invalid = invalid;
			this.parsed = parsed;
		}
		
		public Result clone(){
			return new Result(this.invalid, this.parsed);
		}
		
		public Result min(Result r2){
						
			return this.invalid < r2.invalid ? this : r2;
		}
	}
	
	public Result parseSimple(String str, int start, int end, 
			HashSet<String> dictionary, 
			ArrayList<String> result,
			Hashtable<Integer, Result> cache){
		if(end >= str.length()){
			int inv = 0;
			String s = str.substring(start); 
			if (!dictionary.contains(s)){
				s = s.toUpperCase();
				inv = end - start;
			}
			return new Result(inv, s);
		}
		
		if (cache.containsKey(start)){
			return cache.get(start).clone();
		}
		
		String word = str.substring(start, end + 1);		
		Result bestExact = parseSimple(str, end + 1, end + 1, dictionary, result, cache);
		
		if(dictionary.contains(word))
			bestExact.parsed = word + " " + bestExact.parsed;
		else{
			bestExact.parsed = word.toUpperCase() + " " + bestExact.parsed;
			bestExact.invalid += word.length();
		}
		
		Result bestExtend = parseSimple(str, start, end + 1, dictionary, result, cache);		
		
		Result ret =  bestExact.min(bestExtend);
		cache.put(start, ret.clone());
				
		return ret;
	}
	
    public void testFun(){    
    	HashSet<String> dictionary = new HashSet<String>();
    	dictionary.add("this");
    	dictionary.add("is");
    	dictionary.add("some");
    	dictionary.add("thing");
    	dictionary.add("something");
    	dictionary.add("i");
    	dictionary.add("cannot");
    	dictionary.add("understand");
    	dictionary.add("ab");
    	ArrayList<String> result = new ArrayList<String>();
    	
    	String str = "thisisasomethingicannotunderstandabc";
    	Hashtable<Integer, Result> cache = new Hashtable<Integer, Result>();
    	Result r = parseSimple(str, 0, 0, dictionary, result, cache);
    	
    	System.out.println("Total " + r.invalid + " char(s) are not in the dicttionary");
    	
    	System.out.println(r.parsed);
	}
}