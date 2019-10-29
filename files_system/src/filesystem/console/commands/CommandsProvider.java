package filesystem.console.commands;

import filesystem.console.Console;
import filesystem.console.Context;
import filesystem.console.commands.implementations.*;


public class CommandsProvider {
    public static void provide(Console console) {
        Context context = new Context(console.getCurrentPosition(), console);

        console.addCMD("exit", new ExitCommand(context));
        console.addCMD("tree", new TreeCommand(context));
        console.addCMD("touch", new TouchCommand(context));
        console.addCMD("mkdir", new MkdirCommand(context));
        console.addCMD("cd", new CdCommand(context));
        console.addCMD("ls", new LsCommand(context));
        console.addCMD("rm", new RmCommands(context));
    }
}
