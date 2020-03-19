package kakao2_Dart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
	
	static int sum[] = new int[3]; //점수를 갖고 있을 배열//총 3번의 기회로 점수를 만들기 때문
	
	public static void main(String[] args) {
		/*
		예제	dartResult	answer	설명
		1	1S2D*3T	37	1^1 * 2 + 2^2 * 2 + 3^3
		2	1D2S#10S	9	1^2 + 2^1 * (-1) + 10^1
		3	1D2S0T	3	1^2 + 2^1 + 0^3
		4	1S*2T*3S	23	1^1 * 2 * 2 + 2^3 * 2 + 3^1
		5	1D#2S*3S	5	1^2 * (-1) * 2 + 2^1 * 2 + 3^1
		6	1T2D3D#	-4	1^3 + 2^2 + 3^2 * (-1)
		7	1D2S3T*	59	1^2 + 2^1 * 2 + 3^3 * 2	 
			 
		*/
		
		run("1S2D*3T");
		run("1D2S#10S");
		run("1D2S0T");
		run("1S*2T*3S");
		run("1D#2S*3S");
		run("1T2D3D#");
		run("1D2S3T*");
		
	}
	
	public static void run(String msg) {
		String regex="(\\d{1,2}[S|T|D][*|#]{0,1})";//하나의 문자열에 이 정규식은 3번 들어올 수 있음
		//\\는 뒤에 반드시d 정수가 온다는 뜻()는 하나의 문자로 취급,{}횟수 또는 길이, 정수표시 d는 1~2자까지 올 수 있다. //정규식으로 아얘 구분해서 갖고옴, 그 좌표를 이용
		//정규식을 패턴화함, 패턴을 생성하려면 정적 compile메소드를 호출해야함, 이 메소드는 첫번째 인자로 정규식 문자열을 받아들임
		Pattern p = Pattern.compile(regex+regex+regex);
		//실제 들어오는 문자와 매핑, 패턴을 해석하고 문자열에 대해 일치작업 수행, 패턴 객체에 matcher 메소드 호출
		Matcher m = p.matcher(msg);//실제 넘어오는 값이 msg//패턴쓰고 매쳐쓰고 find 찾아라 꼭 해줘야함
		//찾아라//find 는 Matcher의 멤버함수//find는 1번째 값을 찾아내고 true혹은 false를 반환, group()을 쓰면 방금 찾은 1번째 스트링이 튀어나온다. 그다음 find()를 쓰면 2번째 값을 찾고, group()을 쓰면 2번째 값이 튀어나온다.
		m.find();
		System.out.println("그룹카운트는"+m.groupCount());//패턴내 그룹핑한(괄호지정) 전체 갯수 반환
		//패턴 정규표현식은 1부터 시작하므로 1부터 사용//추후 점수는 또 배열이므로 -1씩 해서 넣어야함
		for(int i=1;i<=m.groupCount();i++) {//괄호단위로 총 3번 들어옴
			//이때 넘어오는건 1S같이 잘려서 들어옴
			//또 정규식으로 그룹을 나눌 것
			Pattern p1 = Pattern.compile("(\\d{1,2})([S|T|D])([*|#]{0,1})");//{0,1}은 옵션이라는 뜻
			Matcher m1=p1.matcher(m.group(i));
			System.out.println(i+"번째"+m.group(i));
			m1.find();//해당예시는 한번만 찾으면 되므로 find를 한번만 씀. 여러번 써야하면 while 돌려야함
			//배열에 담으므로 i-1해서 담음, 패턴이 1부터 시작했었으므로//숫자는 String으로 들어오므로 Integer.parseInt
			//영역 처리 S|T|D//여기는 무조건 1이 들어가므로 1(1단계 점수 들어오는 부분)
			sum[i-1] = (int)Math.pow(Integer.parseInt(m1.group(1)), getPow(m1.group(2)));//먼저 넘어온 값의, 제곱을 구함
			setOption(i,m1.group(3));
		}//각숫자당 3번씩 돔
		System.out.println(msg+" - "+(sum[0]+sum[1]+sum[2]));
	}
	//옵션  처리할 메소드
	public static void setOption(int idx, String m) {
		if("*".equals(m)) {
			sum[idx - 1]*=2;
			if(idx>1) {
				sum[idx-2] *=2;
			}
		}
		else if("#".equals(m)) {
			sum[idx-1] *= -1; //아차상은 그만큼 차감
		}
	}
	
	//제곱근 처리할 거라 Power
	public static int getPow(String m) {
		int val = 0;
		if("S".equals(m)) {
			val=1;
		}else if("D".equals(m)) {
			val=2;
		}else if("T".equals(m)) {
			val=3;
		}
		return val;
	}
}
