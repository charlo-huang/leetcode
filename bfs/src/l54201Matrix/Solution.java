package l54201Matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	public int[][] updateMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			return null;
		int row = matrix.length, col = matrix[0].length;
		int[][] res = new int[row][col];
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (matrix[i][j] == 0) {
					queue.offer(new int[] { i, j });
				}
			}
		}
		int minLen = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] cur = queue.poll();
				List<int[]> nexts = convert(cur, matrix); // check boundary inside convert
				for (int[] next : nexts) {
					int i = next[0], j = next[1];
					if (matrix[i][j] == 1 && res[i][j] == 0) {
						res[i][j] = minLen;
					}
				}
			}
			minLen++;
		}
		return res;
	}

	private List<int[]> convert(int[] cur, int[][] matrix) {
		int row = matrix.length, col = matrix[0].length;
		List<int[]> nexts = new LinkedList<>();
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int[] dir : dirs) {
			int i = cur[0] + dir[0], j = cur[1] + dir[1];
			if (i >= 0 && i < row && j >= 0 && j < col) {
				nexts.add(new int[] { i, j });
			}
		}
		return nexts;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
