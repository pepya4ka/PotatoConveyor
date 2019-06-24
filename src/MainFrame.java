import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*
    Класс, отвечающий за настройку окна приложения
 */
public class MainFrame {
    // Переменные класса
    private Font font = new Font("Impact", Font.BOLD, 32);
    private int width = 1600;
    private int height = 1600;
    private JFrame screen = new JFrame() {};
    private JPanel panel = new JPanel();

    // Структуры, хранящие пары Ключ:Значение (информационные компоненты)
    // и управленческие
    private Map<String, JComponent> data = new HashMap<>();

    //Базовый конструктор класса
    public MainFrame() {}


    //Основная настройка окна
    public void setupScreen() {
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        screen.setBounds(0, 0, width, height);
        screen.setTitle("Kudamono ga tabettai desuyo!");

        setupPanel();
    }

    //Настройка панелей окна
    private void setupPanel() {

        //настройка информационной панели
        panel.setLayout(new GridLayout(5, 2)); //выбор способа размещения элементов
        panel.setName("info_panel");

        screen.add(panel);  //добавление панели на основной экран


        //Создание, добавление на панель и добавление в структуру элементов
        data.put("time", setupTime());
        data.put("log", setupLogger());
        data.put("bucket", setupBucket());

        data.put("conveyor", setupConveyor());
        setupButtons();
        //отрисовка окна
        screen.setVisible(true);
    }

    // Метод настройки компонента конвеера
    private JLabel setupConveyor() {
        JLabel label = new JLabel("Конвеер: ");
        label.setFont(font);
        JLabel icon = new JLabel();
        label.setLabelFor(icon);

        icon.setIcon(getIconObj(Food.POTATO.getIconPath()));
        panel.add(label);
        panel.add(icon);

        return icon;
    }

    // панелька времени
    private JTextField setupTime() {
        JLabel label = new JLabel("Время: ");
        label.setFont(font);
        JTextField text = new JTextField();
        label.setLabelFor(text);
        text.setFont(font);
        panel.add(label);
        panel.add(text);
        return text;
    }

    private JTextArea setupBucket() {
        JLabel label = new JLabel("Корзина: ");
        label.setFont(font);
        JTextArea text = new JTextArea();
        text.setFont(font);
        label.setLabelFor(text);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(label);
        panel.add(scroll);
        return text;
    }

    // окно логгера
    private JTextArea setupLogger() {
        JLabel label = new JLabel("Логи: ");
        label.setFont(font);
        JTextArea text = new JTextArea();
        label.setLabelFor(text);
        text.setFont(font);

        JScrollPane scroll = new JScrollPane(text);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(label);
        panel.add(scroll);
        return text;
    }

    // настройка кнопок
    private void setupButtons() {
        JButton rot = new JButton("Подать питание");
        rot.setFont(font);
        rot.addActionListener(new PowerUpAction());
        panel.add(rot);

        JButton stuck = new JButton("Очистить конвеер");
        stuck.setFont(font);
        stuck.addActionListener(new SotrerAction());
        panel.add(stuck);
    }

    //Методы получения приватных переменных извне
    public Map<String, JComponent> getData() {
        return data;
    }

    //Статический метод класса, возвращающй объект картинки по пути
    public static ImageIcon getIconObj(String url) {
        URL imgSmartURL = MainFrame.class.getResource(url);
        return new ImageIcon(imgSmartURL);
    }
}
