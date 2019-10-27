package vectorcalculator.ui;

import vectorcalculator.provider.DataProvider;
import vectorcalculator.provider.parser.DataParser;
import vectorcalculator.provider.parser.IDataParser;
import vectorcalculator.provider.writer.DataWriter;
import vectorcalculator.provider.writer.IDataWriter;
import vectorcalculator.vectors.IVector;
import vectorcalculator.vectors.VectorCalculator;
import vectorcalculator.vectors.VectorRepository;

import java.util.Optional;
import java.util.Scanner;

public abstract class Menu {
    private static VectorRepository repository = new VectorRepository();

    public static void printMenu() {

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.println("Choose option:");
            System.out.println("1. Create vector");
            System.out.println("2. Calculate");
            System.out.println("3. Print repository");
            System.out.println("4. Load repository");
            System.out.println("5. Save repository");
            System.out.println("6. Exit");
            System.out.println();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    Optional<IVector> optVector = VectorCreator.create();
                    optVector.ifPresent(Menu.repository::add);
                    break;
                case 2:
                    Menu.calculate();
                    break;
                case 3:
                    PrintRepository.print(Menu.repository);
                    break;
                case 4:
                    IDataParser parser = new DataParser();
                    try {
                        Menu.repository = DataProvider.load(parser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    IDataWriter writer = new DataWriter();
                    try {
                        DataProvider.save(Menu.repository, writer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }

    private static void calculate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Vector product");
        System.out.println("4. Scalar product");
        System.out.println("5. Exit");

        int option = scanner.nextInt();

        PrintRepository.print(Menu.repository);
        System.out.println();
        IVector vectorOne = Menu.loadVectorByIndex("Index of first vector:");
        IVector vectorTwo = Menu.loadVectorByIndex("Index of second vector:");

        switch (option) {
            case 1: {
                IVector result = VectorCalculator.addition(vectorOne, vectorTwo);
                result.print();
                Menu.repository.add(result);
                break;
            }
            case 2: {
                IVector result = VectorCalculator.subtraction(vectorOne, vectorTwo);
                result.print();
                Menu.repository.add(result);
                break;
            }
            case 3: {
                IVector result = VectorCalculator.vector(vectorOne, vectorTwo);
                result.print();
                Menu.repository.add(result);
                break;
            }
            case 4: {
                double result = VectorCalculator.scalar(vectorOne, vectorTwo);
                System.out.println(result);
                break;
            }
            default:
                break;
        }
    }

    private static IVector loadVectorByIndex(String label) {
        IVector vector = null;
        boolean runningTwo = true;
        Scanner scanner = new Scanner(System.in);

        while (runningTwo) {
            System.out.println(label);
            int index = scanner.nextInt();
            Optional<IVector> optVector = Menu.repository.getByIndex(index);
            if(optVector.isPresent()) {
                runningTwo = false;
                vector = optVector.get();
            }
        }
        return vector;
    }

}
