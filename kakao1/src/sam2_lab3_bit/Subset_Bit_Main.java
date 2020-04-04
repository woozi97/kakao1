package sam2_lab3_bit;

import java.util.Arrays;
//�κ������� ���ϴ� ������ ��Ʈ������ �̿�
/*
 1<<n
2n�� ���� ���´�.
���Ұ� n���� ����� ��� �κ������� ���� �ǹ��Ѵ�.
Power set(��� �κ� ����)
�����հ� �ڱ� �ڽ��� ������ ��� �κ�����
�� ���Ұ� ���Եǰų� ���Ե��� �ʴ� 2���� ����� ���� ����ϸ� ��� �κ������� ���� ���ȴ�.

i & (1 << j) 
��� ����� i�� j��° ��Ʈ�� 1���� �ƴ����� �ǹ��Ѵ�.

double[] values = {1.0, 1.1, 1.2};
System.out.println(values.toString()); // �̷��� �ϸ� [D@46a49e6 ���� ���� ���ɴϴ�.
System.out.println(Arrays.toString(values)); // �̷��� �ϸ� [1.0, 1.1, 1.2] �� ��µ˴ϴ�.
Arrays.copyOf(�����迭, ������ ����);
 */
//{1,2,3,4,-1,-5}���� Ȧ���� ������ �κ������� ��� ����Ͻÿ�
public class Subset_Bit_Main {
	public static void main(String[] args) {
		int[] array= {1,2,3,4,-1,-5};
		//1 << n �� ���Ұ� n���� ��� ��� �κ������� �� //i<()�� �Ϲ� for������ ����
		for(int i=1;i<(1<<array.length);i++) { //i�� ������ 1~63
			System.out.println();
			System.out.println("i = "+i);
			int[] temp = new int[array.length]; //�ӽ� �κ� ����
			int count =0;//���ο� �迭 ũ��
			boolean isOdd = true;//Ȧ�� ���� Ȯ��
			//�κ� ���� ����
			for(int j=0;j<array.length;j++) { 
				System.out.print("j = "+j+"   ");
				if((i&(1<<j)) != 0) {//i�� j��° ��Ʈ�� 1���� Ȯ��, 0�� �ƴ϶��, ù��°�� i =1//i=1�϶� 2��° ��Ʈ�� 1���� Ȯ��, ��� 0
					if(array[j]%2==0) {//¦�����
						isOdd=false;
					}else {//Ȧ���� �ɷ����� ����
						System.out.println("count(temp �迭�� �ε���) = "+count);
						temp[count++]=array[j];//0�ε����� �ְ� ++������Ŵ
						System.out.println("temp["+(count-1)+"] = "+array[j]);
						System.out.println();
					}
				}
			}
			System.out.println();
			System.out.println("temp�迭 ����? "+Arrays.toString(temp));
			System.out.println("isOdd="+isOdd);
			//���
			if(isOdd) {//true�̸�//Ȧ���� �̾Ƴ±� ������ ������ �ڸ����� 0�� ���� ��. 0�� ��� �������ֱ� ���� �̾Ƴ� �迭�� ũ�⸸ŭ �迭 ���縦 ����
				System.out.println("�κ����� ���");
				System.out.println(Arrays.toString(Arrays.copyOf(temp, count)));
			}
		}
	}
}
