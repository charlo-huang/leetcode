package l286wallAndGate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Solution {

/*	public void wallsAndGates(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			throw new RuntimeException();
		Queue<Integer> queue = new LinkedList<>();
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					queue.offer(i * col + j);
				}
			}
		}

		int minLen = 1;
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int cur = queue.poll();
				int i = cur / col;
				int j = cur % col;
				for (int[] dir : directions) {
					int nextI = i + dir[0];
					int nextJ = j + dir[1];
					if (nextI >= 0 && nextI < row && nextJ >= 0 && nextJ < col
							&& matrix[nextI][nextJ] == Integer.MAX_VALUE) {
						queue.offer(nextI * col + nextJ);
						matrix[nextI][nextJ] = minLen;
					}
				}
			}
			minLen++;
		}
	}*/

	public void wallsAndGates(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			throw new RuntimeException();
		Queue<Integer> queue = new LinkedList<>();
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					queue.offer(i * col + j);
				}
			}
		}

		int minLen = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int cur = queue.poll();
				int[][] next = convert(cur, col, row, matrix);
				for (int[] n : next) {
					if (n != null && matrix[n[0]][n[1]] == Integer.MAX_VALUE) {
						queue.offer(n[0] * col + n[1]);
						matrix[n[0]][n[1]] = minLen;
					}
				}
			}
			minLen++;
		}
	}

	private int[][] convert(int cur, int col, int row, int[][] matrix) {
		int i = cur / col;
		int j = cur % col;
		int[][] directions = new int[4][];
		
		if (i > 0)
			directions[0] = new int[] { i - 1, j };
		if (i + 1 < row)
			directions[1] = new int[] { i + 1, j };
		if (j > 0)
			directions[2] = new int[] { i, j - 1 };
		if (j + 1 < col)
			directions[3] = new int[] { i, j + 1 };
		
		return directions;
	}

	public static void main(String[] args) {
		 int[][] matrix = { { 2147483647, -1, 0, 2147483647 }, { 2147483647,
		 2147483647, 2147483647, -1 },
		 { 2147483647, -1, 2147483647, -1 }, { 0, -1, 2147483647, 2147483647 } };
		//int[][] matrix = null;
		Solution s = new Solution();
		try {
			s.wallsAndGates(matrix);
		} catch (Exception e) {
			throw new RuntimeException("msg"); // Runtime is unchecked. checked need throw or try catch
		}
		s.wallsAndGates(matrix);
		System.out.println(Arrays.deepToString(matrix));
		//how to use random: https://www.geeksforgeeks.org/generating-random-numbers-in-java/
		int rand = new Random().nextInt(5);
		System.out.println(rand);
	}
}