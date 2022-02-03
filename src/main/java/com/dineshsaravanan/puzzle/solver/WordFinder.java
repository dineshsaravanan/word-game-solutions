package com.dineshsaravanan.puzzle.solver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordFinder {
  private static final String wordFile = "words_alpha.txt";

  protected static final Map<Integer, ArrayList<String>> wordsByLength = new HashMap<>();

  public static void main(String[] args) throws IOException {
    initializeWords(wordFile);
  }

  public static void initializeWords(String fileName) throws IOException {
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

    for (var entry: wordsByLength.entrySet()) {
      System.out.println("Size : " + entry.getKey() + ", count: " + entry.getValue().size());
    }
  }
}
