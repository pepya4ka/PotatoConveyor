import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Перечисление еды
public enum Food {
    POTATO("img/potato.png", "большой картофель"),
    SMALL_POTATO("img/potato.png", "малый картофель"),
    ROTTEN_POTATO("img/rotten_potato.png", "гнилой картофель" );

    private String iconPath;
    private String name;

    // Приводим список еды к итерируему списку
    private static final List<Food> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    Food (String iconPath, String name) {
        this.iconPath = iconPath;
        this.name = name;
    }


    // Методы получения еды и данных о еде
    public static Food randomFood() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public String getIconPath() {
        return iconPath;
    }
    public String getIconFilename() {
        return getIconPath().substring(getIconPath().lastIndexOf("/") + 1);
    }
    public static List<Food> getValues() { return VALUES; }

    // метод получения объекта по названию картинки
    public static Food getByFilename(String filename) {
        for (Food item : VALUES) {
            if (item.getIconFilename().equals(filename)) {
                return item;
            }
        }
        return null;
    }

    // переопределение строковой репрезентации
    @Override
    public String toString() {
        return name;
    }
}
