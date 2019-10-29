package filesystem.console.commands.implementations;

import filesystem.console.Context;
import filesystem.console.commands.AbstractCommand;
import filesystem.console.commands.ICommand;
import filesystem.node.directory.DirectoryDisplayer;

public class TreeCommand extends AbstractCommand implements ICommand {
    public TreeCommand(Context context) {
        super(context);
    }

    @Override
    public void run(String params) {
        DirectoryDisplayer directoryDisplayer = new DirectoryDisplayer("|", "-");
        System.out.println();
        directoryDisplayer.display(this.getContext().getCurrentPath());
        System.out.println();
    }
}
