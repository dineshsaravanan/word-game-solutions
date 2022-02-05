package com.dineshsaravanan.game.solver;

import com.dineshsaravanan.utils.ConsoleUtility;
import com.dineshsaravanan.utils.Utility;

import java.util.ArrayList;
import java.util.Map;

public class FindWord {
  private String startsWith = "";
  private String endsWith = "";
  private String excludes = "";
  private String includes = "";
  private String contains = "";
  private String notContains = "";
  private ArrayList<String> words = new ArrayList<>();

  public FindWord words(ArrayList<String> words) {
    this.words = words;

    return this;
  }

  public FindWord words(Map<Integer, ArrayList<String>> wordsByLength, int length) {
    this.words = wordsByLength.getOrDefault(length, new ArrayList<>());

    return this;
  }

  public FindWord startsWith(String startsWith) {
    this.startsWith = startsWith;

    return this;
  }

  public FindWord endsWith(String endsWith) {
    this.endsWith = endsWith;

    return this;
  }

  public FindWord excludes(String excludes) {
    this.excludes = excludes;

    return this;
  }

  public FindWord includes(String includes) {
    this.includes = includes;

    return this;
  }

  public FindWord contains(String contains) {
    this.contains = contains;

    return this;
  }

  public FindWord notContains(String notContains) {
    this.notContains = notContains;

    return this;
  }

  public ArrayList<String> find() {
    var outputWords = new ArrayList<String>();

    if (words.size() == 0) {
      return outputWords;
    }

    for (String word :
        words) {
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

  public static FindWord build() {
    return new FindWord();
  }

  public static void start(Map<Integer, ArrayList<String>> wordByLength) {
    while(true) {
      int wordLength = ConsoleUtility.getIntegerFromConsole("Enter the word length <0 to exit>: ");

      if (wordLength == 0) {
        break;
      }

      String startsWith = ConsoleUtility.getStringFromConsole("Starts with: ");
      String endsWith = ConsoleUtility.getStringFromConsole("Ends with: ");
      String contains = ConsoleUtility.getStringFromConsole("Contains substring: ");
      String notContains = ConsoleUtility.getStringFromConsole("Doesn't contain substring: ");
      String includes = ConsoleUtility.getStringFromConsole("Contains characters: ");
      String excludes = ConsoleUtility.getStringFromConsole("Excludes characters: ");

      var foundWords =
          FindWord
          .build()
          .words(wordByLength, wordLength)
          .contains(contains)
          .notContains(notContains)
          .excludes(excludes)
          .includes(includes)
          .startsWith(startsWith)
          .endsWith(endsWith)
          .find();

      WordGame.printWords(foundWords);
    }
  }
}
