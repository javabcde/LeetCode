import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOM
 * On 2020/5/3 17:06
 */
public class Solution53 {

  /**
   * 解题思路：
   * 示例: [a, b , c, d , e]
   *
   * 解答这类题目, 省略不掉遍历, 因此我们先从遍历方式说起
   *
   * 通常我们遍历子串或者子序列有三种遍历方式
   *
   * 以某个节点为开头的所有子序列: 如 [a]，[a, b]，[ a, b, c] ... 再从以 b 为开头的子序列开始遍历 [b] [b, c]。
   * 根据子序列的长度为标杆，如先遍历出子序列长度为 1 的子序列，在遍历出长度为 2 的 等等。
   * 以子序列的结束节点为基准，先遍历出以某个节点为结束的所有子序列，因为每个节点都可能会是子序列的结束节点，因此要遍历下整个序列，如: 以 b 为结束点的所有子序列: [a , b] [b] 以 c 为结束点的所有子序列: [a, b, c] [b, c] [ c ]。
   * 第一种遍历方式通常用于暴力解法, 第二种遍历方式 leetcode (5. 最长回文子串 ) 中的解法就用到了。
   *
   * 第三种遍历方式 因为可以产生递推关系, 采用动态规划时, 经常通过此种遍历方式, 如 背包问题, 最大公共子串 , 这里的动态规划解法也是以 先遍历出 以某个节点为结束节点的所有子序列 的思路
   *
   * 对于刚接触动态规划的, 我感觉熟悉第三种遍历方式是需要抓住的核心
   *
   * 因为我们通常的惯性思维是以子序列的开头为基准，先遍历出以 a 为开头的所有子序列，再遍历出以 b 为开头的...但是动态规划为了找到不同子序列之间的递推关系，恰恰是以子序列的结束点为基准的，这点开阔了我们的思路。
   *
   * 我在网上看不少解答时，直接阅读其代码，总是感觉很理解很吃力，因为好多没有写清楚，一些遍历到底代表什么意思，看了许久仍不知所以然，下面的代码中摘录了 维基中的解释，感觉比较清楚，供大家理解参考。
   * Kadane算法扫描一次整个数列的所有数值，
   * 在每一个扫描点计算以该点数值为结束点的子数列的最大和（正数和）。
   * 该子数列由两部分组成：以前一个位置为结束点的最大子数列、该位置的数值。
   * 因为该算法用到了“最佳子结构”（以每个位置为终点的最大子数列都是基于其前一位置的最大子数列计算得出,
   * 该算法可看成动态规划的一个例子。
   * 状态转移方程：sum[i] = max{sum[i-1]+a[i],a[i]}
   * 其中(sum[i]记录以a[i]为子序列末端的最大序子列连续和)
   *
   * 作者：lao-hu-8
   * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/xiang-xi-jie-du-dong-tai-gui-hua-de-shi-xian-yi-li/
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   *
   * @param args
   */
  public static void main(String[] args) {

    /*int[] ints = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(maxSubArray(ints));*/
    String s = "(abc)(def)(ghi)(jkl)";
    String[] b = s.split("\\)");
    List<String> sarray = new ArrayList<>();
    for (String s1 : b) {
      if (s1.indexOf('(') > -1) {
        sarray.add(s1.substring(s1.indexOf("(") + 1));
      }
    }
    List<String> search = new ArrayList<>();

    //tentoTwo(908);
    backtrack(search, sarray, 0, new StringBuffer());
    System.out.println(search.toString());

  }

/*  private static void tentoTwo(int i) {
    if (i % 2 == 0) {
      System.out.println(i + "取余2为0");
    } else if (i % 2 == 1) {
      System.out.println(i + "取余2为1");
    }
    if (i < 1) {
      return;
    }
    tentoTwo(i / 2);
  }

  public static int maxSubArray(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int res = nums[0];
    int pre = 0;
    for (int num : nums) {
      pre = Math.max(num, num + pre);
      res = Math.max(pre, res);
    }
    return res;
  }*/

  public static void backtrack(List<String> result, List<String> sarray, int index, StringBuffer combination) {
    if (index == sarray.size()) {
      result.add(combination.toString());
    } else {
      String letters = sarray.get(index);
      int lettersCount = letters.length();
      for (int i = 0; i < lettersCount; i++) {
        combination.append(letters.charAt(i));
        backtrack(result, sarray, index + 1, combination);
        combination.deleteCharAt(index);
      }
    }
  }
}
