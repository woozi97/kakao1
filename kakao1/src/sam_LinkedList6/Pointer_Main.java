package sam_LinkedList6;

import sam_LinkedList6.LinkedList.Node;

/*
 �����͸� 2�� �����ؼ� �ϳ��� ���� ������(���̴� k�� ����), �ϳ��� ��� ù��° �ڸ��� �ְ�, ���̼� ���ÿ� ������ �ϳ��� ��, ������ �����Ͱ� null�� ������
 �ڿ�������� �������� ���� k��°�� ��
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
public class Pointer_Main {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		int k=1;//�ڿ��� ���° ��?
		Node found = KthToLast(ll.getFirst(),k);
		System.out.println(found.data);
	}

	private static Node KthToLast(Node first, int k) {
		//�����͸� 2�� ����
		Node p1=first;
		Node p2=first;
		//p1�� �̸� k��ŭ �̵���
		for(int i=0;i<k;i++) {
			if(p1==null) return null;//���� ������ �Լ� ����
			p1=p1.next;
		}
		//�ΰ��� �����͸� ���� ������//��ĭ�� ���� ��//p1�� null�� �Ǹ� ��������
		while(p1!=null){//p1�� �������� �ƴ� ����
			p1=p1.next;
			p2=p2.next;
		}
		return p2;
	}
}
