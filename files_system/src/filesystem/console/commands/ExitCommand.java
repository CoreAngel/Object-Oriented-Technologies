package filesystem.console.commands;

import filesystem.console.Console;

public class ExitCommand extends AbstractCommand implements ICommand {

    public ExitCommand(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        this.getContext().stopConsole();
    }
}
