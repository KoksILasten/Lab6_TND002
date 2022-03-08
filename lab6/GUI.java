package lab6;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {

    private JButton load, save, search, next, add, delete;
    private JPanel panelButton, panelText;
    private JTextField seachField, nameField, numberField;

    private int person = 0;
    private ArrayList<Person> target;

    private PhoneBook phoneBook = new PhoneBook();

    public GUI() {

        Font theFont = new Font("Serif", Font.PLAIN, 20);

        load = new JButton("Load Phonebook");
        load.setEnabled(true);
        load.setFont(theFont);
        load.addActionListener(this);

        save = new JButton("Save");
        save.setFont(theFont);
        save.setEnabled(false);
        save.addActionListener(this);

        search = new JButton("Search");
        search.setFont(theFont);
        search.setEnabled(false);
        search.addActionListener(this);

        next = new JButton("Next");
        next.setFont(theFont);
        next.setEnabled(false);
        next.addActionListener(this);

        add = new JButton("Add");
        add.setFont(theFont);
        add.setEnabled(false);
        add.addActionListener(this);

        delete = new JButton("Delete");
        delete.setFont(theFont);
        delete.setEnabled(false);
        delete.addActionListener(this);

        seachField = new JTextField();
        seachField.setFont(theFont);
        seachField.addActionListener(this);

        nameField = new JTextField();
        nameField.setFont(theFont);
        nameField.setEditable(false);

        numberField = new JTextField();
        numberField.setFont(theFont);
        numberField.setEditable(false);

        Container cumcontainer = getContentPane();
        panelButton = new JPanel(new GridLayout(3, 2));
        panelText = new JPanel(new GridLayout(3, 1));

        panelButton.add(load);
        panelButton.add(save);
        panelButton.add(search);
        panelButton.add(next);
        panelButton.add(add);
        panelButton.add(delete);

        panelText.add(seachField);
        panelText.add(nameField);
        panelText.add(numberField);

        cumcontainer.setLayout(new GridLayout(1, 2));
        cumcontainer.add(panelButton);
        cumcontainer.add(panelText);

        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == load || ae.getSource() == seachField) {

            String file = phoneBook.load(seachField.getText());
            seachField.setText("");
            nameField.setText(file);

            save.setEnabled(true);
            search.setEnabled(true);
            add.setEnabled(true);
            delete.setEnabled(true);

        }
        if (ae.getSource() == save) {

            if (seachField.getText() == null) {
                nameField.setText("Provide a filename, dummy");
            } else {
                String saved = phoneBook.Save(seachField.getText());
                seachField.setText("");
                nameField.setText(saved);
            }
        }
        if (ae.getSource() == search) {

            String searched = seachField.getText();

            target = phoneBook.search(searched);

            if (target.size() == 0) {
                seachField.setText("");
                nameField.setText("Target not found");

            } else if (target.size() == 1) {
                seachField.setText("");
                nameField.setText(target.get(0).getFullName());
                numberField.setText(String.valueOf(target.get(0).getPhoneNumber()));

            } else if (target.size() > 1) {
                seachField.setText("");
                next.setEnabled(true);
                person = 0;
                nameField.setText(target.get(0).getFullName());
                numberField.setText(String.valueOf(target.get(0).getPhoneNumber()));
                if (person == target.size()) {
                    next.setEnabled(false);
                    person = 0;
                }
            }
            person = 0;
        }
        if (ae.getSource() == next) {
            person++;
            nameField.setText(target.get(person).getFullName());
            numberField.setText(String.valueOf(target.get(person).getPhoneNumber()));
        }
        if (ae.getSource() == delete) {
            String deleted = phoneBook.deletePerson(nameField.getText(), Integer.valueOf(numberField.getText()));
            seachField.setText(deleted);
        }
        if (ae.getSource() == add) {
            if (person == 0) {
                seachField.setText("Type in name and phone number");
                nameField.setText("");
                numberField.setText("");
                nameField.setEditable(true);
                numberField.setEditable(true);
                person++;
            } else if (person == 1) {
                Boolean added = phoneBook.addPerson(nameField.getText(), Integer.valueOf(numberField.getText()));
                person = 0;
                if (added == true) {
                    seachField.setText("Person Added!");
                }

                nameField.setEditable(false);
                numberField.setEditable(false);
                numberField.setText("");
            }

        }
    }

    public static void main(String[] args) {

        GUI myGui = new GUI();
    }
}
