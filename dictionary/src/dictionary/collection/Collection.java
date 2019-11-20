package dictionary.collection;

import java.util.ArrayList;

public class Collection extends Node {
    public Collection() {
        super(null);
    }

    public ArrayList<String> getValuesByString(String str, int limit) {
        ArrayList<String> list = new ArrayList<>();
        this.getValueByString(str, limit, list);
        return list;
    };

    public void addWord(String word) {
        this.addWordToNodes(word);
    }

}
