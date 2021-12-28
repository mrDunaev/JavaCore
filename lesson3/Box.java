package lesson3;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        return fruits.size() * fruits.get(0).weight;
    }

    public boolean compare(Box box) {
        return box.getWeight() == this.getWeight();
    }

    public void moveTo(Box<T> destinationBox) {
        destinationBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        for (int i=0; i<3; i++) {
            appleBox.addFruit(new Apple());
        }

        Box<Orange> orangeBox = new Box<>();
        for (int i=0; i<2; i++) {
            orangeBox.addFruit(new Orange());
        }

        Box<Apple> anotherAppleBox = new Box<>();
        for (int i=0; i<5; i++) {
            anotherAppleBox.addFruit(new Apple());
        }

        System.out.println("Сравнение коробок");
        System.out.println("Коробка с яблоками весит: " + appleBox.getWeight());
        System.out.println("Коробка с апельсинами весит: " + orangeBox.getWeight());
        if (appleBox.compare(orangeBox))
            System.out.println("Коробки одинакового веса");
        else
            System.out.println("Коробки разного веса");

        System.out.println("\nПересыпание фруктов");
        System.out.println("В первой коробке " + appleBox.fruits.size() + " яблок");
        System.out.println("Во второй коробке " + anotherAppleBox.fruits.size() + " яблок");
        anotherAppleBox.moveTo(appleBox);
        System.out.println("Пересыпаем");
        System.out.println("В первой коробке " + appleBox.fruits.size() + " яблок");
        System.out.println("Во второй коробке " + anotherAppleBox.fruits.size() + " яблок");

        //orangeBox.moveTo(appleBox); //нельзя апельсины высыпать в коробку с яблоками
    }
}
