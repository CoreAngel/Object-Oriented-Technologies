package filesystem.console.commands.implementations;

import filesystem.console.Context;
import filesystem.console.commands.AbstractCommand;
import filesystem.console.commands.ICommand;
import filesystem.node.directory.Directory;
import filesystem.node.verify.VerifyException;

public class MkdirCommand extends AbstractCommand implements ICommand {
    public MkdirCommand(Context context) {
        super(context);
    }

    @Override
    public void run(String params) {
        try {
            new Directory(params, this.getContext().getCurrentPath());
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }
    }
}
