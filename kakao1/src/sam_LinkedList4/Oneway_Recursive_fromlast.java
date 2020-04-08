package sam_LinkedList4;

import sam_LinkedList4.LinkedList.Node;

//�ܹ��� ��ũ�帮��Ʈ�� ������ k��° ��带 ã�� �˰������� �����Ͻÿ� -> �ڹٿ����� �Լ� �����͸� ��ȯ�Ҷ� �Ѱ��� �����͸� �� �� �־, �������� ī��Ʈ�� ����ؾ��ϴµ� ��� ��带 ���? ī
/*
 2->3->1->4
 �ܹ��� ��ũ�帮��Ʈ�� �׻� �Ǿպ��� ��
 
 ���1. ��ũ�帮��Ʈ�� ù������ �ڷ� �̵��ϸ鼭 ��ü ��尹���� ����� ��
 k=1, �Ѱ��� 4���� ����, k=1 �̸� ������ ��带 �������ڰ� �Ͽ����Ƿ� +1�ؾ� ����
 �ڿ��� ù��°(k-1)+1 ��° �ؾ� ���� 4����
 k-1=3 +1 = 4 �տ��� 4��° �����Ͱ� �ڿ��� ù��° �������̴�.
 */
//��� 1
//��带 ��ȯ
/*
 ���2:���ȣ��
 ���Ǹ����Ҷ� �ڽ��� ȣ��
 ���ȣ�� Ư���� ���ö� �ڿ��� ���ͳ���. ������ ���ٰ� ���ö� �����
 
 ù��° ��带 ������ �Լ�ȣ��, �Լ��� ���� ����� ���� ��带 ������ �ڽ��� �ٽ� ȣ��
 ��尡 null�� �ɶ� ���� ��� �ڱ��ڽ� ȣ��
 ������ ���� next�� null�ε� �ѹ��� ȣ����, �׷��� �Լ��� null�̱��� �ϰ� 0�� ��ȯ
 �׷��� ��ȯ�ϴ°��� +1�� ��� �� -> ���° ������� �˰� ��
 
 */
class LinkedList{
	Node header; //��� ��� ����
	
	static class Node{
		int data;//������
		Node next = null;//�����ּҰ�,�ʱⰪ�� null
	}
	//������
	LinkedList(){
		header = new Node(); //**��� ��带 �����ϵ��� ��
	}
	//���ҵ� �Ҷ� ���� �ϳ� �ް� ���ο� ��� ����
	void append(int d) {
		Node end = new Node(); //�Ǹ������� ���� ��//*���� ������ ������ ����
		end.data=d; //**���⼭ ���� �� ���
		Node n = header; //** ���⿡ ��� �Ҵ�
		//������ ��带ã���� ���� ����
		while(n.next!=null) {
			n=n.next; //n�� ������ ��带 �����ϰ� ������ �� ��//������ ���� n
		}
		n.next = end; //������ ���� ���� ������ ���� �־��ָ� ���ο� �ְ� �� ������ ��尡 ��
	}
	//����
	void delete(int d) {//������ �� ����
		Node n = header; //**��� �Ҵ��ؼ� ���������� ��,�ܹ���� �ٸ��� �ʱⰪ�� header �� ��(�ܹ���ó�� ù��° ���� �������� �ʾƵ� ��, ù��° ���� header�� �����뵵�θ� ����)
		while(n.next!=null) {//������ ���� ����(���������� �Ȱ�)
			if(n.next.data==d) {//���� ��忡�� �̴������� ���� ���� �������� Ȯ��
				n.next=n.next.next;//���� ������ �� ������������ ����ġ��(�ٷ� ���� ���� �����)
			}else {//���� �ٸ����� ��� �̵��ϸ鼭 ������ ���� ã��
				n=n.next;
			}
		}
		//�� ���� ù��° ����� ���� ���ϵ��� �ڵ���(�ٸ� ��尡 ù��° ����� ������ ��찡 ���� �� �־)
	}
	//���� ��ũ�� ����Ʈ�� � ���� �����Ǿ� �ִ��� �����ϴ� �Լ�
	void retrieve() {
		Node n = header.next; //**��� ���� �����ͺ��� �Ҵ��ؼ� �����
		while(n.next!=null) {//������ ��� ���ܰ���� ã�Ƽ� ���
			System.out.print(n.data+"->");
			n=n.next;//���� ���� �̵�
		}
		//������ ��尡 �Ǳ� ���� while���� Ż���ϰ�, �������� n=n.next;�� �Ҵ������Ƿ� ������ �����͸� �������
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
		int k=3;//�ڿ��� ���° ��?
		KthToLast(ll.getFirst(),k);
	}
	//ī��Ʈ ��ȯ
	private static int KthToLast(Node n, int k) {//ù��° ���� ���� k�� ����(k��ŭ �ڿ��� ����)
		if(n==null) {//��尡 0�̸� ������ ��� �������� �� ���̹Ƿ�
			return 0;
		}
		int count = KthToLast(n.next,k)+1;//�׸��� n.next���� k�� ���� ȣ���ϸ鼭, ȣ���� ���� +1�� �ϸ鼭 k�� ������ ��
		if(count==k) {
			System.out.println(k+"th to last node is "+n.data);
		}
		return count;
	}
}