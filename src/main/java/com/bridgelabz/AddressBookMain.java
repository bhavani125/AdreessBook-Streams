package com.bridgelabz;

import java.util.*;

public class AddressBookMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Creating object for AddressBook class
        AddressBook addressBook = new AddressBook();
        Map<String, AddressBook> addressBookMap = new HashMap<String, AddressBook>();

        while (true) {
            System.out.println("\nWelcome to the Address Book System");
            System.out.println("1)To New Address Book \n2)To Select Address Book \n3)To Delete Address Book \n4)To Search Contact Data \n5) Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the new addressBook Name: ");
                    String bookName = sc.next();
                    sc.nextLine();
                    // adding bookName as a key and value is allocating
                    addressBookMap.put(bookName, new AddressBook());
                    // calling  addressBookOptions method
                    AddressBook.addressBookOptions(addressBookMap.get(bookName));
                    break;
                case 2:
                    System.out.println("Lit of the available Address Books : ");
                    // retrieving keys from hashmap to show addressBookList
                    Set<String> keys = addressBookMap.keySet();
                    Iterator<String> i = keys.iterator();
                    while (i.hasNext()) {
                        System.out.println(i.next());
                    }
                    System.out.println("Enter the AddressBook Name you want to Open : ");
                    String name = sc.nextLine();
                    System.out.println("Current Address Book is : " + name);
                    // calling method with passing address book name
                    AddressBook.addressBookOptions(addressBookMap.get(name));
                    break;
                case 3:
                    System.out.println("Enter Address Book name to be delete: ");
                    name = sc.nextLine();
                    addressBookMap.remove(name);
                    break;
                case 4:
                    System.out.println("Welcome to the search option:");
                    addressBook.searchByOptions();
                case 5:
                    sc.close();
                    return;
                default:
                    System.out.println("You Entered Invalid Choice");
                    break;
            }
        }
    }

}
