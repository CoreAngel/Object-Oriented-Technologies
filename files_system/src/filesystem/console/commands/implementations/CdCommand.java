package filesystem.console.commands.implementations;

import filesystem.console.Context;
import filesystem.console.commands.AbstractCommand;
import filesystem.console.commands.ICommand;
import filesystem.node.Node;
import filesystem.node.directory.Directory;

import java.util.ArrayList;

public class CdCommand extends AbstractCommand implements ICommand {
    public CdCommand(Context context) {
        super(context);
    }

    @Override
    public void run(String params) {
        if (params.equals(".")) {
            // ignore
        } else if (params.equals("..")) {
            Directory newPath = this.getContext().getCurrentPath().getPath();
            if (newPath != null) {
                this.getContext().setCurrentPath(newPath);
            }
        } else {
            ArrayList<Node> nodes = this.getContext().getCurrentPath().getNodesList();
            boolean finded = false;
            for (Node node: nodes) {
                if (node.getName().equals(params) && node instanceof Directory) {
                    this.getContext().setCurrentPath((Directory) node);
                    finded = true;
                }
            }
            if (!finded) {
                System.out.println(params + " not find or is not directory");
            }
        }
    }
}
