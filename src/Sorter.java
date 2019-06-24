// Поток уборщика фруктов
public class Sorter extends Thread {
    @Override
    public void run(){
       if (Main.conveyor.isPaused()) {
           //логгируем
           Main.log("Засор устраняется, ждите");
           //ждем 5 секунд
           try {
               Thread.sleep(5000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           // отпускаем конвеер
           Main.conveyor.setPaused(false);
       } else {
           Main.log("Ложный вызов механика");
       }
    }
}
