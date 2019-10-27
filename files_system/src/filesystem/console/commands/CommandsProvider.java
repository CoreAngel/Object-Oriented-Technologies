package filesystem.console.commands;

import filesystem.console.Console;

import java.util.HashMap;

public class CommandsProvider {
    public static void provide(Console context) {
        context.addCMD("exit", new ExitCommand(context));
        context.addCMD("tree", new TreeCommand(context));
        context.addCMD("touch", new TouchCommand(context));
        context.addCMD("mkdir", new MkdirCommand(context));
        context.addCMD("cd", new CdCommand(context));
        context.addCMD("ls", new LsCommand(context));
        context.addCMD("rm", new RmCommands(context));
    }
}
