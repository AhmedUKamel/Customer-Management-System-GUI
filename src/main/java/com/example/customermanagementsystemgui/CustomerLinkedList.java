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
    public boolean delete(String customer_name) {
        if(isEmpty()) {
            return false;
        } else if(head.data.getName().equals(customer_name)) {
            head = head.next;
            filled--;
            return true;
        } else {
            Node previous = head, pointer = head.next;

            while(pointer != null) {

                if(pointer.data.getName().equals(customer_name)) {
                    previous.next = pointer.next;
                    filled--;
                }

                pointer = pointer.next;
                previous = previous.next;
                return true;
            }
        } return false;
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
        Node current = head;
        Node sorted_head = null;

        while (current != null) {
            Node next = current.next;

            sorted_insert(current, sorted_head);

            current = next;
        }

        head = sorted_head;
    }
    public void sorted_insert(Node new_node, Node sorted_head) {
        if(sorted_head != null)
            System.out.println("If result: " + new_node.data.compareTo(sorted_head.data));
        else
            System.out.println("If result: sorted head is null");

        if (sorted_head == null || new_node.data.compareTo(sorted_head.data)) {
            new_node.next = sorted_head;
            sorted_head = new_node;
        } else {
            Node current = sorted_head;

            System.out.println("Else result: " + new_node.data.compareTo(current.data));
            while (current.next != null && new_node.data.compareTo(current.data))
                current = current.next;

            new_node.next = current.next;
            current.next = new_node;
        }
    }
    public void merge_sort() {
        System.out.println("Merge sort not implemented in Linked List");
    }
    public void heap_sort() {
        System.out.println("Heap sort not implemented in Linked List");
    }
    public void insertion_sort_for_id() {
    }
    public void merge_sort_for_id() {
    }
    public void heap_sort_for_id() {
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
}
