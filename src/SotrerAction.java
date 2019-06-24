import javax.swing.*;
import java.awt.event.ActionEvent;

// Действие для уборки фруктов
public class SotrerAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Sorter().start();
    }
}
