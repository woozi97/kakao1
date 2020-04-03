package sam2_lab3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//https://www.acmicpc.net/problem/17142
//백준 17142
/*비트 시프트 연산자란 말그대로 bit 를 shift 시켜주는 녀석이다.

연산자는 다음과 같이 3가지가 있다.
(1) << : 왼쪽으로 이동
(2) >> : 오른쪽으로 이동
(3) >>> : unsigned, 오른쪽으로 이동

'A << B' 라는 수식이 있다고 하면, A 라는 녀석의 비트 값을 B 만큼 왼쪽으로 이동시키겠다.라고 이해하면 된다. (단, A와 B는 정수이다)
https://secretroute.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%9D%98%E7%A5%9E-Vol1-%EB%B9%84%ED%8A%B8-%EC%8B%9C%ED%94%84%ED%8A%B8-%EC%97%B0%EC%82%B0%EC%9E%90
*/
public class Main {
	//바이러스 위치 기억
	static class Point{
		int r,c,d; //d는 거리, r은 row, c는 column
		//생성자
		Point(int row, int col, int dist){
			r=row;
			c=col;
			d=dist;
		}
	}
	static final int INF=987654321; //최소값 찾으려고 쓰는 임의의 최대값
	static int N, M;
	static int[][] Arr = new int[50][50]; //배열은 최대 50개가 들어올수 있다고 하였음
	static Point[] Virus = new Point[10]; //바이러스는 최대 10개임. 저장용도
	static int VirusCnt;
	static int[] dr = {-1, 1, 0,0};//상하 좌우 row기준, 좌우일때는 row는 변화없음
	static int[] dc = {0,0,-1,1};//상하좌우 일때 col기준, 좌우일때 변화함
	
	static int countBits(int n) {
		int cnt=0; //갯수카운팅을 위한 수를 초기화
		while(n>0) {
			if((n&1)==1) {//맨오른쪽 비트가 1이라면 하나셈
				cnt++;
			}
			n=n>>1;
		}
		return cnt;
	}
	
	static int solve() {
		int ret = INF; //최소값을 찾아야함으로 임의로 큰값으로 초기화
		//총바이러스 수에서 N개만 선택해야함//비트형으로 모든 부분집합 표현하여 원소의 개수가 N개인 것만 사용
		for(int subset = 0; subset <1<<VirusCnt; ++subset) {//subset은 0부터 1을 VirusCnt만큼 쉬프트한?
			//원소의 갯수가 M인것만 사용
			if(countBits(subset)==M) {
				boolean[][] visited = new boolean [50][50];
				int dist = 0; //한칸 가는데 1초
				Queue<Point> myqueue = new LinkedList<Point>();
				//선택된 바이러스를 모두 queue에 넣고 탐색 시작
				for(int i=0;i<VirusCnt;i++) {
					//i번째 바이러스가 visit되었는지 확인
					if((subset & 1 <<i)!=0) {
						visited[Virus[i].r][Virus[i].c] = true;
						myqueue.add(Virus[i]);//해당 큐의 맨 뒤에 전달된 요소를 삽입함.
					}
				}
				while(myqueue.size() != 0) {
					Point curr = myqueue.poll();
					//E poll() 해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환하고, 해당 요소를 큐에서 제거함. 만약 큐가 비어있으면 null을 반환함.
					//지금 방문된곳이 바이러스가 아닌경우 업데이트 되도록(마지막으로 비활성화 바이러스 방문시 시간이 증가되는것을 막음)
					if(Arr[curr.r][curr.c]!=2)
					dist= Math.max(dist, curr.d);
					//네방향으로 진행
					for(int i =0;i<4;i++) {
						//새로운 위치 nr
						//현재위치에 dr, dc값을 더해주면됨
						int nr = curr.r+dr[i], nc = curr.c+dc[i];
						//배열의 범위를 벗어나지 않도록
						if(nr<0 || nr >N-1||nc<0||nc>N-1) continue;
						//이미 방문했거나 벽(1)이면 skip하도록
						if(visited[nr][nc]||Arr[nr][nc]==1) continue;
						visited[nr][nc]=true;
						myqueue.add(new Point(nr,nc,curr.d+1));
					}
				}
				//모든 빈칸을 방문했는지 확인
				boolean flag = true;
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(Arr[i][j]==0 && visited[i][j]==false)
							flag=false;
					}
				}
				if(flag)
					ret=Math.min(ret, dist);
			}
		}
		if(ret==INF) return -1;
		return ret;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		for(int i =0;i<N;++i) {
			for(int j=0;j<N;++j) {
				Arr[i][j]=sc.nextInt();
				//입력된 배열에 바이러스가 있다면 바이러스 배열에 넣어줌
				if(Arr[i][j]==2) {
					Virus[VirusCnt++] = new Point(i,j,0);//바이러스의 수는 바이러스의 인덱스가 되면서 총 갯수가 되기도함
				}
			}
		}
		
		System.out.println(solve());
	}
}
