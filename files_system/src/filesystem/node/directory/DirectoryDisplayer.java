package filesystem.node.directory;

import filesystem.node.Node;
import filesystem.node.file.File;

import java.util.ArrayList;

public class DirectoryDisplayer {

    private String verticalSeparator;
    private String horizontalSeparator;

    public DirectoryDisplayer(String verticalSeparator, String horizontalSeparator) {
        this.verticalSeparator = verticalSeparator;
        this.horizontalSeparator = horizontalSeparator;
    }

    public void display(Directory directory) {
        ArrayList<TreeOffsetDefine> offsets = new ArrayList<>();
        this.displayRecursive(directory, offsets);
    }

    private void displayRecursive(Directory directory, ArrayList<TreeOffsetDefine> offsets) {
        System.out.println(directory.getName() + "/");

        ArrayList<TreeOffsetDefine> newOffsets = new ArrayList<>(offsets);
        TreeOffsetDefine offsetObj = new TreeOffsetDefine(directory.getName().length() + 3, directory.getNodesList().size());
        newOffsets.add(offsetObj);

        ArrayList<Node> nodes = directory.getNodesList();

        for (Node node : nodes) {
            for (int i = 0; i < newOffsets.size(); ++i) {
                TreeOffsetDefine offset = newOffsets.get(i);
                if (i == newOffsets.size() - 1) {
                    System.out.print(this.verticalSeparator);
                    System.out.print(this.horizontalSeparator.repeat(offset.getOffset()));
                    offset.reduceChildren();
                } else {
                    if (!offset.isEmpty()) {
                        System.out.print(this.verticalSeparator);
                        System.out.print(" ".repeat(offset.getOffset()));
                    } else {
                        System.out.print(" ".repeat(offset.getOffset() + 1));
                    }

                }
            }

            if (node instanceof File) {
                System.out.println(node.getName());
            } else {
                Directory dir = (Directory) node;
                this.displayRecursive(dir, newOffsets);
            }
        }
    }
}
