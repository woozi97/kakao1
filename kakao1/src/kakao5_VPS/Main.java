package kakao5_VPS;

import java.util.Stack;

//https://tech.kakao.com/2019/10/02/kakao-blind-recruitment-2020-round1/
/*
 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
  3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
  4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
  4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
  4-3. ')'를 다시 붙입니다.
  4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
  4-5. 생성된 문자열을 반환합니다.
  
  접근방법:
스택은 LIFO(Last-In-Frist-Out) 특성을 갖는다. 
LIFO 특성은 나중에 들어온 값이 처음으로 빠져나가는것을 뜻한다. 
스택에서는 값을 넣을때 "푸쉬(push)"이라 부르고, 스택에서 값을 뺄때 "팝(pop)"이라 한다.
이 문제에서는 입력을 받아서, 입력 하나하나 비교해 나간다. 
여는 괄호 '(' 만을 스택에 담는다. 그리고 닫는 괄호 ')' 가 나왔을때, 스택에 여는괄호 '(' 가 있는지 확인한다
(스택이 안비어있는지 확인). 여느괄호 '(' 가 있다면, pop하여 닫는 괄호 ')' 와 짝을 맞춘다.
마지막으로 스택에 값이 비어있지 않다면 여는괄호 '(' 와 닫는 괄호의 ')' 쌍이 맞지 않다는 뜻이므로, "NO"를 출력해준다.

 */
public class Main {
	int pos; //위치로 사용
	boolean isCorrect(String str) {
		// 2.열린괄호의 수와 닫힌 괄호의 수가 최초로 같은 시점을 찾음
		// 올바른 문자열인지 아닌지 확인할 용도의 메소드
		boolean ret = true;
		int left = 0, right = 0;// 왼쪽 괄호, 오른쪽 괄호 카운트용

		Stack<Character> mystack = new Stack<Character>();
		
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)=='(') {//현재문자가 (이면
				left++;
				mystack.push('(');
			}else {//닫힌 괄호의 경우
				right++;
				if(mystack.empty())//닫힌괄호가 나왔는데 스택이 비어있으면 짝이 안맞는 거니깐 불린을 false로 바꿈
					ret=false;
				else//열린괄호와 닫힌괄호의 수가 맞다면
					mystack.pop();//짝이 맞는 열린괄호가 스택에 들어있으므로 pop을 해서 꺼내줌
			}
			if(left==right) {
				pos = i+1;//현재 위치 다음이라서 v의 시작 위치 & u의 길이라는 의미
				return ret;//true반환
			}
		}
		return true;
	}

	public String solution(String p) {
		// 1
		if (p.isEmpty())
			return p;
		
		boolean correct = isCorrect(p);//2번확인
		
		String u = p.substring(0,pos);//pos위치를 이용해서 u를 만든다
		String v = p.substring(pos,p.length());
		
		//올바른 문자인 경우
		if(correct) {//3단계 v에 대해서 1번부터 다시 수행하고 //3-1번 u에 붙여서 반환
			return u+solution(v);
		}
		
		
		//4단계 올바르지 않은 경우
		//4-1번, 4-2번
		String answer = "("+solution(v)+")";//4-3
		
		
		//4-4:첫문자와 마지막 문제 제거(포문의 시작과 끝으로 조절)
		for(int i=1;i<u.length()-1;i++) {
			if(u.charAt(i)=='(') //현재문자가 열린괄호이면
				answer +=")"; //열린괄호면 엔서에 닫힌 괄호로 붙여주고
			else
				answer +="(";
		}
		//4-5
		return answer;

	}

	public static void main(String[] args) {

	}
}
