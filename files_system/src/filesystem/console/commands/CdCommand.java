package filesystem.console.commands;

import filesystem.console.Console;
import filesystem.node.Node;
import filesystem.node.directory.Directory;

import java.util.ArrayList;

public class CdCommand extends AbstractCommand implements ICommand {
    public CdCommand(Console context) {
        super(context);
    }

    @Override
    public void run(String params) {
        if (params.equals(".")) {
            // ignore
        } else if (params.equals("..")) {
            Directory newPath = this.getContext().getCurrentPosition().getPath();
            if (newPath != null) {
                this.getContext().setCurrentPosition(newPath);
            }
        } else {
            ArrayList<Node> nodes = this.getContext().getCurrentPosition().getNodesList();
            boolean finded = false;
            for (Node node: nodes) {
                if (node.getName().equals(params) && node instanceof Directory) {
                    this.getContext().setCurrentPosition((Directory) node);
                    finded = true;
                }
            }
            if (!finded) {
                System.out.println(params + " not find or is not directory");
            }
        }
    }
}
