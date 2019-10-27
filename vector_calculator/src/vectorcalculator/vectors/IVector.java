package vectorcalculator.vectors;

import java.io.Serializable;

public interface IVector extends Serializable {
    double getX();
    double getY();
    double getAbs();
    double[] getAnglesAsDegrees();
    double[] getAnglesAsRadians();
    VectorTypes getType();
    void print();
    Double scalar(Vector vector);
    Double scalar(Vector3D vector);
    IVector vector(Vector vector);
    IVector vector(Vector3D vector);
    IVector addition(Vector vector);
    IVector addition(Vector3D vector);
    IVector subtraction(Vector vector);
    IVector subtraction(Vector3D vector);
}
