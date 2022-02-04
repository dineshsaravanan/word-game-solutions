package com.dineshsaravanan.puzzle.solver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WordFinderTest {

  @Test
  void initializeWords_shouldThrowWhenFileNotFound() {
    IOException thrown = assertThrows(IOException.class, ()-> WordFinder.initializeWords("test.txt"));

    assertEquals("Can not find word list: unable to read test.txt", thrown.getMessage());
  }

  @Test
  void initializeWords_mapShouldBeEmptyIfFileEmpty() throws IOException {
    WordFinder.initializeWords("words_alpha.empty.txt");
    assertTrue(WordFinder.wordsByLength.isEmpty());
  }

  @Test
  void initializeWords_shouldPopulateMapBySize() throws IOException {
    String[] size1 = {"A"};
    String[] size2 = {"IS"};
    String[] size3 = {"WAS"};
    String[] size4 = {"TEST", "SWIM"};
    String[] size5 = {"AUDIO", "EMPTY"};

    WordFinder.initializeWords("words_alpha.test_sample.txt");
    assertFalse(WordFinder.wordsByLength.isEmpty());
    assertEquals(new ArrayList<>(Arrays.asList(size1)), WordFinder.wordsByLength.get(1));
    assertEquals(new ArrayList<>(Arrays.asList(size2)), WordFinder.wordsByLength.get(2));
    assertEquals(new ArrayList<>(Arrays.asList(size3)), WordFinder.wordsByLength.get(3));
    assertEquals(new ArrayList<>(Arrays.asList(size4)), WordFinder.wordsByLength.get(4));
    assertEquals(new ArrayList<>(Arrays.asList(size5)), WordFinder.wordsByLength.get(5));

    // Should reinitialize the map
    WordFinder.initializeWords("words_alpha.test_sample.txt");
    assertFalse(WordFinder.wordsByLength.isEmpty());
    assertEquals(new ArrayList<>(Arrays.asList(size1)), WordFinder.wordsByLength.get(1));
    assertEquals(new ArrayList<>(Arrays.asList(size2)), WordFinder.wordsByLength.get(2));
    assertEquals(new ArrayList<>(Arrays.asList(size3)), WordFinder.wordsByLength.get(3));
    assertEquals(new ArrayList<>(Arrays.asList(size4)), WordFinder.wordsByLength.get(4));
    assertEquals(new ArrayList<>(Arrays.asList(size5)), WordFinder.wordsByLength.get(5));

    assertNull(WordFinder.wordsByLength.get(10));
  }

  @Test
  void findWord_returnEmptyListIfNoWordInThatLength() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(45, "", "", "", "", "", "")
    );
  }

  @Test
  void findWord_returnAllStringsOfMatchingLength() throws IOException {
    String[] size1 = {"A"};
    String[] size4 = {"TEST", "SWIM"};
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<>(Arrays.asList(size1)),
        WordFinder.findWord(1, "", "", "", "", "", "")
    );

    assertEquals(
        new ArrayList<>(Arrays.asList(size4)),
        WordFinder.findWord(4, "", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfWordListIsEmpty() throws IOException {
    WordFinder.initializeWords("words_alpha.empty.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(4, "BE", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotStartsWith() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(4, "A", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotEndsWith() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(4, "", "TS", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfContainsIsNotSubset() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(4, "", "", "SE", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfNotContainsIsSubset() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(3, "", "", "", "AS", "", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfIncludesIsNotPartOfWord() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(3, "", "", "", "", "ASP", "")
    );
  }

  @Test
  void findWord_shouldReturnEmptySetIfAnyExcludesIsPartOfWord() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    assertEquals(
        new ArrayList<String>(),
        WordFinder.findWord(3, "", "", "", "", "", "APE")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfStartsWith() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(1, "A", "", "", "", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(2, "i", "", "", "", "", "")
    );

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(5, "EM", "", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfEndsWith() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(1, "", "A", "", "", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(2, "", "s", "", "", "", "")
    );

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(5, "EM", "Y", "", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfContainsSubStr() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(1, "", "", "A", "", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(2, "", "", "s", "", "", "")
    );

    foundWords = new String[]{"EMPTY"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(5, "E", "Y", "MPT", "", "", "")
    );

    foundWords = new String[]{"LATEST", "MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "TE", "", "", "")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "L", "", "TE", "", "", "")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "R", "TE", "", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfNotContainsSubStr() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(1, "", "", "", "B", "", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(2, "", "", "", "T", "", "")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "TE", "M", "", "")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "TE", "L", "", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfIncludes() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    String[] foundWords = {"A"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(1, "", "", "", "", "A", "")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(2, "", "", "", "", "S", "")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "", "", "L", "")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "TE", "", "MTE", "")
    );
  }

  @Test
  void findWord_shouldReturnMatchingWordsIfExcludes() throws IOException {
    WordFinder.initializeWords("words_alpha.test_sample.txt");

    String[] foundWords = new String[0];
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(1, "", "", "", "", "", "A")
    );

    foundWords = new String[]{"IS"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(2, "", "", "", "", "I", "B")
    );

    foundWords = new String[]{"LATEST"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "", "", "L", "MOP")
    );

    foundWords = new String[]{"MASTER"};
    assertEquals(
        new ArrayList<>(Arrays.asList(foundWords)),
        WordFinder.findWord(6, "", "", "TE", "", "M", "LOP")
    );
  }
}