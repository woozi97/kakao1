package sam2_lab3_bit;

import java.util.Arrays;
//부분집합을 구하는 문제는 비트연산을 이용
/*
 1<<n
2n의 값을 갖는다.
원소가 n개일 경우의 모든 부분집합의 수를 의미한다.
Power set(모든 부분 집합)
공집합과 자기 자신을 포함한 모든 부분집합
각 원소가 포함되거나 포함되지 않는 2가지 경우의 수를 계산하면 모든 부분집합의 수가 계산된다.

i & (1 << j) 
계산 결과는 i의 j번째 비트가 1인지 아닌지를 의미한다.

double[] values = {1.0, 1.1, 1.2};
System.out.println(values.toString()); // 이렇게 하면 [D@46a49e6 같은 값이 나옵니다.
System.out.println(Arrays.toString(values)); // 이렇게 하면 [1.0, 1.1, 1.2] 이 출력됩니다.
Arrays.copyOf(원본배열, 복사할 길이);
 */
//{1,2,3,4,-1,-5}에서 홀수로 구성된 부분집합을 모두 출력하시오
public class Subset_Bit_Main {
	public static void main(String[] args) {
		int[] array= {1,2,3,4,-1,-5};
		//1 << n 은 원소가 n개일 경우 모든 부분집합의 수 //i<()는 일반 for문으로 생각
		for(int i=1;i<(1<<array.length);i++) { //i의 범위는 1~63
			System.out.println();
			System.out.println("i = "+i);
			int[] temp = new int[array.length]; //임시 부분 집합
			int count =0;//새로운 배열 크기
			boolean isOdd = true;//홀수 여부 확인
			//부분 집합 생성
			for(int j=0;j<array.length;j++) { 
				System.out.print("j = "+j+"   ");
				if((i&(1<<j)) != 0) {//i의 j번째 비트가 1인지 확인, 0이 아니라면, 첫번째는 i =1//i=1일때 2번째 비트가 1인지 확인, 모두 0
					if(array[j]%2==0) {//짝수라면
						isOdd=false;
					}else {//홀수를 걸러내는 로직
						System.out.println("count(temp 배열의 인덱스) = "+count);
						temp[count++]=array[j];//0인덱스에 넣고 ++증가시킴
						System.out.println("temp["+(count-1)+"] = "+array[j]);
						System.out.println();
					}
				}
			}
			System.out.println();
			System.out.println("temp배열 값은? "+Arrays.toString(temp));
			System.out.println("isOdd="+isOdd);
			//출력
			if(isOdd) {//true이면//홀수만 뽑아냈기 때문에 나머지 자리에는 0이 있을 것. 0을 모두 삭제해주기 위해 뽑아낸 배열의 크기만큼 배열 복사를 해줌
				System.out.println("부분집합 출력");
				System.out.println(Arrays.toString(Arrays.copyOf(temp, count)));
			}
		}
	}
}
