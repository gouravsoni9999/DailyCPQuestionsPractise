class RandomizedSet {
    // TC : O(1) for every operation
    // SC : O(n)
    List<Integer> list;
    Map<Integer, Integer> map;//stores <no.,idx>
    Random rand; //declare random object variable
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)) 
            return false;//already present
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }
    
    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;//not present
        int idx = map.get(val);//access the idx which we have to remove
        int lastElement = list.get(list.size()-1);

        // move last element to the spot of the element to delete
        list.set(idx, lastElement);
        map.put(lastElement, idx);

        // delete last element
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }
    
    public int getRandom() {
        int n = list.size();
        int randomIdx = rand.nextInt(n); // gives idx 0..n-1
        return list.get(randomIdx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
