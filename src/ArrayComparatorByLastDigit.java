import java.util.Comparator;

public class ArrayComparatorByLastDigit implements Comparator<Integer> {

    @Override
    public int compare(Integer n1, Integer n2) {
        int lasDigitN1 = Math.abs(n1 % 10);
        int lasDigitN2 = Math.abs(n2 % 10);
        return Integer.compare(lasDigitN1, lasDigitN2);
    }
}
