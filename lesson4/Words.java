package lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class Words {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("One","Two","Three","Four","Two","Three","Three","Four","Four","Four"));

        LinkedHashSet<String> uniqueWords = new LinkedHashSet<>(words);

        System.out.println(words);
        System.out.println(uniqueWords);

        for (String uniqueWord : uniqueWords) {
            int count = 0;
            for (String word : words) {
                if (uniqueWord.equals(word))
                    count++;
            }
            System.out.println(uniqueWord + ": " + count);
        }
    }
}
