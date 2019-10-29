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

    public void startConsole() {
        Scanner scanner = new Scanner(System.in);
        this.running = true;

        while (this.running) {
            this.printIndicator();
            String line = scanner.nextLine().trim();
            String[] args = line.split(" ", 2);
            if (args.length == 1) {
                this.runCommand(args[0].trim(), "");
            } else {
                this.runCommand(args[0].trim(), args[1].trim());
            }

        }
    }

    private void runCommand(String name, String params) {
        if (!name.isEmpty()) {
            if (this.commands.containsKey(name)) {
                this.commands.get(name).run(params);
            } else {
                this.printIndicator();
                System.out.println("Unknown command: " + name);
            }
        }

    }

    public void stopConsole() {
        this.running = false;
    }

    public void addCMD(String name, ICommand cmd) {
        commands.put(name, cmd);
    }

    private void printIndicator() {
        System.out.print(this.currentPosition.getStringPath() + ":  ");
    }

}
