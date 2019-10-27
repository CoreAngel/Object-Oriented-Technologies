package vectorcalculator.vectors;

import java.util.Arrays;

public class Vector3D extends Vector {
    protected double z;

    protected Vector3D() {
        this.type = VectorTypes.VECTOR3D;
    }

    public double getZ() {
        return z;
    }

    @Override
    public void print() {
        System.out.println("X: " + this.getX());
        System.out.println("Y: " + this.getY());
        System.out.println("Z: " + this.getZ());
        System.out.println("ABS: " + this.getAbs());
        System.out.println("Angles as Degrees: " + Arrays.toString(this.getAnglesAsDegrees()));
        System.out.println("Angles as Radians: " + Arrays.toString(this.getAnglesAsRadians()));
        System.out.println();
    }

    public static Vector3D createByPoint(double x, double y, double z) {
        Vector3D vector = new Vector3D();

        vector.x = x;
        vector.y = y;
        vector.z = z;
        vector.abs = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        vector.angles = new double[]{
                Math.atan(y/x),
                Math.acos(z/vector.abs)
        };

        return vector;
    }

//    first form x to y, second off of z-axis
    public static Vector3D createByAngles(double abs, double[] anglesAsRadians) {
        Vector3D vector = new Vector3D();

        vector.abs = abs;
        vector.angles = anglesAsRadians;
        vector.x = abs * Math.sin(anglesAsRadians[1]) * Math.cos(anglesAsRadians[0]);
        vector.y = abs * Math.sin(anglesAsRadians[1]) * Math.sin(anglesAsRadians[0]);
        vector.z = abs * Math.cos(anglesAsRadians[1]);

        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector3D) {
            Vector3D vector = (Vector3D) o;
            return this.x == vector.getX() && this.y == vector.getY() && this.z == vector.getZ();
        }
        return false;
    }

    @Override
    public IVector addition(Vector vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);
        return vector3D.addition(vector);
    }

    @Override
    public IVector addition(Vector3D vector) {
        return Vector3D.createByPoint(
                this.x + vector.getX(),
                this.y + vector.getY(),
                this.z + vector.getZ()
        );
    }

    @Override
    public IVector subtraction(Vector vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);
        return vector3D.subtraction(vector);
    }

    @Override
    public IVector subtraction(Vector3D vector) {
        return Vector3D.createByPoint(
                this.x - vector.getX(),
                this.y- vector.getY(),
                this.z - vector.getZ()
        );
    }

    @Override
    public IVector vector(Vector vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);
        return vector3D.vector(vector);
    }

    @Override
    public IVector vector(Vector3D vector) {
        return Vector3D.createByPoint(
                this.y * vector.getZ() - this.z * vector.getY(),
                -1 * (this.x * vector.getZ() - this.z * vector.getX()),
                this.x * vector.getY() - this.y * vector.getX()
        );
    }

    @Override
    public Double scalar(Vector vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);
        return vector3D.scalar(vector);
    }

    @Override
    public Double scalar(Vector3D vector) {
        return this.x * vector.getX() + this.y * vector.getY() + this.z * vector.getZ();
    }

}
