package com.dineshsaravanan.utils;
import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleUtility {
  public static String readLine(BufferedReader reader, String prompt) {
    System.out.println(prompt);
    String s = "";
    try {
      s = reader.readLine();
    } catch (IOException e) {
      System.out.println(e);
    }

    return s;
  }

  public static int getIntegerFromConsole(BufferedReader reader, String prompt) {
    String line = readLine(reader, prompt);

    return Integer.parseInt(line);
  }

  public static String getStringFromConsole(BufferedReader reader, String prompt) {
    return readLine(reader, prompt);
  }
}
