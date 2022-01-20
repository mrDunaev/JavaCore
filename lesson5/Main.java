package lesson5;

public class Main {
    public static void main(String[] args) {
        AppData appData = new AppData();
        appData.load("data.csv");
        System.out.println(appData);

        String[] newHeader = new String[]{"Column 1", "Column 2", "Column 3"};
        int[][] newData = new int[][]{{10,20,30},{40,50,60}};

        appData.setHeader(newHeader);
        appData.setData(newData);

        appData.save("data.csv");
    }
}
