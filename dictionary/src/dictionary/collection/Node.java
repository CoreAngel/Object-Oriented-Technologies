package dictionary.collection;

import dictionary.aggregator.Aggregator;
import dictionary.aggregator.Pair;

import java.util.ArrayList;
import java.util.HashMap;

class Node {

    private Integer value;
    private boolean isWord = false;
    private Aggregator<Integer, Node> nodes = new Aggregator<>();
//    private HashMap<Integer, Node> nodes = new HashMap<>();

    public Node(Integer value) {
        this.value = value;
    }

    public void setIsWord(boolean word) {
        isWord = word;
    }

    void addWordToNodes(String value) {
        if (!value.isEmpty()) {
            Integer firstCharAsInt = (int)value.charAt(0);
            String restOfString = value.substring(1);
            Node node = nodes.getValueByKey(firstCharAsInt);
            if (node == null) {
                Node newNode = new Node(firstCharAsInt);
                if (restOfString.isEmpty()) {
                    newNode.setIsWord(true);
                } else {
                    newNode.addWordToNodes(restOfString);
                }
                nodes.add(firstCharAsInt, newNode);
            } else {
                node.addWordToNodes(restOfString);
            }
        }

    }

    void getValueByString(String enteredWord, Integer limit, ArrayList<String> findedString) {
        if (!enteredWord.isEmpty()) {
            String createdString = enteredWord.substring(0, enteredWord.length() - 1);
            this.getValueByString(enteredWord, limit, findedString, createdString);
        }

    };

    private void getValueByString(String enteredWord, Integer limit, ArrayList<String> findedString, String createdString) {
        if (enteredWord.isEmpty()) {
            String newCreatedString = createdString + (char)((int)this.value);

            if (this.isWord) {
                findedString.add(newCreatedString);
            }

            if (!this.limitHasReached(limit, findedString)) {
                for (Pair<Integer, Node> value : nodes) {
                    Node node = value.getValue();
                    node.getValueByString(enteredWord, limit, findedString, newCreatedString);
                    if (this.limitHasReached(limit, findedString)) {
                        break;
                    }
                }
            }
        } else {
            Integer firstCharAsInt = (int)enteredWord.charAt(0);
            String restOfString = enteredWord.substring(1);
            Node node = nodes.getValueByKey(firstCharAsInt);
            if (node != null) {
                node.getValueByString(restOfString, limit, findedString, createdString);
            }
        }
    };

    private boolean limitHasReached(int oldLimit, ArrayList<String> findedString) {
        return oldLimit - findedString.size() <= 0;
    }
}
