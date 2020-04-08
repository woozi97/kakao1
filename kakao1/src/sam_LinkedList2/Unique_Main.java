package sam_LinkedList2;

/*
 정렬되어있지 않은 링크드리스트의 중복값을 제거하시오
 단, 별도의 버퍼를 사용하지 말 것
 
 3->2->1->2->4
 
 뭔가 저장할 수 있는 버퍼를 사용해서 쉽게 일단 해봄
 HashSet(키값을 갖고 찾아들어가는 시간이 1밖에 안걸림)
 해쉬셋 이미 저장했다가 이미 값이 있다 하면 그 노드는 삭제
N이라는 포인터를 이동해가며 체크

버퍼대신 포인터를 사용해서 구현
포인터 n과 러너 r을 선언, n은 하나씩 r은 왔다갔다 다녀옴, 러너야 n인 3의 값이 있으면 삭제하고 와라
지우면서 다음 링크 주소도 바꿔야함
 */
class LinkedList{
	Node header; //헤더 노드 선언
	
	static class Node{
		int data;//데이터
		Node next = null;//다음주소값,초기값은 null
	}
	//생성자
	LinkedList(){
		header = new Node(); //**헤더 노드를 생성하도록 함
	}
	//어팬드 할때 값을 하나 받고 새로운 노드 생성
	void append(int d) {
		Node end = new Node(); //맨마지막에 넣을 것//*받은 값으로 만들지 않음
		end.data=d; //**여기서 받은 값 사용
		Node n = header; //** 여기에 헤더 할당
		//마지막 노드를찾을때 까지 돌림
		while(n.next!=null) {
			n=n.next; //n의 마지막 노드를 선택하고 나오게 될 것//마지막 값은 n
		}
		n.next = end; //마지막 값에 새로 생성한 값을 넣어주면 새로운 애가 젤 마지막 노드가 됨
	}
	//삭제
	void delete(int d) {//삭제할 값 받음
		Node n = header; //**헤더 할당해서 시작점으로 함,단방향과 다르게 초기값을 header 로 줌(단방향처럼 첫번째 값을 삭제하지 않아도 됨, 첫번째 값은 header로 관리용도로만 쓰임)
		while(n.next!=null) {//마지막 까지 돌림(마지막까진 안감)
			if(n.next.data==d) {//직전 노드에서 이다음값이 내가 지울 값인지를 확인
				n.next=n.next.next;//나의 다음을 그 다음다음으로 갈아치움(바로 다음 노드는 사라짐)
			}else {//값이 다른경우는 계속 이동하면서 삭제할 값을 찾음
				n=n.next;
			}
		}
		//이 경우는 첫번째 헤더는 삭제 못하도록 코딩함(다른 노드가 첫번째 헤더를 참조할 경우가 있을 수 있어서)
	}
	//현재 링크드 리스트에 어떤 값이 나열되어 있는지 나열하는 함수
	void retrieve() {
		Node n = header.next; //**헤더 다음 데이터부터 할당해서 출력함
		while(n.next!=null) {//마지막 노드 전단계까지 찾아서 출력
			System.out.print(n.data+"->");
			n=n.next;//다음 노드로 이동
		}
		//마지막 노드가 되기 전에 while문을 탈출하고, 마지막에 n=n.next;를 할당했으므로 마지막 데이터를 출력해줌
		 System.out.println(n.data);
	}
	void removeDups() {
		Node n = header; //가장 먼저 선언은 포인터 n선언
		while(n!=null&&n.next!=null) {//n은 마지막 노드까지 돔//마지막이 중복되는경우 n이 next로 이동되는 것을 방지하기 위해 n!=null조건 추가
			Node r = n;//러너는 n의 위치에서 실행
			while(r.next!=null) {
				if(n.data==r.next.data) {//왜 next.data와 비교하냐면 n과 같은 위치에서 다음 노드의 값을 보고 삭제하고 이동해야 하므로, 이게 더 효율적(삭제해야할 노드 안에 있는것 보다 전에 있는게 더 나음)
					//n의 데이터와 r이 돌면서 만난 데이터와 같은지
					r.next=r.next.next;//같으면 r의 주소를 다다음 주소로 갈아치운다.
				}else {
					//중보깅 안되면 그냥 이동;
					r=r.next;
				}
			}
			//러너가 다 돌고오면 n은 그 다음 값으로 이동
			n=n.next;//이 코드는 마지막 코드가 중복되면 에러남, r은 다음이 중복해서 삭제해버렸는데 n은 마지막으로 이동해 버리므로 조건 하나 더 필요
		}
	}
}

/*
 시간O(N^2)
 공간O(1)
 시간은 많이 들지만 공간 효율성있음*/
public class Unique_Main {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		/*
		ll.append(2);
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.append(3);
		*/
		ll.append(2);
		ll.append(2);
		ll.retrieve();
		ll.removeDups();
		ll.retrieve();
	}
}
