import java.util.*;
import java.util.Queue;
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        // TC : O(N.M.26) -> We check 26 substitutions for all M characters across N words.
        // SC : O(N.N.M)  -> The queue stores deep copies of entire word lists rather than just individual words.
        // TLE

        List<List<String>> ans = new ArrayList<>(); // stores answer
        Queue<List<String>> que = new LinkedList<>();
        List<String> list = new ArrayList<>();

        list.add(beginWord);
        que.add(new ArrayList<>(list));

        List<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);

        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }

        // if the endWord is not in the wordSet, no path exist 
        if (!wordSet.contains(endWord)) {
            return ans;
        }

        while (!que.isEmpty()) {
            // processing current level
            for (int size = que.size(); size > 0; size--) {
                List<String> listFromQue = que.poll();
                String lastWord = listFromQue.get(listFromQue.size() - 1);

                if (lastWord.equals(endWord)) {
                    ans.add(listFromQue);
                    continue;
                }

                StringBuilder str = new StringBuilder(lastWord);
                for (int i = 0; i < str.length(); i++) {
                    char og = str.charAt(i);
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        str.setCharAt(i, ch);
                        String newWord = str.toString();

                        if (wordSet.contains(newWord)) {
                            usedOnLevel.add(newWord);
                            listFromQue.add(newWord);
                            que.add(new ArrayList<>(listFromQue));
                            listFromQue.remove(listFromQue.size() - 1);
                        }
                    }
                    str.setCharAt(i, og);
                }

            }

            // remove all usedOnLevel elements and clear it(only after a level completes)
            for (String string : usedOnLevel) {
                wordSet.remove(string);
            }
            usedOnLevel.clear();

            // If we found any valid path to endWord at this level, we don't need to check deeper levels (this is done only to record shortest transformation sequences)
            if (ans.size() > 0) {
                break;
            }
        }

        return ans;
    }
}

class OptimizedSolution {
    Map<String, Integer> map;
    List<List<String>> ans;
    int wordLen;

    private void dfs(String word, String beginWord, List<String> list) {
        if (word.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(list);
            Collections.reverse(validPath); // Reverse copy to preserve backtracking list
            ans.add(validPath);
            return;
        }

        int steps = map.get(word);
        StringBuilder sb = new StringBuilder(word);

        for (int i = 0; i < wordLen; i++) {
            char og = word.charAt(i);

            for (char ch = 'a'; ch <= 'z'; ch++) {
                sb.setCharAt(i, ch);
                String newWord = sb.toString();

                // Move backward: look for neighbors that are exactly 1 step closer to beginWord
                if (map.containsKey(newWord) && map.get(newWord) == steps - 1) {
                    list.add(newWord);
                    dfs(newWord, beginWord, list);
                    list.remove(list.size() - 1); // backtrack
                }
            }

            sb.setCharAt(i, og);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // optimal solution (CP way)

        ans = new ArrayList<>(); // stores answer

        Set<String> wordSet = new HashSet<>();
        for (String str : wordList) {
            wordSet.add(str);
        }

        map = new HashMap<>(); // stores {word, level}
        Queue<String> que = new LinkedList<>();

        // Initialization
        map.put(beginWord, 1);
        que.add(beginWord);
        wordSet.remove(beginWord);
        wordLen = beginWord.length();


        // BFS level by level
        while (!que.isEmpty()) {
            
            String word = que.poll();
            if (word.equals(endWord)) {
                break;
            }
            int steps = map.get(word);
            StringBuilder sb = new StringBuilder(word);
            for (int i = 0; i < wordLen; i++) {
                char og = word.charAt(i);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sb.setCharAt(i, ch);
                    String newWord = sb.toString();
                    if (wordSet.contains(newWord)) {
                        que.add(newWord);
                        map.put(newWord, steps + 1);
                        wordSet.remove(newWord);
                    }
                }
                sb.setCharAt(i, og);
            }
        }

        // Backtrack using DFS if a path to endWord exists
        if (map.containsKey(endWord)) {
            List<String> list = new ArrayList<>();
            list.add(endWord);
            dfs(endWord, beginWord, list);
        }

        return ans;
    }
}