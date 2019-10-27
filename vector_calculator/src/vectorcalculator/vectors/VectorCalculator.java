package vectorcalculator.vectors;

public abstract class VectorCalculator {

    public static IVector addition(IVector v1, IVector v2) {
        if (v2.getType() == VectorTypes.VECTOR2D)
            return v1.addition((Vector) v2);
        else if(v2.getType() == VectorTypes.VECTOR3D)
            return v1.addition((Vector3D) v2);
        return null;
    }

    public static IVector subtraction(IVector v1, IVector v2) {
        if (v2.getType() == VectorTypes.VECTOR2D)
            return v1.subtraction((Vector) v2);
        else if(v2.getType() == VectorTypes.VECTOR3D)
            return v1.subtraction((Vector3D) v2);
        return null;
    }

    public static Double scalar(IVector v1, IVector v2) {
        if (v2.getType() == VectorTypes.VECTOR2D)
            return v1.scalar((Vector) v2);
        else if(v2.getType() == VectorTypes.VECTOR3D)
            return v1.scalar((Vector3D) v2);
        return null;
    }

    public static IVector vector(IVector v1, IVector v2) {
        if (v2.getType() == VectorTypes.VECTOR2D)
            return v1.vector((Vector) v2);
        else if(v2.getType() == VectorTypes.VECTOR3D)
            return v1.vector((Vector3D) v2);
        return null;
    }
}
