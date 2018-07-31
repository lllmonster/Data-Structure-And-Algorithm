import java.util.*;
// "static void main must be defined in a public class"
// 2D Array
public class text {
	public static void main(String[] args) {
		System.out.println("Example 1");
		int[][] a = new int[2][5];
		printArray(a);
		System.out.println("Example 2");
		int[][] b = new int[2][];
		printArray(b);
		System.out.println("Example 3");
		b[0] = new int[3];
		b[1] = new int[5];
		printArray(b);
	}
}
// Dynamic Array
public class test {
	public static void main(String[] args) {
		// 1. initialize
		List<Integer> v0 = new ArrayList<>();
		List<Integer> v1; // v1 == null
		// 2. cast an array to a vector
		Integer[] a = {0,1,2,3,4};
		v1 = new ArrayList<>(Arrays.asList(a));
		// 3. make a copy
		List<Integer> v2 = v1; // another reference to v1
		List<integer> v3 = new ArrayList<>(v1); // make an actual copy of v1
		// 4. get length
		System.out.println("The size of v1 is: " + v1.size());
		// 5. access element
		System.out.println("The first element in v1 is: " + v1.get(0));
		// 6. iterate the vector
		System.out.print("Version1, the contents of v1 are: ");
		for (int i = 0; i < v1.size(); i++) {
			System.out.println(" " + v1.get(i));
		}
		System.out.println();
		System.out.print("Version2, the contents of v1 are: ");
		for (int item : v1) {
			System.out.println(" " + item);
		}
		System.out.println();
		// 7. modify element
		v2.set(0, 5); // modify v2 will actually modify v1
		System.out.println("The first element in v1 is: " + v1.get(0));
		v3.set(0, -1); // modify v3 will not modify v1
		System.out.println("The first element in v1 is: " + v1.get(0));
		// 8. sort
		Collections.sort(v1);
		// 9. add new element at the end of the vector
		v1.add(-1);
		v1.add(1,6);
		// 10. delete the last element
		v1.remove(v1.size() - 1);
	}
}
//Array
public class Main {
  public static void main(String[] args) {
    // 1. initialize
    int[] a0 = new int[5];
    int[] a1 = {1,2,3};
    // 2. Get Length
    System.out.println("The size of a1 is: " + a1.length);
    // 3. Access the elements
    System.out.println("The first element is: " + a1[0]);
    // 4. Iterate all elements
    System.out.print("Version1, the contents of a1 are: ");
    for (int i = 0; i < a1.length; i++) {
      System.out.print(" " + a1[i]);
    }
    System.out.println();
    System.out.print("Version2, the contents of a1 are: ");
    for(int item: a1) {
      System.out.print(" " + item);
    }
    System.out.println();
    // 5. Modify element
    a1[0] = 4;
    // 6. Sort
    Arrays.sort(a1);
  }
}