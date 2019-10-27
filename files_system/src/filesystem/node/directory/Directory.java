package filesystem.node.directory;

import filesystem.node.Node;
import filesystem.node.verify.Verifier;
import filesystem.node.verify.VerifyException;

import java.util.ArrayList;
import java.util.Optional;

public class Directory extends Node {
    private ArrayList<Node> nodesList;

    public Directory(String name, Directory path) {
        super(name, path);
        this.nodesList = new ArrayList<>();
        if (path != null) {
            path.add(this);
        }
    }

    public void printNodes() {
        System.out.println();
        for (Node node: this.nodesList) {
            if (node instanceof Directory) {
                System.out.println(node.getName() + '/');
            } else {
                System.out.println(node.getName());
            }
        }
        System.out.println();
    }

    public ArrayList<Node> getNodesList() {
        return this.nodesList;
    }

    public void add(Node node) {
        try {
            Verifier.verifyDuplicate(node, this);
            this.nodesList.add(node);
        } catch (VerifyException e) {
            System.out.println(e.getMessage());
        }

    }

    public void remove(String name) {
        this.nodesList.removeIf(nodeFromArr -> name.equals(nodeFromArr.getName()));
    }

    public Optional<Node> getByNode(Node node) {
        return this.nodesList.stream().filter(nodeInArr -> nodeInArr.equals(node)).findFirst();
    }

    public String getStringPath() {
        if (this.getPath() == null) {
            return this.getName();
        } else {
            return this.getStringRecursive(this.getPath()) + this.getName();
        }
    }

    private String getStringRecursive(Directory dir) {
        if (dir.getPath() == null) {
            return dir.getName() + "/";
        } else {
            return this.getStringRecursive(dir.getPath()) + dir.getName() + "/";
        }
    }
}
