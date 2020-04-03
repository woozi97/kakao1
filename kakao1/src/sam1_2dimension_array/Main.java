package sam1_2dimension_array;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 https://www.acmicpc.net/problem/17140
 �� �� �Ǵ� ���� �ִ� ���� �����Ϸ���, ������ ���� �� �� ���Դ��� �˾ƾ� �Ѵ�. 
 �� ����, ���� ���� Ƚ���� Ŀ���� ������, �׷��� ���� ���������� ���� Ŀ���� ������ �����Ѵ�. 
 �� �������� �迭 A�� ���ĵ� ����� �ٽ� �־�� �Ѵ�. ���ĵ� ����� �迭�� ���� ����, ���� ���� Ƚ���� ��� ������, ������ ���� �����̴�.

���� ���, [3, 1, 1]���� 3�� 1��, 1�� 2�� �����Ѵ�. ����, ���ĵ� ����� [3, 1, 1, 2]�� �ȴ�. 
�ٽ� �� �迭���� 3�� 1��, 1�� 2��, 2�� 1�� �����Ѵ�. �ٽ� �����ϸ� [2, 1, 3, 1, 1, 2]�� �ȴ�.
 
 */
public class Main {
	static class Number{
		//���ڿ� �����޼��� �ʵ�� ����//���� ��Ŀ� �� �ΰ��� ���� �迭�ؾ��ϹǷ� Ŭ������ ������ ����°� ��
		int n, c;
		//������
		Number(int num, int cnt){
			n=num;
			c=cnt;
		}
	}
	
	static int R, C, K;
	static int[][] Arr = new int[100][100];
	
	static int solve() {
		//��� ���� ���Ͽ� �� ���� ����, ������ ���� ����
		int rsize =3, csize=3;
		for(int t=0;t<=100;++t) {//t�� �ð�
			if(Arr[R][C]==K) //ó������ �ι��� �࿡ ���� ���� ���� �����Ƿ� 
				return t; 
			
			if(rsize>=csize) {//r����, ���Ǽ��� ���� ������ ���ų� ũ��
				//i�� ���� ����
				for(int i=0;i<rsize;i++) {
					List<Number> numlist = new ArrayList<Number>();
					//�� ���� ī��Ʈ �ϱ����� cnt
					int[] cnt = new int[101];
					//���ε��� ����
					for(int j=0;j<csize;++j) {
						//�迭�� ���� �̿��Ͽ� cnt �迭�� ���� 1�� ������
						cnt[Arr[i][j]]++;
					}
					//numlist�� ���� ����
					for(int c=1;c<=100;++c) {//c�� �ѹ�
						if(cnt[c]>0) {//cnt[1]����
							numlist.add(new Number(c, cnt[c]));//���ڰ� ������ ���� add�����Ƿ�, ����Ƚ���� ������� �̹� �������ڰ� �տ� ����
						}
					}
					//����ī��Ʈ ���� �������� ����
					numlist.sort((lhs, rhs)->{//���ٽ�ǥ��
						return lhs.c-rhs.c;//���� ī��Ʈ Ƚ�� ������ ���� �տ����� ������������ ������, �̹� ���� �������� ���� ���� ������ ����
					});
					//���ĵ����� for������ ������ ��Ŀ� �ٽ� ����
					int idx=0;
					for(Number num : numlist) {//numlist�� ���Ұ����� 100�� �Ѿ ���� �����Ƿ�
						if(idx>=99) break;
						Arr[i][idx++]=num.n;//���ڸ� ���� �ְ�
						Arr[i][idx++]=num.c;
					}
					//r���� ����� ������ �þ ���� �����Ƿ�, ���� �����������Ƿ� ���� �þ �� ����
					csize=Math.max(csize, idx);
					System.out.println("idx="+idx);
					//idx���۰��� ������� r���� �����ϰ� ���� ����
					//�� ���� �������� 99�ε������� ��ĭ�� 0���� ä���
					for(;idx<100;++idx)
						Arr[i][idx]=0;

					/*for(int b=0; b<idx;b++) {
							System.out.print("��� ����"+Arr[i][b]);
					}*/
					
				}
			}else {//c���� ������
				//�̹��� ���� ����
				for(int j=0;j<csize;j++) {
					List<Number> numlist = new ArrayList<Number>();
					//�� ���� ī��Ʈ �ϱ����� cnt
					int[] cnt = new int[101];
					//���ε��� ����
					for(int i=0;i<rsize;++i) {
						//�迭�� ���� �̿��Ͽ� cnt �迭�� ���� 1�� ������
						cnt[Arr[i][j]]++;//j�� �����ǰ� i�� �ϳ��� �����ϸ鼭 ������ ����Ƚ�� ī��Ʈ
					}
					//Num list�� ���� ����
					for(int c=1;c<=100;++c) {
						if(cnt[c]>0) {
							numlist.add(new Number(c, cnt[c]));//���ڰ� ������ ���� add�����Ƿ�, ����Ƚ���� ������� �̹� �������ڰ� �տ� ����
						}
					}
					//������
					numlist.sort((lhs, rhs)->{
						return lhs.c-rhs.c;//����Ƚ�� ������ ���� �տ����� ������������ ������
					});
					//���ĵ����� ��Ŀ� �ٽ� ����
					int idx=0;
					for(Number num : numlist) {//numlist�� ���Ұ����� 100�� �Ѿ ���� �����Ƿ�
						if(idx>=99) break;
						//�����Ҷ��� ���� ��������, ���� �����ϰ� ���� �������Ѱ��鼭 �Է�
						Arr[idx++][j]=num.n;//���ڸ� ���� �ְ�
						Arr[idx++][j]=num.c;
					}
					//c���� ����� ����� �þ ���� �����Ƿ�
					rsize=Math.max(rsize, idx);
					for(;idx<100;++idx)//99���� �ε����� ������ 0���� ä���ֱ�
						Arr[idx][j]=0; //������
				}
			}
			
		}
		return -1;//�ּҽð��� 100�� �Ѿ�� ��쿡�� -1�� ����Ѵ�.
	}
	
	public static void main(String[] args) {
		//ù�ٿ� R,C,K�� �־�����.
		Scanner sc = new Scanner(System.in);
		R= sc.nextInt()-1; //������ ù���� ���ε�, �ε��� 0���� �̿��ϱ� ���� -1���� (��� R,C�� ���� K�� ���� ã�°�)
		C= sc.nextInt()-1;
		K= sc.nextInt();
		
		//ù�� ������ 3x3 ��� ����
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;++j) {
				Arr[i][j]=sc.nextInt();
			}
		}
		//System.out.println("Arr[0][1]= "+Arr[0][1]);
		
		System.out.println(solve());
	}
}
