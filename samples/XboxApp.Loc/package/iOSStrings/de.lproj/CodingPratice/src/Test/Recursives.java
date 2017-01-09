package Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import junit.framework.TestCase;

public class Recursives extends TestCase {
	void getAllParentheses(int pairCount){
		ArrayList<String> list = new ArrayList<String>();
		char[] str = new char[2 * pairCount];
		getParentheses(list, pairCount, pairCount, str, 0);
		
		for (String s : list){
			System.out.println(s);			
		}		
	}
	
	void getParentheses(ArrayList<String> list, int left, int right, char[] str, int count){
		if (left == 0 && right == 0) {
			list.add(String.valueOf(str));
			return;
		}
		
		if (left > 0){
			str[count] = '(';
			getParentheses(list, left -1, right, str, count + 1);			
		}
		
		if (right > 0 && left < right) {
			str[count] = ')';
			getParentheses(list, left, right - 1, str, count + 1);
		}		
	}
	
	class ParseResult{
		int invalidChars;
		String parsedWords;
		
		public ParseResult(int invalidChars, String parsedWords){
			this.invalidChars = invalidChars;
			this.parsedWords = parsedWords;
		}		
	}
	
	ParseResult recoverSentence(String str, 
			  					int start, 
			  					int end, 
			  					HashSet<String> dic, 
			  					Hashtable<Integer, ParseResult> cache){
		if (cache.containsKey(start))
			return cache.get(start);
		
		if (end >= str.length()){
			String word = str.substring(start);
			if (dic.contains(word))
				return new ParseResult(0, word);
			else
				return new ParseResult(end - start, word.toUpperCase());
		}
		
		String word = str.substring(start, end + 1);
		ParseResult rest = recoverSentence(str, end + 1, end + 1, dic, cache);
		ParseResult exact;
		if (dic.contains(word))
			exact = new ParseResult(rest.invalidChars, word + " " + rest.parsedWords);
		else
			exact = new ParseResult(rest.invalidChars + word.length(), word.toUpperCase() + " " + rest.parsedWords);
		
		ParseResult extend = recoverSentence(str, start, end + 1, dic, cache);
		
		ParseResult finalResult;
		if (exact.invalidChars >= extend.invalidChars)
			finalResult = extend;
		else
			finalResult = exact;
		
		cache.put(start, finalResult);
		
		return finalResult;
		
	}
	
	
	public void testFun(){
		getAllParentheses(5);
		
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
    	
    	String str = "thisisasomethingicannotunderstandabc";
    	Hashtable<Integer, ParseResult> cache = new Hashtable<Integer, ParseResult>();
    	ParseResult r = recoverSentence(str, 0, 0, dictionary, cache);
    	
    	System.out.println("Total " + r.invalidChars + " char(s) are not in the dicttionary");
    	
    	System.out.println(r.parsedWords);
	}
}
