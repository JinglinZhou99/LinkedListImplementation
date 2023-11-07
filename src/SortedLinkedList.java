public class SortedLinkedList<T extends Comparable<T>> {
    private Node head;
    private boolean ascending;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    public SortedLinkedList(boolean ascending) {
        this.head = null;
        this.ascending = ascending;
    }

    public void add(T value) {
        Node newNode = new Node(value);
        if (head == null || (ascending ? value.compareTo(head.value) <= 0 : value.compareTo(head.value) >= 0)) {
            newNode.next = head;
            head = newNode;
        } else {
            Node cur = head;
            while (cur.next != null && (ascending ? value.compareTo(cur.next.value) > 0 : value.compareTo(cur.next.value) < 0)) {
                cur = cur.next;
            }
            newNode.next = cur.next;
            cur.next = newNode;
        }
    }

    public boolean find(T value) {
        Node cur = head;
        while (cur != null) {
            if (cur.value.equals(value)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public void delete(T value) {
        if (head == null) return;
        if (head.value.equals(value)) {
            head = head.next;
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            if (cur.next.value.equals(value)) {
                cur.next = cur.next.next;
                return;
            }
            cur = cur.next;
        }
    }

    public void reverse() {
        ascending = !ascending;
        if (head == null || head.next == null) {
            return;
        }
        Node prev = null;
        Node cur = head;
        Node tempNext;
        while (cur != null) {
            tempNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tempNext;
        }
        head = prev;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = head;
        while (cur != null) {
            sb.append(cur.value).append(" -> ");
            cur = cur.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static void main(String[] args) {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(true);

        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);
        System.out.println("After adding elements: " + list);

        System.out.println("Find 3: " + list.find(3));
        System.out.println("Find 5: " + list.find(5));

        list.delete(3);
        System.out.println("After deleting 3: " + list);

        list.reverse();
        System.out.println("After reversing: " + list);
    }
}
