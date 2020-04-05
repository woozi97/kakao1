package sam2_lab3_bit;

import java.util.NoSuchElementException;

/*FIFO
 add() : 맨뒤에 추가
 remove() : 맨 앞에꺼 지움
 peek() : 맨앞에 뭐가 있는지 봄
 isEmpty() : 큐가 비었는지 확인
 
 */

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


public class Queue_Test {
	public static void main(String[] args) {
		Queue<Integer> q= new Queue<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.peek());
		System.out.println(q.remove());
		System.out.println(q.isEmpty());
		System.out.println(q.remove());
		System.out.println(q.isEmpty());
		
	}
}
