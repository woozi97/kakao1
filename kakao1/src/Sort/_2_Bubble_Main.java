package Sort;

//��������
public class _2_Bubble_Main {

	public static void main(String[] args) {
		int[] sort = { 3, 2, 5, 4, 1 };
		int temp = 0;

		print(sort);
		System.out.println();
		//���⼭ i�� �����ϸ鼭 �ϴ� ������ sort.length-i�� Ȱ���ϰ��� �ϴ°�
		for (int i = 0; i < sort.length; i++) {
			for (int j = 0; j < sort.length - 1; j++) {// ������ ������ �񱳴��//���������̶� ó���� 0~9���� �״����� 0~8���� �̷������� ������ �پ��� ����
				if (sort[j] > sort[j + 1]) {
					temp = sort[j]; // ���� for������ ���� �ּҰ��� temp�� ����
					sort[j] = sort[j + 1];// ���� for���� �����ߴٸ� i�� ���� 0���� ����
					sort[j + 1] = temp;
				}
			}
		}
		print(sort);
	}

	public static void print(int[] sort) {
		for (int i = 0; i < sort.length; i++) {
			System.out.print(sort[i]);
		}
	}

}
