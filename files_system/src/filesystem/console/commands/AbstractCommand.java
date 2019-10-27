package filesystem.console.commands;

import filesystem.console.Console;

public class AbstractCommand {

    private Console context;

    public AbstractCommand(Console context) {
        this.context = context;
    }

    public Console getContext() {
        return this.context;
    }

}
