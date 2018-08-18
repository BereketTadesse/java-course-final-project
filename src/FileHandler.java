import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    private static FileInputStream userfile;


    FileHandler() {

    }

    public static boolean userFileExists() {
            try {
                userfile = new FileInputStream("main/user.dat");
            }
            catch (IOException E) {
                return false;
            }
            return true;
    }

    public static void createUserFile(User user) {
        try {
            FileOutputStream usr = new FileOutputStream("/main/user.dat");
        }
        catch (FileNotFoundException exception) {

        }

    }
}
