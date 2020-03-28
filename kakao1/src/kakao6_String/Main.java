package kakao6_String;
/*
 예를 들어, “ababcdcdababcdcd”의 경우 문자를 1개 단위로 자르면 전혀 압축되지 않지만, 
 2개 단위로 잘라서 압축한다면 “2ab2cd2ab2cd”로 표현할 수 있습니다. 
 다른 방법으로 8개 단위로 잘라서 압축한다면 “2ababcdcd”로 표현할 수 있으며, 
 이때가 가장 짧게 압축하여 표현할 수 있는 방법입니다.

압축할 문자열 s가 매개변수로 주어질 때, 위에 설명한 방법으로 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 
가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.
 */
public class Main {
	public static int solution(String s) {//입력값 최대 1000자
		int answer =s.length();//몇글자씩 잘라야 제일 좋은지 알수없기 때문에, 목표는 결과가 최소가 되는 길이를 찾을 거고, 그 최대는 s의 길이를 넘을 수 없으므로. s의 길이로 초기화
		
		//길이를 어떻게 끊어야하는지 정하는 역할
		//pos는 0, 잘라야하는길이는 i = 1부터 시작
		for (int i = 1; i < s.length()/2; i++) {//절반만큼만 가면 더이상 커질수는 없다.
			int pos =0;//연산을 수행할 위치
			int len = s.length();//최초길이로 초기화 한다음에 압축되는만큼 빼줌
			
			//첫번째 문자열 길이를 끊고 이동하는 역할
			for(;pos+i<=s.length();) {//pos+i의 길이가 문자열 최대 길이가 될 때까지 진행
				//포문안에서 pos를 증가시킬것
				String unit = s.substring(pos,pos+i);//들어온 문자열을 pos부터 pos+i까지 잘라본다//반복 기준이되는 문자열이됨
				pos+=i;//pos가 i만큼 위치 이동
				
				//반복횟수를 세는 for 문
				int cnt=0;//반복횟수 세는 용도
				for(;pos+i<=s.length();) {
					if(unit.equals(s.substring(pos,pos+i))) {//pos는 이미 위에서 증가시켜 놓았으므로 pos, pos+i로 다시 끊을 수 있음//이때 unit이 다음글자를 똑같은 길이로 잘랐을때 일치하면
						++cnt;//반복횟수 증가
						pos+=i;//pos 이동
					}else {//뒷문자열이 일치하지 않으면 break;
						break;
					}
				}
				if(cnt>0) {//연속된 문자가 하나라도 존재하면, 더해져야하는 문자열 길이로 구현
					len-=i*cnt;//각각의 길이가 i라서 cnt횟수만큼 빼주면 됨(문자열 압축)
					
					if(cnt<9) {//반복횟수가 한자리이면 
						len+=1;
					}else if(cnt<99) {
						len+=2;
					}else if(cnt<999) {
						len+=3;
					}else len+=4; //1000자 들어올 수 있기 떄문에 여기까지만
				}
				
				answer = Math.min(answer, len); //최소값비교
			}
			
		}
		return answer;
	}
	public static void main(String[] args) {
		String num = "abcabcabc";
		int i=solution(num);
		System.out.println(i);
	}
}
