package sam2_lab3_bit;

import java.util.EmptyStackException;

/*LIFO
 pop() : 맨 마지막 데이터를 뽑는거
 push(): 새로운 데이터를 마지막에 넣는거
 peek() : 마지막 데이터를 보는것
 isEmpty() : 스택에 데이터가 있는지 확인
 
 E: 요소(Element)
K: 키(Key)
N: 숫자(Number)
T: 타입(Type)
V: 값(Value)
S, U, V: 두번째, 세번째, 네번째에 선언된 타입
 */
	//객체를 만들때 데이터타입을 명시하도록 한것<T>, 인티저 같은 것을 꼭 적어줘야함
class Stack<T>{
	 class Node<T>{//같은 타입을 받는 노드 선언 
		 private T data;
		 private Node<T> next;
		 
		 public Node(T data) {//생성자에서 해당 타입의 데이터를 받아서 내부 변수에 저장
			 this.data=data;
		 }
	 }
	 
	 private Node<T> top;//스택은 맨위의 주소만 알고 있으면 됨
	 
	 public T pop() {
		 if(top == null) {
			 throw new EmptyStackException();
		 }
		 T item = top.data; //데이터 반환전에 그 다음애를 top으로 만들어야해서 데이터 백업
		//다음애를 탑으로 만들어줌
		 top = top.next;
		 return item;
	 }
	 public  void push(T item) {
		 Node<T> t = new Node<T>(item);
		 t.next = top;//t 앞에 새로받은 노드를 위치시키고
		 top = t;//그 노드가 탑이됨
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
		System.out.println(s.peek());//마지막꺼를 보는거지 지우는게 아님
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
	}
}
