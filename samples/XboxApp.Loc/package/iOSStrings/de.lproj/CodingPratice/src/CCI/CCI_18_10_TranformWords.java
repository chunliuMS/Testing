
package CCI;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import junit.framework.TestCase;

//Given 2 words with equal length in the dictionary, write a method to transform one word to another 
//by changing only 1 character at a time. The new word get in each step must be in the dictionary.
public class CCI_18_10_TranformWords extends TestCase {
	
	class Transformer {
		public Transformer(){
			dictionary.add("dog");
			dictionary.add("cat");
			dictionary.add("fat");
			dictionary.add("pat");
			dictionary.add("pot");
			dictionary.add("dot");
			dictionary.add("fit");
			dictionary.add("pig");
			dictionary.add("sit");
			dictionary.add("hit");
			dictionary.add("bit");
		}
		
		public void transform(String begin, String end){
			Queue<String> q = new LinkedList<String>();
			Set<String> visited = new HashSet<String>();
			Hashtable<String, String> trace = new Hashtable<String, String>();
			
			q.add(begin);
			visited.add(begin);
			
			while(!q.isEmpty()){
				String word = q.poll();
				Set<String> s = getNewWords(word);
				for(String str: s){
					if(dictionary.contains(str)){						
						if (str.equalsIgnoreCase(end)){
							System.out.println("Found the path!!!");
							trace.put(str, word);
							String back = str;
							StringBuilder sb = new StringBuilder();							
							while (back != null){
								sb.append(back).append("-");
								back = trace.get(back);
							}
							
							System.out.println(sb.toString());
							
							return;
						}
						if (!visited.contains(str)){
							q.add(str);
							visited.add(str);
							trace.put(str, word);
						}
					}
				}
			}
		}
		
		private Set<String> getNewWords(String word){
			Set<String> s = new HashSet<String>();
			for (int i = 0; i < word.length(); i++){
				char ch = word.charAt(i);
				char[] array = word.toCharArray();
				for (char c = 'a'; c <= 'z'; c++){
					if (ch != c){
						array[i] = c;
						s.add(new String(array));
					}
				}				
			}
			
			return s;
		}
		
		private Set<String> dictionary = new HashSet<String>();
	}
	
    public void testFun(){       	
    	Transformer tf = new Transformer();
    	tf.transform("dog", "cat");
    	tf.transform("cat", "dog");
	}
}