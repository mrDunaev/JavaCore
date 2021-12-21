package lesson2;

public class Lesson2 {
    public static void main(String[] args) {
        String[][] array1 = new String[][] {{"1","1","1","1"},{"2","2","2","2"},{"3","3","3","3"},{"4","4","4","4"}};
        String[][] array2 = new String[][] {{"1","1","1","1"},{"2","2","2"},{"3","3","3","3"},{"4","4","4","4"}};
        String[][] array3 = new String[][] {{"1","1","1","1"},{"2","2","g","2"},{"3","3","3","3"},{"4","4","4","4"}};
        printArray(array1);
        tryToFindArraySum(array1);
        printArray(array2);
        tryToFindArraySum(array2);
        printArray(array3);
        tryToFindArraySum(array3);
    }

    public static void tryToFindArraySum (String[][] array) {
        try {
            arraySum(array);
        } catch (MyArraySizeException e) {
            System.out.println("Неверный размер массива");
        } catch (MyArrayDataException e) {
            System.out.println("Неверный формат данных в строке " + e.i + ", столбце " + e.j);
        }
    }

    public static void arraySum (String[][] array) throws MyArraySizeException {
        int sum = 0;
        boolean correctSize = true;

        if (array.length != 4) {
            correctSize = false;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i].length != 4)
                    correctSize = false;
            }
        }

        if (!correctSize) {
            throw new MyArraySizeException();
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    try {
                        sum += Integer.parseInt(array[i][j]);
                    } catch (NumberFormatException ex) {
                        throw new MyArrayDataException(i+1, j+1);
                    }
                }
            }
            System.out.println("Сумма элементов массива: " + sum);
        }
    }

    public static void printArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
