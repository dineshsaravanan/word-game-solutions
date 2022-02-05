package com.dineshsaravanan.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtility {
  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  public static String readLine(String prompt) {
    System.out.println(prompt);
    String s = "";
    try {
      s = reader.readLine();
    } catch (IOException e) {
      System.out.println(e);
    }

    return s;
  }

  public static int getIntegerFromConsole(String prompt) {
    String line = readLine(prompt);

    while(!Utility.isInteger(line)) {
      System.out.println("Error: input is not a valid integer...");
      line = readLine(prompt);
    }

    return Integer.parseInt(line);
  }

  public static int getIntegerFromConsole(String prompt, int min) {
    int value = getIntegerFromConsole(prompt);

    while (value < min) {
      System.out.println("Error: input is smaller than min...");
      value = getIntegerFromConsole(prompt);
    }

    return value;
  }

  public static int getIntegerFromConsole(String prompt, int min, int max) {
    int value = getIntegerFromConsole(prompt, min);

    while (value > max) {
      System.out.println("Error: input is greater than max...");
      value = getIntegerFromConsole(prompt);
    }

    return value;
  }

  public static String getStringFromConsole(String prompt) {
    return readLine(prompt);
  }

  public static String getStringFromConsole(String prompt, int minSize) {
    String s = getStringFromConsole(prompt);

    while (s.length() < minSize) {
      System.out.println("Error: Input length is smaller than min...");

      s = getStringFromConsole(prompt);
    }

    return s;
  }

  public static String getStringFromConsole(String prompt, int minSize, int maxSize) {
    String s = getStringFromConsole(prompt, minSize);

    while (s.length() > maxSize) {
      System.out.println("Error: Input length is greater than max...");

      s = getStringFromConsole(prompt);
    }

    return s;
  }
}
