package com.example.customermanagementsystemgui;
public class ArrayCustomerList {
    private static final int MAX_SIZE = 1000;
    private Customer[] customers_array = new Customer[MAX_SIZE];
    private int top = -1;
    public boolean insert(Customer customer) {
        if(isFull()) return false;

        customers_array[++top] = customer;
        return true;
    }
    public boolean delete(int customer_contract_id) {
        if(isEmpty()) return false;

        for(int i = 0; i <= top; i++)
            if(customers_array[i].getContract_id() == customer_contract_id) {
                for(int j = i; j < top; j++)
                    customers_array[j] = customers_array[j+1];
                top--;
                return true;
            }

        return false;
    }
    public Customer get(int index) {
        if(index < 0 || index > top) return null;

        return customers_array[index];
    }
    public Customer linear_search(String customer_name_or_phone) {
        if(isEmpty()) return null;

        for(int i = 0; i <= top; i++)
            if(customers_array[i].getName().equals(customer_name_or_phone) ||
                    customers_array[i].getPhone().equals(customer_name_or_phone))
                return customers_array[i];

        return null;
    }
    public Customer linear_search(int customer_contract_id) {
        if(isEmpty()) return null;

        for(int i = 0; i <= top; i++)
            if(customers_array[i].getContract_id() == customer_contract_id)
                return customers_array[i];

        return null;
    }
    public Customer binary_search(String customer_name) {
        if(isEmpty()) return null;

        int left = 0, right = top, middle;
        while(left <= right) {
            middle = (left + right) / 2;

            if(customers_array[middle].getName().equals(customer_name))
                return customers_array[middle];
            else if(customers_array[middle].getName().compareTo(customer_name) < 0)
                left = middle + 1;
            else
                right = middle - 1;
        }

        return null;
    }
    public Customer binary_search(int customer_contract_id) {
        if(isEmpty()) return null;

        int left = 0, right = top, middle;
        while(left <= right) {
            middle = (left + right) / 2;

            if(customers_array[middle].getContract_id() == customer_contract_id)
                return customers_array[middle];
            else if(customers_array[middle].getContract_id() < customer_contract_id)
                left = middle + 1;
            else
                right = middle - 1;
        }

        return null;
    }
    public void bubble_sort() {
        for(int pass = 0; pass <= top; pass++)
            for(int step = 0; step < pass; step++)
                if(customers_array[step].compareTo(customers_array[step+1])) {
                    Customer temp = customers_array[step];
                    customers_array[step] = customers_array[step+1];
                    customers_array[step+1] = temp;
                }
    }
    public void bubble_sort_for_id() {
        for (int pass = 0; pass <= top; pass++)
            for (int step = 0; step < top; step++)
                if (customers_array[step].getContract_id() > customers_array[step + 1].getContract_id()) {
                    Customer temp = customers_array[step];
                    customers_array[step] = customers_array[step + 1];
                    customers_array[step + 1] = temp;
                }
    }
    public void insertion_sort() {
        for(int pass = 1; pass <= top; pass++) {
            int index = pass - 1;
            Customer key = customers_array[pass];

            while(index >= 0
                    && customers_array[index].compareTo(customers_array[pass])) {
                customers_array[index+1] = customers_array[index];
                index--;
            }

            customers_array[index+1] = key;
        }
    }
    public void insertion_sort_for_id() {
        for(int pass = 1; pass <= top; pass++) {
            int index = pass - 1;
            Customer key = customers_array[pass];

            while(index >= 0
                    && customers_array[index].getContract_id() > customers_array[pass].getContract_id()) {
                customers_array[index+1] = customers_array[index];
                index--;
            }

            customers_array[index+1] = key;
        }
    }
    public void selection_sort() {
        for(int pass = 0; pass <= top; pass++) {
            int smallest_index = pass;

            for(int index = pass; index <= top; index++) {
                if(customers_array[smallest_index].compareTo(customers_array[index]))
                    smallest_index = index;
            }

            if(smallest_index != pass) {
                Customer temp = customers_array[smallest_index];
                customers_array[smallest_index] = customers_array[pass];
                customers_array[pass] = temp;
            }
        }
    }
    public void selection_sort_for_id() {
        for(int pass = 0; pass <= top; pass++) {
            int smallest_index = pass;
            for(int index = pass; index <= top; index++) {
                if(customers_array[smallest_index].getContract_id() > customers_array[index].getContract_id())
                    smallest_index = index;
            }
            if(smallest_index != pass) {
                Customer temp = customers_array[smallest_index];
                customers_array[smallest_index] = customers_array[pass];
                customers_array[pass] = temp;
            }
        }
    }
    public void merge_sort(int left, int right) {
        if (left < right) {
            int middle = (right + left) / 2;

            merge_sort(left, middle);
            merge_sort(middle + 1, right);

            merge(left, middle, right);
        }
    }
    public void merge(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Customer L[] = new Customer[n1];
        Customer R[] = new Customer[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = customers_array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = customers_array[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (R[j].compareTo(L[i]))
                customers_array[k] = L[i++];
            else
                customers_array[k] = R[j++];
            k++;
        }

        while (i < n1)
            customers_array[k++] = L[i++];

        while (j < n2)
            customers_array[k++] = R[j++];
    }
    public void merge_sort_for_id(int left, int right) {
        if (left < right) {
            int middle = (right + left) / 2;

            merge_sort_for_id(left, middle);
            merge_sort_for_id(middle + 1, right);

            merge_for_id(left, middle, right);
        }
    }
    public void merge_for_id(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Customer L[] = new Customer[n1];
        Customer R[] = new Customer[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = customers_array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = customers_array[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (R[j].getContract_id() > L[i].getContract_id())
                customers_array[k] = L[i++];
            else
                customers_array[k] = R[j++];
            k++;
        }

        while (i < n1)
            customers_array[k++] = L[i++];

        while (j < n2)
            customers_array[k++] = R[j++];
    }
    public void heap_sort() {
        int n = top + 1;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(n, i);

        for (int i = n - 1; i > 0; i--) {
            Customer temp = customers_array[0];
            customers_array[0] = customers_array[i];
            customers_array[i] = temp;

            heapify(i, 0);
        }
    }
    public void heapify(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && customers_array[l].compareTo(customers_array[largest]))
            largest = l;

        if (r < n && customers_array[r].compareTo(customers_array[largest]))
            largest = r;

        if (largest != i) {
            Customer swap = customers_array[i];
            customers_array[i] = customers_array[largest];
            customers_array[largest] = swap;

            heapify(n, largest);
        }
    }
    public void heap_sort_for_id() {
        int n = top + 1;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify_for_id(n, i);

        for (int i = n - 1; i > 0; i--) {
            Customer temp = customers_array[0];
            customers_array[0] = customers_array[i];
            customers_array[i] = temp;

            heapify_for_id(i, 0);
        }
    }
    public void heapify_for_id(int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && customers_array[l].getContract_id() > customers_array[largest].getContract_id())
            largest = l;

        if (r < n && customers_array[r].getContract_id() > customers_array[largest].getContract_id())
            largest = r;

        if (largest != i) {
            Customer swap = customers_array[i];
            customers_array[i] = customers_array[largest];
            customers_array[largest] = swap;

            heapify_for_id(n, largest);
        }
    }
    public void reverse() {
        Customer[] reversed = new Customer[MAX_SIZE];
        for(int i = top, j = 0; i >= 0; i--, j++)
            reversed[j] = customers_array[i];
        customers_array = reversed;
    }
    public int capacity() {
        return top + 1;
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public boolean isFull() {
        return top == MAX_SIZE - 1;
    }
    public void clear() {
        top = -1;
    }
}