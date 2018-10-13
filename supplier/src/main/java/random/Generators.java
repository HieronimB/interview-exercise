package random;

import java.util.Random;

public class Generators {
    private static String[] RANDOM_STRINGS = {"Position", "John Doe", "Foo", "Bar"};
    private static String[] RANDOM_COUNTRY_CODE = {"PL", "US", "SI", "RR", "KP"};

    public static GeneratorStrategy STRING = () -> {
        Random rand = new Random();
        return RANDOM_STRINGS[rand.nextInt(RANDOM_STRINGS.length)];
    };

    public static GeneratorStrategy BOOL = () -> {
        Random rand = new Random();
        return String.valueOf(rand.nextBoolean());
    };

    public static GeneratorStrategy INTEGER = () -> {
        Random rand = new Random();
        return String.valueOf(rand.nextInt(100000));
    };

    public static GeneratorStrategy COUNTRY_CODE = () -> {
        Random rand = new Random();
        return RANDOM_COUNTRY_CODE[rand.nextInt(RANDOM_COUNTRY_CODE.length)];
    };
}
