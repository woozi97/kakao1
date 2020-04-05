package sam2_lab3_bit;

import java.util.NoSuchElementException;

/*FIFO
 add() : �ǵڿ� �߰�
 remove() : �� �տ��� ����
 peek() : �Ǿտ� ���� �ִ��� ��
 isEmpty() : ť�� ������� Ȯ��
 
 */

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
