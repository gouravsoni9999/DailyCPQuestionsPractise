class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        // if n words
        // TC : O(n)+O(u.log(k))+O(k) where u = no. of unique words in a map
        // SC : O(u)+O(k)
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // Maintain a minHeap of size k
        PriorityQueue<String> minHeap = new PriorityQueue<>((a,b)->{
            if(map.get(a).equals(map.get(b))){
                return b.compareTo(a);
            }
            else{
                return map.get(a)-map.get(b);
            }
        });

        for(String key: map.keySet()){
            minHeap.add(key);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        while(!minHeap.isEmpty()){
            list.add(minHeap.poll());
        }
        Collections.reverse(list);
        return list;
    }
}
