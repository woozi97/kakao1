package Sort;

//삽입정렬: 기준이 되는 인덱스의 앞쪽을 검사하여 기준이 되는 인덱스가 들어갈 자리를 찾아서 삽입하는 방법
//기준이 되는 수는 삽입 정렬이 될때마다 값이 밀려 바뀌기 때문에 변수에 저장해놓고 비교한다.
//기준이 되는 수와 앞쪽 인덱스를 비교하여 앞쪽에 기준이 되는 수보다 큰값을 가진 인덱스가 있으면 한칸밀고 기준이 되는 인덱스의 수를 넣음
public class _3_Insertion_Main {

	public static void main(String[] args) {
		int[] sort = { 3, 2, 5, 4, 1};
		int temp = 0;
		int j = 0;
		print(sort);
		System.out.println();
		
		//두번째부터 기준이 되어 시작한다.(왼쪽과 비교하므로)//왼쪽값들은 모두 정렬이 되어있다.
		for (int i = 1; i < sort.length; i++) {
			int b = sort[i]; // 기준이 되는 수 저장//2
			//int j=i-1; 비교할 대상//sort[j]가 기준수보다 클경우까지만 비교
			//비교기준이 for문에 들어감
			for (j = i - 1; j >= 0 && sort[j] > b; j--) {
				sort[j + 1] = sort[j]; //비교대상이 큰 경우 오른쪽으로 밀어냄
				//sort[1]==sort[0]=3 , j=-1
			}
			sort[j + 1] = b;//최종 삽입될 위치에 기준값 저장
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
