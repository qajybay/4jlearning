package berrx.multithreading;

public class WaitNotifyPractice {
    private static final Object lock = new Object();
    private static boolean isReady = false;

    public static void main(String[] args) {
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                while (!isReady) {
                    try {
                        System.out.println("Жду сигнала...");
                        lock.wait(); // поток ожидает
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Получен сигнал! Продолжаю работу.");
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Готовлюсь послать сигнал...");
                isReady = true;
                lock.notify(); // пробуждает поток
                System.out.println("Сигнал отправлен.");
            }
        });

        waiter.start();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        notifier.start();
    }
}