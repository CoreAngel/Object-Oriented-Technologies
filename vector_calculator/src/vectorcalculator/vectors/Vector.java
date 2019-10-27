package vectorcalculator.vectors;

import java.util.Arrays;

public class Vector implements IVector {
    protected double x;
    protected double y;
    protected double[] angles;
    protected double abs;
    protected VectorTypes type;

    protected Vector() {
        this.type = VectorTypes.VECTOR2D;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getAbs() {
        return this.abs;
    }

    @Override
    public double[] getAnglesAsDegrees() {
        int length = this.angles.length;
        double[] anglesDegrees = new double[length];

        for(int i = 0; i < length; ++i) {
            anglesDegrees[i] = Math.toDegrees(this.angles[i]);
        }

        return anglesDegrees;
    }

    @Override
    public double[] getAnglesAsRadians() {
        return this.angles;
    }

    @Override
    public VectorTypes getType() {
        return this.type;
    }

    @Override
    public void print() {
        System.out.println("X: " + this.getX());
        System.out.println("Y: " + this.getY());
        System.out.println("ABS: " + this.getAbs());
        System.out.println("Angles as Degrees: " + Arrays.toString(this.getAnglesAsDegrees()));
        System.out.println("Angles as Radians: " + Arrays.toString(this.getAnglesAsRadians()));
        System.out.println();
    }

    public static Vector createByPoint(double x, double y) {
        Vector vector = new Vector();

        vector.x = x;
        vector.y = y;
        vector.abs = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        vector.angles = new double[]{ Math.atan(y/x) };

        return vector;
    }

    public static Vector createByAngles(double abs, double[] anglesAsRadians) {
        Vector vector = new Vector();

        vector.abs = abs;
        vector.angles = anglesAsRadians;
        vector.x = abs * Math.cos(anglesAsRadians[0]);
        vector.y = abs * Math.sin(anglesAsRadians[0]);

        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector) {
            Vector vector = (Vector) o;
            return this.x == vector.getX() && this.y == vector.getY();
        }
        return false;
    }

    @Override
    public IVector addition(Vector vector) {
        return Vector.createByPoint(
                this.x + vector.getX(),
                this.y + vector.getY()
        );
    }

    @Override
    public IVector addition(Vector3D vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);

        return Vector3D.createByPoint(
                vector3D.getX() + vector.getX(),
                vector3D.getY() + vector.getY(),
                vector3D.getZ() + vector.getZ()
        );
    }

    @Override
    public IVector subtraction(Vector vector) {
        return Vector.createByPoint(
                this.x - vector.getX(),
                this.y - vector.getY()
        );
    }

    @Override
    public IVector subtraction(Vector3D vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);

        return Vector3D.createByPoint(
                vector3D.getX() - vector.getX(),
                vector3D.getY() - vector.getY(),
                vector3D.getZ() - vector.getZ()
        );
    }

    @Override
    public IVector vector(Vector vector) {
        return Vector3D.createByPoint(
                0,
                0,
                this.x * vector.getX() - this.y * vector.y
        );
    }

    @Override
    public IVector vector(Vector3D vector) {
        Vector3D vector3D = Vector3D.createByPoint(this.x, this.y, 0);

        return Vector3D.createByPoint(
                vector3D.getY() * vector.getZ() - vector3D.getZ() * vector.getY(),
                -1 * (vector3D.getX() * vector.getZ() - vector3D.getZ() * vector.getX()),
                vector3D.getX() * vector.getY() - vector3D.getY() * vector.getX()
        );
    }

    @Override
    public Double scalar(Vector vector) {
        return this.x * vector.getX() + this.y * vector.getY();
    }

    @Override
    public Double scalar(Vector3D vector) {
        return this.x * vector.getX() + this.y * vector.getY() + 0 * vector.getZ();
    }
}
