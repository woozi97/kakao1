package kakao5_VPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 입력
입력 데이터는 표준 입력을 사용한다. 입력은 T개의 테스트 데이터로 주어진다. 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 하나의 괄호 문자열의 길이는 2 이상 50 이하이다. 

출력
출력은 표준 출력을 사용한다. 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.

예제입력
6
(())())
(((()())()
(()())((()))
((()()(()))(((())))()
()()()()(()()())()
(()((())()(
 
예제출력
NO
NO
YES
NO
YES
NO

백준 9012
*/
public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// BuffredReader는 입력받을때 readLine()쓰면되고 무조건 String으로 들어오므로 필요시 형변환을 꼭해줘야한다.
		// BufferedReader로 들어온 값을 공백단위로 끊어서 이용하려면 StringTokenizer()을 써야한다.
		String input = br.readLine();

		int i;
		boolean isVPS = true;
		Stack_Test<Character> stack = new Stack_Test<Character>();

		char temp;
		int left = 0, right =0;
		for (i = 0; i < input.length(); i++) {
			temp = input.charAt(i);// 현재 문자

			if (temp == '(') {
				// 여는 괄호는 스택에 push하여 담는다.
				stack.push(temp);
				left++;
			} else if (temp == ')') {
				// 닫는 괄호가 나온 경우 스택의 맨 위의 값이 여는 괄호인지 비교한다.
				right++;
				if (!stack.isEmpty()) {
					// 스택이 비어있지 않고 맨 윗값이 여는 괄호라면 스택의 맨 위값을 pop한다.
					stack.pop();
				} else {
					isVPS = false;
					break;
				}
			}
			if(left==right)
				isVPS=true;
		}
		if (isVPS) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
