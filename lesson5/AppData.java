package lesson5;

import java.io.*;
import java.util.ArrayList;

public class AppData {
    private String[] header;
    private int[][] data;

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String column : header) {
            result.append(column).append(";");
        }

        for (int[] row : data) {
            result.deleteCharAt(result.length()-1).append("\n");
            for (int number : row) {
                result.append(number).append(";");
            }
        }
        result.deleteCharAt(result.length()-1);

        return result.toString();
    }

    public void load(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            header = bufferedReader.readLine().split(";");
            ArrayList<int[]> dataList = new ArrayList<>();
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null ) {
                String[] stringData = tempString.split(";");
                int[] intData = new int[stringData.length];
                for (int i = 0; i < stringData.length; i++) {
                    intData[i] = Integer.parseInt(stringData[i]);
                }
                dataList.add(intData);
            }
            data = dataList.toArray(new int[][]{});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.print(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
