package sam1_2dimension_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 https://www.acmicpc.net/problem/17140
 한 행 또는 열에 있는 수를 정렬하려면, 각각의 수가 몇 번 나왔는지 알아야 한다. 
 그 다음, 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬한다. 
 그 다음에는 배열 A에 정렬된 결과를 다시 넣어야 한다. 정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.

예를 들어, [3, 1, 1]에는 3이 1번, 1가 2번 등장한다. 따라서, 정렬된 결과는 [3, 1, 1, 2]가 된다. 
다시 이 배열에는 3이 1번, 1이 2번, 2가 1번 등장한다. 다시 정렬하면 [2, 1, 3, 1, 1, 2]가 된다.
 
 */
public class Main {
	static class Number{
		//숫자와 등장햇수를 필드로 가짐//정답 행렬에 이 두값을 갖고 배열해야하므로 클래스를 별도로 만드는게 편리
		int n, c;
		//생성자
		Number(int num, int cnt){
			n=num;
			c=cnt;
		}
	}
	
	static int R, C, K;
	static int[][] Arr = new int[100][100];
	
	static int solve() {
		//행과 열을 비교하여 행 연산 할지, 열연산 할지 결정
		int rsize =3, csize=3;
		for(int t=0;t<=100;++t) {//t는 시간
			if(Arr[R][C]==K) //처음부터 두번쨰 행에 답이 있을 수도 있으므로 
				return t; 
			
			if(rsize>=csize) {//r연산, 행의수가 열의 수보다 같거나 크면
				//i로 행을 고정
				for(int i=0;i<rsize;i++) {
					List<Number> numlist = new ArrayList<Number>();
					//각 수를 카운트 하기위해 cnt
					int[] cnt = new int[101];
					//열인덱스 진행
					for(int j=0;j<csize;++j) {
						//배열의 값을 이용하여 cnt 배열에 값을 1씩 느려감
						cnt[Arr[i][j]]++;
					}
					//numlist에 값을 넣음
					for(int c=1;c<=100;++c) {//c는 넘버
						if(cnt[c]>0) {//cnt[1]부터
							numlist.add(new Number(c, cnt[c]));//숫자가 작은거 부터 add했으므로, 등장횟수가 같은경우 이미 작은숫자가 앞에 있음
						}
					}
					//등장카운트 수로 오름차순 정렬
					numlist.sort((lhs, rhs)->{//람다식표현
						return lhs.c-rhs.c;//등장 카운트 횟수 작은거 부터 앞에오고 오름차순으로 정리됨, 이미 위에 포문에서 작은 숫자 순으로 넣음
					});
					//정렬됐으니 for문으로 꺼내서 행렬에 다시 기입
					int idx=0;
					for(Number num : numlist) {//numlist의 원소개수가 100을 넘어갈 수도 있으므로
						if(idx>=99) break;
						Arr[i][idx++]=num.n;//숫자를 먼저 넣고
						Arr[i][idx++]=num.c;
					}
					//r연산 수행수 열수가 늘어날 수가 있으므로, 행을 고정시켰으므로 열이 늘어날 수 있음
					csize=Math.max(csize, idx);
					System.out.println("idx="+idx);
					//idx시작값은 여기까지 r연산 수행하고 얻은 열수
					//한 행의 열수부터 99인덱스까지 빈칸은 0으로 채우기
					for(;idx<100;++idx)
						Arr[i][idx]=0;

					/*for(int b=0; b<idx;b++) {
							System.out.print("행렬 찍음"+Arr[i][b]);
					}*/
					
				}
			}else {//c연산 열단위
				//이번엔 열을 고정
				for(int j=0;j<csize;j++) {
					List<Number> numlist = new ArrayList<Number>();
					//각 수를 카운트 하기위해 cnt
					int[] cnt = new int[101];
					//행인덱스 진행
					for(int i=0;i<rsize;++i) {
						//배열의 값을 이용하여 cnt 배열에 값을 1씩 느려감
						cnt[Arr[i][j]]++;//j는 고정되고 i가 하나씩 증가하면서 숫자의 등장횟수 카운트
					}
					//Num list에 값을 넣음
					for(int c=1;c<=100;++c) {
						if(cnt[c]>0) {
							numlist.add(new Number(c, cnt[c]));//숫자가 작은거 부터 add했으므로, 등장횟수가 같은경우 이미 작은숫자가 앞에 있음
						}
					}
					//정렬함
					numlist.sort((lhs, rhs)->{
						return lhs.c-rhs.c;//등장횟수 작은거 부터 앞에오고 오름차순으로 정리됨
					});
					//정렬됐으니 행렬에 다시 기입
					int idx=0;
					for(Number num : numlist) {//numlist의 원소개수가 100을 넘어갈 수도 있으므로
						if(idx>=99) break;
						//기입할때도 열을 기준으로, 열을 고정하고 행을 증가시켜가면서 입력
						Arr[idx++][j]=num.n;//숫자를 먼저 넣고
						Arr[idx++][j]=num.c;
					}
					//c연산 수행수 행수가 늘어날 수가 있으므로
					rsize=Math.max(rsize, idx);
					for(;idx<100;++idx)//99까지 인덱스가 못가면 0으로 채워주기
						Arr[idx][j]=0; //열고정
				}
			}
			
		}
		return -1;//최소시간이 100을 넘어가는 경우에는 -1을 출력한다.
	}
	
	public static void main(String[] args) {
		//첫줄에 R,C,K가 주어진다.
		Scanner sc = new Scanner(System.in);
		R= sc.nextInt()-1; //문제는 첫번쨰 행인데, 인덱스 0부터 이용하기 위해 -1해줌 (행렬 R,C가 숫자 K인 것을 찾는것)
		C= sc.nextInt()-1;
		K= sc.nextInt();
		
		//첫줄 다음에 3x3 행렬 받음
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;++j) {
				Arr[i][j]=sc.nextInt();
			}
		}
		//System.out.println("Arr[0][1]= "+Arr[0][1]);
		
		System.out.println(solve());
	}
}
