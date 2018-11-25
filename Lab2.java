import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        System.out.print("Put your text here: ");
        String text = Console.read();
        String reversed = Console.reverseLast(text);
        Console.log(reversed);
    }
}

class Console {
    public static String read() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        sc.close();
        return line;
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static String[] toSentenses(String s) { return s.split("\\."); }

    public static String[] toWords(String s) { return s.split("\\s"); }

    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static String reverseLast(String text) {
        String result = new String();
        String[] sentenses = Console.toSentenses(text);
        for (String sentense: sentenses) {
            String[] words = Console.toWords(sentense);
            int last = words.length - 1;
            words[last] = Console.reverse(words[last]);
            result += words[0];
            for (int i = 1; i < words.length; ++i) {
                String word = words[i];
                result += " " + word;
            }
            result += ".";
        }
        return result;
    }
}