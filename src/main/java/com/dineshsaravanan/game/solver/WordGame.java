package com.dineshsaravanan.game.solver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordGame {
  private static final String wordFile = "words_alpha.txt";

  public static void main(String[] args) throws IOException {
    var words = initializeWords(wordFile);
    FindWord.start(words);
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
}
