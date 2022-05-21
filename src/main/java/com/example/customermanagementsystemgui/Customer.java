package com.example.customermanagementsystemgui;
public class Customer {
    private String name;
    private int contract_id;
    private String nationality;
    private String phone;
    private double current_bill;
    private double accumulated_bill;
    public Customer(String name, int contract_id, String nationality, String phone, double current_bill, double accumulated_bill) {
        this.name = name;
        this.contract_id = contract_id;
        this.nationality = nationality;
        this.phone = phone;
        this.current_bill = current_bill;
        this.accumulated_bill = accumulated_bill;
    }
    public Customer() {}
    public void setName(String new_name) {
        this.name = new_name;
    }
    public void setContract_id(int new_id) {
        this.contract_id = new_id;
    }
    public void setNationality(String new_nationality) {
        this.nationality = new_nationality;
    }
    public void setPhone(String new_phone) {
        this.phone = new_phone;
    }
    public void setCurrent_bill(double new_bill) {
        this.current_bill = new_bill;
        this.accumulated_bill += this.current_bill;
    }
    public void setAccumulated_bill(double accumulated_bill) {
        this.accumulated_bill = accumulated_bill;
    }
    public String getName() {
        return this.name;
    }
    public int getContract_id() {
        return this.contract_id;
    }
    public String getNationality() {
        return this.nationality;
    }
    public String getPhone() {
        return this.phone;
    }
    public double getCurrent_bill() {
        return this.current_bill;
    }
    public double getAccumulated_bill() {
        return this.accumulated_bill;
    }
    public boolean compareTo(Customer second_customer) {
        return this.name.compareTo(second_customer.name) > 0;
    }
    public String line() {
        return  name + "-" +
                contract_id + "-" +
                nationality + "-" +
                phone + "-" +
                current_bill + "-" +
                accumulated_bill;
    }
    public boolean equals(Object second_customer) {
        Customer pointer = (Customer) second_customer;

        return  this.name.equals(pointer.name) &&
                this.contract_id == pointer.contract_id &&          // Only this enough, Because contract ID is UNIQUE
                this.nationality.equals(pointer.nationality) &&
                this.phone.equals(pointer.phone);
    }
}