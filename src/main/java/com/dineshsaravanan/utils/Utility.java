package com.dineshsaravanan.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class Utility {
  public static boolean isInteger(String strNum) {
    if (strNum == null) {
      return false;
    }
    try {
      Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static boolean isStartsWith(String word, String startSegment, Boolean caseSensitive) {
    if (!caseSensitive) {
      word = word.toUpperCase(Locale.ROOT);
      startSegment = startSegment.toUpperCase(Locale.ROOT);
    }

    return word.indexOf(startSegment) == 0;
  }

  public static boolean isEndsWith(String word, String startSegment, Boolean caseSensitive) {
    if (!caseSensitive) {
      word = word.toUpperCase(Locale.ROOT);
      startSegment = startSegment.toUpperCase(Locale.ROOT);
    }

    return word.lastIndexOf(startSegment) == (word.length() - startSegment.length());
  }

  public static boolean hasSubString(String word, String subString, Boolean caseSensitive) {
    if (!caseSensitive) {
      word = word.toUpperCase(Locale.ROOT);
      subString = subString.toUpperCase(Locale.ROOT);
    }

    return word.lastIndexOf(subString) >= 0;
  }

  public static boolean hasNoSubString(String word, String subString, Boolean caseSensitive) {
    if (subString.equals("")) {
      return true;
    }
    return !(hasSubString(word, subString, caseSensitive));
  }

  public static boolean includes(String word, String chars, Boolean caseSensitive) {
    if (chars.equals("")) {
      return true;
    }

    if (!caseSensitive) {
      word = word.toUpperCase(Locale.ROOT);
      chars = chars.toUpperCase(Locale.ROOT);
    }

    return stringToCharacterSet(word).containsAll
        (stringToCharacterSet(chars));
  }

  public static boolean excludes(String word, String chars, Boolean caseSensitive) {
    if (chars.equals("")) {
      return true;
    }

    if (!caseSensitive) {
      word = word.toUpperCase(Locale.ROOT);
      chars = chars.toUpperCase(Locale.ROOT);
    }

    return !containsAny(stringToCharacterSet(word), stringToCharacterSet(chars));
  }


  public static String characterSetToString(Set<Character> chars) {
    var stringBuilder = new StringBuilder();

    for (Character c :
        chars) {
      stringBuilder.append(c);
    }

    return stringBuilder.toString();
  }

  private static boolean containsAny(Collection<?> container, Collection<?> contains) {
    for (Object content :
        contains) {
      if (container.contains(content)) {
        return true;
      }
    }

    return false;
  }

  private static Set<Character> stringToCharacterSet(String s) {
    Set<Character> set = new HashSet<>();
    for (char c : s.toCharArray()) {
      set.add(c);
    }
    return set;
  }
}
