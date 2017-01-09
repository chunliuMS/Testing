package Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

import junit.framework.TestCase;

public class Strings extends TestCase {
	
	ArrayList<String> allNeighbors(String s, HashSet<String> dict){
		ArrayList<String > al = new ArrayList<String> ();
		for (int i = 0; i < s.length(); i++){
			char[] a = s.toCharArray();
			char c = s.charAt(i);
			for (char cc = 'a';  cc <= 'z'; cc++){
				if (cc != c){
					a[i] = cc;
					String word = String.valueOf(a);
					if (dict.contains(word)){
						al.add(word);
					}
				}
			}
		}
		
		return al;
	}
	
	ArrayList<String> transformWords(String orig, String dest, HashSet<String> dict){
		Hashtable<String, String> path = new Hashtable<String, String>();
		HashSet<String> visited = new HashSet<String>();
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(orig);
		
		while(!queue.isEmpty()){
			String str = queue.poll();
			visited.add(str);
			ArrayList<String> neighbors = allNeighbors(str, dict);
			for (String s : neighbors){
				if (s.equals(dest)){
					path.put(s, str);
					break;
				}
				else if (!visited.contains(s)){
					queue.add(s);
					path.put(s, str);
				}
			}
		}		
		
		if (path.get(dest) != null){
			ArrayList<String> p = new ArrayList<String>();
			while(dest != null){
				p.add(0, dest);
				dest = path.get(dest);
			}
			return p;			
		}
		else
			return null;
	}
	
	
	public void testFun(){
		HashSet<String> dictionary = new HashSet<String>();
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
		
		ArrayList<String> al = transformWords("dog", "cat", dictionary);
		assertTrue(al != null && al.size() > 0);
		System.out.println();
		for(String s : al){
			System.out.print(s + " ");
		}
			
	}
}
