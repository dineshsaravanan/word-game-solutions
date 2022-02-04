package com.dineshsaravanan.puzzle.solver;

import com.dineshsaravanan.utils.ConsoleUtility;
import com.dineshsaravanan.utils.Utility;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordFinder {
  private static final String wordFile = "words_alpha.txt";

  protected static final Map<Integer, ArrayList<String>> wordsByLength = new HashMap<>();

  public static void main(String[] args) throws IOException {
    System.setProperty("org.jline.terminal.dumb", "true");
    initializeWords(wordFile);

    while(true) {
      int wordLength = ConsoleUtility.getIntegerFromConsole("Enter the word length <0 to exit>: ");

      if (wordLength == 0) {
        break;
      }

      String startsWith = ConsoleUtility.getStringFromConsole("Starts with: ");
      String endsWith = ConsoleUtility.getStringFromConsole("Ends with: ");
      String contains = ConsoleUtility.getStringFromConsole("Contains substring: ");
      String notContain = ConsoleUtility.getStringFromConsole("Doesn't contain substring: ");
      String includes = ConsoleUtility.getStringFromConsole("Contains characters: ");
      String excludes = ConsoleUtility.getStringFromConsole("Excludes characters: ");

      var words = findWord(wordLength, startsWith, endsWith, contains, notContain, includes, excludes);

      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < words.size(); i++) {
        if (i != 0) builder.append(", ");

        builder.append(words.get(i));
      }

      System.out.println(builder);
    }
  }

  protected static void initializeWords(String fileName) throws IOException {
    wordsByLength.entrySet().removeIf(e -> true);

    var in =  WordFinder.class.getClassLoader().getResourceAsStream(fileName);

    if (in == null) {
      throw new IOException("Can not find word list: unable to read " + fileName);
    }

    var fr = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

    var line = fr.readLine();
    while(line != null) {
      var len = line.length();
      var words = wordsByLength.computeIfAbsent(len, k -> new ArrayList<>());

      words.add(line);
      line = fr.readLine();
    }
  }

  protected static ArrayList<String> findWord(int length, String startsWith, String endsWith, String contains, String notContains, String includes, String excludes)
  {
    var initialWords = wordsByLength.getOrDefault(length, new ArrayList<>());
    var outputWords = new ArrayList<String>();

    if (initialWords.size() == 0) {
      return outputWords;
    }

    for (String word :
        initialWords) {
      if(
          Utility.isStartsWith(word, startsWith, false)
              && Utility.isEndsWith(word, endsWith, false)
              && Utility.hasSubString(word, contains, false)
              && Utility.hasNoSubString(word, notContains, false)
              && Utility.includes(word, includes, false)
              && Utility.excludes(word, excludes, false)
      ) {
        outputWords.add(word);
      }
    }

    return outputWords;
  }
}
