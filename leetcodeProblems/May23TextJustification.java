class Solution {
    private String findLine(int i,int j,int spaceBetweenWords,int remainingSpace,String[] words,int maxWidth){
        StringBuilder line = new StringBuilder();
        for(int k = i;k < j;k++){
            // take every word in range [i,j)
            line.append(words[k]); // append words[k] in line

            if(k == j-1){
                // last word of line: no need to append extra spaces after this
                continue;
            }
            for(int z = 1;z <= spaceBetweenWords;z++){
                line.append(" ");
            }
            if(remainingSpace > 0){
                line.append(" ");
                remainingSpace--;
            }
        }
        while(line.length() < maxWidth){
            line.append(" ");
        }
        return line.toString();
    }
    public List<String> fullJustify(String[] words, int maxWidth) {
	// TC : O(n * maxWidth) where O(n) comes from outer while loop
	// and O(maxWidth) multiplied to build the line using stringBuilder
	// SC : let m no. of lines in list output: therefore O(m * maxWidth)
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;
        while(i < n){
            int lettersCnt = words[i].length(); // took the ith word
            int j = i+1;// see for next j's can they accomodate in line
            int spaces = 0;
            // below we take 1 becoz of 1 space
            while(j < n && lettersCnt+1+words[j].length()+spaces <= maxWidth){
                lettersCnt += words[j].length(); // we can take j
                spaces++;// 1 space was added in line
                j++;
            }
            
            // how many characters are remaining for that line to be completely filled
            int remainingSlots = maxWidth - lettersCnt;

            
            int spaceBetweenWords = (spaces == 0)?0:remainingSlots/spaces;

            // even after evenly distributing spaces, some more spaces are remaining
            int remainingSpace = (spaces == 0) ? 0 : remainingSlots % spaces;

            if(j == n){
                // last line
                // left justified
                spaceBetweenWords = 1;
                remainingSpace = 0;
            }
            result.add(findLine(i,j,spaceBetweenWords, remainingSpace, words, maxWidth));
            i = j; // for next line, start from j
        }
        return result;
    }
}
