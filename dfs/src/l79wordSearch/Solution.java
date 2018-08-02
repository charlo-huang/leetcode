package l79wordSearch;

public class Solution {
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0)
			return false;
		int row = board.length, col = board[0].length;
		boolean[][] visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(dfs(board, 0, 0, word, 0, visited)) return true;
			}
		}
		return false;
	}

	private boolean dfs(char[][] board, int i, int j, String word, int idx, boolean[][] visited) {
		if (word.length() == idx)
			return true;
		if (i < 0 || i >= board.length || j < 0 || j > board[0].length || board[i][j] != word.charAt(idx))
			return false;

		// 4 neighbors
		visited[i][j] = true;
		boolean res = dfs(board, i - 1, j, word, idx + 1, visited) || dfs(board, i + 1, j, word, idx + 1, visited)
				|| dfs(board, i, j - 1, word, idx + 1, visited) || dfs(board, i, j + 1, word, idx + 1, visited);
		visited[i][j] = false;
		return res;
	}
}