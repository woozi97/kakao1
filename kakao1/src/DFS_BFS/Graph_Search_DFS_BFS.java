package DFS_BFS;
//첫노드 지정해줘야함
//DFS:Stack
//맨 위에서 데이터를 꺼내고, 뽑은 애의 자식노드들을(복수가능) 추가, 꺼낸애는 출력(이미 스택에 들어갔던거는 패스)
//BFS:Queue
//밑에서 하나 꺼내고 그 뽑은 노드의 자식노드 추가, 꺼낸 노드 출력
/*
 DFS(0)
 0 1 3 5 7 6 8 4 2
 BFS(0)
 0 1 2 3 4 5 6 7 8 
 
 //3번째 노드에서 시작한 경우- 그래프는 트리가 아니므로 반드시 0에서 출발하지 않음
  DFS(3)
  3 5 7 6 8 4 2 1 0
  BFS(3)
  3 1 2 4 5 0 6 7 8
  
  재귀함수 DFS(0)
  일단 노드에 방문하면 출력하고, 자식들을 순서대로 재귀호출해줌
  자식들이 호출받으면 스스로 출력하고 자신들을 호출
  반환하기 전에 자식들을 먼저 호출하기 때문에 재귀호출로 깊이 조회 가능
  
  dfsR(0)
  	dfsR(1)
  		dfsR(2)
  			dfsR(4) - 이때 2의 자식 3과 4중 뭘 먼저 출력할지는 애초에 입력할때 연관성에 따라 다름
  				dfsR(3)
  				 dfsR(5)
  				  dfsR(6)
  				   dfsR(8)
  				  dfsR(7)
 스택과 다른점: 스택은 자식이 여러개인 경우 쌓고나서 호출하기 때문에, 자식중에 맨 마지막에 들어간 애가 먼저 출력됨
 재귀호출은 정방향으로 호출되기 때문에, 먼저 들어간게 먼저 호출됨
 
 */

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

class Queue<T>{
	class Node<T>{
		private T data;
		private Node<T> next;
	
		public Node(T data) {
			this.data=data;
		}
	}
	
	//큐는 앞뒤로 주소 필요//멤버변수 선언
	private Node<T> first;
	private Node<T> last;
	
	public void add(T item) {
		Node<T> t = new Node<T>(item);
		
		if(last != null) {
			last.next=t;
		}
		last =t;
		if(first ==null) {//첫번째 값도 없으면 같은 값을 할 당해줌
			first=last;
		}
	}
	public T remove() {
		if(first ==null) {
			throw new NoSuchElementException();
		}
		T data = first.data;//데이터를 지우기전에 백업먼저
		first = first.next; //그 다음에 있는애가 first가 되게함
		
		if(first==null) {
			//first가 null이면 last도 함께 변경해준다
			last = null;
		}
		return data;//복사해둔 데이터 반환
	}
	public T peek() {
		if(first==null) {
			throw new NoSuchElementException();	
		}
		return first.data;//null이 아니면 현재 first가 가르키는 데이터를 반환하면 됨
	}
	public boolean isEmpty() {
		return first ==null;
	}
}

