package Sort;
//선택정
public class _1_Selection_Main {

	public static void main(String[] args) {
		int[] sort = { 3, 2, 5, 4, 1 };
		int temp = 0;
		int indexControl=0;
		System.out.print(sort[0] + " " + sort[1] + " " + sort[2] + " " + sort[3] + " " + sort[4]);
		System.out.println();
		for (int i = 0; i < sort.length-1; i++) {
			indexControl=i;
			for(int j=i+1;j<sort.length;j++) {
				if(sort[indexControl]>sort[j]) {
					indexControl=j;
					System.out.println("sort["+indexControl+"] = "+sort[indexControl]);//제일 작은수 sort[indexControl]이 구해짐
				}
			}
			System.out.println("indexControl = "+indexControl);
				//temp=sort[4]=1
			temp=sort[indexControl]; //안쪽 for문에서 구한 최소값을 temp에 넣음
			sort[indexControl]=sort[i];//안쪽 for문만 수행했다면 i의 값은 0부터 시작
			//sort[4]=3
			sort[i]=temp;
			//sort[0]=1;
		}
		System.out.print(sort[0] + " " + sort[1] + " " + sort[2] + " " + sort[3] + " " + sort[4]);
	}

}
