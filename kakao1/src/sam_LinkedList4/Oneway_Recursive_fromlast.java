package sam_LinkedList4;

import sam_LinkedList4.LinkedList.Node;

//단방향 링크드리스트의 끝에서 k번째 노드를 찾는 알고리즘을 구현하시오 -> 자바에서는 함수 데이터를 반환할때 한개의 데이터만 할 수 있어서, 돌때마다 카운트도 계산해야하는데 어떻게 노드를 기억? 카
/*
 2->3->1->4
 단방향 링크드리스트는 항상 맨앞부터 셈
 
 방법1. 링크드리스트의 첫노드부터 뒤로 이동하면서 전체 노드갯수가 몇개인지 셈
 k=1, 총갯수 4개를 세고, k=1 이면 마지막 노드를 가져오자고 하였으므로 +1해야 맞음
 뒤에서 첫번째(k-1)+1 번째 해야 숫자 4가됨
 k-1=3 +1 = 4 앞에서 4번째 데이터가 뒤에서 첫번째 데이터이다.
 */
//방법 1
//노드를 반환
/*
 방법2:재귀호출
 조건만족할때 자신을 호출
 재귀호출 특성상 나올때 뒤에서 부터나옴. 끝까지 들어갔다가 나올때 세면됨
 
 첫번째 노드를 가지고 함수호출, 함수는 받은 노드의 다음 노드를 가지고 자신을 다시 호출
 노드가 null이 될때 까지 계속 자기자신 호출
 마지막 노드는 next가 null인데 한번더 호출함, 그러면 함수는 null이구나 하고 0을 반환
 그러면 반환하는값에 +1을 계속 함 -> 몇번째 노드인지 알게 됨
 
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

public class Oneway_Recursive_fromlast {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		int k=3;//뒤에서 몇번째 값?
		KthToLast(ll.getFirst(),k);
	}
	//카운트 반환
	private static int KthToLast(Node n, int k) {//첫번째 노드와 숫자 k를 받음(k만큼 뒤에서 부터)
		if(n==null) {//노드가 0이면 마지막 노드 다음까지 간 것이므로
			return 0;
		}
		int count = KthToLast(n.next,k)+1;//그리고 n.next값을 k와 같이 호출하면서, 호출한 값에 +1을 하면서 k와 같은지 비교
		if(count==k) {
			System.out.println(k+"th to last node is "+n.data);
		}
		return count;
	}
}
