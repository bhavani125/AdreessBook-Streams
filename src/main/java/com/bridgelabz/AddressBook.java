package com.bridgelabz;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    public ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public boolean addContact(Contacts c) {
        List<Contacts> checkByName = searchByName(c.getFirstName());
        for (Contacts equalName : checkByName)
            if (equalName.equals(c))
                return false;
        contactsArrayList.add(c);
        return true;
    }

    // Creating searchName method to search contact by name
    public List<Contacts> searchByName(String name) {
        // stream and lambda for find filter given name from arraylist
        return contactsArrayList.stream().filter(person -> person.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    // Creating searchByCity method to search contact by city
    public List<Contacts> searchByCity(String city) {
        return contactsArrayList.stream().filter(person -> person.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
    // Creating searchByState method to search contact by state
    public List<Contacts> searchByState(String state) {

        return contactsArrayList.stream().filter(person -> person.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }

    //Creating editContact method
    public boolean editContact(Contacts current, Contacts edit) {
        System.out.println("Enter firstname of the user you want to the edit:");
        String firstName = sc.next();
        if (!contactsArrayList.contains(current))
            return false;
        contactsArrayList.remove(current);
        contactsArrayList.add(edit);
        return true;
    }

    //Creating deleteContact method
    public boolean deleteContact(Contacts contacts) {
        contactsArrayList.remove(contacts);
        return true;
    }

    // for showing output details
    @Override
    public String toString() {
        if (contactsArrayList.isEmpty())
            return "No contacts found!";
        String result = new String();
        for (int i = 0; i < contactsArrayList.size(); i++) {
            result += " " + contactsArrayList.get(i);
        }
        return result;
    }

    //Creating readContact method for adding details
    public static Contacts readContact() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter State: ");
        String state = sc.nextLine();
        System.out.print("Enter Email ID: ");
        String email = sc.nextLine();
        System.out.print("Enter Zip Code: ");
        Long zip = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter Phone Number: ");
        Long phoneNum = sc.nextLong();
        sc.nextLine();
        return new Contacts(firstName, lastName, address, city, state,email, zip, phoneNum);
    }

    //Creating addressBookOptions method for showing option for contacts
    public static void addressBookOptions(AddressBook addressBook) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1)To Add contact details\n2)To Edit contact details\n3)To Delete contact details\n4)Show contacts details\n5)Back to main menu");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    // calling addContact with passing method readContact
                    if (addressBook.addContact(readContact()))
                        System.out.println("Contact is Added Successfully");
                    else
                        System.out.println("Contact Already Exist");
                    break;
                case 2:
                    System.out.println("Enter First name to edit contacts: ");
                    String name = sc.nextLine();
                    // list of equal first name
                    List<Contacts> equalName = addressBook.searchByName(name);
                    if (equalName.isEmpty())// (if not match found)
                        System.out.println("Data Not Found....!");

                        // if only one equal match found
                    else if (equalName.size() == 1) {
                        //calling edit method with name and method
                        addressBook.editContact(equalName.get(0), readContact());
                        System.out.println("Contacts of data modified....!");
                    } else {
                        // if more than one firstname match found in equal name list
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter index to edit : ");
                        int i = sc.nextInt();
                        sc.nextLine();
                        addressBook.editContact(equalName.get(i), readContact());
                        System.out.println("Contacts is Modified....!");
                    }
                    break;
                case 3:
                    System.out.println("Enter First name to delete contact: ");
                    name = sc.nextLine();
                    equalName = addressBook.searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found.....!");
                    else if (equalName.size() == 1) {
                        addressBook.deleteContact(equalName.get(0));
                        System.out.println("Contact data deleted....!");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.println("Enter an index to delete");
                        int index = sc.nextInt();
                        sc.nextLine();
                        addressBook.deleteContact(equalName.get(index));
                        System.out.println("Cotact data deleted....!");
                    }
                    break;
                case 4:
                    System.out.println(addressBook.toString()); // call tostring method for showing details
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        }
    }

    public void searchByOptions() {
        AddressBookMain addressBook = new AddressBookMain();
        Scanner sc = new Scanner(System.in);
        System.out.println("1)By name\n2)By city\n3)By state\n4)Back");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter name: ");
                String name = sc.nextLine();
                contactsArrayList.forEach(book -> searchByName(name).forEach(System.out::println));
                break;
            case 2:
                System.out.println("Enter city: ");
                String city = sc.nextLine();
                contactsArrayList.forEach(book -> searchByCity(city).forEach(System.out::println));
                break;
            case 3:
                System.out.println("Enter state: ");
                String state = sc.nextLine();
                contactsArrayList.forEach(book -> searchByState(state).forEach(System.out::println));
                break;
            case 4:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }

    }
}

