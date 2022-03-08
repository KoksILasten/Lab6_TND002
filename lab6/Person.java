package lab6;

public class Person {
    private String givenName;
    private String surName;
    private int phoneNumber;

    public Person(String thegivenName, String surname, int thephoneNumber) {
        givenName = thegivenName;
        surName = surname;
        phoneNumber = thephoneNumber;
    }

    public String getSirName() {
        return surName;
    }

    public String getFullName() {
        return givenName + " " + getSirName();
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

}
