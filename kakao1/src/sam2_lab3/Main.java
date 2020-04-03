package sam2_lab3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//https://www.acmicpc.net/problem/17142
//���� 17142
/*��Ʈ ����Ʈ �����ڶ� ���״�� bit �� shift �����ִ� �༮�̴�.

�����ڴ� ������ ���� 3������ �ִ�.
(1) << : �������� �̵�
(2) >> : ���������� �̵�
(3) >>> : unsigned, ���������� �̵�

'A << B' ��� ������ �ִٰ� �ϸ�, A ��� �༮�� ��Ʈ ���� B ��ŭ �������� �̵���Ű�ڴ�.��� �����ϸ� �ȴ�. (��, A�� B�� �����̴�)
https://secretroute.tistory.com/entry/%EC%9E%90%EB%B0%94%EC%9D%98%E7%A5%9E-Vol1-%EB%B9%84%ED%8A%B8-%EC%8B%9C%ED%94%84%ED%8A%B8-%EC%97%B0%EC%82%B0%EC%9E%90
*/
public class Main {
	//���̷��� ��ġ ���
	static class Point{
		int r,c,d; //d�� �Ÿ�, r�� row, c�� column
		//������
		Point(int row, int col, int dist){
			r=row;
			c=col;
			d=dist;
		}
	}
	static final int INF=987654321; //�ּҰ� ã������ ���� ������ �ִ밪
	static int N, M;
	static int[][] Arr = new int[50][50]; //�迭�� �ִ� 50���� ���ü� �ִٰ� �Ͽ���
	static Point[] Virus = new Point[10]; //���̷����� �ִ� 10����. ����뵵
	static int VirusCnt;
	static int[] dr = {-1, 1, 0,0};//���� �¿� row����, �¿��϶��� row�� ��ȭ����
	static int[] dc = {0,0,-1,1};//�����¿� �϶� col����, �¿��϶� ��ȭ��
	
	static int countBits(int n) {
		int cnt=0; //����ī������ ���� ���� �ʱ�ȭ
		while(n>0) {
			if((n&1)==1) {//�ǿ����� ��Ʈ�� 1�̶�� �ϳ���
				cnt++;
			}
			n=n>>1;
		}
		return cnt;
	}
	
	static int solve() {
		int ret = INF; //�ּҰ��� ã�ƾ������� ���Ƿ� ū������ �ʱ�ȭ
		//�ѹ��̷��� ������ N���� �����ؾ���//��Ʈ������ ��� �κ����� ǥ���Ͽ� ������ ������ N���� �͸� ���
		for(int subset = 0; subset <1<<VirusCnt; ++subset) {//subset�� 0���� 1�� VirusCnt��ŭ ����Ʈ��?
			//������ ������ M�ΰ͸� ���
			if(countBits(subset)==M) {
				boolean[][] visited = new boolean [50][50];
				int dist = 0; //��ĭ ���µ� 1��
				Queue<Point> myqueue = new LinkedList<Point>();
				//���õ� ���̷����� ��� queue�� �ְ� Ž�� ����
				for(int i=0;i<VirusCnt;i++) {
					//i��° ���̷����� visit�Ǿ����� Ȯ��
					if((subset & 1 <<i)!=0) {
						visited[Virus[i].r][Virus[i].c] = true;
						myqueue.add(Virus[i]);//�ش� ť�� �� �ڿ� ���޵� ��Ҹ� ������.
					}
				}
				while(myqueue.size() != 0) {
					Point curr = myqueue.poll();
					//E poll() �ش� ť�� �� �տ� �ִ�(���� ���� �����) ��Ҹ� ��ȯ�ϰ�, �ش� ��Ҹ� ť���� ������. ���� ť�� ��������� null�� ��ȯ��.
					//���� �湮�Ȱ��� ���̷����� �ƴѰ�� ������Ʈ �ǵ���(���������� ��Ȱ��ȭ ���̷��� �湮�� �ð��� �����Ǵ°��� ����)
					if(Arr[curr.r][curr.c]!=2)
					dist= Math.max(dist, curr.d);
					//�׹������� ����
					for(int i =0;i<4;i++) {
						//���ο� ��ġ nr
						//������ġ�� dr, dc���� �����ָ��
						int nr = curr.r+dr[i], nc = curr.c+dc[i];
						//�迭�� ������ ����� �ʵ���
						if(nr<0 || nr >N-1||nc<0||nc>N-1) continue;
						//�̹� �湮�߰ų� ��(1)�̸� skip�ϵ���
						if(visited[nr][nc]||Arr[nr][nc]==1) continue;
						visited[nr][nc]=true;
						myqueue.add(new Point(nr,nc,curr.d+1));
					}
				}
				//��� ��ĭ�� �湮�ߴ��� Ȯ��
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
				//�Էµ� �迭�� ���̷����� �ִٸ� ���̷��� �迭�� �־���
				if(Arr[i][j]==2) {
					Virus[VirusCnt++] = new Point(i,j,0);//���̷����� ���� ���̷����� �ε����� �Ǹ鼭 �� ������ �Ǳ⵵��
				}
			}
		}
		
		System.out.println(solve());
	}
}
