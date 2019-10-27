package filesystem.node.directory;

class TreeOffsetDefine {
    private int offset;
    private int numberChildrenInNode;

    TreeOffsetDefine(int offset, int numberChildrenInNode) {
        this.offset = offset;
        this.numberChildrenInNode = numberChildrenInNode;
    }

    int getOffset() {
        return offset;
    }

    void reduceChildren() {
        if (this.numberChildrenInNode > 0) {
            this.numberChildrenInNode -= 1;
        }
    }

    boolean isEmpty() {
        return this.numberChildrenInNode == 0;
    }
}
