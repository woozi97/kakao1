package looksaySequence_ant;

public class Lss {
	/*
	1
	11
	12
	1121
	122111
	112213
	12221131
	*/
	public static void main(String[] args) {
		String result="";
		int times = 10;
		//10�� �ݺ�      
		for(int seq=0;seq<times;seq++) {        
			//������ 1�̴�.//���� �ѹ��� ����
			if("".equals(result)) {//����Ʈ�� ���� ���
				result="1";
			}
			else {
				//�ѱ��ھ� �� �����Ѵ�.
				char[] input = result.toCharArray();//toCharArray() �ϸ� �迭�� �����//�迭�־� ��������
				result="";//result�� input�� �ְ� �ʱ�ȭ
				String target = "";//�о�� ���� target�� �־����, target�� �ٲ�� result�� append��//ī��Ʈ�� ���� �뵵(����� ����), result�� �־ ����� ���̹Ƿ� result �ʱ�ȭ;
				//input �ϳ��ϳ� �б�, ù��° input 1, �ι�° 11, ������ 12
				for(int i =0;i<input.length;i++) {//input�� 12�� ���� �ι������
					//ù���ڴ� target�� append�Ѵ�., result���� append //������ �ʿ��ؼ� ���� �������ٴ� �׳� �������
					if(i==0) {
						target =""+input[i];//target�� �ݺ����� //input[0]=1//�տ� �ƹ��͵� ���� ���ڿ� ""�� �����ָ� �ڵ����� ĳ���õ�
						result="1"; //result�� �ʱ�ȭ �������Ƿ� �ٽ� �־���						
					}
					//�Ȱ��� ���ڰ� ���� ���� target�� append//���ʿ�
					//�ι�° �������� target�� 1�̵�
					else if(target.charAt(0) ==input[i] ) {//charAt�� ù���� ������ �� ����
						target +=""+input[i]; //target�� 11�� ��
						System.out.println("target="+target);
					}
					//�ٸ����ڰ� �������� ���// �ٸ����� result�� target���� append, ù���ڴ� target�� append
					//�ٸ����� ���� �ϳ��ۿ� �����Ƿ� else�� �ص��� else if(target.charAt(0) !=input[i] ) 
					else {
						//���ο� ���ڰ� ������ result�� ���� ����
						result+=""+target.length()+input[i];//112�� ��
						target =""+input[i];//Ÿ�ٿ� ���ο� ���� ��
					}
					//1121
					
					//1���� ������ �ݺ����� �����ٸ�, ������ Ȯ��
					if(i ==(input.length-1)) {
						//���⼭ �����ִ� target.length()�� ���ο� ���� ����
						result +=""+target.length();//���ڿ� ""�� append �ؼ� ���ڷ� ĳ����//2ȸ�϶� target���� 11�� �� ����
					}
				}
			}
			
			//���
			//System.out.println(result);
			System.out.printf("%2d ȸ�� - %s\n", seq+1, result);//����� result��
		}                
	}
}
