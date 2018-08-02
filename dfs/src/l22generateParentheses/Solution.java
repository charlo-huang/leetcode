package l22generateParentheses;

import java.util.LinkedList;
import java.util.List;

public class Solution {
	public List<String> generateParenthesis(int n) {
		if (n < 0)
			throw new RuntimeException();
		if (n == 0)
			return new LinkedList<>();

		List<String> res = new LinkedList<>();
		dfs(res, new StringBuilder(), 0, 0, n);
		return res;
	}

	private void dfs(List<String> res, StringBuilder path, int left, int right, int n) {
		if (left == n && right == n) {
			res.add(path.toString()); // toString() is to deep copy(return a String)
			return;
		}

		if (left < right || left > n) {
			return;
		}

		// left parenthesis
		//if (left < n) {
			path.append("(");
			dfs(res, path, left + 1, right, n);
			path.setLength(path.length() - 1); // or path.deleteCharAt(..)
		//}
		// right parenthesis
		//if (left > right) {
			path.append(")");
			dfs(res, path, left, right + 1, n);
			path.setLength(path.length() - 1);
		//}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.generateParenthesis(3));
	}

}
