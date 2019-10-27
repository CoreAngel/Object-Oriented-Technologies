package filesystem.node.file;

import filesystem.node.Node;
import filesystem.node.directory.Directory;

public class File extends Node {
    private byte[] content;

    public File(String name, Directory path) {
        super(name, path);
        this.setContent(new byte[]{});
        path.add(this);
    }

    public File(String name, byte[] data, Directory path) {
        super(name, path);
        this.setContent(data);
        path.add(this);
    }

    public void setContent(byte[] data) {
        this.content = data;
    }

    public byte[] getContent() {
        return this.content;
    }
}
