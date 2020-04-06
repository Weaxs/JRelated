package basic;

import java.util.Comparator;

public class ComparaTest implements Comparable<ComparaTest>{

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int compareTo(ComparaTest o) {
        return getValue() > o.getValue()?1:(getValue() < o.getValue()?-1:0);
    }
}

class ComparatorTest implements Comparator<ComparaTest> {

    @Override
    public int compare(ComparaTest o1, ComparaTest o2) {
        return o1.getValue() > o2.getValue()?1:(o1.getValue() < o2.getValue()?-1:0);
    }
}
