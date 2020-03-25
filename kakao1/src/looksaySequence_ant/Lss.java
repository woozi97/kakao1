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
		//10번 반복      
		for(int seq=0;seq<times;seq++) {        
			//시작은 1이다.//최초 한번만 실행
			if("".equals(result)) {//리절트가 없는 경우
				result="1";
			}
			else {
				//한글자씩 뜯어서 봐야한다.
				char[] input = result.toCharArray();//toCharArray() 하면 배열로 저장됨//배열넣어 포문돌림
				result="";//result는 input에 넣고 초기화
				String target = "";//읽어온 값을 target에 넣어야함, target이 바뀌면 result에 append함//카운트를 세는 용도(몇개인지 누적), result에 넣어서 출력할 것이므로 result 초기화;
				//input 하나하나 읽기, 첫번째 input 1, 두번째 11, 세번쨰 12
				for(int i =0;i<input.length;i++) {//input에 12가 들어가면 두번돌면됨
					//첫문자는 target에 append한다., result에도 append //순번이 필요해서 향상된 포문보다는 그냥 포문사용
					if(i==0) {
						target =""+input[i];//target은 반복숫자 //input[0]=1//앞에 아무것도 없는 문자열 ""을 더해주면 자등으로 캐스팅됨
						result="1"; //result를 초기화 시켰으므로 다시 넣어줌						
					}
					//똑같은 문자가 들어온 경우는 target에 append//비교필요
					//두번째 포문돌때 target은 1이됨
					else if(target.charAt(0) ==input[i] ) {//charAt은 첫글자 가져올 수 있음
						target +=""+input[i]; //target이 11이 됨
						System.out.println("target="+target);
					}
					//다른문자가 왔을때는 출력// 다른문자 result에 target수를 append, 첫문자는 target에 append
					//다른경우는 이제 하나밖에 없으므로 else로 해도됨 else if(target.charAt(0) !=input[i] ) 
					else {
						//새로운 문자가 왔을때 result에 수를 더함
						result+=""+target.length()+input[i];//112가 됨
						target =""+input[i];//타겟에 새로운 숫자 들어감
					}
					//1121
					
					//1번을 돌더라도 반복문이 끝났다면, 끝인지 확인
					if(i ==(input.length-1)) {
						//여기서 더해주는 target.length()는 새로운 수의 길이
						result +=""+target.length();//숫자에 ""을 append 해서 문자로 캐스팅//2회일때 target에는 11이 들어가 있음
					}
				}
			}
			
			//출력
			//System.out.println(result);
			System.out.printf("%2d 회전 - %s\n", seq+1, result);//출력은 result만
		}                
	}
}
