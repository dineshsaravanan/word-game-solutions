package com.dineshsaravanan.game.solver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FindWordTest {

  @Test
  void findWord_returnEmptyListIfNoWordInThatLength() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(45, "", "", "", "", "", "")
    );
  }

  @Test
  void findWord_returnAllStringsOfMatchingLength() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));
    String[] size1 = {"A"};
    String[] size4 = {"TEST", "SWIM"};

    assertEquals(
        new ArrayList<>(Arrays.asList(size1)),
        findWord.find(1, "", "", "", "", "", "")
    );

    assertEquals(
        new ArrayList<>(Arrays.asList(size4)),
        findWord.find(4, "", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfWordListIsEmpty() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.empty.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(4, "BE", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotStartsWith() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(4, "A", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotEndsWith() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(), findWord.find(4, "", "TS", "", "", "", ""));
  }

  @Test
  void findWord_shouldReturnEmptySetIfContainsIsNotSubset() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(4, "", "", "SE", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotContainsIsSubset() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(3, "", "", "", "AS", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfIncludesIsNotPartOfWord() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(3, "", "", "", "", "ASP", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfAnyExcludesIsPartOfWord() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    assertEquals(
        new ArrayList<String>(),
        findWord.find(3, "", "", "", "", "", "APE")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfStartsWith() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(1, "A", "", "", "", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(2, "i", "", "", "", "", "")
    );

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(5, "EM", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfEndsWith() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(1, "", "A", "", "", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(2, "", "s", "", "", "", "")
    );

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(5, "EM", "Y", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfContainsSubStr() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(1, "", "", "A", "", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(2, "", "", "s", "", "", "")
    );

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(5, "E", "Y", "MPT", "", "", "")
    );

    foundWords = new String[]{"LATEST", "MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "TE", "", "", "")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "L", "", "TE", "", "", "")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "R", "TE", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfNotContainsSubStr() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(1, "", "", "", "B", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(2, "", "", "", "T", "", "")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "TE", "M", "", "")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "TE", "L", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfIncludes() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(1, "", "", "", "", "A", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(2, "", "", "", "", "S", "")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "", "", "L", "")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "TE", "", "MTE", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfExcludes() throws IOException {
    var findWord = FindWord.build(WordGame.initializeWords("words_alpha.test_sample.txt"));

    String[] foundWords = new String[0];
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(1, "", "", "", "", "", "A")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(2, "", "", "", "", "I", "B")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "", "", "L", "MOP")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        findWord.find(6, "", "", "TE", "", "M", "LOP")
    );
  }
}