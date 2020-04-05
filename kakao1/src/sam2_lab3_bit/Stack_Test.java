package sam2_lab3_bit;

import java.util.EmptyStackException;

/*LIFO
 pop() : �� ������ �����͸� �̴°�
 push(): ���ο� �����͸� �������� �ִ°�
 peek() : ������ �����͸� ���°�
 isEmpty() : ���ÿ� �����Ͱ� �ִ��� Ȯ��
 
 E: ���(Element)
K: Ű(Key)
N: ����(Number)
T: Ÿ��(Type)
V: ��(Value)
S, U, V: �ι�°, ����°, �׹�°�� ����� Ÿ��
 */
	//��ü�� ���鶧 ������Ÿ���� ����ϵ��� �Ѱ�<T>, ��Ƽ�� ���� ���� �� ���������
class Stack<T>{
	 class Node<T>{//���� Ÿ���� �޴� ��� ���� 
		 private T data;
		 private Node<T> next;
		 
		 public Node(T data) {//�����ڿ��� �ش� Ÿ���� �����͸� �޾Ƽ� ���� ������ ����
			 this.data=data;
		 }
	 }
	 
	 private Node<T> top;//������ ������ �ּҸ� �˰� ������ ��
	 
	 public T pop() {
		 if(top == null) {
			 throw new EmptyStackException();
		 }
		 T item = top.data; //������ ��ȯ���� �� �����ָ� top���� �������ؼ� ������ ���
		//�����ָ� ž���� �������
		 top = top.next;
		 return item;
	 }
	 public  void push(T item) {
		 Node<T> t = new Node<T>(item);
		 t.next = top;//t �տ� ���ι��� ��带 ��ġ��Ű��
		 top = t;//�� ��尡 ž�̵�
	 }
	 public T peek() {
		 if(top==null) {
			 throw new EmptyStackException(); 
		 }
		 return top.data;
	 }
	 public boolean isEmpty() {
		 return top ==null;
	 }
}

public class Stack_Test {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.peek());//���������� ���°��� ����°� �ƴ�
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
	}
}
