/**
 * Created by TOM
 * On 2022/12/8 19:46
 */
public class Solution1812 {
  public boolean squareIsWhite(String coordinates) {
    return (coordinates.charAt(0) + coordinates.charAt(1)) % 2 == 1;
  }
}
