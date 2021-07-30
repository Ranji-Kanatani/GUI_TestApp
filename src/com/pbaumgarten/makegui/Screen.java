package com.pbaumgarten.makegui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Screen extends JFrame {
	private JPanel panelTop;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JList listPeople;
	private JButton buttonNew;
	private JButton buttonSave;
	private JTextField textName;
	private JTextField textAddress;
	private JLabel labelAge;
	private JTextField textDateOfBirth;
	private JPanel panelMain;
	private JTextField textEmail;
	private JTextField textPhoneNumber;
	private ArrayList<Person> people;
	private DefaultListModel listPeopleModel;

	Screen() {
		super("My fancy contacts project");
		this.setContentPane(this.panelMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		people = new ArrayList<Person>();
		listPeopleModel = new DefaultListModel();
		listPeople.setModel(listPeopleModel);
		buttonSave.setEnabled(false);

		buttonNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonNewClick(e);
			}
		});
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonSavedClick(e);
			}
		});
		listPeople.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listPeopleSelection(e);
			}
		});
	}

	public void buttonNewClick(ActionEvent e) {
		Person p = new Person(
				textName.getText(),
				textEmail.getText(),
				textPhoneNumber.getText(),
				textDateOfBirth.getText());
		people.add(p);
		refreshPeopleList();
	}

	public void buttonSavedClick(ActionEvent e) {
		int personNumber = listPeople.getSelectedIndex();
		if (personNumber >= 0) {
			Person p = people.get(personNumber);
			p.setName(textName.getText());
			p.setEmail(textEmail.getText());
			p.setPhoneNumber(textPhoneNumber.getText());
			p.setDateOfBirth(textDateOfBirth.getText());
			refreshPeopleList();
		}
	}

	public void listPeopleSelection(ListSelectionEvent e) {
		int personNumber = listPeople.getSelectedIndex();
		if (personNumber >= 0) {
			Person p = people.get(personNumber);
			textName.setText(p.getName());
			textEmail.setText(p.getEmail());
			textPhoneNumber.setText(p.getPhoneNumber());
			textDateOfBirth.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
			labelAge.setText(Integer.toString(p.getAge()) + " years");
			buttonSave.setEnabled(true);
		} else {
			buttonSave.setEnabled(false);
		}
	}

	public void refreshPeopleList() {
		listPeopleModel.removeAllElements();
		System.out.println("Removing all people from list");
		for (Person p : people) {
			System.out.println("Adding person to list: " + p.getName());
			listPeopleModel.addElement(p.getName());
		}
	}

	public void addPeople(Person p) {
		people.add(p);
		refreshPeopleList();
	}

	public static void main(String[] args) {
		Screen screen = new Screen();
		screen.setVisible(true);
		Person a = new Person("Kenji Koiso", "k_koiso@gmail.com", "090-6984-7888", "29/07/2003");
		Person b = new Person("Natsuki Shinohara", "n_shin@gmail.com", "080-8954-1054", "19/07/2002");
		Person c = new Person("King Kazuma", "king_kazuma@gmail.com", "070-9843-6416", "29/05/2008");
		Person d = new Person("Sakae JiNnouchi", "sakae_ba@gmail.com", "090-9864-0648", "01/08/1932");
		Person e = new Person("Wabisuke JiNnouchi", "wabiwabi@gmail.com", "080-6548-0814", "29/03/1980");
		screen.addPeople(a);
		screen.addPeople(b);
		screen.addPeople(c);
		screen.addPeople(d);
		screen.addPeople(e);
	}
}
