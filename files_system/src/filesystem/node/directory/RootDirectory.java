package filesystem.node.directory;

import filesystem.node.Node;

public class RootDirectory extends Directory {

    public RootDirectory(String name) {
        super(name, null);
    }

    @Override
    public void setPath(Directory node) throws SetPathRootExecption {
        throw new SetPathRootExecption();
    }

}
