/**
with regex */
class Solution {
    public String interpret(String command) {
        return command.replaceAll("\\(\\)", "o").replaceAll("\\(al\\)", "al");
    }
}

/**
my own solu */
class Solution {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                sb.append("G");
            } else if (command.charAt(i+1) == 'a') {
                sb.append("al");
                i += 3;
            } else {
                sb.append("o");
                i += 1;
            }
        }
        return sb.toString();
    }
}