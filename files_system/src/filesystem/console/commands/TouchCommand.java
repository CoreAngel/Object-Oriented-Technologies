package filesystem.console.commands;

import filesystem.console.Console;
import filesystem.node.file.File;
import filesystem.node.verify.VerifyException;

public class TouchCommand extends AbstractCommand implements ICommand {

    public TouchCommand(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        try {
            new File(params, this.getContext().getCurrentPosition());
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }

    }
}
