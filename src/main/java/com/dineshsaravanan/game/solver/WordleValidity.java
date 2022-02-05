package com.dineshsaravanan.game.solver;

public enum WordleValidity {
  EXACT_MATCH('0'),
  NOT_IN_POSITION('1'),
  NOT_FOUND('2');

  private final char value;

  WordleValidity(char value)
  {
    this.value = value;
  }

  public static WordleValidity getWordleValidity(final char value)
  {
    for (WordleValidity type : WordleValidity.values())
      if (type.value == value)
        return type;

    return null;
  }
}
