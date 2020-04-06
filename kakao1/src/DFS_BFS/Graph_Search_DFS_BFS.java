package DFS_BFS;
//ù��� �����������
//DFS:Stack
//�� ������ �����͸� ������, ���� ���� �ڽĳ�����(��������) �߰�, �����ִ� ���(�̹� ���ÿ� �����Ŵ� �н�)
//BFS:Queue
//�ؿ��� �ϳ� ������ �� ���� ����� �ڽĳ�� �߰�, ���� ��� ���
/*
 DFS(0)
 0 1 3 5 7 6 8 4 2
 BFS(0)
 0 1 2 3 4 5 6 7 8 
 
 //3��° ��忡�� ������ ���- �׷����� Ʈ���� �ƴϹǷ� �ݵ�� 0���� ������� ����
  DFS(3)
  3 5 7 6 8 4 2 1 0
  BFS(3)
  3 1 2 4 5 0 6 7 8
  
  ����Լ� DFS(0)
  �ϴ� ��忡 �湮�ϸ� ����ϰ�, �ڽĵ��� ������� ���ȣ������
  �ڽĵ��� ȣ������� ������ ����ϰ� �ڽŵ��� ȣ��
  ��ȯ�ϱ� ���� �ڽĵ��� ���� ȣ���ϱ� ������ ���ȣ��� ���� ��ȸ ����
  
  dfsR(0)
  	dfsR(1)
  		dfsR(2)
  			dfsR(4) - �̶� 2�� �ڽ� 3�� 4�� �� ���� ��������� ���ʿ� �Է��Ҷ� �������� ���� �ٸ�
  				dfsR(3)
  				 dfsR(5)
  				  dfsR(6)
  				   dfsR(8)
  				  dfsR(7)
 ���ð� �ٸ���: ������ �ڽ��� �������� ��� �װ��� ȣ���ϱ� ������, �ڽ��߿� �� �������� �� �ְ� ���� ��µ�
 ���ȣ���� ���������� ȣ��Ǳ� ������, ���� ���� ���� ȣ���
 
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
	
	//ť�� �յڷ� �ּ� �ʿ�//������� ����
	private Node<T> first;
	private Node<T> last;
	
	public void add(T item) {
		Node<T> t = new Node<T>(item);
		
		if(last != null) {
			last.next=t;
		}
		last =t;
		if(first ==null) {//ù��° ���� ������ ���� ���� �� ������
			first=last;
		}
	}
	public T remove() {
		if(first ==null) {
			throw new NoSuchElementException();
		}
		T data = first.data;//�����͸� ��������� �������
		first = first.next; //�� ������ �ִ¾ְ� first�� �ǰ���
		
		if(first==null) {
			//first�� null�̸� last�� �Բ� �������ش�
			last = null;
		}
		return data;//�����ص� ������ ��ȯ
	}
	public T peek() {
		if(first==null) {
			throw new NoSuchElementException();	
		}
		return first.data;//null�� �ƴϸ� ���� first�� ����Ű�� �����͸� ��ȯ�ϸ� ��
	}
	public boolean isEmpty() {
		return first ==null;
	}
}

class Graph{
	class Node{
		int data;
		//������ ������ LinkedList�� ����
		LinkedList<Node> adjacent;//�׷��������� �ڽ� ��尡 �ƴ϶� ������ ��尡 ���ķ� ���
		boolean marked; //�湮�ߴ��� Ȯ���� �÷��� �ϳ�
		Node(int data){ //�����ڿ��� �����͸� �ް�, �÷��׸� false�� �ʱ�ȭ�ϰ� LinkedList�� �غ��Ŵ
			this.data=data;
			this.marked=false;
			adjacent = new LinkedList<Node>();
		}
	}
	//�׷��� �����
	Node[] nodes; //��带 ������ �迭
	Graph(int size){//���Ƿ� �׷��� ũ�� ������Ŵ
		nodes=new Node[size];
		for(int i=0; i<size;i++) {
			nodes[i]=new Node(i);//���Ǹ� ���� ������ �迭�� ��ȣ�� �����ϰ� ����
		}
	}
	//�� ����� ���踦 �����ϴ� �Լ�
	void addEdge(int i1, int i2) {
		Node n1 = nodes[i1]; //�����Ͱ� �ε����� �����Ƿ� ���� ���ڸ� �״�� ���
		Node n2 = nodes[i2];
		//�ΰ��� ��忡 ������ ��带 �����ϴ� ��ũ�� ����Ʈ�� ������ �ִ��� Ȯ���ϰ� ������ ���� �߰�����
		if(!n1.adjacent.contains(n2)) {
			n1.adjacent.add(n2);
		}
		if(!n2.adjacent.contains(n1)) {
			n2.adjacent.add(n1);
		}
	}
	void dfs() {
		dfs(0); //dfs �Լ��� �׳� ȣ���ϸ� 0������ �����ϴ� ������ ��
	}
	//���� �ε����� �޾Ƽ� dfs ��ȸ����� ����ϴ� �Լ�
	void dfs(int index) {
		Node root = nodes[index];//�ش� �ε����� ��带 �������� 
		Stack<Node> stack = new Stack<Node>(); //�����ϳ� ����
		stack.push(root); //���� ��带 ���ÿ� �߰�
		root.marked=true; //���ÿ� ���ٰ� ǥ��
		while(!stack.isEmpty()) {
			//���ÿ� �����Ͱ� ���� ������ �ݺ�
			//���ÿ��� �����͸� �ϳ� ��������, ������ ����� �ڽ� ��带 �߰�(���ÿ� �߰����� �ʾҴ� ��常 �߰�)
			Node r = stack.pop();
			for(Node n:r.adjacent) {
				if(n.marked==false) {
					n.marked=true;
					stack.push(n);
				}
			}
			visit(r);//���
		}
	}
	void bfs() {
		//���ھ��� ȣ��Ǹ� 0�� ���� ����
		bfs(0);
	}
	void bfs(int index) {
		//�ε����� ���� ���ڷ� ��带 �޾ƿ�
		Node root = nodes[index];
		Queue<Node> queue = new Queue<Node>();
		//�����͸� ť�� �߰�, ��ŷ
		queue.add(root);
		root.marked=true;
		while(!queue.isEmpty()) {
			Node r = queue.remove();//�����͸� ������
			for(Node n:r.adjacent) {//�����Ϳ��� ���� �ڽĳ�带 �߰� r.adjacent
				if(n.marked==false) {
					n.marked=true;
					queue.add(n);
				}
			}
			visit(r);
		}
	}
	
	//���ȣ�� DFS
	void dfsR(Node r) {
		//���ȣ��� ��ũ�帮��Ʈ�� ����� �ּҸ� ���� �ֱ� ������ �Ű������� ��带 �޾ƾ���
		if(r==null) return;//���� ��尡 null�϶��� ����
		r.marked=true;//ȣ��Ǿ��ٰ� ��ŷ��
		//�ڽĵ� ȣ���� �����͸� ���� �����
		visit(r);
		//ȣ���� �������� �ڽĵ��� ȣ������(��µ� �������� �ڽĳ��)
		for(Node n:r.adjacent) {
			if(n.marked==false) {
				dfsR(n);
			}
		}
	}
	//���۳�带 �پ��ϰ� ������ �������� �迭�濡 �ε����� �޴� �Լ��� �ϳ� ����
	void dfsR(int index) {
		Node r = nodes[index];
		dfsR(r);//�ε����� ������ �ش� ��带 ã�Ƽ� ���ȣ�� ����
	}
	void dfsR() {
		dfsR(0);//�ƹ��͵� ������ 0���� ����
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
		Graph g = new Graph(9); //9�� ��带 ����
	//���� ��(�ΰ��� �־��ְ�, �ѹ� ���� ����ϸ� �������� �߰��Ǳ⶧���� �ߺ����� �ѹ��� ���ָ��)
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
