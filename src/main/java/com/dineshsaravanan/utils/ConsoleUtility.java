package com.dineshsaravanan.utils;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

public class ConsoleUtility {
  private static LineReader lineReader;

  private static LineReader getLineReader() {
    if (lineReader == null) {
      lineReader = LineReaderBuilder
          .builder()
          .build();
    }

    return lineReader;
  }

  public static int getIntegerFromConsole(String prompt) {
    lineReader = getLineReader();

    String line = lineReader.readLine(prompt);
    while(!Utility.isInteger(line)) {
      line = lineReader.readLine(prompt);
    }

    return Integer.parseInt(line);
  }

  public static String getStringFromConsole(String prompt) {
    return getLineReader().readLine(prompt);
  }
}
