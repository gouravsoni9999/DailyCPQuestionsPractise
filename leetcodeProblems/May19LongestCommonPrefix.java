class Solution {
    public String longestCommonPrefix(String[] strs) {
        // TC : O(S) where S is count of all chars in all strs
        // SC : O(m) where m is the length of the longest common prefix
        StringBuilder ans = new StringBuilder();
        int n = strs.length;
        for(int i = 0;i < strs[0].length();i++){
            char ch = strs[0].charAt(i);
            for(int j = 1;j < n;j++){
                if(i >= strs[j].length() || ch != strs[j].charAt(i)){
                    return ans.toString();
                }else{
                    continue;
                }
            }
            ans.append(ch);
        }
        return ans.toString();
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        // let n strings
        // TC : O(nlog(n.m))+O(max(len1,len2)) where len1 is the length of strs[0] and len2 is the length of strs[n-1]
        // SC : O(k) where k is the length of longest common prefix
        // sort the array
        Arrays.sort(strs);
        // compare 1st and last string in strs
        int i = 0;
        StringBuilder ans = new StringBuilder();
        int len = strs.length;
        int m = strs[0].length();
        int n = strs[len-1].length();
        while(i < m && i < n){
            if(strs[0].charAt(i) == strs[len-1].charAt(i)){
                ans.append(strs[0].charAt(i));
            }else{
                break;
            }
            i++;
        }
        return ans.toString();
    }
}

class TrieNode{
    boolean isEndOfWord = false;
    TrieNode[] children = new TrieNode[26];
    int childCount = 0;
}
class Solution {
    private void insert(String str,TrieNode root){
        TrieNode crawler = root;
        for(char ch: str.toCharArray()){
            int idx = ch - 'a';
            if(crawler.children[idx] == null){
                crawler.childCount++;
                crawler.children[idx] = new TrieNode();
            }
            crawler = crawler.children[idx];
        }
        crawler.isEndOfWord = true; // for last char, mark the end of word
    }
    public String longestCommonPrefix(String[] strs) {
        // TC and SC is O(S+m) where S is no. of all chars in all strings in strs and m is no. of all chars in longest common prefix
        if(strs == null || strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        // using Trie DS
        TrieNode root = new TrieNode();
        // build Trie
        for(String str: strs){
            if(str.isEmpty()) return "";
            insert(str,root);
        }
        StringBuilder ans = new StringBuilder();
        // traverse Trie until you find out if a node contains multiple children
        TrieNode crawler = root;
        while(crawler != null){
            if(crawler.childCount != 1 || crawler.isEndOfWord == true){
                break;
            }else{
                for(int i = 0;i < 26;i++){
                    if(crawler.children[i] != null){
                        ans.append((char)(i+'a'));
                        crawler = crawler.children[i];
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
