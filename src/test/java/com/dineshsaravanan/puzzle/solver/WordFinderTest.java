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
    String[] size1 = {"a"};
    String[] size2 = {"aa"};
    String[] size3 = {"aaa"};
    String[] size4 = {"avav", "avva"};
    String[] size5 = {"asdda", "asdda"};

    WordFinder.initializeWords("words_alpha.test_sample.txt");
    assertFalse(WordFinder.wordsByLength.isEmpty());
    assertEquals(WordFinder.wordsByLength.get(1).size(), 1);
    assertEquals(WordFinder.wordsByLength.get(2).size(), 1);
    assertEquals(WordFinder.wordsByLength.get(3).size(), 1);
    assertEquals(WordFinder.wordsByLength.get(4).size(), 2);
    assertEquals(WordFinder.wordsByLength.get(5).size(), 2);

    assertEquals(WordFinder.wordsByLength.get(1), new ArrayList<>(Arrays.asList(size1)));
    assertEquals(WordFinder.wordsByLength.get(2), new ArrayList<>(Arrays.asList(size2)));
    assertEquals(WordFinder.wordsByLength.get(3), new ArrayList<>(Arrays.asList(size3)));
    assertEquals(WordFinder.wordsByLength.get(4), new ArrayList<>(Arrays.asList(size4)));
    assertEquals(WordFinder.wordsByLength.get(5), new ArrayList<>(Arrays.asList(size5)));

    assertNull(WordFinder.wordsByLength.get(6));
  }
}