package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    public List<String> parseLine(String text) throws NumberFormatException {
        List<String> commands = new ArrayList<>(Arrays.asList(text.split(" ")));
        commands.removeIf(string -> string.equals(""));
        if (checkCommands(commands)) {
            return null;
        }
        return commands;
    }
    private boolean checkCommands(List<String> list) {
        return list.size() < 3;
    }
    public boolean numbersIsNull(Double n1, Double n2) {
        return n1 == null || n2 == null;
    }
    public Double parseNumber(List<String> list, int index) throws NumberFormatException {
        Double res;
        try {
            res = Double.parseDouble(list.get(index));
        } catch (NumberFormatException exception) {
            res = null;
        }
        return res;
    }
    public String parseOperator(List<String> commands, int index) {
        return commands.get(index);
    }
    public int getIndexPriorityOperator(List<String> commands) {
        int index;
        List<String> res = new ArrayList<>(commands);
        if (commands.contains("*") && commands.contains("/")) {
            index = Math.min(commands.indexOf("*"), commands.indexOf("/"));
            res.remove(res.get(index));
        } else if (commands.contains("*")) {
            index = commands.indexOf("*");
            res.remove(res.get(index));
        } else if (commands.contains("/")) {
            index = commands.indexOf("/");
            res.remove(res.get(index));
        } else {
            index = -1;
        }
        return index;
    }
}
