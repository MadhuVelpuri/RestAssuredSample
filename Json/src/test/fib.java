package test;

public class fib {
	 static int n1 = 0, n2 =1 , n3 = 0;;
	public static void fibo(int count){
		
		
		if(count>0){
		n3 = n1+n2;
		n1= n2;
		n2 = n3;
		System.out.println(n3);
		fibo(count-1);
		}
	}
public static void main(String[] args) {
	fibo(8);
}
}
