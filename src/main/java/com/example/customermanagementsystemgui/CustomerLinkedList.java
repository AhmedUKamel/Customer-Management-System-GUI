package com.example.customermanagementsystemgui;
public class CustomerLinkedList {
    public class Node {
        Customer data;
        Node next;
        public Node(Customer data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node head;
    private Node sorted;
    private int filled;
    public boolean insert(Customer customer) {
        if(head == null)
            head = new Node(customer, null);
        else {
            Node pointer = head;

            while(pointer.next != null)
                pointer = pointer.next;

            pointer.next = new Node(customer, null);
        } filled++;
        return true;
    }
    public boolean delete(int customer_contract_id) {
        if(isEmpty()) {
            return false;
        } else if(head.data.getContract_id() == customer_contract_id) {
            head = head.next;
            filled--;
            return true;
        } else {
            Node previous = head, pointer = head.next;

            while(pointer != null) {

                if(pointer.data.getContract_id() == customer_contract_id) {
                    previous.next = pointer.next;
                    filled--;
                    return true;
                }

                pointer = pointer.next;
                previous = previous.next;
            }
        } return false;
    }
    public Customer get(int index) {
        if(index < 0 || index >= filled) return null;

        Node pointer = head;
        while (index-- != 0)
            pointer = pointer.next;

        return pointer.data;
    }
    public Customer linear_search(String customer_name_or_phone) {
        if(isEmpty()) return null;

        Node pointer = head;
        while(pointer != null) {
            if(pointer.data.getName().equals(customer_name_or_phone)
                    || pointer.data.getPhone().equals(customer_name_or_phone))
                return pointer.data;
            pointer = pointer.next;
        }

        return null;
    }
    public Customer linear_search(int customer_contract_id) {
        if(isEmpty()) return null;

        Node pointer = head;
        while(pointer != null) {
            if(pointer.data.getContract_id() == customer_contract_id)
                return pointer.data;
            pointer = pointer.next;
        }

        return null;
    }
    public Customer binary_search(String customer_name) {
        Node start = head;
        Node last = null;

        do {
            Node mid = middle_node(start, last);

            if (mid == null)
                return null;

            if (mid.data.getName().equals(customer_name))
                return mid.data;

            else if (mid.data.getName().compareTo(customer_name) < 0)
                start = mid.next;

            else
                last = mid;

        } while (last == null || last != start);

        return null;
    }
    public Customer binary_search(int customer_contract_id) {
        Node start = head;
        Node last = null;

        do {
            Node mid = middle_node(start, last);

            if (mid == null)
                return null;

            if (mid.data.getContract_id() == customer_contract_id)
                return mid.data;

            else if (mid.data.getContract_id() > customer_contract_id)
                start = mid.next;

            else
                last = mid;

        } while (last == null || last != start);

        return null;
    }
    public Node middle_node(Node start, Node last) {
        if (start == null)
            return null;

        Node slow = start;
        Node fast = start.next;

        while (fast != last) {
            fast = fast.next;

            if (fast != last) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }
    public void bubble_sort() {
        for (int pass = 0; pass < filled; pass++ ) {

            Node pointer = head;
            Node next = head.next;

            for (int step = 0; step < filled - 1; step++) {

                if (pointer.data.compareTo(next.data)) {
                    Customer temp = pointer.data;
                    pointer.data = next.data;
                    next.data = temp;
                }

                pointer = next;
                next = next.next;
            }
        }
    }
    public void bubble_sort_for_id() {
        for (int pass = 0; pass < filled; pass++ ) {

            Node pointer = head;
            Node next = head.next;

            for (int step = 0; step < filled - 1; step++) {

                if (pointer.data.getContract_id() > next.data.getContract_id()) {
                    Customer temp = pointer.data;
                    pointer.data = next.data;
                    next.data = temp;
                }

                pointer = next;
                next = next.next;
            }
        }
    }
    public void selection_sort() {
        Node pointer = head;

        while (pointer != null) {
            Node min = pointer;
            Node next = pointer.next;

            while (next != null) {
                if (min.data.compareTo(next.data))
                    min = next;

                next = next.next;
            }

            Customer temp = pointer.data;
            pointer.data = min.data;
            min.data = temp;
            pointer = pointer.next;
        }
    }
    public void selection_sort_for_id() {
        Node pointer = head;

        while (pointer != null) {
            Node min = pointer;
            Node next = pointer.next;

            while (next != null) {
                if (min.data.getContract_id() > next.data.getContract_id())
                    min = next;

                next = next.next;
            }

            Customer temp = pointer.data;
            pointer.data = min.data;
            min.data = temp;
            pointer = pointer.next;
        }
    }
    public void insertion_sort() {
        sorted = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            sorted_insert(current);
            current = next;
        }
        head = sorted;
    }
    public void sorted_insert(Node new_node) {
        if (sorted == null || sorted.data.compareTo(new_node.data)) {
            new_node.next = sorted;
            sorted = new_node;
        } else {
            Node current = sorted;
            while (current.next != null && new_node.data.compareTo(current.next.data))
                current = current.next;
            new_node.next = current.next;
            current.next = new_node;
        }
    }
    public void insertion_sort_for_id() {
        sorted = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            sorted_insert_for_id(current);
            current = next;
        }
        head = sorted;
    }
    public void sorted_insert_for_id(Node new_node) {
        if (sorted == null || sorted.data.getContract_id() > new_node.data.getContract_id()) {
            new_node.next = sorted;
            sorted = new_node;
        } else {
            Node current = sorted;
            while (current.next != null && new_node.data.getContract_id() > current.next.data.getContract_id())
                current = current.next;
            new_node.next = current.next;
            current.next = new_node;
        }
    }
    public Node merge_sort(Node head) {
        if (head == null || head.next == null)
            return head;
        Node middle = get_middle(head);
        Node nextofmiddle = middle.next;
        middle.next = null;
        Node left = merge_sort(head);
        Node right = merge_sort(nextofmiddle);
        Node sortedlist = sorted_merge(left, right);
        return sortedlist;
    }
    public Node get_middle(Node head) {
        if (head == null)
            return head;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } return slow;
    }
    public Node sorted_merge(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (b.data.compareTo(a.data)) {
            result = a;
            result.next = sorted_merge(a.next, b);
        }
        else {
            result = b;
            result.next = sorted_merge(a, b.next);
        }
        return result;
    }
    public  Node merge_sort_for_id(Node head) {
        if (head == null || head.next == null)
            return head;
        Node middle = get_middle_for_id(head);
        Node nextofmiddle = middle.next;
        middle.next = null;
        Node left = merge_sort_for_id(head);
        Node right = merge_sort_for_id(nextofmiddle);
        Node sortedlist = sorted_merge_for_id(left, right);
        return sortedlist;
    }
    public Node get_middle_for_id(Node head) {
        if (head == null)
            return head;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } return slow;
    }
    public Node sorted_merge_for_id(Node a, Node b) {
        Node result = null;
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.data.getContract_id() < b.data.getContract_id()) {
            result = a;
            result.next = sorted_merge_for_id(a.next, b);
        }
        else {
            result = b;
            result.next = sorted_merge_for_id(a, b.next);
        }
        return result;
    }
    public void heap_sort() {
        Node pointer = head;
        int i = 0;
        Node[] arr = new Node[filled];
        while (pointer != null) {
            arr[i++] = pointer;
            pointer = pointer.next;
        }
        sort_array(arr);
    }
    public void sort_array(Node[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            Customer temp = arr[0].data;
            arr[0].data = arr[i].data;
            arr[i].data = temp;
            heapify(arr, i, 0);
        }
    }
    private void heapify(Node[] arr, int n, int i) {
        int largest = i;
        int right = 2 * i + 2;
        int left = 2 * i + 1;

        if (left < n && arr[left].data.getName().compareTo(arr[largest].data.getName()) > 0)
            largest = left;

        if (right < n && arr[right].data.getName().compareTo(arr[largest].data.getName()) > 0)
            largest = right;

        if (largest != i) {
            Customer swap = arr[i].data;
            arr[i].data = arr[largest].data;
            arr[largest].data = swap;
            heapify(arr, n, largest);
        }
    }
    public void heap_sort_for_id() {
        Node pointer = head;
        int i = 0;
        Node[] arr = new Node[filled];
        while (pointer != null) {
            arr[i++] = pointer;
            pointer = pointer.next;
        }
        sort_array_for_id(arr);
    }
    public  void sort_array_for_id(Node[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify_for_id(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            Customer temp = arr[0].data;
            arr[0].data = arr[i].data;
            arr[i].data = temp;
            heapify_for_id(arr, i, 0);
        }
    }
    public  void heapify_for_id(Node[] arr, int n, int i) {
        int largest = i;
        int right = 2 * i + 2;
        int left = 2 * i + 1;

        if (left < n && arr[left].data.getContract_id() > arr[largest].data.getContract_id())
            largest = left;

        if (right < n && arr[right].data.getContract_id() > arr[largest].data.getContract_id())
            largest = right;

        if (largest != i) {
            Customer swap = arr[i].data;
            arr[i].data = arr[largest].data;
            arr[largest].data = swap;
            heapify(arr, n, largest);
        }
    }
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }
    public int capacity() {
        return filled;
    }
    public boolean isEmpty() {
        return head == null;
    }
    public void clear() {
        head = null;
        filled = 0;
    }
    public Node getHead() {
        return head;
    }
    public void setHead(Node new_head) {
        head = new_head;
    }
}