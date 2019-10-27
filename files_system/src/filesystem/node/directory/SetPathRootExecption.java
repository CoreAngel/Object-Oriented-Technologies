package filesystem.node.directory;

public class SetPathRootExecption extends RuntimeException {
    SetPathRootExecption() {
        super("You cannot set path for root element");
    }
}
