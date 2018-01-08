package test;

public class Fibonacci {
	
	static int n1=0;
	static int n2=1;
	static int n3;
	
	public static void series(int count){
		
		if(count>0){
		n3=n2+n1;
		n1=n2;
		n2=n3;
		System.out.print(","+n3);
		series(count-1);
		}
	
	}
public static void main(String[] args) {
	System.out.print("0,1");
	series(8);
}
}
