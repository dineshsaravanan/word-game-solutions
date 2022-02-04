package com.dineshsaravanan.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

  @Test
  void isInteger_shouldReturnFalseIfNull() {
    assertFalse(Utility.isInteger(null));
  }

  @Test
  void isInteger_shouldReturnFalseIfAllAlphabets() {
    assertFalse(Utility.isInteger("test?"));
  }

  @Test
  void isInteger_shouldReturnFalseIfAnyAlphabets() {
    assertFalse(Utility.isInteger("123t"));
  }

  @Test
  void isInteger_shouldReturnTrueIfInteger() {
    assertTrue(Utility.isInteger("123"));
  }

  @Test
  void isInteger_shouldReturnTrueIfDouble() {
    assertTrue(Utility.isInteger("123.0"));
  }

  @Test
  void isStartsWith_returnTrueIfStartSegmentInputIsEmpty() {
    assertTrue(Utility.isStartsWith("", "", true));
    assertTrue(Utility.isStartsWith("", "", false));
    assertTrue(Utility.isStartsWith("AMP", "", true));
    assertTrue(Utility.isStartsWith("AMP", "", false));
  }

  @Test
  void isStartsWith_returnFalseIfStartSegmentIsNotAtStart() {
    assertFalse(Utility.isStartsWith("", "AMP", true));
    assertFalse(Utility.isStartsWith("", "AMP", false));
    assertFalse(Utility.isStartsWith("AMPE", "amp", true));
    assertFalse(Utility.isStartsWith("CDAMPD", "DAMP", false));
  }

  @Test
  void isStartsWith_returnTrueIfStartSegmentAtStart() {
    assertTrue(Utility.isStartsWith("AMP", "AMP", true));
    assertTrue(Utility.isStartsWith("AMP", "amp", false));
    assertTrue(Utility.isStartsWith("AMPLE", "AMP", true));
    assertTrue(Utility.isStartsWith("CAMPLED", "CAmP", false));
  }

  @Test
  void isEndsWith_returnTrueIfEndSegmentInputIsEmpty() {
    assertTrue(Utility.isEndsWith("", "", true));
    assertTrue(Utility.isEndsWith("", "", false));
    assertTrue(Utility.isEndsWith("AMP", "", true));
    assertTrue(Utility.isEndsWith("AMP", "", false));
  }

  @Test
  void isEndsWith_returnFalseIfEndSegmentIsNotAtEnd() {
    assertFalse(Utility.isEndsWith("", "AMP", true));
    assertFalse(Utility.isEndsWith("", "AMP", false));
    assertFalse(Utility.isEndsWith("AMP", "amp", true));
    assertFalse(Utility.isEndsWith("AMPD", "AMP", false));
  }

  @Test
  void isEndsWith_returnTrueIfEndSegmentAtEnd() {
    assertTrue(Utility.isEndsWith("AMP", "AMP", true));
    assertTrue(Utility.isEndsWith("AMP", "amp", false));
    assertTrue(Utility.isEndsWith("CAMPLE", "PLE", true));
    assertTrue(Utility.isEndsWith("CAMPLED", "PlED", false));
  }

  @Test
  void hasSubString_returnTrueIfSubStrInputIsEmpty() {
    assertTrue(Utility.hasSubString("", "", true));
    assertTrue(Utility.hasSubString("", "", false));
    assertTrue(Utility.hasSubString("AMP", "", true));
    assertTrue(Utility.hasSubString("AMP", "", false));
  }

  @Test
  void hasSubString_returnFalseIfSubStrIsNotInWord() {
    assertFalse(Utility.hasSubString("", "A", true));
    assertFalse(Utility.hasSubString("", "a", false));
    assertFalse(Utility.hasSubString("AMPLE", "AME", true));
    assertFalse(Utility.hasSubString("AMPLE", "PLES", false));
  }

  @Test
  void hasSubString_returnTrueIfSubStrIsInWord() {
    assertTrue(Utility.hasSubString("A", "A", true));
    assertTrue(Utility.hasSubString("A", "a", false));
    assertTrue(Utility.hasSubString("AMPLE", "AMP", true));
    assertTrue(Utility.hasSubString("AMPLE", "MpL", false));
  }

  @Test
  void hasNoSubString_returnTrueIfSubStrInputIsEmpty() {
    assertTrue(Utility.hasNoSubString("", "", true));
    assertTrue(Utility.hasNoSubString("", "", false));
    assertTrue(Utility.hasNoSubString("AMP", "", true));
    assertTrue(Utility.hasNoSubString("AMP", "", false));
  }

  @Test
  void hasNoSubString_returnTrueIfSubStrIsNotInWord() {
    assertTrue(Utility.hasNoSubString("", "A", true));
    assertTrue(Utility.hasNoSubString("", "a", false));
    assertTrue(Utility.hasNoSubString("AMPLE", "AME", true));
    assertTrue(Utility.hasNoSubString("AMPLE", "PLES", false));
  }

  @Test
  void hasSubString_returnFalseIfSubStrIsInWord() {
    assertFalse(Utility.hasNoSubString("A", "A", true));
    assertFalse(Utility.hasNoSubString("A", "a", false));
    assertFalse(Utility.hasNoSubString("AMPLE", "AMP", true));
    assertFalse(Utility.hasNoSubString("AMPLE", "MpL", false));
  }

  @Test
  void includes_returnTrueIfCharsIsEmpty() {
    assertTrue(Utility.includes("", "", true));
    assertTrue(Utility.includes("", "", false));
    assertTrue(Utility.includes("AMP", "", true));
    assertTrue(Utility.includes("AMP", "", false));
  }

  @Test
  void includes_returnFalseIfCharsNotInWord() {
    assertFalse(Utility.includes("A", "a", true));
    assertFalse(Utility.includes("A", "B", false));
    assertFalse(Utility.includes("AMPLIFY", "ALIYR", true));
    assertFalse(Utility.includes("AMPLIFY", "aliyr", false));
  }

  @Test
  void includes_returnTrueIfCharsInWord() {
    assertTrue(Utility.includes("A", "A", true));
    assertTrue(Utility.includes("A", "a", false));
    assertTrue(Utility.includes("AMPLIFY", "ALIY", true));
    assertTrue(Utility.includes("AMPLIFY", "aliy", false));
  }

  @Test
  void excludes_returnTrueIfCharsIsEmpty() {
    assertTrue(Utility.excludes("", "", true));
    assertTrue(Utility.excludes("", "", false));
    assertTrue(Utility.excludes("AMP", "", true));
    assertTrue(Utility.excludes("AMP", "", false));
  }

  @Test
  void excludes_returnTrueIfCharsNotInWord() {
    assertTrue(Utility.excludes("A", "a", true));
    assertTrue(Utility.excludes("A", "B", false));
    assertTrue(Utility.excludes("AMPLIFY", "EGGD", true));
    assertTrue(Utility.excludes("AMPLIFY", "eggd", false));
  }

  @Test
  void excludes_returnFalseIfCharsInWord() {
    assertFalse(Utility.excludes("A", "A", true));
    assertFalse(Utility.excludes("A", "a", false));
    assertFalse(Utility.excludes("AMPLIFY", "ALIY", true));
    assertFalse(Utility.excludes("AMPLIFY", "aliy", false));
    assertFalse(Utility.excludes("AMPLIFY", "aeggd", false));
  }
}