package sam_LinkedList5;

import sam_LinkedList5.LinkedList.Node;
//����O(N), �ð��� O(2N)

//�ܹ��� ��ũ�帮��Ʈ�� ������ k��° ��带 ã�� �˰����� �����Ͻÿ� 
//-> �ڹٿ����� �Լ� �����͸� ��ȯ�Ҷ� �Ѱ��� �����͸� �� �� �־, �������� ī��Ʈ�� ����ؾ��ϴµ� ��� ��带 ���? 
/*
 �Լ��� �����Ҷ� ���ڰ� �ƴ϶� �ּҸ� ������ �� ������ ��*/
/*
 2->3->1->4
 �ܹ��� ��ũ�帮��Ʈ�� �׻� �Ǿպ��� ��
 
 ���1. ��ũ�帮��Ʈ�� ù������ �ڷ� �̵��ϸ鼭 ��ü ��尹���� ����� ��
 k=1, �Ѱ��� 4���� ����, k=1 �̸� ������ ��带 �������ڰ� �Ͽ����Ƿ� +1�ؾ� ����
 �ڿ��� ù��°(k-1)+1 ��° �ؾ� ���� 4����
 k-1=3 +1 = 4 �տ��� 4��° �����Ͱ� �ڿ��� ù��° �������̴�.
 */
//��ü�� �迭�� ���ÿ� �����͸� ����
//ī���͸� ��ü�� �־ ��ü�� �ּҸ� ����
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
class Reference{
	public int count=0; //ī��Ʈ�� ����
}
public class Oneway_Recursive_fromlast_FindNode {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.retrieve();
		int k=1;//�ڿ��� ���° ��?
		Reference r = new Reference();//ȣ���Ҷ� ���۷��� ����
		Node found = KthToLast(ll.getFirst(),k,r);
		System.out.println(found.data);
	}

	//ī��Ʈ�� ���۷����� �����ϰ� ���� ��ȯ��
	private static Node KthToLast(Node n, int k, Reference r) {//ù��° ���� ���� k�� ����(k��ŭ �ڿ��� ����)
		if(n==null) {//��尡 0�̸� ������ ��� �������� �� ���̹Ƿ�
			return null;//��ȯ�� ����̹Ƿ� ������ null
		}
		//ã�� ��带 ����
		Node found = KthToLast(n.next,k,r);//�׸��� n.next���� k�� ���� ȣ���ϸ鼭, ȣ���� ���� +1�� �ϸ鼭 k�� ������ ��
		//���۷����� ī��Ʈ�� �÷���
		r.count++;
		if(r.count==k) {
			return n;//������ �ش� ��带 ��ȭ
		}
		return found;//���������� �̹� ã�Ƽ� ��ȯ���� ��带 ��ȯ
	}
}
