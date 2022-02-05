package com.dineshsaravanan.game.solver;

import com.dineshsaravanan.utils.ConsoleUtility;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordGame {
  private static final String wordFile = "words_alpha.txt";

  public static void main(String[] args) throws IOException {
    var words = initializeWords(wordFile);
    boolean shouldQuit = false;

    while(!shouldQuit) {
      int mode = ConsoleUtility.getIntegerFromConsole("Mode <0 - exit, 1 - findWord, 2 - Wordle>: ");
      if (mode == 0) {
        shouldQuit = true;
      }
      else if (mode == 1) {
        FindWord.start(words);
      }
      else  {
        WordleSolver.start(words);
      }
    }
  }

  public static Map<Integer, ArrayList<String>> initializeWords(String fileName) throws IOException {
    Map<Integer, ArrayList<String>> wordsByLength = new HashMap<>();

    var in =  WordGame.class.getClassLoader().getResourceAsStream(fileName);

    if (in == null) {
      throw new IOException("Can not find word list: unable to read " + fileName);
    }

    try (var fr = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
      var line = fr.readLine();
      while(line != null) {
        var len = line.length();
        var words = wordsByLength.computeIfAbsent(len, k -> new ArrayList<>());

        words.add(line);
        line = fr.readLine();
      }
    }

    return wordsByLength;
  }

  static void printWords(ArrayList<String> words) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < words.size(); i++) {
      if (i != 0 && (i+1)%7 != 0) builder.append(", ");
      if ((i+1) % 7 == 0) {
        builder.append("\n\r");
      }
      builder.append(words.get(i));
    }

    System.out.println(builder);
  }
}
