import java.util.*;
import java.util.Queue;
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // TC : O(m.n.n.26)
        // SC : O(m.n) where m is wordList.size() 
        int n = beginWord.length();
        // make a set of all words in wordList
        Set<String> set = new HashSet<>();
        for (String str : wordList) {
            set.add(str);
        }

        if (!set.contains(endWord)) {
            return 0; // endWord is not in wordList
        }

        Queue<String> que = new LinkedList<>();
        que.add(beginWord);
        Map<String, Integer> map = new HashMap<>();
        map.put(beginWord, 1); // 1st level 

        set.remove(beginWord); // set removes startWord, as it is not required(and can hamper later if not removed)

        // using BFS
        while (!que.isEmpty()) {
            // find all elements in that level, and then operate 
            for (int size = que.size(); size > 0; size--) {
                String word = que.poll();
                int level = map.get(word);

                if(word.equals(endWord))
                    return level;
                
                for(int i = 0;i < n;i++){
                    StringBuilder sb = new StringBuilder(word);
                    char og = word.charAt(i);
                    for(char ch = 'a';ch <= 'z';ch++){
                        sb.setCharAt(i, ch);
                        String str = sb.toString();
                        if(set.contains(str)){
                            set.remove(str);
                            que.add(str);
                            map.put(str, level+1);
                        }
                    }
                }
                
            }
        }

        return 0;

    }
}