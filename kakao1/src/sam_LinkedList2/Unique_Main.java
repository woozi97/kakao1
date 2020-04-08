package sam_LinkedList2;

/*
 ���ĵǾ����� ���� ��ũ�帮��Ʈ�� �ߺ����� �����Ͻÿ�
 ��, ������ ���۸� ������� �� ��
 
 3->2->1->2->4
 
 ���� ������ �� �ִ� ���۸� ����ؼ� ���� �ϴ� �غ�
 HashSet(Ű���� ���� ã�Ƶ��� �ð��� 1�ۿ� �Ȱɸ�)
 �ؽ��� �̹� �����ߴٰ� �̹� ���� �ִ� �ϸ� �� ���� ����
N�̶�� �����͸� �̵��ذ��� üũ

���۴�� �����͸� ����ؼ� ����
������ n�� ���� r�� ����, n�� �ϳ��� r�� �Դٰ��� �ٳ��, ���ʾ� n�� 3�� ���� ������ �����ϰ� �Ͷ�
����鼭 ���� ��ũ �ּҵ� �ٲ����
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
	void removeDups() {
		Node n = header; //���� ���� ������ ������ n����
		while(n!=null&&n.next!=null) {//n�� ������ ������ ��//�������� �ߺ��Ǵ°�� n�� next�� �̵��Ǵ� ���� �����ϱ� ���� n!=null���� �߰�
			Node r = n;//���ʴ� n�� ��ġ���� ����
			while(r.next!=null) {
				if(n.data==r.next.data) {//�� next.data�� ���ϳĸ� n�� ���� ��ġ���� ���� ����� ���� ���� �����ϰ� �̵��ؾ� �ϹǷ�, �̰� �� ȿ����(�����ؾ��� ��� �ȿ� �ִ°� ���� ���� �ִ°� �� ����)
					//n�� �����Ϳ� r�� ���鼭 ���� �����Ϳ� ������
					r.next=r.next.next;//������ r�� �ּҸ� �ٴ��� �ּҷ� ����ġ���.
				}else {
					//�ߺ��� �ȵǸ� �׳� �̵�;
					r=r.next;
				}
			}
			//���ʰ� �� �������� n�� �� ���� ������ �̵�
			n=n.next;//�� �ڵ�� ������ �ڵ尡 �ߺ��Ǹ� ������, r�� ������ �ߺ��ؼ� �����ع��ȴµ� n�� ���������� �̵��� �����Ƿ� ���� �ϳ� �� �ʿ�
		}
	}
}

/*
 �ð�O(N^2)
 ����O(1)
 �ð��� ���� ������ ���� ȿ��������*/
public class Unique_Main {
	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		/*
		ll.append(2);
		ll.append(1);
		ll.append(2);
		ll.append(3);
		ll.append(4);
		ll.append(3);
		*/
		ll.append(2);
		ll.append(2);
		ll.retrieve();
		ll.removeDups();
		ll.retrieve();
	}
}