package com.dineshsaravanan.game.solver;

import com.dineshsaravanan.utils.ConsoleUtility;
import com.dineshsaravanan.utils.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class FindWord {
  private static FindWord instance;

  private Map<Integer, ArrayList<String>> wordsByLength;

  public static FindWord build(Map<Integer, ArrayList<String>> wordByLength) {
    if (instance == null) {
      instance = new FindWord(wordByLength);
    }
    else {
      instance.wordsByLength = wordByLength;
    }

    return instance;
  }

  private FindWord(Map<Integer, ArrayList<String>> wordsByLength) {
    this.wordsByLength = wordsByLength;
  }

  public static void start(Map<Integer, ArrayList<String>> wordByLength) throws IOException {
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

      var words = FindWord.build(wordByLength).find(wordLength, startsWith, endsWith, contains, notContain, includes, excludes);

      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < words.size(); i++) {
        if (i != 0) builder.append(", ");

        builder.append(words.get(i));
      }

      System.out.println(builder);
    }
  }

  protected ArrayList<String> find(int length, String startsWith, String endsWith, String contains, String notContains, String includes, String excludes)
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
