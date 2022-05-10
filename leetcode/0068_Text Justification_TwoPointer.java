/**
MyIdea -> find each line should be, and justify each line
Optimized Idea -> find each left n right point. would be make program easier.
 */


class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        
        int left = 0;
        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            res.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        
        return res;
    }
    
    private int findRight(int left, String[] words, int maxWidth) {
        int len = 0;
        while (left < words.length && len + words[left].length() <= maxWidth) {
            len = len + 1 + words[left].length();
            left++;
        }
        return len==0 ? left : left-1;
    }
    
    private String justify(int left, int right, String[] words, int maxWidth) {
        if (left == right) {
            return words[left] + getBlank(maxWidth - words[left].length());
        }
        
        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);
        
        String space = isLastLine ? " " : getBlank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces;
        
        StringBuilder result = new StringBuilder();
        for (int i = left; i <= right; i++)
            result.append(words[i])
                .append(space)
                .append(remainder-- > 0 ? " " : "");
        return result.toString().trim() + getBlank(maxWidth - result.toString().trim().length());
    }
    
    private String getBlank(int length) {
        return new String(new char[length]).replace('\0',' ');
    }
    
    private int wordsLength(int left, int right, String[] words) {
        int wordsLength = 0;
        for (int i = left; i <= right; i++) wordsLength += words[i].length();
        return wordsLength;
    }
}
/**
====================================================================================
 */
class MySolution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        
        int idx = 0;
        while (idx < words.length) {
            int len = 0;
            int start = idx;
            List<String> tmp = new ArrayList<>();
            while (start < words.length && len + words[start].length() <= maxWidth) {
                tmp.add(words[start]);
                len = len + 1 + words[start].length();
                start++;
            }
            res.add(justify(tmp, (start >= words.length), maxWidth));
            idx = start;
        }
        return res;
    }
    
    private String justify(List<String> list, boolean lastLine, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        if (!lastLine) {
            int wordLength = 0;
            for (String w : list) {
                wordLength += w.length();
            }
            if (list.size() == 1) {
                sb.append(list.get(0));
                sb.append(getBlank(maxWidth - wordLength));
                return sb.toString();
            }
            int blankLength = (maxWidth - wordLength) / (list.size()-1);
            int offset = (maxWidth - wordLength) % (list.size()-1);
            sb.append(list.get(0));
            if (offset != 0) {
                sb.append(getBlank(blankLength+1));
                offset--;
            } else {
                sb.append(getBlank(blankLength));
            }
            for (int i = 1; i < list.size(); i++) {
                sb.append(list.get(i));
                if (i != list.size() - 1) {
                    if (offset != 0) {
                        sb.append(getBlank(blankLength+1));
                        offset--;
                    } else {
                        sb.append(getBlank(blankLength));
                    }
                }
            }
            
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (i != list.size()-1) {
                    sb.append(list.get(i));
                    sb.append(" ");
                } else {
                    sb.append(list.get(i));
                }
            }
            
            sb.append(getBlank(maxWidth - sb.toString().length()));
        }
        
        return sb.toString();
    }
    
    private String getBlank(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {sb.append(" ");}
        return sb.toString();
    }
}