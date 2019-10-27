package filesystem.console.commands;

import filesystem.console.Console;
import filesystem.node.Node;
import filesystem.node.directory.Directory;

import java.util.ArrayList;
import java.util.Iterator;

public class RmCommands extends AbstractCommand implements ICommand {
    public RmCommands(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        this.getContext().getCurrentPosition().remove(params);
    }
}
