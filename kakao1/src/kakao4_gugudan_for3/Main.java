package kakao4_gugudan_for3;

/*
 문제: for문을 3개이상 사용해서 이런 형식으로 만들어라
 2 x 1 = 2 //x1 는 밑에서 쓸수 없으므로 지우고 그 뒤는 x2만 더함
 2 x 2 = 4
 2 x 2 x 2 = 8
 2 x 2 x 2 x 2 = 16 ...
 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 = 512 , 2를 9번 곱합
 3 x 1 = 3
 ....
 9 x 9 x 9 x 9 x 9 x 9 x 9 x 9 x 9 = 387420489
 */

public class Main {

	public static void main(String[] args) {
		//2단부터 9단 까지 for문 1개
		for(int i=2;i<=9;i++) {
			for(int j=1;j<=9;j++) {//곱해지는 횟수
				print(i,j);
			}
		}
		
	}

	public static void print(int x, int y) {//int i와 j값을 받음
		//2,1 -> 2 x 1= 2
		//2,2 -> 2 x 2 = 4
		//2,3 -> 2 x 2 x 2 = 8
		String stringVal=""; //식을 출력할 스트링 변수
		int sum = x; //시작을 x에서 시작 x x 1 로 시작 하므로
		//1일 때 1, 2일때 2, 3일때 3 등 뒤에 붙는 숫자 필요
		int val=1;
		//연산//1부터 x가 몇번 찍혀야하는지
		for(int z=1;z<=y;z++) {
			if(y==1) {
				val=1;
				stringVal += x+" * "+val;
				sum = x; //최초로 넘어온 값 x
			}
			else {//y가 1이 아니면 지우고 다시시작
				if(z==2) {
					val=x;//몇단인지 //3
					stringVal = ""+x;// 3
					sum = x; // 3
				}
				//이어 붙이는 부분//2단 이상은 붙이기만 하면 되므로
				val = x;//3
				sum *= val;// 3 *3 =9
				stringVal += " * "+val;//3 * 3
				
			}
		}
		System.out.println(stringVal+ " = "+sum);
	}
}