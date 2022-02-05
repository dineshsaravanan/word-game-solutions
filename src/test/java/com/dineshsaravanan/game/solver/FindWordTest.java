package com.dineshsaravanan.game.solver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FindWordTest {

  @Test
  void findWord_returnEmptyListIfNoWordInThatLength() throws IOException {
    var findWord = FindWord.build();

    findWord.words(WordGame.initializeWords("words_alpha.test_sample.txt"), 45);

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_returnAllStringsOfMatchingLength() throws IOException {
    String[] size1 = {"A"};
    String[] size4 = {"TEST", "SWIM"};

    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    assertEquals(
        new ArrayList<>(Arrays.asList(size1)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 4)
        .startsWith("")
        .endsWith("")
        .contains("");
    assertEquals(
        new ArrayList<>(Arrays.asList(size4)),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfWordListIsEmpty() throws IOException {
    var words = WordGame.initializeWords("words_alpha.empty.txt");

    var findWord = FindWord.build();
    findWord.words(words, 4);
    findWord.startsWith("BE");

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotStartsWith() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 4);
    findWord.startsWith("A");

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotEndsWith() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 4);
    findWord.endsWith("TS");

    assertEquals(
        new ArrayList<String>(), findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfContainsIsNotSubset() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 4);
    findWord.contains("TS");

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotContainsIsSubset() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 3);
    findWord.notContains("AS");

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfIncludesIsNotPartOfWord() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 3);
    findWord.includes("ASP");

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfAnyExcludesIsPartOfWord() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 3);
    findWord.excludes("APE");

    assertEquals(
        new ArrayList<String>(),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfStartsWith() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    findWord.startsWith("A");
    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 2);
    findWord.startsWith("i");
    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 5);
    findWord.startsWith("EM");
    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfEndsWith() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    findWord.endsWith("A");
    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 2);
    findWord.endsWith("s");
    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 5)
        .startsWith("EM")
        .endsWith("Y");

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfContainsSubStr() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    findWord.contains("A");
    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 2);
    findWord.contains("s");
    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 5);
    findWord.startsWith("E");
    findWord.endsWith("Y");
    findWord.contains("MPT");
    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.contains("TE");
    foundWords = new String[]{"LATEST", "MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.contains("TE");
    findWord.startsWith("L");
    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.contains("TE");
    findWord.endsWith("R");
    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfNotContainsSubStr() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    findWord.notContains("B");
    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 2);
    findWord.notContains("T");
    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.notContains("M");
    findWord.contains("TE");
    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.notContains("L");
    findWord.contains("TE");
    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfIncludes() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    findWord.includes("A");
    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 2);
    findWord.includes("s");
    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.includes("L");
    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.includes("MTE");
    findWord.contains("TE");
    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfExcludes() throws IOException {
    var words = WordGame.initializeWords("words_alpha.test_sample.txt");

    var findWord = FindWord.build();
    findWord.words(words, 1);
    findWord.excludes("A");
    String[] foundWords = new String[0];
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 2);
    findWord.excludes("B");
    findWord.includes("I");
    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.includes("L");
    findWord.excludes("MOP");
    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );

    findWord = FindWord.build();
    findWord.words(words, 6);
    findWord.contains("TE");
    findWord.includes("M");
    findWord.excludes("LOP");
    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find()
    );
  }
}