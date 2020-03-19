package kakao2_Dart;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Dart score : ");
		String inputVal = scanner.nextLine();
		
		int len = inputVal.length(); //입력된 값의 길이
		int step = 1; // 1:점수 2:보너스 3:옵션[*,#] //점수는 숫자만 들어올거고 옵션은 문자가 들어옴//3단계는 옵션이라 없을 수도 있음
		int lenPos = 0; //입력된 값의 길이, 한자씩 위치를 따짐
		int[] score = new int [3]; //들어올 3개의 점수
		int scorePos = 0; //점수의 좌표
		//1S2D*3T - 37  1^1 * 2 + 2^2 * 2 + 3^3
		//1. 입력된 길이만큼 반복 분석
		while(lenPos<len) {
			String target = inputVal.substring(lenPos,lenPos+1); //한글자만 가져옴
			if(step==1) {
				try {
					//1.1 1단계 처리(숫자만 올 수 있다.)
					score[scorePos]=Integer.parseInt(target);
					step++;
				}catch(Exception e) {
					//1.3 3단계 옵션처리 //숫자배열에 문자가 들어가게 되면 오류가 발생하는 것을  try catch를 활용하여 처리
					if("*".equals(target)){
						//1.3.1 스타상
						score[scorePos-1]=score[scorePos-1]*2;
						if(scorePos>1) {
							score[scorePos-2]=score[scorePos-2]*2;
						}
					}else if("#".equals(target)){
						//1.3.2 아차상, 마이너스 된다. 곱하기 마이너스 하면 됨
						score[scorePos-1]=score[scorePos-1]*(-1);
					}else {
						System.out.println("유효한 다트 점수가 아닙니다.");
						return;
					}
				}
			}
			else if(step==2) {
				//1.2 2단계 영역처리
				if("S".equals(target)) {
					//1.2.1 싱글처리
					score[scorePos]=(int)Math.pow(score[scorePos], 1);
					
				}else if("D".equals(target)) {
					//1.2.2 더블처리
					score[scorePos]=(int)Math.pow(score[scorePos], 2);
				}else if("T".equals(target)) {
					//1.2.3  트피플처리
					score[scorePos]=(int)Math.pow(score[scorePos], 3);
				}else if("0".equals(target)) {
					//1.1.1 10점 처리
					//점수가 0에서 10점 사이의 정수이기 때문에 0이 나올 수 있다.
					if(score[scorePos]==1) {
						score[scorePos]=10;
						//2단계 처리를 다시 하기 위해서 값을 조절
						scorePos--;
						step++;
					}
				}
				else {
					System.out.println("유효한 다트 점수가 아닙니다.");
					return;	
				}
				scorePos++;
				step--; //다시 1단계로 넘어가야하므로
			}
			//다음 문자 처리
			lenPos++;
		}
		System.out.println(score[0]+score[1]+score[2]);
	}
}
