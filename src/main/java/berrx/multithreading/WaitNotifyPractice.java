package berrx.multithreading;

import lombok.Data;

public class WaitNotifyPractice {
    private static final Object lock = new Object();
    private static final Book lockBook = new Book();
    private static boolean isReady = false;

    public static void main(String[] args) {
        Thread waiter = new Thread(() -> {
            synchronized (lockBook) {
                while (!isReady) {
                    try {
                        System.out.println("Жду сигнала...");
                        lockBook.wait(); // поток ожидает
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Получен сигнал! Продолжаю работу.");
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lockBook) {
                System.out.println("Готовлюсь послать сигнал...");
                isReady = true;
                lockBook.notify(); // пробуждает поток
                System.out.println("Сигнал отправлен.");
            }
        });

        waiter.start();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        notifier.start();
    }

    @Data
    public static class Book {
        private String name;
        private String author;
    }
}