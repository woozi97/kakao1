package sam_LinkedList3;


//단방향 링크드리스트의 끝에서 k번째 노드를 찾는 알고리즘을 구현하시오
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
 
 */
class Node{
	int data;
	//다음 Node를 가지고 있어야함
	Node next = null; //초기화 마지막 노드는 next에 null을 가지고 있어야 하므로
	
	Node(int d){
		this.data=d;
	}

//어팬드 할때 값을 하나 받고 새로운 노드 생성
void append(int d) {
	Node end = new Node(d); //맨마지막에 넣을 것//새로운 노드를 받은 값으로 만들어라
	Node n = this; //포인터; this는 첫번째 노드를 선택한것
	//마지막 노드를찾을때 까지 돌림
	while(n.next!=null) {
		n=n.next; //n의 마지막 노드를 선택하고 나오게 될 것//마지막 값은 n
	}
	n.next = end; //마지막 값에 새로 생성한 값을 넣어주면 새로운 애가 젤 마지막 노드가 됨
}
//삭제
void delete(int d) {//삭제할 값 받음
	Node n = this; //임의의 포인터를 만들어 젤 첨을 가리키도록 함. 지워할 할 값을 찾아야하므로
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
	Node n = this; //첨부터 찾아야하니깐 포인터 하나 선언해서 this로 함
	while(n.next!=null) {//마지막 노드 전단계까지 찾아서 출력
		System.out.print(n.data+"->");
		n=n.next;//다음 노드로 이동
	}
	//마지막 노드가 되기 전에 while문을 탈출하고, 마지막에 n=n.next;를 할당했으므로 마지막 데이터를 출력해줌
	 System.out.println(n.data);
}
}
public class Oneway_fromlast {
	public static void main(String[] args) {
		Node first = new Node(1);
		first.append(2);
		first.append(3);
		first.append(4);
		first.retrieve();
		int k=1;//뒤에서 몇번째 값?
		Node kth = KthToLast(first,k);
		System.out.println("Last k("+k+")th data is "+kth.data);
	}

	private static Node KthToLast(Node first, int k) {//첫번째 노드와 숫자 k를 받음(k만큼 뒤에서 부터)
		Node n = first;//노드포인트 선언
		int total = 1; //토탈 숫자 셀 공간 
		while(n.next!=null) {//마지막노드까지 가지 않으니깐 total을 1부터 셈
			total++;//토탈 수를 셈
			n=n.next;
		}
		n=first; //n을 처음으로 보내서 앞에서 부터 셈
		for(int i=1;i<total-k+1;i++) {
			n=n.next;//n은 다음로드를 반환하기 때문에 i는 1부터 시작
		}
		return n;
	}
}
