package sam2_lab3_bit;

public class Bit_Main {
	public static void main(String[] args) {
		int num=15;
		System.out.println("십진수 = "+num+" , 이진수 = "+Integer.toBinaryString(num));
		System.out.println("십진수 = "+(num<<2)+" , 이진수 = "+Integer.toBinaryString(num<<2));
		System.out.println("십진수 = "+(num>>2)+" , 이진수 = "+Integer.toBinaryString(num>>2));
		System.out.println("십진수 = "+(~num)+" , 이진수 = "+Integer.toBinaryString(~num));
		
		
		int num1=15;
		int num2=8;
		
		System.out.println("num1 이진수  : "+Integer.toBinaryString(num1));
		System.out.println("num2 이진수  : "+Integer.toBinaryString(num2));
		System.out.println("num1 & num2  : "+Integer.toBinaryString(num1&num2));//x&y 기본 AND 연산자처럼 해당 자리수에서 둘다 1인 경우 1을 반환하고 나머지는 0을 반환
		System.out.println("num1 | num2  : "+Integer.toBinaryString(num1|num2));//x|y 기본 OR연산처럼 해당 자리수에서 하나라도 1인 경우 1을 반환하고 둘다 0인 경우 0을 반환
		System.out.println("num1 ^ num2  : "+Integer.toBinaryString(num1^num2));//x^y 해당 자리수에서 서로 다른 1을 반환하고 같은 경우 0을 반환
	}
}
