package dsa;

/**
 * 并查集 基于重量(子节点的数量)
 * Created by TOM
 * On 2020/2/18 22:46
 */
public class UnionFindWeight {

  private int[] parent;
  private int[] weight;
  private int size;

  public UnionFindWeight(int size) {
    this.parent = new int[size];
    this.weight = new int[size];
    this.size = size;
    for (int i = 0; i < size; i++) {
      this.parent[i] = i;
      this.weight[i] = 1;
    }
  }

  public int find(int element) {
    while (element != parent[element]) {
      //路径压缩 将当前节点的父类改为父类的父类
      parent[element] = parent[parent[element]];
      //
      element = parent[element];
    }
    return element;
  }

  public boolean isConnected(int firstElement, int secondElement) {
    return find(firstElement) == find(secondElement);
  }

  public void unionElements(int firstElement, int secondElement) {
    int firstRoot = find(firstElement);
    int secondRoot = find(secondElement);

    //如果已经属于同一个集合了，就不用再合并了。
    if (firstRoot == secondRoot) {
      return;
    }

    if (weight[firstRoot] > weight[secondRoot]) {
      parent[secondRoot] = firstRoot;
      weight[firstRoot] += weight[secondRoot];
    } else {//weight[firstRoot] <= weight[secondRoot]
      parent[firstRoot] = secondRoot;
      weight[secondRoot] += weight[firstRoot];
    }
  }

  private void printArr(int[] arr) {
    for (int p : arr) {
      System.out.print(p + "\t");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int n = 10;
    UnionFindWeight union = new UnionFindWeight(n);

    System.out.println("初始parent：");
    union.printArr(union.parent);
    System.out.println("初始weight：");
    union.printArr(union.weight);

    System.out.println("连接了5 6 之后的parent：");
    union.unionElements(5, 6);
    union.printArr(union.parent);
    System.out.println("连接了5 6 之后的weight：");
    union.printArr(union.weight);

    System.out.println("连接了1 2 之后的parent：");
    union.unionElements(1, 2);
    union.printArr(union.parent);
    System.out.println("连接了1 2 之后的weight：");
    union.printArr(union.weight);

    System.out.println("连接了2 3 之后的parent：");
    union.unionElements(2, 3);
    union.printArr(union.parent);
    System.out.println("连接了2 3 之后的weight：");
    union.printArr(union.weight);

    System.out.println("连接了1 4 之后的parent：");
    union.unionElements(1, 4);
    union.printArr(union.parent);
    System.out.println("连接了1 4 之后的weight：");
    union.printArr(union.weight);

    System.out.println("连接了1 5 之后的parent：");
    union.unionElements(1, 5);
    union.printArr(union.parent);
    System.out.println("连接了1 5 之后的weight：");
    union.printArr(union.weight);

    System.out.println("1  6 是否连接：" + union.isConnected(1, 6));

    System.out.println("1  8 是否连接：" + union.isConnected(1, 8));
  }
}
