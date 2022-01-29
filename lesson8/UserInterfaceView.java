package lesson8;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите имя города (на русском): ");
            String city = scanner.nextLine();
            String command;

            while (true) {
                System.out.println(
                        "Введите 1 для получения погоды на сегодня;\n" +
                        "Введите 5 для прогноза на 5 дней;\n" +
                        "Введите 2 для получения данных из базы;\n" +
                        "Для выхода введите 0:");
                command = scanner.nextLine();
                if (!command.matches("[1520]")) {
                    System.out.println("Команда не распознана");
                }
                else break;
            }

            if (command.equals("0")) break;

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}