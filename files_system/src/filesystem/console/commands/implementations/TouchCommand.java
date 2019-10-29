package filesystem.console.commands.implementations;

import filesystem.console.Context;
import filesystem.console.commands.AbstractCommand;
import filesystem.console.commands.ICommand;
import filesystem.node.file.File;
import filesystem.node.verify.VerifyException;

public class TouchCommand extends AbstractCommand implements ICommand {

    public TouchCommand(Context context) {
        super(context);
    }

    @Override
    public void run(String params) {
        try {
            new File(params, this.getContext().getCurrentPath());
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }

    }
}
