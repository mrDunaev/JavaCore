package lesson3;

import java.util.Arrays;

public class ArrayBox<T> {
    private T[] array;

    public ArrayBox(T... array) {
        this.array = array;
    }

    public void changeElementsOfArray(int firstElement, int secondElement) {
        if (firstElement < 1 || secondElement > array.length)
            System.out.println("Неверный номер элемента массива");
        else {
            T tmp;
            tmp = array[firstElement-1];
            array[firstElement-1] = array[secondElement-1];
            array[secondElement-1] = tmp;
        }
    }

    @Override
    public String toString() {
        return "ArrayBox{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public static void main(String[] args) {
        ArrayBox<Integer> integerArrayBox = new ArrayBox<>(1, 2, 3, 4, 5, 6, 7);
        System.out.println(integerArrayBox);
        integerArrayBox.changeElementsOfArray(1,7);
        System.out.println(integerArrayBox);

        ArrayBox<String> stringArrayBox = new ArrayBox<>("one", "two", "three", "four", "five");
        System.out.println(stringArrayBox);
        stringArrayBox.changeElementsOfArray(3,4);
        System.out.println(stringArrayBox);
    }
}
