/**
 * This P68 class addresses the issue on page 68 of Cracking the Coding Interview
 * It's purpose is to return all integers less than 1000 that satisfy the equation: a^3 + b^3 = c^3 + d^3
 * It runs in O(n^2) and doesn't print out redundant values
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P68 {
	static class Tuple implements Comparable<Tuple>{
		public final int x;
		public final int y;
		public Tuple(int x, int y){
			if(x < y){
				this.x = x;
				this.y = y;
			}else{
				this.y = x;
				this.x = y;
			}
		}
		//Compare method returns 0 if it's the same tuple
		@Override
		public int compareTo(Tuple arg0) {
			if(this.x == arg0.x && this.y == arg0.y){
				return 0;
			}else{
				return 1;
			}
		}
		//For debugging purposes
		@Override
		public String toString(){
			return "x: " + this.x + ", y: " + this.y;
		}
	}
	
	public static void main(String[] args){
		Map<Double, List<Tuple>> allComb = new HashMap<Double, List<Tuple>>();
		for(int a = 1; a < 1000; a++){
			for(int b = 1; b < 1000; b++){
				double comb = Math.pow(a, 3) + Math.pow(b, 3);
				Tuple t = new Tuple(a, b);
				if(!allComb.containsKey(comb)){
					List<Tuple> l = new ArrayList<Tuple>();
					allComb.put(comb, l);
				}
				allComb.get(comb).add(t);
			}
		}
		//Map is populated
		//Get set of keys
		Set<Double> set = allComb.keySet();
		for(Double k: set){
			List<Tuple> l = allComb.get(k);
			//In retrospect this functionality is unneeded because no duplicate tuples will be found in the list 
			if(l.size() > 1){
				if(l.get(0).compareTo(l.get(1)) != 0){
					System.out.printf("%d^3 + %d^3 = %d^3 + %d^3\n", l.get(0).x, l.get(0).y, l.get(1).x, l.get(1).y);
				}
			}
		}
	}
}
