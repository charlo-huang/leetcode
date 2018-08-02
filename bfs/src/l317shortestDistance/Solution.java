package l317shortestDistance;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	public int shortestDistance(int[][] grid) {
		// corner case return -1
		
		// need to count building #. 
		int row = grid.length, col = grid[0].length;
		int[][] cost = new int[row][col];
		int bldgNum = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					bldgNum++;
				}
			}
		}
		// bfs each building, fill in & update distance value at every empty spot
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 1) {
					int num = bfs(i, j, cost, grid);
					if (num != bldgNum)
						return -1; // if this building can not reach all other buildings
				}
			}
		}
		
		// traverse grid to find the min distance
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (grid[i][j] == 0 && cost[i][j] != 0) { // *** !=0 means visited(value has been updated)
					min = Math.min(min, cost[i][j]);
				}
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	private int bfs(int i, int j, int[][] cost, int[][] grid) {
		Queue<int[]> queue = new LinkedList<>();
		int row = grid.length, col = grid[0].length;
		boolean[][] visited = new boolean[row][col];
		queue.offer(new int[] { i, j });
		visited[i][j] = true; // ***

		int minLen = 1, bldgNum = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				int[] cur = queue.poll();
				List<int[]> nexts = convert(cur, grid); // only check boundary
				for (int[] next : nexts) {
					int rowIdx = next[0], colIdx = next[1];
					if (!visited[rowIdx][colIdx] && grid[rowIdx][colIdx] == 0) {
						queue.offer(new int[] { rowIdx, colIdx });
						cost[rowIdx][colIdx] += minLen;
						visited[rowIdx][colIdx] = true;
					}
					
					// keep bldgNum counter
					if (!visited[rowIdx][colIdx] && grid[rowIdx][colIdx] == 1) {
						bldgNum++;
						visited[rowIdx][colIdx] = true;
					}
				}
			}
			minLen++;
		}
		
		// post-processing: optimize searching by skipping
		for (int idxI = 0; idxI < row; idxI++) {
			for (int idxJ = 0; idxJ < col; idxJ++) {
				if (!visited[idxI][idxJ] && grid[idxI][idxJ] == 0) {
					grid[idxI][idxJ] = 2;
				}
			}
		}

		return bldgNum;
	}

	private List<int[]> convert(int[] cur, int[][] grid) {
		int row = grid.length, col = grid[0].length;
		int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		List<int[]> nexts = new LinkedList<>();
		for (int[] dir : directions) {
			int rowIdx = cur[0] + dir[0], colIdx = cur[1] + dir[1];
			if (rowIdx >= 0 && rowIdx < row && colIdx >= 0 && colIdx < col) {
				nexts.add(new int[] { rowIdx, colIdx });
			}
		}
		return nexts;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
		Solution s = new Solution();
		int min = s.shortestDistance(matrix);
		System.out.println(min);
	}

}
