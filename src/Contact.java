import java.io.Serializable;

class Contact implements Serializable {
    private int id;
    private String fname;
    private String lname;
    private String[] phoneNumbers;
    private static int initialContactID = 10000;
    static int lifeTimeContacts = 0;
    static int availableContacts = 0;

    static final int MAX_PHONES = 5;

    Contact(String fname, String lname, String[] phoneNumbers) {
        this.id = initialContactID + lifeTimeContacts;
        this.fname = fname;
        this.lname = lname;
        this.phoneNumbers = phoneNumbers;
        availableContacts++;
        lifeTimeContacts++;
    }

    static int getAvailableContacts() {
        return availableContacts;
    }

    static int getLifeTimeContacts() {
        return lifeTimeContacts;
    }

    int getId() {
        return id;
    }

    String getFname() {
        return fname;
    }

    String getLname() {
        return lname;
    }

    String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    static int getInitialContactID() {
        return initialContactID;
    }

}


class User {
    private String fname;
    private String lname;

    User() {
    }

    User(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    void setFname(String fname) {
        this.fname = fname;
    }

    void setLname(String lname) {
        this.lname = lname;
    }

    String getFname() {
        return fname;
    }

    String getLname() {
        return lname;
    }
}