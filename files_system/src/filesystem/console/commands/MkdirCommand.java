package filesystem.console.commands;

import filesystem.console.Console;
import filesystem.node.directory.Directory;
import filesystem.node.verify.VerifyException;

public class MkdirCommand extends AbstractCommand implements ICommand {
    public MkdirCommand(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        try {
            new Directory(params, this.getContext().getCurrentPosition());
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }
    }
}
