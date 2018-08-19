public class Tester {
    private static User user = new User();

    public static void main(String [] args) {
        // new ResolutionPane();
        if (!FileHandler.userFileExists())
            new WelcomePane();
        else {
            FileHandler.readUserFile(user);
            new MainScreen();
        }
        // view();
    }

    public static void setUserFname(String fname) {
        user.setFname(fname);
    }

    public static void setUserLname(String lname) {
        user.setLname(lname);
    }

    public static User getUser() {
        return user;
    }

    static void view() {
        System.out.println(user.getFname() + " " + user.getLname());
    }
}
