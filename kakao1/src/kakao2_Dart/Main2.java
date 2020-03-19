package kakao2_Dart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2 {
	
	static int sum[] = new int[3]; //������ ���� ���� �迭//�� 3���� ��ȸ�� ������ ����� ����
	
	public static void main(String[] args) {
		/*
		����	dartResult	answer	����
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
		String regex="(\\d{1,2}[S|T|D][*|#]{0,1})";//�ϳ��� ���ڿ��� �� ���Խ��� 3�� ���� �� ����
		//\\�� �ڿ� �ݵ��d ������ �´ٴ� ��()�� �ϳ��� ���ڷ� ���,{}Ƚ�� �Ǵ� ����, ����ǥ�� d�� 1~2�ڱ��� �� �� �ִ�. //���Խ����� �ƾ� �����ؼ� �����, �� ��ǥ�� �̿�
		//���Խ��� ����ȭ��, ������ �����Ϸ��� ���� compile�޼ҵ带 ȣ���ؾ���, �� �޼ҵ�� ù��° ���ڷ� ���Խ� ���ڿ��� �޾Ƶ���
		Pattern p = Pattern.compile(regex+regex+regex);
		//���� ������ ���ڿ� ����, ������ �ؼ��ϰ� ���ڿ��� ���� ��ġ�۾� ����, ���� ��ü�� matcher �޼ҵ� ȣ��
		Matcher m = p.matcher(msg);//���� �Ѿ���� ���� msg//���Ͼ��� ���ľ��� find ã�ƶ� �� �������
		//ã�ƶ�//find �� Matcher�� ����Լ�//find�� 1��° ���� ã�Ƴ��� trueȤ�� false�� ��ȯ, group()�� ���� ��� ã�� 1��° ��Ʈ���� Ƣ��´�. �״��� find()�� ���� 2��° ���� ã��, group()�� ���� 2��° ���� Ƣ��´�.
		m.find();
		System.out.println("�׷�ī��Ʈ��"+m.groupCount());//���ϳ� �׷�����(��ȣ����) ��ü ���� ��ȯ
		//���� ����ǥ������ 1���� �����ϹǷ� 1���� ���//���� ������ �� �迭�̹Ƿ� -1�� �ؼ� �־����
		for(int i=1;i<=m.groupCount();i++) {//��ȣ������ �� 3�� ����
			//�̶� �Ѿ���°� 1S���� �߷��� ����
			//�� ���Խ����� �׷��� ���� ��
			Pattern p1 = Pattern.compile("(\\d{1,2})([S|T|D])([*|#]{0,1})");//{0,1}�� �ɼ��̶�� ��
			Matcher m1=p1.matcher(m.group(i));
			System.out.println(i+"��°"+m.group(i));
			m1.find();//�ش翹�ô� �ѹ��� ã���� �ǹǷ� find�� �ѹ��� ��. ������ ����ϸ� while ��������
			//�迭�� �����Ƿ� i-1�ؼ� ����, ������ 1���� �����߾����Ƿ�//���ڴ� String���� �����Ƿ� Integer.parseInt
			//���� ó�� S|T|D//����� ������ 1�� ���Ƿ� 1(1�ܰ� ���� ������ �κ�)
			sum[i-1] = (int)Math.pow(Integer.parseInt(m1.group(1)), getPow(m1.group(2)));//���� �Ѿ�� ����, ������ ����
			setOption(i,m1.group(3));
		}//�����ڴ� 3���� ��
		System.out.println(msg+" - "+(sum[0]+sum[1]+sum[2]));
	}
	//�ɼ�  ó���� �޼ҵ�
	public static void setOption(int idx, String m) {
		if("*".equals(m)) {
			sum[idx - 1]*=2;
			if(idx>1) {
				sum[idx-2] *=2;
			}
		}
		else if("#".equals(m)) {
			sum[idx-1] *= -1; //�������� �׸�ŭ ����
		}
	}
	
	//������ ó���� �Ŷ� Power
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
