package vectorcalculator.ui;

import vectorcalculator.vectors.IVector;
import vectorcalculator.vectors.Vector;
import vectorcalculator.vectors.Vector3D;

import java.util.Optional;
import java.util.Scanner;

public abstract class VectorCreator {

    public static Optional<IVector> create() {
        System.out.println("Witch type of vector do you want create:");
        System.out.println("1. 2D by Point");
        System.out.println("2. 2D by Angles");
        System.out.println("3. 3D by Point");
        System.out.println("4. 3D by Angles");
        System.out.println("5. Back");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();

        switch (type) {
            case 1: {
                System.out.println("X: ");
                double x = scanner.nextDouble();
                System.out.println("Y: ");
                double y = scanner.nextDouble();

                return Optional.of(Vector.createByPoint(x, y));
            }
            case 2: {
                System.out.println("Angle: ");
                double angle = scanner.nextDouble();
                System.out.println("Abs: ");
                double abs = scanner.nextDouble();

                return Optional.of(Vector.createByAngles(abs, new double[]{angle}));
            }
            case 3: {
                System.out.println("X: ");
                double x = scanner.nextDouble();
                System.out.println("Y: ");
                double y = scanner.nextDouble();
                System.out.println("Z: ");
                double z = scanner.nextDouble();

                return Optional.of(Vector3D.createByPoint(x, y, z));
            }
            case 4: {
                System.out.println("Angle form x to y: ");
                double angleOne = scanner.nextDouble();
                System.out.println("Angle off of z-axis: ");
                double angleTo = scanner.nextDouble();
                System.out.println("Abs: ");
                double abs = scanner.nextDouble();

                return Optional.of(Vector3D.createByAngles(abs, new double[]{angleOne, angleTo}));
            }
            default:
                return Optional.empty();
        }
    }

}
