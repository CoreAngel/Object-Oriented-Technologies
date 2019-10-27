package filesystem.console.commands;

import filesystem.console.Console;
import filesystem.node.directory.DirectoryDisplayer;

public class TreeCommand extends AbstractCommand implements ICommand {
    public TreeCommand(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        DirectoryDisplayer directoryDisplayer = new DirectoryDisplayer("|", "-");
        System.out.println();
        directoryDisplayer.display(this.getContext().getCurrentPosition());
        System.out.println();
    }
}
