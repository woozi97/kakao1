package sam2_lab3_bit;

public class Bit_Main {
	public static void main(String[] args) {
		int num=15;
		System.out.println("������ = "+num+" , ������ = "+Integer.toBinaryString(num));
		System.out.println("������ = "+(num<<2)+" , ������ = "+Integer.toBinaryString(num<<2));
		System.out.println("������ = "+(num>>2)+" , ������ = "+Integer.toBinaryString(num>>2));
		System.out.println("������ = "+(~num)+" , ������ = "+Integer.toBinaryString(~num));
		
		
		int num1=15;
		int num2=8;
		
		System.out.println("num1 ������  : "+Integer.toBinaryString(num1));
		System.out.println("num2 ������  : "+Integer.toBinaryString(num2));
		System.out.println("num1 & num2  : "+Integer.toBinaryString(num1&num2));//x&y �⺻ AND ������ó�� �ش� �ڸ������� �Ѵ� 1�� ��� 1�� ��ȯ�ϰ� �������� 0�� ��ȯ
		System.out.println("num1 | num2  : "+Integer.toBinaryString(num1|num2));//x|y �⺻ OR����ó�� �ش� �ڸ������� �ϳ��� 1�� ��� 1�� ��ȯ�ϰ� �Ѵ� 0�� ��� 0�� ��ȯ
		System.out.println("num1 ^ num2  : "+Integer.toBinaryString(num1^num2));//x^y �ش� �ڸ������� ���� �ٸ� 1�� ��ȯ�ϰ� ���� ��� 0�� ��ȯ
	}
}
