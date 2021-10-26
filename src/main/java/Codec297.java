import baseEntity.TreeNode;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by TOM
 * On 2020/6/16 11:04
 */
public class Codec297 {

  private static final String spliter = ",";
  private static final String NN = "X"; //当做 null

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb);
    return sb.toString();
  }

  private void buildString(TreeNode node, StringBuilder sb) {
    if (node == null) {
      sb.append(NN).append(spliter);
    } else {
      sb.append(node.val).append(spliter);
      buildString(node.left, sb);
      buildString(node.right, sb);
    }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    Deque<String> nodes = new LinkedList<>(Arrays.asList(data.split(spliter)));
    return buildTree(nodes);
  }

  private TreeNode buildTree(Deque<String> nodes) {
    String val = nodes.remove();
    if (val.equals(NN)) {
      // 根节点不可能为NULL
      return null;
    } else {
      TreeNode node = new TreeNode(Integer.parseInt(val));
      node.left = buildTree(nodes);
      node.right = buildTree(nodes);
      return node;
    }
  }
}
