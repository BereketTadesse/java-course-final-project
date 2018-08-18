import java.io.*;
import java.nio.charset.Charset;

public class FileHandler {
    private static String fnameFile = "src/files/main/userdata/fname.dat"; // For some reason, the file stream starts from a
    private static String lnameFile = "src/files/main/userdata/lname.dat"; // different directory to ImageIcon

    public static boolean userFileExists() {
        try {
            File file = new File(fnameFile);
            return file.exists();
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static boolean userFileRead(User user) {
            try (InputStream fnameInputStream = new FileInputStream(fnameFile);
                 InputStream lnameInputStream = new FileInputStream(lnameFile)
            ) {
                byte[] bytes;
                bytes = fnameInputStream.readAllBytes();
                user.setFname(new String(bytes, Charset.defaultCharset()));
                bytes = lnameInputStream.readAllBytes();
                user.setLname(new String(bytes, Charset.defaultCharset()));
            }
            catch (IOException E) {
                return false;
            }
            return true;
    }


    public static boolean createUserFile(User user) {

        try (OutputStream fnameStream = new FileOutputStream(fnameFile);
             OutputStream lnameStream = new FileOutputStream(lnameFile)
        ) {
            byte[] bytes;
            bytes = user.getFname().getBytes();
            fnameStream.write(bytes);
            if (! user.getLname().isEmpty()) {
                bytes = user.getLname().getBytes();
                lnameStream.write(bytes);
            }
            return true;
        }
        catch (IOException exception) {
            // exception.printStackTrace();
            new FileCreationError();
            return false;
        }
    }
}
