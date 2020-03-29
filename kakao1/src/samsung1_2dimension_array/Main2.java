package samsung1_2dimension_array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*https://cwondev.tistory.com/15 ����
 Comparable - �⺻ ���ı����� �����ϴµ� ���.
Comparator - �⺻ ���ı��� �ܿ� �ٸ� �������� �����ϰ����� �� ���. 

���
Comparable ���� �� ������ compareTo �޼ҵ带 �������̵��� �����ؾ� �ϴµ�, �� ���� ����� ���� ���İ��� ���´�.
����, ������Ʈ�� �ٸ� ������ �񱳸� ���Ѵٸ� compareTo�� �ϳ��ϳ� �ٲ��� �ʿ� ����, Comparator�� �̿��ϸ� �ȴ�.

���(?)

Collections.sort() , Arrays.sort() �� ~~.sort()�� �迭�̳� ����Ʈ�� ������ �� Comparator�� �������� �ʾ��� ���
Comprarable�� ������ Ŭ������ ��ü�� ������ ���뿡 ���� ����!! 

https://gmlwjd9405.github.io/2018/10/06/java-==-and-equals.html
��ȯ ����: integer type
���� ��ü < ���ڷ� �Ѿ�� ��ü: return ����
���� ��ü == ���ڷ� �Ѿ�� ��ü: return 0
���� ��ü > ���ڷ� �Ѿ�� ��ü: return ���
����ڰ� ������ ���� ���ؿ� ���� �����ϱ� ���� �뵵 �� compareTo() �޼��带 �������̵��Ͽ� �����Ѵ�.
���� ���
������ ��ü�� Comparable interface�� implements ��, compareTo() �޼��带 �������̵��Ͽ� �����Ѵ�.
compareTo() �޼��� �ۼ���
���� ��ȯ ���� ����
���� �Ǵ� 0�̸� ��ü�� �ڸ��� �״�� �����Ǹ�, ����� ��쿡�� �� ��ü�� �ڸ��� �ٲ��.
���� ��� ���
Arrays.sort(array)
Collections.sort(list)

 */
public class Main2 {
	//Comparable �������̽� ��ӹ����� ������ ����� <>�� �־���
	static class Num implements Comparable<Num>{
		int n;
		int len;
		
		Num(int n, int len){
			this.n=n;
			this.len=len;
		}

		@Override//Comparable �������̽� ����ϸ� �������̵��ؾ��ϴ� �޼ҵ�
		public int compareTo(Num o) {
			if(this.len==o.len) {//ī���� Ƚ���� ���� ���
				return this.n<o.n?-1:1;
				/*
				 ��ȯ ����: integer type
				���� ��ü < ���ڷ� �Ѿ�� ��ü: return ����
				���� ��ü == ���ڷ� �Ѿ�� ��ü: return 0
				���� ��ü > ���ڷ� �Ѿ�� ��ü: return ���
				���� �Ǵ� 0�̸� ��ü�� �ڸ��� �״�� �����Ǹ�, ����� ��쿡�� �� ��ü�� �ڸ��� �ٲ��
				 */
			}
			return this.len<o.len?-1:1;
		}
	}
	
	static int[][] map;
	static int[] cnt;
	static ArrayList<Num> list;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int r=Integer.parseInt(st.nextToken())-1;
		int c=Integer.parseInt(st.nextToken())-1;
		int val=Integer.parseInt(st.nextToken());
		
		map=new int[3][3];
		
		for(int i=0; i<3;i++) {
			st= new StringTokenizer(br.readLine()," ");
			for(int j=0;j<3;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int time=-1;
		
		while(true) {
			time++;
			
			if(time>100) {//100�Ѿ�� -1
				time=-1;
				break;
			}
			
			if(r<map.length&&c<map[0].length) {
				if(map[r][c]==val)//�ݺ��� ��������
					break;
			}
			
			int row=map.length;
			int col=map[0].length;
			
			int[][] temp=new int[101][101];
			
			if(row>=col) {//R����
				int max = Integer.MIN_VALUE;
				for(int i=0;i<row;i++) {
					cnt = new int[101];
					for(int j=0;j<col;j++) {
						if(map[i][j]==0) continue;
						int n=map[i][j];
						cnt[n]++;
					}
					list = new ArrayList<>();
					
					for(int j=1;j<cnt.length;j++) {
						if(cnt[j]!=0) {
							list.add(new Num(j, cnt[j]));//100��
						}
					}
					Collections.sort(list);
					
					//�迭�� ������� �������ִµ� �����Ҷ����� ���� ū ���̰� ������ �迭�� ���缭 ������, �迭�� ����� �����ϱ� �����
					//������ �־��� ���� ũ�� ��ŭ temp�迭�� ���� ����� �����ϰ� ���߿� �ٽ� �������� ����� Ȱ��
					int z=0;
					for(int j=0;j<list.size();j++) {
						temp[i][z]=list.get(j).n;
						temp[i][z+1]=list.get(j).len;
						z+=2;
					}
					if(max<list.size()*2) max = list.size()*2;
				}
				if(max>100) max=100;
				
				map= new int[row][max];
				
				for(int i=0;i<map.length;i++) {
					for(int j=0;j<map[i].length;j++) {
						map[i][j]=temp[i][j];
					}
				}
			}
			else {//C����
				int max = Integer.MIN_VALUE;
				for(int i=0;i<col;i++) {
					cnt = new int[101];
					for(int j=0;j<row;j++) {
						if(map[j][i]==0) continue;
						int n=map[j][i];
						cnt[n]++;
					}
					list = new ArrayList<>();
					
					for(int j=1;j<cnt.length;j++) {
						if(cnt[j]!=0) {
							list.add(new Num(j, cnt[j]));//100��
						}
					}
					Collections.sort(list);
					
					//�迭�� ������� �������ִµ� �����Ҷ����� ���� ū ���̰� ������ �迭�� ���缭 ������, �迭�� ����� �����ϱ� �����
					//������ �־��� ���� ũ�� ��ŭ temp�迭�� ���� ����� �����ϰ� ���߿� �ٽ� �������� ����� Ȱ��
					int z=0;
					for(int j=0;j<list.size();j++) {
						temp[z][i]=list.get(j).n;
						temp[z+1][i]=list.get(j).len;
						z+=2;
					}
					if(max<list.size()*2) max = list.size()*2;
				}
				if(max>100) max=100;
				
				map= new int[max][col];
				
				for(int i=0;i<map.length;i++) {
					for(int j=0;j<map[i].length;j++) {
						map[i][j]=temp[i][j];
					}
				}
			
			}
		}
		System.out.println(time);
	}
}
