import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Поток конвеера
public class Conveyor extends Thread {
    private boolean powered = false;
    private boolean paused = false;
    private Random rnd = new Random();
    private Map<Food, Integer> bucket = new HashMap<>();

    public Conveyor() {
        for (Food food :
                Food.getValues()) {
            bucket.put(food, 0);
        }
    }

    @Override
    public void run() {
        // Жизненный цикл конвеера
        while (true) {
            try {
                // Пауза чтобы не грузить проц
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Проверки, если всё ок - спавним еду
            if (isPowered()) {
                if (!isPaused()) {
                    if (isLoaded()) {
                        spawnFood();
                    }
                }
            }
        }
    }

    // Шанс на появление еды
    public boolean isLoaded() {
        if (rnd.nextInt(1000) > 995) {
            return true;
        }
        return false;
    }

    // Спавн еды и логгирование
    public void spawnFood() {
        int countPatato = 0;
        Food food = Food.randomFood();
        bucket.put(food, bucket.get(food) + 1);
//        System.out.println(bucket.get(food));
        JLabel conveyor = (JLabel) Main.frame.getData().get("conveyor");
        conveyor.setIcon(MainFrame.getIconObj(food.getIconPath()));
        Main.log("Поступил " + food.toString());
        Main.updBucket(bucket);
        if (bucket.get(food) == 2) {
            int select = JOptionPane.showConfirmDialog(null, "Набралось максимальное количество " + food.toString() + ", очистить корзину?", "Ошибка", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (select == 0) {
                bucket.put(food, 0);
            }
        }
        // Если фрукт - алертим и стопим процесс

        if (rnd.nextInt(10) > 7) {
            Main.log("ЗАСТРЕВАНИЕ ЛЕНТЫ!");
            setPaused(true);
        }
    }

    // Установка паузы из-за фруктов на линии
    public void setPaused(boolean paused) {
        if (paused) {
            Main.log("Конвеер ожидает очистки");
        } else {
            Main.log("Лента очищена");
            if (isPowered()) spawnFood();
        }
        this.paused = paused;
    }

    // Проверка на паузу
    public boolean isPaused() {
        return paused;
    }

    // Установка питания
    public void setPowered(boolean powered) {
        if (!powered) {
            Main.log("СБОЙ ПИТАНИЯ! ОСТАНОВКА!");
        } else {
            Main.log("Питание подано, запуск");
        }
        this.powered = powered;
    }

    // Проверка на питание
    public boolean isPowered() {
        return powered;
    }
}
