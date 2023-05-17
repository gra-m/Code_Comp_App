package fun.madeby.code_comp.casetypes;

import java.util.function.IntPredicate;

public enum StringType {
      // Based on ASCII Windows-1252
      NUMERIC {public IntPredicate apply(){return arg -> (arg > 47 && arg < 58); }}, // [\d]
      NUMERIC_SP {public IntPredicate apply(){return arg ->(arg > 47 && arg < 58 || arg == 32);}}, //[\d ]
      NUMERIC_OSEP {public IntPredicate apply() {return arg -> (arg > 46 && arg < 58 || arg == 92 || arg == 124 || arg == 44);}},// [\d \\\,\/\|] {0-9\/,|}
      SINGLE_WORD_A_Z {public IntPredicate apply() {return arg -> ((arg > 64 && arg < 91) || (arg > 96 && arg < 123));}}, //[a-zA-Z]
      OTHER {public IntPredicate apply() {return arg -> ((arg > 0 && arg < 256));}}, // assumed all ASCII
      UNKNOWN{public IntPredicate apply() {return arg -> false;}}; // don't know what chars() returns for non latin yet.

public abstract IntPredicate apply();
}
