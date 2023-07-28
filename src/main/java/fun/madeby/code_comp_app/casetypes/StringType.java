package fun.madeby.code_comp_app.casetypes;

import java.util.function.IntPredicate;

/**
 * Int predicates defining acceptable characters are returned by the apply method and used in look-ups against strings.
 * https://www.ascii-code.com
 * 
 * NUMERIC == NUMERIC only      characters 48-57 only 0-9 [\d]
 * NUMERIC_SP == NUMERIC or space   characters 48-57 and 32 only [\d ]  !! numeric only matches this 100% too
 * NUMERIC_OSEP == NUMERIC or / , \ | characters 47-57 92, 124 and 44  [\d \\\,\/\|] {0-9\/,|} !! numeric only matches this 100% too
 * SINGLE_WORD_A_Z == single word no spaces or other separators  [a-zA-Z]
 * OTHER ==0-256 ASCII !! numeric only matches this 100% too
 * UNKNOWN == catch all
 *
 */
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
