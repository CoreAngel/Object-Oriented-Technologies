package filesystem.console;

import filesystem.node.directory.Directory;

public class Context {
    private Directory currentPath;
    private Console console;

    public Context(Directory currentPath, Console console) {
        this.currentPath = currentPath;
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Directory getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(Directory currentPath) {
        this.currentPath = currentPath;
        this.console.setCurrentPosition(currentPath);
    }

}
