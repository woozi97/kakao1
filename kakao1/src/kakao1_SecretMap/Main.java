package kakao1_SecretMap;
//���� �ּ�: https://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
//1�� ����
public class Main {
	public static void main(String[] args) {
		int n = 5;
		int arr1[]= {9, 20, 28, 18, 11};
		int arr2[]= {30, 1, 21, 17, 28};
		//��ǲ�� �����ε� �ƿ�ǲ�� ���ڰ� �ƴ� (String���� ǥ��)
		//["#####","# # #", "### #", "# ##", "#####"]
		String[] result = new String[n]; //��¿�
		
		for(int i=0;i<n;i++) {
			//��Ʈ OR�����ڷ� ���� ��Ʈ �� ����ϳ��� 1�̸� ����� 1�� �ǰ� ��� 0�϶��� 0�� ��
			int arr = arr1[i] | arr2[i]; //��Ʈ������ | (OR)������//�� ������ ��Ʈ OR�� ��ħ
			System.out.println(""+Integer.toBinaryString(arr)); //���⼭ ����غ��� ������� ����
		}

		System.out.println("");
		
		for(int i=0;i<n;i++) {
			String resultString="";
			int arr = arr1[i] | arr2[i];
			//System.out.println(arr);
			int targetBit=1;//�ǳ��ڸ��� �ǹ�, ���⼭ <<����Ʈ�ϸ� 2, 4, 8, 16�� �ٲ�
			//�� ���� 5������ �ǹǷ� �� ���� #####
			for(int j=0;j<n;j++) {
				//&: ��Ʈ AND�����ڷ� ���� ��Ʈ�� ��� 1�϶� ����� 1�̵ǰ� �׷��� ������ 0�̵�
				//arr�� 11111�̶�� Ÿ�ٺ�Ʈ�� �� ��¥�� 1�� ��
			resultString=((arr& targetBit)>0?"#":" ")+resultString;//targetBit�� 1���ΰ� &������ �ϸ� ��Ʈ�� 1���� 0���� �˼��ִ�.
			//�����ʿ��� �������� �����ϴ� ���(2������)
			targetBit=targetBit<<1;//��Ʈ ����Ʈ�� �Ἥ �ڸ��� ��´�. ���� ����Ʈ ������ ���� �ִ� �ǿ������� ��Ʈ���� ������ ������ ����ŭ �������� �̵�, ������ ���� ��Ʈ�� 0���� ä��. ������� [0010] ->[0100]����
			}
			result[i]=resultString;
			System.out.println(result[i]);
		}
	}
}