class Graph{
	class Node{
		int data;
		//인접한 노드들은 LinkedList로 만듦
		LinkedList<Node> adjacent;//그래프에서는 자식 노드가 아니라 인접한 노드가 뭐냐로 사용
		boolean marked; //방문했는지 확인할 플래그 하나
		Node(int data){ //생성자에서 데이터를 받고, 플래그를 false로 초기화하고 LinkedList를 준비시킴
			this.data=data;
			this.marked=false;
			adjacent = new LinkedList<Node>();
		}
	}
	//그래프 만들기
	Node[] nodes; //노드를 저장할 배열
	Graph(int size){//편의로 그래프 크기 고정시킴
		nodes=new Node[size];
		for(int i=0; i<size;i++) {
			nodes[i]=new Node(i);//편의를 위해 데이터 배열방 번호를 동일하게 만듦
		}
	}
	//두 노드의 관계를 저장하는 함수
	void addEdge(int i1, int i2) {
		Node n1 = nodes[i1]; //데이터가 인덱스와 같으므로 받은 숫자를 그대로 사용
		Node n2 = nodes[i2];
		//두개의 노드에 인접한 노드를 저장하는 링크드 리스트에 상대방이 있는지 확인하고 없으면 서로 추가해줌
		if(!n1.adjacent.contains(n2)) {
			n1.adjacent.add(n2);
		}
		if(!n2.adjacent.contains(n1)) {
			n2.adjacent.add(n1);
		}
	}
	void dfs() {
		dfs(0); //dfs 함수를 그냥 호출하면 0번부터 시작하는 것으로 함
	}
	//시작 인덱스를 받아서 dfs 순회결과를 출력하는 함수
	void dfs(int index) {
		Node root = nodes[index];//해당 인덱스로 노드를 가져오고 
		Stack<Node> stack = new Stack<Node>(); //스택하나 생성
		stack.push(root); //현재 노드를 스택에 추가
		root.marked=true; //스택에 들어갔다고 표시
		while(!stack.isEmpty()) {
			//스택에 데이터가 없을 때까지 반복
			//스택에서 데이터를 하나 가져오고, 가져온 노드의 자식 노드를 추가(스택에 추가되지 않았던 노드만 추가)
			Node r = stack.pop();
			for(Node n:r.adjacent) {
				if(n.marked==false) {
					n.marked=true;
					stack.push(n);
				}
			}
			visit(r);//출력
		}
	}
	void bfs() {
		//인자없이 호출되면 0번 부터 시작
		bfs(0);
	}
	void bfs(int index) {
		//인덱스로 받은 인자로 노드를 받아옴
		Node root = nodes[index];
		Queue<Node> queue = new Queue<Node>();
		//데이터를 큐에 추가, 마킹
		queue.add(root);
		root.marked=true;
		while(!queue.isEmpty()) {
			Node r = queue.remove();//데이터를 가져옴
			for(Node n:r.adjacent) {//데이터에서 꺼낸 자식노드를 추가 r.adjacent
				if(n.marked==false) {
					n.marked=true;
					queue.add(n);
				}
			}
			visit(r);
		}
	}
	
	//재귀호출 DFS
	void dfsR(Node r) {
		//재귀호출시 링크드리스트가 노드의 주소를 갖고 있기 때문에 매개변수로 노드를 받아야함
		if(r==null) return;//받은 노드가 null일때는 나감
		r.marked=true;//호출되었다고 마킹함
		//자식들 호출전 데이터를 먼저 출력함
		visit(r);
		//호출이 되지않은 자식들을 호출해줌(출력된 데이터의 자식노드)
		for(Node n:r.adjacent) {
			if(n.marked==false) {
				dfsR(n);
			}
		}
	}
	//시작노드를 다양하게 실험해 보기위해 배열방에 인덱스를 받는 함수도 하나 만듦
	void dfsR(int index) {
		Node r = nodes[index];
		dfsR(r);//인덱스를 받으면 해당 노드를 찾아서 재귀호출 진행
	}
	void dfsR() {
		dfsR(0);//아무것도 없으면 0부터 시작
	}
	
	void visit(Node n) {
		System.out.print(n.data+" ");
	}
}

/*
 
  0
 /
 1--3    7
 | /| \ /
 |/ |  5
 2--4   \
         6-8
 */

public class Graph_Search_DFS_BFS {
	public static void main(String[] args) {
		Graph g = new Graph(9); //9개 노드를 만듦
	//관계 명세(두개씩 넣어주고, 한번 관계 명시하면 양쪽으로 추가되기때문에 중복없이 한번만 해주면됨)
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(0, 1);
		g.addEdge(2, 4);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(5, 6);
		g.addEdge(5, 7);
		g.addEdge(6, 8);
		//g.dfs(3);
		//g.bfs(3);
		g.dfsR(3);
	}
}
