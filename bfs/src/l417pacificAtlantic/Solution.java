package l417pacificAtlantic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	public List<int[]> pacificAtlantic(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			return new LinkedList<int[]>(); //****clarify

		Queue<int[]> queue = new LinkedList<>();
		List<int[]> res = new LinkedList<>();
		int row = matrix.length, col = matrix[0].length;
		boolean[][] pacific = new boolean[row][col];
		boolean[][] atlantic = new boolean[row][col];

		// top, from pacific
		for (int j = 0; j < col; j++) {
			queue.offer(new int[] { 0, j });
			pacific[0][j] = true;
		}

		// left
		for (int i = 1; i < row; i++) { // ***from 1
			queue.offer(new int[] { i, 0 });
			pacific[i][0] = true;
		}

		bfs(queue, matrix, pacific, atlantic, res);

		// bottom
		for (int j = 0; j < col; j++) {
			queue.offer(new int[] { row - 1, j });
			atlantic[row - 1][j] = true;
		}

		// right
		for (int i = 0; i < row - 1; i++) {
			queue.offer(new int[] { i, col - 1 });
			atlantic[i][col - 1] = true;
		}

		bfs(queue, matrix, atlantic, pacific, res);

		return res;

	}

	private void bfs(Queue<int[]> queue, int[][] matrix, boolean[][] selfSet, boolean[][] otherSet, List<int[]> res) {
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			// check overlap
			if (otherSet[cur[0]][cur[1]]) {
				res.add(new int[] { cur[0], cur[1] });
			}

			List<int[]> nexts = convert(cur, matrix); // convert to 4 directions and check boundaries
			for (int[] next : nexts) {
				int i = next[0], j = next[1];
				if (matrix[cur[0]][cur[1]] <= matrix[i][j] && !selfSet[i][j]) {
					queue.offer(new int[] { i, j });
					selfSet[i][j] = true;
				}
			}
		}
	}

	private List<int[]> convert(int[] cur, int[][] matrix) {
		List<int[]> nexts = new LinkedList<>();
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int row = matrix.length, col = matrix[0].length;
		for (int[] dir : dirs) {
			int i = cur[0] + dir[0], j = cur[1] + dir[1];
			if (i >= 0 && i < row && j >= 0 && j < col) { //*******>=0
				nexts.add(new int[] { i, j });
			}
		}
		return nexts;
	}

}
