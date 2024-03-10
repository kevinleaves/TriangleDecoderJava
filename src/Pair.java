public class Pair {
  private int number;
  private String word;

  public Pair(String line) {
    try {
      String[] splitLine = line.split(" ");
      this.number = Integer.parseInt(splitLine[0]);
      this.word = splitLine[1];
    } catch (NumberFormatException e) {
      System.out.println("Number Format Error " + e.getMessage());
      throw e;
    }
  }

  public int getNumber() {
    return this.number;
  }

  public String getWord() {
    return this.word;
  }

  public String toString() {
    return this.number + " " + this.word;
  }
}
