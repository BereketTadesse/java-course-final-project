import java.io.*;
import java.nio.charset.Charset;

import static java.lang.Integer.valueOf;

public class FileHandler {
    private static String fnameFile = "src/files/main/userdata/fname.dat"; // For some reason, the file stream starts from a
    private static String lnameFile = "src/files/main/userdata/lname.dat"; // different directory to ImageIcon
    private static String contactDir = "src/files/main/contacts";
    private static String maxFile = contactDir + "/max.dat";

    public static boolean userFileExists() {
        try {
            File file = new File(fnameFile);
            return file.exists();
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static boolean readUserFile(User user) {
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

    public static boolean saveContactFile(Contact contact) {
        try (OutputStream contactStream = new FileOutputStream(generateContactFileName(contact))) {
            File contactFile = new File(generateContactFileName(contact));
            ObjectOutputStream objSave = new ObjectOutputStream(contactStream);
            if (contactFile.exists()) {
                objSave.writeObject(contact);
                objSave.flush();
                objSave.close();
                saveMaxFile(Contact.getAvailableContacts());
                return true;
            }
            else
                return false;
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static Contact readContactFile(int id) {
        try (InputStream contactInStream = new FileInputStream(generateContactFileName(id))) {
            ObjectInputStream objStream = new ObjectInputStream(contactInStream);
            // Contact contact;
            return (Contact) objStream.readObject();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            new ContactReadError();
            return null;
        }
    }

    private static void saveMaxFile(int availableContacts) {
        Integer availableContactsWrapper = valueOf(availableContacts);

        try (OutputStream maxFileStream = new FileOutputStream(maxFile)) {
            File maxFileCheck = new File(maxFile);
            ObjectOutputStream maxFileObjStream = new ObjectOutputStream(maxFileStream);
            if (maxFileCheck.exists()) {
                maxFileObjStream.writeObject(availableContactsWrapper);
                maxFileObjStream.flush();
                maxFileObjStream.close();
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static int readMaxFile() {
        int availableContacts = Contact.getAvailableContacts();
        Integer availableContactsWrapper;
        try (InputStream maxFileInStream = new FileInputStream(maxFile)) {
            ObjectInputStream maxFileObjInStream = new ObjectInputStream(maxFileInStream);
            availableContactsWrapper = (Integer) maxFileObjInStream.readObject();
            availableContacts = availableContactsWrapper.intValue();
            // System.out.println(availableContacts);
        }
        catch (IOException exception) {
            exception.printStackTrace();
            new ContactReadError();
        }
        finally {
            return availableContacts;
        }
    }

    public static boolean maxFileExists() {
        try {
            File file = new File(maxFile);
            return file.exists();
        }
        catch (Exception exception) {
            return false;
        }

    }

    public static Contact[] readAllContacts() {
        int numberOfContacts;
        numberOfContacts = FileHandler.readMaxFile();
        // System.out.println(numberOfContacts);
        Contact[] allContacts = new Contact[numberOfContacts];
        for (int i = 0; i < numberOfContacts; i++) {
            allContacts[i] = readContactFile(Contact.getInitialContactID() + i);
        }
        return allContacts;
    }

    public static String generateContactFileName(Contact contact) {
        return generateContactFileName(contact.getId());
        // return contactDir + "/" + Integer.toString(contact.getId()) + ".cfile";
    }

    public static String generateContactFileName(int id) {
        return contactDir + "/" + Integer.toString(id) + ".cfile";
    }
}
