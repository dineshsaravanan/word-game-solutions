package com.dineshsaravanan.game.solver;

import com.dineshsaravanan.utils.ConsoleUtility;
import com.dineshsaravanan.utils.Utility;

import java.util.*;

public class WordleSolver {
  private final int wordLength;
  private final String exitWord;
  private int attempts = 0;
  private final Set<Character> included = new HashSet<>();
  private final Set<Character> excluded = new HashSet<>();
  private final char[] possibleString;
  private final Map<Character, Set<Integer>> impossibleWordPositions = new HashMap();
  private ArrayList<String> wordsToSearch;


  public static void start(Map<Integer, ArrayList<String>> wordsByLength) {
    int wordLength = ConsoleUtility.getIntegerFromConsole("Enter the word length <0 to exit>: ", 0, 10);

    if (wordLength == 0) {
      return;
    }

    var ws = new WordleSolver(wordLength, wordsByLength);

    ws.startNewAttempt();
  }

  private WordleSolver(int wordLength, Map<Integer, ArrayList<String>> wordsByLength) {
    this.wordLength = wordLength;
    this.possibleString = new char[wordLength];
    this.wordsToSearch = wordsByLength.getOrDefault(wordLength, new ArrayList<>());

    char[] chars = new char[wordLength];
    Arrays.fill(chars, '0');

    this.exitWord = new String(chars);
  }

  private void startNewAttempt() {
    attempts++;
    System.out.println("*** Attempt #" + attempts + " ***");

    String wordPrompt = "Enter the word <"+this.exitWord+" to exit>: ";
    String word = ConsoleUtility.getStringFromConsole(wordPrompt, wordLength);

    if (word.equals(exitWord)) {
      return;
    }

    String validityPrompt = "Enter the validity matrix [0 - Match, 1 - Incorrect position, 2 - Not found]: ";
    String validity = ConsoleUtility.getStringFromConsole(validityPrompt, wordLength);

    word = word.toUpperCase(Locale.ROOT);

    processAttempt(word, validity);

    var startsWith = wordStartsWith();
    var endsWith = wordEndsWith();
    var inc =  Utility.characterSetToString(included);
    var exc = Utility.characterSetToString(excluded);
    var words = FindWord.find(wordsToSearch, startsWith, endsWith, "", "", inc,  exc);
    words = filterForCharPosition(words);

    WordGame.printWords(words);
    wordsToSearch = words;

    startNewAttempt();
  }

  private ArrayList<String> filterForCharPosition(ArrayList<String> words) {
    words = filterForWrongPositions(words);
    words = filterForCorrectPositions(words);

    return words;
  }

  private ArrayList<String> filterForCorrectPositions(ArrayList<String> words) {
    if (words.size() == 0) return words;

    var filteredWords = new ArrayList<String>();

    for (String s : words) {
      String word = s.toUpperCase(Locale.ROOT);
      boolean selectWord = true;
      for (int j = 0; j < possibleString.length; j++) {
        if (possibleString[j] != 0 && word.charAt(j) != possibleString[j]) {
          selectWord = false;
          break;
        }
      }

      if (selectWord) {
        filteredWords.add(word);
      }
    }

    return filteredWords;
  }

  private ArrayList<String> filterForWrongPositions(ArrayList<String> words) {
    if (words.size() == 0) return words;

    var filteredWords = new ArrayList<String>();

    for (String s : words) {
      String word = s.toUpperCase(Locale.ROOT);
      boolean selectWord = true;
      for (int j = 0; j < word.length(); j++) {
        if(isCharAtImpossiblePosition(word.charAt(j), j)) {
          selectWord = false;
          break;
        }
      }

      if (selectWord) {
        filteredWords.add(word);
      }
    }

    return filteredWords;
  }

  private void markIncludedAndExcluded(String word, String validity) {
    for (int i = 0; i < wordLength; i++) {
      var charValidity = validity.charAt(i);
      var character = word.charAt(i);

      // Is the current char a match or misplaced?
      var enumValidity = WordleValidity.getWordleValidity(charValidity);
      if (enumValidity == WordleValidity.EXACT_MATCH || enumValidity == WordleValidity.NOT_IN_POSITION) {
        included.add(character);
        // There can be multiple same characters in string
        // don't exclude the character another instance is found
        excluded.remove(character);
      } else {
        // There can be multiple same characters in string
        // don't exclude the character another instance is found
        if (!included.contains(character)) {
          excluded.add(character);
        }
      }
    }
  }

  private void buildPossibleString(String word, String validity) {
    for (int i = 0; i < wordLength; i++) {
      var charValidity = validity.charAt(i);
      var enumValidity = WordleValidity.getWordleValidity(charValidity);
      if (enumValidity == WordleValidity.NOT_FOUND) {
        markCharPositionAsImpossible(word.charAt(i), i);
        continue;
      }

      if (enumValidity == WordleValidity.EXACT_MATCH) {
        possibleString[i] = word.charAt(i);
        continue;
      }

      // WordleValidity.NOT_IN_POSITION
      markCharPositionAsImpossible(word.charAt(i), i);
    }
  }

  private void markCharPositionAsImpossible(char c, int position) {
    var positions = impossibleWordPositions.get(c);

    if (positions == null) {
      positions = new HashSet<>();
      impossibleWordPositions.put(c, positions);
    }

    positions.add(position);
  }

  private boolean isCharAtImpossiblePosition(char c, int j) {
    var positions = impossibleWordPositions.get(c);

    // the character has not been encountered in a wrong position yet
    if (positions == null) return false;

    return positions.contains(j);
  }

  private String wordStartsWith() {
    StringBuilder startsWith = new StringBuilder();

    int i = 0;
    char charInString = possibleString[i];

    while((int)charInString != 0 && i < wordLength) {
      startsWith.append(charInString);

      i++;
      charInString = possibleString[i];
    }

    return startsWith.toString();
  }

  private String wordEndsWith() {
    StringBuilder endsWith = new StringBuilder();

    int i = wordLength-1;

    while((int)possibleString[i] != 0 && i >= 0) {
      endsWith.append(possibleString[i]);
      i--;
    }

    endsWith.reverse();

    return endsWith.toString();
  }

  private void processAttempt(String word, String validity) {
    markIncludedAndExcluded(word, validity);
    buildPossibleString(word, validity);
  }
}
