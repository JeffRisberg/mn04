package com.company.domain;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CryptoUtils {

  private static final int DEFAULT_SEPARATOR = '|';

  public static Hasher defaultHasher() {
    return Hashing.murmur3_128().newHasher();
  }

  public static String aiHash(String... sources) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(Arrays.stream(sources).collect(Collectors.joining()).getBytes());
      return Arrays.toString(md.digest());
    }catch(NoSuchAlgorithmException nsx) {
      throw new IllegalArgumentException("Error hashing using md5", nsx);
    }
  }

  public static long hash(String... sources) {
    return hash(DEFAULT_SEPARATOR, defaultHasher(), sources);
  }

  public static long hash(Hasher hasher, String... sources) {
    return hash(DEFAULT_SEPARATOR, hasher, sources);
  }

  public static long hash(int separator, String... sources) { return hash(separator, defaultHasher(), sources); }

  public static long hash(int separator, Hasher hasher, String... sources) {
    if (hasher == null) {
      hasher = defaultHasher();
    }
    for (String source : sources) {
      hasher.putBytes(source.getBytes());
      hasher.putInt(separator);
    }
    return hasher.hash().asLong();
  }

  public static long hash(boolean cleanText, String... sources) { return hash(cleanText, defaultHasher(), sources); }

  public static long hash(boolean cleanText, Hasher hasher, String... sources) {
    if (hasher == null) {
      hasher = defaultHasher();
    }
    Pattern pattern = Pattern.compile("[\\s+|\\p{Punct}]"); // pattern for removing punctuation and whitespaces
    for (String source : sources) {
      if (cleanText) {
        Matcher matcher = pattern.matcher(source);
        source = matcher.replaceAll("").toLowerCase();
      }
      hasher.putBytes(source.getBytes());
      hasher.putInt(DEFAULT_SEPARATOR);
    }
    return hasher.hash().asLong();
  }

  public static String hashStr(String... sources) {
    return Long.toString(hash(sources));
  }

  public static long generateEntityId(String entityId, EntityType type) {
    return CryptoUtils.hash(type.name(), entityId.toLowerCase());
  }
}
