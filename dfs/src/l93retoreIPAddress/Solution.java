package l93retoreIPAddress;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Solution {
	public List<String> restoreIpAddresses(String s) {
		if (s == null || s.length() == 0)
			// throw new IllegalArgumentException("String is null or empty");
			return new LinkedList<>();

		try {
			new BigInteger(s);
		} catch (IllegalArgumentException e) {
			System.out.println("String: " + s + " can't convert to Integer");
		}
		
		// invalid: 012; ip length > 12
		if (s.charAt(0) == '0' || s.length() > 12)
			return new LinkedList<>();
			//throw new IllegalArgumentException("invalid ip");
			
		List<String> res = new LinkedList<>();
		dfs(res, new StringBuilder(), s, 0, 0);
		return res;
	}

	// idx: the first index of a byte; n: the byte position of ip 0~3
	private void dfs(List<String> res, StringBuilder path, String s, int idx, int n) {
		if (n == 4 && idx == s.length()) {
			res.add(path.toString());
			return;
		}
		
		// pruning invalid ip
		if (n > 4) // || idx + len > s.length() already checked
			return;

		// construct number which len 1->3
		// idx 0 1 2 -> idx+len 1 2 3-> last pos is string length!!
		for (int len = 1; len <= 3; len++) { 
			if (idx + len > s.length()) // pruning illegal for input length
				break;
			int val = Integer.valueOf(s.substring(idx, idx + len)); 
			// int val = Integer.parseInt(s.substring(idx, idx + len));

			int size = path.length();
			if (val < 256) {
				if (n == 0)
					path.append(val);
				if (n > 0)
					path.append("." + val);
				dfs(res, path, s, idx + len, n + 1);
				path.setLength(size);
			}
			
			// avoid ip start with 0 (exclusive), like 01, 012, but 0 is ok
			if (val == 0)
				break;
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		// System.out.println(s.restoreIpAddresses("abc"));
		// System.out.println(s.restoreIpAddresses("a23"));
		// System.out.println(s.restoreIpAddresses("12a3456123123"));
		 System.out.println(s.restoreIpAddresses("1231231231"));
	}
}
