package com.dineshsaravanan.game.solver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WordGameTest {
  @Test
  void initializeWords_shouldThrowWhenFileNotFound() {
    IOException thrown = assertThrows(IOException.class, ()-> WordGame.initializeWords("test.txt"));

    assertEquals("Can not find word list: unable to read test.txt", thrown.getMessage());
  }

  @Test
  void initializeWords_mapShouldBeEmptyIfFileEmpty() throws IOException {
    var map = WordGame.initializeWords("words_alpha.empty.txt");
    assertTrue(map.isEmpty());
  }

  @Test
  void initializeWords_shouldPopulateMapBySize() throws IOException {
    String[] size1 = {"A"};
    String[] size2 = {"IS"};
    String[] size3 = {"WAS"};
    String[] size4 = {"TEST", "SWIM"};
    String[] size5 = {"AUDIO", "EMPTY"};

    var map = WordGame.initializeWords("words_alpha.test_sample.txt");
    assertFalse(map.isEmpty());
    assertEquals(new ArrayList<>(Arrays.asList(size1)), map.get(1));
    assertEquals(new ArrayList<>(Arrays.asList(size2)), map.get(2));
    assertEquals(new ArrayList<>(Arrays.asList(size3)), map.get(3));
    assertEquals(new ArrayList<>(Arrays.asList(size4)), map.get(4));
    assertEquals(new ArrayList<>(Arrays.asList(size5)), map.get(5));

    // Should return a new map
    var anotherMap = WordGame.initializeWords("words_alpha.empty.txt");
    assertTrue(anotherMap.isEmpty());
    assertNotSame(map, anotherMap);
  }
}