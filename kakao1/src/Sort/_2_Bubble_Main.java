package Sort;

//버블정렬
public class _2_Bubble_Main {

	public static void main(String[] args) {
		int[] sort = { 3, 2, 5, 4, 1 };
		int temp = 0;

		print(sort);
		System.out.println();
		//여기서 i가 증가하면서 하는 역할은 sort.length-i로 활용하고자 하는것
		for (int i = 0; i < sort.length; i++) {
			for (int j = 0; j < sort.length - 1; j++) {// 어디까지 비교할지 비교대상//버블정렬이라 처음엔 0~9까지 그다음엔 0~8까지 이런식으로 뒤쪽이 줄어들기 때문
				if (sort[j] > sort[j + 1]) {
					temp = sort[j]; // 안쪽 for문에서 구한 최소값을 temp에 넣음
					sort[j] = sort[j + 1];// 안쪽 for문만 수행했다면 i의 값은 0부터 시작
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
