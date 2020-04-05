package kakao5_VPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 �Է�
�Է� �����ʹ� ǥ�� �Է��� ����Ѵ�. �Է��� T���� �׽�Ʈ �����ͷ� �־�����. �Է��� ù ��° �ٿ��� �Է� �������� ���� ��Ÿ���� ���� T�� �־�����. �� �׽�Ʈ �������� ù° �ٿ��� ��ȣ ���ڿ��� �� �ٿ� �־�����. �ϳ��� ��ȣ ���ڿ��� ���̴� 2 �̻� 50 �����̴�. 

���
����� ǥ�� ����� ����Ѵ�. ���� �Է� ��ȣ ���ڿ��� �ùٸ� ��ȣ ���ڿ�(VPS)�̸� ��YES��, �ƴϸ� ��NO���� �� �ٿ� �ϳ��� ���ʴ�� ����ؾ� �Ѵ�.

�����Է�
6
(())())
(((()())()
(()())((()))
((()()(()))(((())))()
()()()()(()()())()
(()((())()(
 
�������
NO
NO
YES
NO
YES
NO

���� 9012
*/
public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// BuffredReader�� �Է¹����� readLine()����ǰ� ������ String���� �����Ƿ� �ʿ�� ����ȯ�� ��������Ѵ�.
		// BufferedReader�� ���� ���� ��������� ��� �̿��Ϸ��� StringTokenizer()�� ����Ѵ�.
		String input = br.readLine();

		int i;
		boolean isVPS = true;
		Stack_Test<Character> stack = new Stack_Test<Character>();

		char temp;
		int left = 0, right =0;
		for (i = 0; i < input.length(); i++) {
			temp = input.charAt(i);// ���� ����

			if (temp == '(') {
				// ���� ��ȣ�� ���ÿ� push�Ͽ� ��´�.
				stack.push(temp);
				left++;
			} else if (temp == ')') {
				// �ݴ� ��ȣ�� ���� ��� ������ �� ���� ���� ���� ��ȣ���� ���Ѵ�.
				right++;
				if (!stack.isEmpty()) {
					// ������ ������� �ʰ� �� ������ ���� ��ȣ��� ������ �� ������ pop�Ѵ�.
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
