package l127wordLadder;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (beginWord == null || endWord == null || wordList == null) return 0;
		
		Set<String> wordSet = new HashSet<>();
		for (String word : wordList) {
			wordSet.add(word);
		}
		if(!wordSet.contains(endWord)) return 0;
		
		Queue<String> queue = new LinkedList<>();
		queue.offer(beginWord);
		int minLen = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) { // when size == 0 -> won't enter, after this evaluation -> size == -1
				String cur = queue.poll();
				if (cur.equals(endWord)) {
					return minLen;
				}
				// convert: find the next converted words that are contained in wordList
				List<String> nexts = convert(cur, wordSet);
				for (String next : nexts) {
					queue.offer(next);
					wordSet.remove(next);
				}

			}
			minLen++;
		}
		return 0;
	}

	private List<String> convert(String cur, Set<String> wordSet) {
		List<String> nexts = new LinkedList<>();
		char[] curChars = cur.toCharArray();
		
		for (int i = 0; i < curChars.length; i++) {
			char tmp = curChars[i];
			for (char c = 'a'; c <= 'z'; c++) { // WRONG: c <= 'z' && c != tmp; meaning exit for loop if condition fails!!
				curChars[i] = c;
				String str = String.valueOf(curChars);
				if (c != tmp && wordSet.contains(str)) {
					nexts.add(str);
				}
			}
			curChars[i] = tmp;
		}
		return nexts;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		List<String> wordList = new LinkedList<>();
		// ["hot","dot","dog","lot","log","cog"]
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		int num = s.ladderLength("hit", "cog", wordList);
		System.out.println(num);
		/*
		 * Create List and add ele of sub types of declared type: List<Number>
		 * linkedNumbers = new LinkedList<>(); linkedNumbers.add(new Integer(123));
		 * linkedNumbers.add(new Float(3.1415)); linkedNumbers.add(new Double(299.988));
		 * linkedNumbers.add(new Long(67000));
		 */

	}
}