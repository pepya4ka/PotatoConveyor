import javax.swing.*;
import java.awt.event.ActionEvent;

// Действие для кнопки перезапуска питания
public class PowerUpAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Main.conveyor.isPowered()) {
            Main.log("Конвеер перезапущен");
            Main.conveyor.setPowered(true);
        } else {
            Main.log("Конвеер уже запущен");
        }
    }
}
