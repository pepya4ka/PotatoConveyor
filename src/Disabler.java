import java.util.Random;

// Поток, отключающий питание конвеера (спавнит ошибки)
public class Disabler extends Thread {
    private Random rnd = new Random();
    @Override
    public void run() {
        while (true) {
            if (Main.conveyor.isAlive()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Шансы всего .05 процента, но 100 запросов в секунду - не шутка
                if (Main.conveyor.isPowered() && rnd.nextInt(10000) > 9995) {
                    Main.conveyor.setPowered(false);
                }
            }
        }
    }
}
