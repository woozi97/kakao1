package samsung1_2dimension_array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*https://cwondev.tistory.com/15 참고
 Comparable - 기본 정렬기준을 구현하는데 사용.
Comparator - 기본 정렬기준 외에 다른 기준으로 정렬하고자할 때 사용. 

결론
Comparable 구현 후 내부의 compareTo 메소드를 오버라이드해 정의해야 하는데, 이 정의 결과에 따라 정렬값이 나온다.
또한, 오브젝트의 다른 값으로 비교를 원한다면 compareTo를 하나하나 바꿔줄 필요 없이, Comparator를 이용하면 된다.

요약(?)

Collections.sort() , Arrays.sort() 등 ~~.sort()는 배열이나 리스트를 정렬할 때 Comparator를 지정하지 않았을 경우
Comprarable을 구현한 클래스의 객체에 구현된 내용에 따라 정렬!! 

https://gmlwjd9405.github.io/2018/10/06/java-==-and-equals.html
반환 형태: integer type
현재 객체 < 인자로 넘어온 객체: return 음수
현재 객체 == 인자로 넘어온 객체: return 0
현재 객체 > 인자로 넘어온 객체: return 양수
사용자가 정의한 정렬 기준에 맞춰 정렬하기 위한 용도 로 compareTo() 메서드를 오버라이드하여 구현한다.
구현 방법
정렬할 객체에 Comparable interface를 implements 후, compareTo() 메서드를 오버라이드하여 구현한다.
compareTo() 메서드 작성법
위의 반환 형태 참고
음수 또는 0이면 객체의 자리가 그대로 유지되며, 양수인 경우에는 두 객체의 자리가 바뀐다.
정렬 사용 방법
Arrays.sort(array)
Collections.sort(list)

 */
public class Main2 {
	//Comparable 인터페이스 상속받을때 정렬할 대상을 <>에 넣어줌
	static class Num implements Comparable<Num>{
		int n;
		int len;
		
		Num(int n, int len){
			this.n=n;
			this.len=len;
		}

		@Override//Comparable 인터페이스 상속하면 오버라이딩해야하는 메소드
		public int compareTo(Num o) {
			if(this.len==o.len) {//카운팅 횟수가 같은 경우
				return this.n<o.n?-1:1;
				/*
				 반환 형태: integer type
				현재 객체 < 인자로 넘어온 객체: return 음수
				현재 객체 == 인자로 넘어온 객체: return 0
				현재 객체 > 인자로 넘어온 객체: return 양수
				음수 또는 0이면 객체의 자리가 그대로 유지되며, 양수인 경우에는 두 객체의 자리가 바뀐다
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
			
			if(time>100) {//100넘어가면 -1
				time=-1;
				break;
			}
			
			if(r<map.length&&c<map[0].length) {
				if(map[r][c]==val)//반복문 종료조건
					break;
			}
			
			int row=map.length;
			int col=map[0].length;
			
			int[][] temp=new int[101][101];
			
			if(row>=col) {//R연산
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
							list.add(new Num(j, cnt[j]));//100열
						}
					}
					Collections.sort(list);
					
					//배열의 사이즈는 정해져있는데 진행할때마다 가장 큰 길이가 나오는 배열에 맞춰서 만들지, 배열의 사이즈가 수정하기 어려워
					//문제에 주어진 조건 크기 만큼 temp배열을 따로 만들어 저장하고 나중에 다시 가져오는 방법을 활용
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
			else {//C연산
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
							list.add(new Num(j, cnt[j]));//100열
						}
					}
					Collections.sort(list);
					
					//배열의 사이즈는 정해져있는데 진행할때마다 가장 큰 길이가 나오는 배열에 맞춰서 만들지, 배열의 사이즈가 수정하기 어려워
					//문제에 주어진 조건 크기 만큼 temp배열을 따로 만들어 저장하고 나중에 다시 가져오는 방법을 활용
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
