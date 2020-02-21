package com.alllexe.phonebook;

import java.util.Random;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.02.2020
 */
public class ContactGeneration {

  static String insert = "insert into contacts(id, name, surname, middle_name, phone_home, address, email, user_id) values";
  static String formatStr = "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s'),";
  static String[] names = {"Mike", "Bob", "Alex", "Ann", "Tony", "Gabe", "Tom", "Ali"};
  static String[] surnames = {"Kovalski", "McRow", "Green", "Armor", "Bobby", "Jackson", "Ferris", "Hendricks"};
  static String[] middlenames = {"", "Simpson", "A", "Bob", "Col", "Sam", "F", "H"};
  static String[] streets = {"Green", "Yellow", "Anderson", "White", "Black"};

  public static void main(String[] args) {

    int id = 100;

    int user_id = 100;
    for (int i = 0; i < 15; i++) {
      String name = getName();
      System.out.println(String.format(formatStr, id++, name, getSurname(), getMiddleName(), getPhoneHome(), getAddress(), getEmail(name), user_id));
    }
    user_id = 102;
    for (int i = 0; i < 15; i++) {
      String name = getName();
      System.out.println(String.format(formatStr, id++, name, getSurname(), getMiddleName(), getPhoneHome(), getAddress(), getEmail(name), user_id));
    }

    user_id = 103;
    for (int i = 0; i < 15; i++) {
      String name = getName();
      System.out.println(String.format(formatStr, id++, name, getSurname(), getMiddleName(), getPhoneHome(), getAddress(), getEmail(name), user_id));
    }

  }

  private static String getEmail(String name) {
    return name.toLowerCase()+"@mail.com";
  }

  private static String getAddress() {
    Random rand = new Random(); //instance of random class
    int upperbound = 115;
    return String.format("%s, %s", getRandomFromArray(streets), rand.nextInt(upperbound));
  }

  private static String getPhoneHome() {
    //+380(66) 1234567
    Random rand = new Random(); //instance of random class
    int upperbound = 10;
    int int_random = rand.nextInt(upperbound);
    return String.format("+380(66) %s%s%s%s%s%s%s",
        rand.nextInt(upperbound),
        rand.nextInt(upperbound),
        rand.nextInt(upperbound),
        rand.nextInt(upperbound),
        rand.nextInt(upperbound),
        rand.nextInt(upperbound),
        rand.nextInt(upperbound));
  }

  private static String getMiddleName() {
    return getRandomFromArray(middlenames);
  }

  private static String getSurname() {
    return getRandomFromArray(surnames);
  }

  private static String getRandomFromArray(String[] arr) {
    Random rand = new Random(); //instance of random class
    int upperbound = arr.length;
    int int_random = rand.nextInt(upperbound);
    return arr[int_random];
  }

  private static String getName() {
    return getRandomFromArray(names);
  }


}
