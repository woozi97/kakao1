package kakao1_SecretMap;
//문제 주소: https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//1번 문제
public class Main {
	public static void main(String[] args) {
		int n = 5;
		int arr1[]= {9, 20, 28, 18, 11};
		int arr2[]= {30, 1, 21, 17, 28};
		//인풋은 숫자인데 아웃풋은 숫자가 아님 (String으로 표현)
		//["#####","# # #", "### #", "# ##", "#####"]
		String[] result = new String[n]; //출력용
		
		for(int i=0;i<n;i++) {
			//비트 OR연산자로 양쪽 비트 중 어느하나라도 1이면 결과가 1이 되고 모두 0일때만 0이 됨
			int arr = arr1[i] | arr2[i]; //비트연산의 | (OR)연산임//각 정수를 비트 OR로 합침
			System.out.println(""+Integer.toBinaryString(arr)); //여기서 출력해보면 결과물이 나옴
		}

		System.out.println("");
		
		for(int i=0;i<n;i++) {
			String resultString="";
			int arr = arr1[i] | arr2[i];
			//System.out.println(arr);
			int targetBit=1;//맨끝자리를 의미, 여기서 <<쉬프트하면 2, 4, 8, 16로 바뀜
			//한 값당 5개까지 되므로 그 포문 #####
			for(int j=0;j<n;j++) {
				//&: 비트 AND연산자로 양쪽 비트가 모두 1일때 결과가 1이되고 그렇지 않으면 0이됨
				//arr은 11111이라면 타겟비트는 맨 끝짜리 1을 비교
			resultString=((arr& targetBit)>0?"#":" ")+resultString;//targetBit에 1을두고 &연산을 하면 비트가 1인지 0인지 알수있다.
			//오른쪽에서 왼쪽으로 접근하는 방법(2진수라서)
			targetBit=targetBit<<1;//비트 쉬프트를 써서 자리를 잡는다. 좌측 쉬프트 연산자 왼편에 있는 피연산자의 비트값을 오른편에 지정한 수만큼 왼쪽으로 이동, 오른쪽 남는 비트는 0으로 채움. 예를들면 [0010] ->[0100]으로
			}
			result[i]=resultString;
			System.out.println(result[i]);
		}
	}
}
