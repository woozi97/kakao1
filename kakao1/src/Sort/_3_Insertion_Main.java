package Sort;

//��������: ������ �Ǵ� �ε����� ������ �˻��Ͽ� ������ �Ǵ� �ε����� �� �ڸ��� ã�Ƽ� �����ϴ� ���
//������ �Ǵ� ���� ���� ������ �ɶ����� ���� �з� �ٲ�� ������ ������ �����س��� ���Ѵ�.
//������ �Ǵ� ���� ���� �ε����� ���Ͽ� ���ʿ� ������ �Ǵ� ������ ū���� ���� �ε����� ������ ��ĭ�а� ������ �Ǵ� �ε����� ���� ����
public class _3_Insertion_Main {

	public static void main(String[] args) {
		int[] sort = { 3, 2, 5, 4, 1};
		int temp = 0;
		int j = 0;
		print(sort);
		System.out.println();
		
		//�ι�°���� ������ �Ǿ� �����Ѵ�.(���ʰ� ���ϹǷ�)//���ʰ����� ��� ������ �Ǿ��ִ�.
		for (int i = 1; i < sort.length; i++) {
			int b = sort[i]; // ������ �Ǵ� �� ����//2
			//int j=i-1; ���� ���//sort[j]�� ���ؼ����� Ŭ�������� ��
			//�񱳱����� for���� ��
			for (j = i - 1; j >= 0 && sort[j] > b; j--) {
				sort[j + 1] = sort[j]; //�񱳴���� ū ��� ���������� �о
				//sort[1]==sort[0]=3 , j=-1
			}
			sort[j + 1] = b;//���� ���Ե� ��ġ�� ���ذ� ����
				//sort[0]==2
			System.out.println("j+1="+(j+1));
		}
		print(sort);
	}

	public static void print(int[] sort) {
		for (int i = 0; i < sort.length; i++) {
			System.out.print(sort[i]);
		}
	}

}
