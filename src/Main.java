import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class Main {
    // глобальные статические переменные
    public static MainFrame frame = new MainFrame();
    public static Conveyor conveyor = new Conveyor();
    public static Disabler disabler = new Disabler();

    // Основной поток приложения
    public static void main(String[] args) throws InterruptedException {
        frame.setupScreen();

        manager();
    }

    // Жизненный цикл основного потока (обновляет таймер, запускает перед циклом потоки)
    public static void manager() throws InterruptedException {
        JTextField time = (JTextField) frame.getData().get("time");
        conveyor.start();
        disabler.start();
        while (true) {
            time.setText(getTime());
            Thread.sleep(10);
        }
    }

    // логгирование
    public static void log(String mess) {
        JTextArea log = (JTextArea) Main.frame.getData().get("log");
        log.append(Main.getTime()+ ">> " + mess + "\n");
    }

    public static void updBucket(Map<Food, Integer> bucket) {
        JTextArea textArea = (JTextArea) Main.frame.getData().get("bucket");
        textArea.setText("");
        for (Map.Entry<Food, Integer> entry :
                bucket.entrySet()) {
            textArea.append(String.format("%s: %d;\n", entry.getKey().toString(), entry.getValue()));
        }
    }

    // получение форматированного времени
    public static String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
