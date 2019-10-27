package vectorcalculator.ui;

import vectorcalculator.vectors.IVector;
import vectorcalculator.vectors.Vector3D;
import vectorcalculator.vectors.VectorRepository;
import vectorcalculator.vectors.VectorTypes;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class PrintRepository {

    public static void print(VectorRepository repository) {
        ArrayList<IVector> vectors = repository.getList();

        for (int i = 0; i < vectors.size(); ++i) {
            IVector vector = vectors.get(i);

            System.out.println("Index: " + i);
            System.out.println("X: " + vector.getX());
            System.out.println("Y: " + vector.getY());
            if (vector.getType() == VectorTypes.VECTOR3D) {
                System.out.println("Z: " + ((Vector3D)vector).getZ());
            }
            System.out.println("ABS: " + vector.getAbs());
            System.out.println("Angles as Degrees: " + Arrays.toString(vector.getAnglesAsDegrees()));
            System.out.println("Angles as Radians: " + Arrays.toString(vector.getAnglesAsRadians()));
            System.out.println();
        }
    }

}
