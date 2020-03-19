package kakao2_Dart;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Dart score : ");
		String inputVal = scanner.nextLine();
		
		int len = inputVal.length(); //�Էµ� ���� ����
		int step = 1; // 1:���� 2:���ʽ� 3:�ɼ�[*,#] //������ ���ڸ� ���ðŰ� �ɼ��� ���ڰ� ����//3�ܰ�� �ɼ��̶� ���� ���� ����
		int lenPos = 0; //�Էµ� ���� ����, ���ھ� ��ġ�� ����
		int[] score = new int [3]; //���� 3���� ����
		int scorePos = 0; //������ ��ǥ
		//1S2D*3T - 37  1^1 * 2 + 2^2 * 2 + 3^3
		//1. �Էµ� ���̸�ŭ �ݺ� �м�
		while(lenPos<len) {
			String target = inputVal.substring(lenPos,lenPos+1); //�ѱ��ڸ� ������
			if(step==1) {
				try {
					//1.1 1�ܰ� ó��(���ڸ� �� �� �ִ�.)
					score[scorePos]=Integer.parseInt(target);
					step++;
				}catch(Exception e) {
					//1.3 3�ܰ� �ɼ�ó�� //���ڹ迭�� ���ڰ� ���� �Ǹ� ������ �߻��ϴ� ����  try catch�� Ȱ���Ͽ� ó��
					if("*".equals(target)){
						//1.3.1 ��Ÿ��
						score[scorePos-1]=score[scorePos-1]*2;
						if(scorePos>1) {
							score[scorePos-2]=score[scorePos-2]*2;
						}
					}else if("#".equals(target)){
						//1.3.2 ������, ���̳ʽ� �ȴ�. ���ϱ� ���̳ʽ� �ϸ� ��
						score[scorePos-1]=score[scorePos-1]*(-1);
					}else {
						System.out.println("��ȿ�� ��Ʈ ������ �ƴմϴ�.");
						return;
					}
				}
			}
			else if(step==2) {
				//1.2 2�ܰ� ����ó��
				if("S".equals(target)) {
					//1.2.1 �̱�ó��
					score[scorePos]=(int)Math.pow(score[scorePos], 1);
					
				}else if("D".equals(target)) {
					//1.2.2 ����ó��
					score[scorePos]=(int)Math.pow(score[scorePos], 2);
				}else if("T".equals(target)) {
					//1.2.3  Ʈ����ó��
					score[scorePos]=(int)Math.pow(score[scorePos], 3);
				}else if("0".equals(target)) {
					//1.1.1 10�� ó��
					//������ 0���� 10�� ������ �����̱� ������ 0�� ���� �� �ִ�.
					if(score[scorePos]==1) {
						score[scorePos]=10;
						//2�ܰ� ó���� �ٽ� �ϱ� ���ؼ� ���� ����
						scorePos--;
						step++;
					}
				}
				else {
					System.out.println("��ȿ�� ��Ʈ ������ �ƴմϴ�.");
					return;	
				}
				scorePos++;
				step--; //�ٽ� 1�ܰ�� �Ѿ���ϹǷ�
			}
			//���� ���� ó��
			lenPos++;
		}
		System.out.println(score[0]+score[1]+score[2]);
	}
}
