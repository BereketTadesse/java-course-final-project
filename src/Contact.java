class Contact {
    private int id;
    private String fname;
    private String lname;
    private String[] phoneNumbers;
    private static int currentMaxId = 10000;

    public static final int PHONE = 0;
    public static final int EMAIL = 1;

    Contact(Contact c) {
        this.id = c.id;
        this.fname = c.fname;
        this.lname = c.lname;
        this.phoneNumbers = c.phoneNumbers;
        incrementMaxId();
    }

    Contact(int id, String fname, String lname, String[] phoneNumbers) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phoneNumbers = phoneNumbers;
        incrementMaxId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void addPhoneNumbers(String newNumber) {
        String[] newList = new String[phoneNumbers.length + 1];

        for (int i = 0; i < phoneNumbers.length; i++)
            newList[i] = phoneNumbers[i];
        newList[newList.length - 1] = newNumber;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void incrementMaxId() {
        currentMaxId++;
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

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
}