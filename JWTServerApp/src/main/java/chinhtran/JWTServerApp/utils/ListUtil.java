package chinhtran.JWTServerApp.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtil {

  public static List<Long> getInexistList(final List<Long> source, final List<Long> destination) {
    Set<Long> sourceSet = new HashSet<>(source);
    destination.forEach(
        e -> {
          sourceSet.remove(e);
        });
    return new ArrayList<>(sourceSet);
  }

  public static List<Long> getInexistList(final Set<Long> source, final List<Long> destination) {
    Set<Long> sourceSet = source;
    destination.forEach(
        e -> {
          sourceSet.remove(e);
        });
    return new ArrayList<>(sourceSet);
  }
}
