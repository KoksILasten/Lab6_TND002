package lab6;

import java.io.*;
import java.util.ArrayList;

public class PhoneBook {

    private ArrayList<Person> listOfNumbers;
    private static BufferedReader freader;
    private static BufferedWriter listExtractor;

    public PhoneBook() {
        listOfNumbers = new ArrayList<Person>();
    }

    public String load(String fileName) {

        try {
            String filePath = System.getProperty("user.dir");

            fileName = filePath + "\\lab6\\" + fileName;

            freader = new BufferedReader(new FileReader(fileName));

            String Line;
            while ((Line = freader.readLine()) != null) {
                String[] pContent = Line.split(" +");
                listOfNumbers.add(new Person(pContent[0], pContent[1], Integer.valueOf(pContent[2])));
            }

            return "Phone book loaded";
        } catch (FileNotFoundException e) {
            return "Try again!";
        } catch (IOException e) {
            return "OWNO EWOWWOW";
        }
    }

    public ArrayList<Person> search(String searchedPerson) {

        ArrayList<Person> personSearch = new ArrayList<Person>();

        boolean isNumber;

        try {
            Double.parseDouble(searchedPerson);
            isNumber = true;
        } catch (NumberFormatException e) {
            isNumber = false;
        }

        if (isNumber) {
            for (Person p : listOfNumbers) {
                if (Integer.parseInt(searchedPerson) == p.getPhoneNumber()) {
                    personSearch.add(p);
                }
            }

        } else {
            for (Person p : listOfNumbers) {
                if (p.getSirName().equals(searchedPerson)) {
                    personSearch.add(p);
                }
            }
        }

        return personSearch;
    }

    public String deletePerson(String fullName, int phoneNumber) {

        // for (Person p : search(fullName)) {
        for (Person p : listOfNumbers) {

            if (p.getFullName().equals(fullName) && p.getPhoneNumber() == phoneNumber) {

                listOfNumbers.remove(p);

                return "Snaps* Person reduced to atoms";
            }
        }

        return "Mission failed, we'll get them next time";

    }

    public boolean addPerson(String fullname, int phoneNumber) {

        for (Person p : listOfNumbers) {

            if (!p.getFullName().equals(fullname) && p.getPhoneNumber() != phoneNumber) {

                String[] mynamajeff = fullname.split(" +");
                if (mynamajeff.length == 2) {
                    Person added = new Person(mynamajeff[0], mynamajeff[1], phoneNumber);
                    listOfNumbers.add(added);
                    return true;
                }
            }
        }

        return false;
    }

    public String Save(String name) {
        try {
            listExtractor = new BufferedWriter(new FileWriter(name));

            for (Person e : listOfNumbers) {
                listExtractor.write(String.format("%20s %5d", e.getFullName(), e.getPhoneNumber()) + "\n");
            }
            listExtractor.close();
            return "Saved " + listOfNumbers.size() + " People to the file";

        } catch (IOException e) {
            return "RUROO RAGGY, RETAR";
        }

    }
}
