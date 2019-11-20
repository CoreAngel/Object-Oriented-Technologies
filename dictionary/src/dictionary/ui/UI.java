package dictionary.ui;

import dictionary.collection.Collection;
import dictionary.loader.Loader;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private Collection collection;

    public UI() {
        this.collection = new Collection();
    }

    public void printMenu() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Choose option: ");
            System.out.println("1. Show suggestions");
            System.out.println("2. Add word");
            System.out.println("3. Load dictionary from file");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    this.suggestionOption();
                    break;
                case 2:
                    this.addWordOption();
                    break;
                case 3:
                    this.loadDictionaryOption();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }

    private void suggestionOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write part of word: ");
        String word = scanner.nextLine();

        System.out.print("Write limit: ");
        int limit = scanner.nextInt();
        if (limit <= 0 || limit > 50) {
            limit = 50;
        }

        ArrayList<String> words = this.collection.getValuesByString(word, limit);
        System.out.println();
        System.out.println("Found the first " + limit + " words:");
        System.out.println(words);
    }

    private void addWordOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write word which you want to be added: ");
        String word = scanner.nextLine().trim();
        collection.addWord(word);
    }

    private void loadDictionaryOption() {
        Loader loader = new Loader("./dictionary.txt");
        loader.loadTo(this.collection);
        System.out.println("Dictionary has been loaded");
    }

}
