package sam_LinkedList1;

/*
 LinkedList는 일렬로 연결된 데이터를 저장할 사용
 다음데이터의 주소를 가지고 있는 구조, 길이가 정해져 있지 않음
배열은 물리적으로 주소가 연결되어있음, 그래서 중간에 더 늘리는거나 하는것이 불가능
링크드 리스트는 해당 노드가 갖고있던 주소를 바꿔주면됨, 링크드리스트는 주소를 찾아야하기 때문에 배열보다 느릴수도 있음
 
LinkedList 단/양방향
 단방향이면 항상 검색할때 처음부터 다시해야함
양방향이면 앞뒤로 자유롭게 조회할 수 있음
 - 삭제나 조회할때 갖고있는 주소를 이전 노드나, 이다음 로드로 변경해주어야함
*/
//단방향 링크드리스트 구현
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
public class Oneway_Main {
	public static void main(String[] args) {
		Node head = new Node(1);//시작하는 노드 하나 만듦
		head.append(2);
		head.append(3);
		head.append(4);
		head.retrieve();
		head.delete(2);
		head.delete(3);
		head.retrieve();
		
	}
}
