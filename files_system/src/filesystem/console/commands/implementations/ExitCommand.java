package filesystem.console.commands.implementations;

import filesystem.console.Context;
import filesystem.console.commands.AbstractCommand;
import filesystem.console.commands.ICommand;

public class ExitCommand extends AbstractCommand implements ICommand {

    public ExitCommand(Context context) {
        super(context);
    }

    @Override
    public void run(String params) {
        this.getContext().getConsole().stopConsole();
    }
}
