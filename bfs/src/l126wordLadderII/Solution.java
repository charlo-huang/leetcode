package l126wordLadderII;

import java.util.*;

public class Solution{
	
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> res = new ArrayList<List<String>>();

		Set<String> wordSet = new HashSet<>();
		for (String word : wordList) { // TODO LAMBDA: wordList.forEach(word -> wordSet.add(word)); DONE
			wordSet.add(word);
		}

		Queue<String> queue = new LinkedList<String>();
		HashMap<String, List<String>> graph = new HashMap<String, List<String>>();
		queue.offer(beginWord);
		boolean flag = false;
		while (!queue.isEmpty()) {
			Set<String> visitedThisLev = new HashSet<String>();
			int size = queue.size();
			while (size-- > 0) {
				String cur = queue.poll();
				List<String> nexts = convert(cur, wordSet);
				for (String str : nexts) {
					if (str.equals(endWord))
						flag = true;

					List<String> oneLvl = graph.getOrDefault(str, new LinkedList<String>());
					oneLvl.add(cur);
					graph.put(str, oneLvl);
					
					if (!visitedThisLev.contains(str)) {
						queue.offer(str);
						visitedThisLev.add(str);
					}
					
				}

			}
			wordSet.removeAll(visitedThisLev);
			
			if (flag) {
				List<String> one = new LinkedList<String>();
				one.add(endWord);
				search(res, one, endWord, beginWord, graph);
				return res;

			}
		}
		return res;
	}

	private List<String> convert(String cur, Set<String> wordSet) {
		List<String> nexts = new LinkedList<>();
		char cc[] = cur.toCharArray();
		for (int i = 0; i < cc.length; i++) {
			char temp = cc[i];
			for (char c = 'a'; c <= 'z'; c++) {
				cc[i] = c;
				String str = String.valueOf(cc);
				if (c != temp && wordSet.contains(str)) {
					nexts.add(String.valueOf(cc));
				}
			}
			cc[i] = temp;
		}
		return nexts;
	}

	// one: 1 possible path; cur: starting from endWord to beginWord; end: beginWord
	private void search(List<List<String>> res, List<String> onePath, String cur, String end,
			HashMap<String, List<String>> graph) {
		
		// copy list to res if cur hits beginWord
		if (cur.equals(end)) {
			List<String> copy = new LinkedList<String>(onePath);
			res.add(copy);
			return;
		}

		// prepare next, for: 1) add first 2) call dfs 3) set back needed
		List<String> next = graph.get(cur);
		for (String n : next) {
			onePath.add(0, n); // ((LinkedList<String>) one).addFirst(n);
			search(res, onePath, n, end, graph);
			onePath.remove(0); // or ((LinkedList<String>) one).removeFirst();
		}
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
		List<List<String>> res = s.findLadders("hit", "cog", wordList);
		res.forEach(i -> System.out.println(i));
		
	}



}
