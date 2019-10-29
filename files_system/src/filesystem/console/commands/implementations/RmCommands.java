package filesystem.console.commands.implementations;

import filesystem.console.Context;
import filesystem.console.commands.AbstractCommand;
import filesystem.console.commands.ICommand;

public class RmCommands extends AbstractCommand implements ICommand {
    public RmCommands(Context context) {
        super(context);
    }

    @Override
    public void run(String params) {
        this.getContext().getCurrentPath().remove(params);
    }
}
