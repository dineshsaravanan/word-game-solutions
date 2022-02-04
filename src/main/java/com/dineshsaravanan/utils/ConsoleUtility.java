package com.dineshsaravanan.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUtility {
  public static String readLine(String prompt) throws IOException {
    String s = "";

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.println(prompt);
      s = "";
      try {
        s = reader.readLine();
      } catch (IOException e) {
        System.out.println(e);
      }
    }

    return s;
  }

  public static int getIntegerFromConsole(String prompt) throws IOException {
    String line = readLine(prompt);

    return Integer.parseInt(line);
  }

  public static String getStringFromConsole(String prompt) throws IOException {
    return readLine(prompt);
  }
}
