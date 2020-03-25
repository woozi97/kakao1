package kakao4_gugudan_for3;

/*
 ����: for���� 3���̻� ����ؼ� �̷� �������� ������
 2 x 1 = 2 //x1 �� �ؿ��� ���� �����Ƿ� ����� �� �ڴ� x2�� ����
 2 x 2 = 4
 2 x 2 x 2 = 8
 2 x 2 x 2 x 2 = 16 ...
 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 = 512 , 2�� 9�� ����
 3 x 1 = 3
 ....
 9 x 9 x 9 x 9 x 9 x 9 x 9 x 9 x 9 = 387420489
 */

public class Main {

	public static void main(String[] args) {
		//2�ܺ��� 9�� ���� for�� 1��
		for(int i=2;i<=9;i++) {
			for(int j=1;j<=9;j++) {//�������� Ƚ��
				print(i,j);
			}
		}
		
	}

	public static void print(int x, int y) {//int i�� j���� ����
		//2,1 -> 2 x 1= 2
		//2,2 -> 2 x 2 = 4
		//2,3 -> 2 x 2 x 2 = 8
		String stringVal=""; //���� ����� ��Ʈ�� ����
		int sum = x; //������ x���� ���� x x 1 �� ���� �ϹǷ�
		//1�� �� 1, 2�϶� 2, 3�϶� 3 �� �ڿ� �ٴ� ���� �ʿ�
		int val=1;
		//����//1���� x�� ��� �������ϴ���
		for(int z=1;z<=y;z++) {
			if(y==1) {
				val=1;
				stringVal += x+" * "+val;
				sum = x; //���ʷ� �Ѿ�� �� x
			}
			else {//y�� 1�� �ƴϸ� ����� �ٽý���
				if(z==2) {
					val=x;//������� //3
					stringVal = ""+x;// 3
					sum = x; // 3
				}
				//�̾� ���̴� �κ�//2�� �̻��� ���̱⸸ �ϸ� �ǹǷ�
				val = x;//3
				sum *= val;// 3 *3 =9
				stringVal += " * "+val;//3 * 3
				
			}
		}
		System.out.println(stringVal+ " = "+sum);
	}
}