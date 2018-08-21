import javax.swing.*;

public class ListModel extends AbstractListModel {
    String[] names;

    ListModel() {

    }

    ListModel(String[] nameList) {
        names = nameList;
        sort();
    }

    public int getSize() {
        return names.length;
    }

    public Object getElementAt(int index) {
        return (String)names[index];
    }

    public void addElement(String element) {
        String[] newList = new String[names.length + 1];
        for (int i = 0; i < names.length; i++)
            newList[i] = names[i];
        newList[names.length] = element;
        sort();
    }

    public void sort() {
        String temp;
        for (int i = 0; i < names.length; i++) {
            for (int j = i + 1; j < names.length; j++) {
                if (names[i].compareTo(names[j]) > 0) {
                    temp = names[i];
                    names[i] = names[j];
                    names[j] = temp;
                }
            }
        }
        fireContentsChanged(this, 0, names.length);
    }
}
