package RecursionAndDynamic;
import java.util.Hashtable;

import org.junit.Test;

//You can type A, ctr-a (select all) ctr-c (copy) and ctr-v for n times, calculate 
//the maximum characters you can get.
public class MostChars {	
	public class Key{
		Key(int n, int t, int copy){
			this.n = n;
			this.t = t;
			this.copy = copy;
		}
		
		public boolean equals(Object o){
			if (o instanceof Key){
				Key key = (Key)o;
				return key.n == this.n && key.t == this.t && key.copy == this.copy;
			}
			else
				return false;
		}
		
		public int hashCode(){
			return this.t ^ (n * 99) ^ copy;
		}
		
		int n;
		int t;
		int copy;
	}

	//not as good as the DP solution, but very easy to understand.
	public int mostChars(int n, int t, int copy, Hashtable<Key, Integer> ht){			
		if(n == 0)
			return t;
		
		Key key = new Key(n, t, copy); 
		if (ht.containsKey(key))
			return ht.get(key);
		
		int t1;
		if(copy > 0 )
			t1 = mostChars(n-1, t+copy, copy, ht);
		else
			t1 = mostChars(n -1, t+1, copy, ht);
		int t2 = 0;
		if(n > 3)
			t2 = mostChars(n-4, 2*t, t, ht);
		
		int ret = Math.max(t1, t2);
		ht.put(key, ret);
		return ret;
	}		
	
	public long count(int n){
		long[] s = new long[n+1];
		for (int i = 1; i <= n; i++)
			s[i] = i;
		
		for (int i = 1; i <= n - 4; i++){
			long val = 2 * s[i];
			if (s[i+4] < val)
				s[i+4] = val;			
			
			val += s[i];
			
			for (int j = i+5; j <= n; j++){					
				if (val > s[j])
					s[j] = val;
				val += s[i];
			}
		}
		return s[n];
	}
		
	@Test
	public void test() {	
		
		for (int i = 0; i < 30; i++){
			Hashtable<Key, Integer> ht = new Hashtable<Key, Integer>();
			System.out.println(i + "\t-- " + mostChars(i, 0, 0, ht) + "\t " + count(i));			
		}
		
		for (int i = 30; i <= 200; i++)
			System.out.println(i + "\t " + count(i));	
	}
}
