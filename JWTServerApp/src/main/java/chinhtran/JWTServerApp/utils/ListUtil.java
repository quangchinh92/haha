package chinhtran.JWTServerApp.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtil {

  /**
   * Compare source and destination, if source elements do not exist in destination then return
   * those elements.
   *
   * @param source List<Long>
   * @param destination List<Long>
   * @return inexistList List<Long>
   */
  public static List<Long> getInexistList(final List<Long> source, final List<Long> destination) {
    Set<Long> sourceSet = new HashSet<>(source);
    destination.forEach(
        e -> {
          sourceSet.remove(e);
        });
    return new ArrayList<>(sourceSet);
  }

  /**
   * Compare source and destination, if source elements do not exist in destination then return
   * those elements.
   *
   * @param source Set<Long>
   * @param destination List<Long>
   * @return inexistList List<Long>
   */
  public static List<Long> getInexistList(final Set<Long> source, final List<Long> destination) {
    Set<Long> result = source;
    destination.forEach(
        e -> {
          result.remove(e);
        });
    return new ArrayList<>(result);
  }
}
