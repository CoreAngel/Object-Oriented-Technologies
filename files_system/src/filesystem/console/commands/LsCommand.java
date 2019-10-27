package filesystem.console.commands;

import filesystem.console.Console;

public class LsCommand extends AbstractCommand implements ICommand {
    public LsCommand(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        this.getContext().getCurrentPosition().printNodes();
    }
}
