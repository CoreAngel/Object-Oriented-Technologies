package vectorcalculator.vectors;

import java.util.ArrayList;
import java.util.Optional;

public class VectorRepository {
    private ArrayList<IVector> vectors = new ArrayList<>();

    public void add(IVector vector) {
        this.vectors.add(vector);
    }

    public void remove(IVector vector) {
        this.vectors.remove(vector);
    }

    public void clear() {
        this.vectors.clear();
    }

    public ArrayList<IVector> getList() {
        return this.vectors;
    }

    public Optional<IVector> getByVector(IVector vector) {
        int index = this.vectors.indexOf(vector);

        if(index != -1) {
            return Optional.of(this.vectors.get(index));
        }
        return Optional.empty();
    }

    public Optional<IVector> getByIndex(int index) {
        int length = this.vectors.size();

        if(index >= 0 && index < length) {
            return Optional.of(this.vectors.get(index));
        }
        return Optional.empty();
    }



}
