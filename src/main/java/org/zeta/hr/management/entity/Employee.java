package org.zeta.hr.management.entity;

public class Employee {
    private static int counter = 0;
    private int id;
    private String firstName;
    private String lastName;
    private String role;
    private int reportsTo;
    private int leaveBalance = 22;
    private int sickLeaves = 12;
    private int paidLeaves = 10;
    private String contact;
    private String emailId;
    private String phone;
    private String address;
    private String city;
    private String locality;
    private String state;
    private String pinCode;

    public Employee () {}

    public Employee(String firstName, String lastName, String role, int reportsTo,
                    String contact, String emailId, String phone,
                    String address, String city, String locality, String state, String pinCode) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.reportsTo = reportsTo;
        this.contact = contact;
        this.emailId = emailId;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.locality = locality;
        this.state = state;
        this.pinCode = pinCode;
    }

    public static int getCounter () {
        return counter;
    }

    public static void setCounter (int counter) {
        Employee.counter = counter;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getRole () {
        return role;
    }

    public void setRole (String role) {
        this.role = role;
    }

    public int getReportsTo () {
        return reportsTo;
    }

    public void setReportsTo (int reportsTo) {
        this.reportsTo = reportsTo;
    }

    public int getLeaveBalance () {
        return leaveBalance;
    }

    public void setLeaveBalance (int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public int getSickLeaves () {
        return sickLeaves;
    }

    public void setSickLeaves (int sickLeaves) {
        this.sickLeaves = sickLeaves;
    }

    public int getPaidLeaves () {
        return paidLeaves;
    }

    public void setPaidLeaves (int paidLeaves) {
        this.paidLeaves = paidLeaves;
    }

    public String getContact () {
        return contact;
    }

    public void setContact (String contact) {
        this.contact = contact;
    }

    public String getEmailId () {
        return emailId;
    }

    public void setEmailId (String emailId) {
        this.emailId = emailId;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public String getLocality () {
        return locality;
    }

    public void setLocality (String locality) {
        this.locality = locality;
    }

    public String getState () {
        return state;
    }

    public void setState (String state) {
        this.state = state;
    }

    public String getPinCode () {
        return pinCode;
    }

    public void setPinCode (String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString () {
        return id + " | " + firstName + " " + lastName + " | " + role + " | " + reportsTo +
               " | " + contact + " | " + emailId + " | " + phone +
               " | " + address + ", " + locality + ", " + city +
               ", " + state + ", " + pinCode;
    }
}
