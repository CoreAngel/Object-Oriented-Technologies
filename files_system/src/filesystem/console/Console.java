package filesystem.console;

import filesystem.console.commands.ICommand;
import filesystem.node.directory.Directory;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private Directory currentPosition;
    private boolean running;
    private HashMap<String, ICommand> commands;

    public Console(Directory root) {
        this.commands = new HashMap<>();
        this.currentPosition = root;
    }

    public Directory getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Directory currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void stopConsole() {
        this.running = false;
    }

    public void addCMD(String name, ICommand cmd) {
        commands.put(name, cmd);
    }

    public void startConsole() {
        Scanner scanner = new Scanner(System.in);
        this.running = true;

        while (this.running) {
            this.printIndicator();
            String line = scanner.nextLine().trim();
            String[] args = line.split(" ", 2);
            if (args.length == 1) {
                this.runCommand(args[0], "");
            } else {
                this.runCommand(args[0], args[1]);
            }

        }
    }

    private void runCommand(String name, String params) {
        if(this.commands.containsKey(name)) {
            this.commands.get(name).run(params);
        } else {
            this.printIndicator();
            System.out.println("Unknown command: " + name);
        }
    }

    private void printIndicator() {
        System.out.print(this.currentPosition.getStringPath() + ":  ");
    }

}
