package filesystem.node;

import filesystem.node.directory.Directory;
import filesystem.node.directory.RootDirectory;
import filesystem.node.verify.Verifier;
import filesystem.node.verify.VerifyException;

public abstract class Node {
    private String name;
    private Directory path;

    public Node(String name, Directory path) throws VerifyException {
        Verifier.verifyName(name);

        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public Directory getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(Directory path) throws VerifyException {
        Directory tmp = this.path;
        this.path = path;
        try {
            Verifier.verifyPath(path);
        } catch (VerifyException e) {
            this.path = tmp;
            throw e;
        }

    }
}
