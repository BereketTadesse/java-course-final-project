public class Run {
    private static User user = new User();

    public static void main(String [] args) {
        if (!FileHandler.directoriesExist() && !FileHandler.userFileExists())
            new WelcomePane();
        else {
            FileHandler.readUserFile(user);
            new MainScreen();
        }
    }
}
