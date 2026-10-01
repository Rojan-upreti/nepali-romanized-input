// Transliteration engine: converts common Romanized Nepali tokens to Devanagari.
package com.lekhai.api;

import java.util.LinkedHashMap;
import java.util.Map;

public class TransliterationService {
  private static final Map<String, String> TOKENS = new LinkedHashMap<>();
  static {
    String[][] pairs = {{"chh","छ"},{"ksh","क्ष"},{"gy","ज्ञ"},{"kh","ख"},{"gh","घ"},{"ch","च"},{"jh","झ"},{"th","थ"},{"dh","ध"},{"ph","फ"},{"bh","भ"},{"sh","श"},{"aa","आ"},{"ee","ई"},{"oo","ऊ"},{"ai","ऐ"},{"au","औ"},{"a","अ"},{"i","इ"},{"u","उ"},{"e","ए"},{"o","ओ"},{"k","क"},{"g","ग"},{"c","क"},{"j","ज"},{"t","त"},{"d","द"},{"n","न"},{"p","प"},{"b","ब"},{"m","म"},{"y","य"},{"r","र"},{"l","ल"},{"v","व"},{"w","व"},{"s","स"},{"h","ह"},{"q","क"},{"x","क्ष"},{"z","ज"}};
    for (String[] pair : pairs) TOKENS.put(pair[0], pair[1]);
  }

  public String transliterate(String input) {
    StringBuilder output = new StringBuilder();
    String lower = input.toLowerCase();
    for (int i = 0; i < input.length();) {
      char current = input.charAt(i);
      if (!Character.isLetter(current) || current > 127) { output.append(current); i++; continue; }
      String match = null;
      for (String token : TOKENS.keySet()) if (lower.startsWith(token, i)) { match = token; break; }
      if (match == null) { output.append(current); i++; } else { output.append(TOKENS.get(match)); i += match.length(); }
    }
    return output.toString();
  }
}
