package filesystem;


import filesystem.console.Context;
import filesystem.console.commands.CommandsProvider;
import filesystem.node.directory.Directory;
import filesystem.node.directory.RootDirectory;
import filesystem.node.file.File;
import filesystem.console.Console;

public class Main {

    public static void main(String[] args) {

        RootDirectory root = new RootDirectory("root");
        Directory dir = new Directory("Dir1", root);

        File file1 = new File("file1", dir);

        Directory dir2 = new Directory("Dir2", dir);
        Directory dir3 = new Directory("Dir3", dir2);
        Directory dir4 = new Directory("Dir4", dir3);
        Directory dir5 = new Directory("Dir5", dir4);
        Directory dir6 = new Directory("Dir6", dir2);
        Directory dir7 = new Directory("Dir7", dir3);
        Directory dir8 = new Directory("Dir8", root);
        System.out.println(dir8.toString());

        File file2 = new File("file2", dir7);
        File file3 = new File("file3", dir3);
        File file4 = new File("file4", dir7);
        File file5 = new File("file5", root);

        Directory dir9 = new Directory("Dir9", root);
        Directory dir10 = new Directory("Dir10", dir9);

        File file6 = new File("file6", dir10);
        File file7 = new File("file7", dir10);


        Console console = new Console(root);
        CommandsProvider.provide(console);
        console.startConsole();

    }
}
