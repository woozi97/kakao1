package sam_LinkedList6;

import sam_LinkedList6.LinkedList.Node;

/*
 포인터를 2개 선언해서 하나를 먼저 가게함(차이는 k로 받음), 하나는 계속 첫번째 자리에 있고, 둘이서 동시에 앞으로 하나씩 감, 먼저간 포인터가 null을 만나면
 뒤에따라오던 포인터의 값이 k번째가 됨
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
	Node getFirst() {
		return header.next;
	}
}
public class Pointer_Main {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		int k=1;//뒤에서 몇번째 값?
		Node found = KthToLast(ll.getFirst(),k);
		System.out.println(found.data);
	}

	private static Node KthToLast(Node first, int k) {
		//포인터를 2개 선언
		Node p1=first;
		Node p2=first;
		//p1을 미리 k만큼 이동함
		for(int i=0;i<k;i++) {
			if(p1==null) return null;//널을 만나면 함수 종료
			p1=p1.next;
		}
		//두개의 포인터를 같이 돌려봄//한칸씩 같이 감//p1이 null이 되면 빠져나옴
		while(p1!=null){//p1이 마지막이 아닌 동안
			p1=p1.next;
			p2=p2.next;
		}
		return p2;
	}
}
