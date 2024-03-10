import java.util.Comparator;

public class PairComparator implements Comparator<Pair> {
  @Override
  public int compare(Pair pair1, Pair pair2) {
    // Compare based on the number field of Pair objects
    return Integer.compare(pair1.getNumber(), pair2.getNumber());
  }
}
