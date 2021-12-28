package lesson2;

public class MyArrayDataException extends NumberFormatException {
    int i, j;

    public MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
