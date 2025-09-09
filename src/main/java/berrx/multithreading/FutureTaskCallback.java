package berrx.multithreading;

import java.util.concurrent.*;

public class FutureTaskCallback {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();

        Callable<String> work = () -> {
            Thread.sleep(5000);
            return "OK";
        };

        FutureTask<String> fts = new FutureTask<>(work) {
            @Override
            protected void done() {
                try {
                    String result = get();
                    System.out.println("Completable with: " + result.concat(" "+ Thread.currentThread().getName()));
                } catch (Exception e) {
                    System.out.println("Failed: " + e);
                }
            }
        };

        pool.submit(fts);

        // main thread continues immediately
        System.out.println("Main continues...".concat(" " + Thread.currentThread().getName()));

        FutureTask<String> myTask = new MyFutureTask(work);

        pool.submit(myTask);

        pool.shutdown();
    }


    static class MyFutureTask extends FutureTask<String> {
        public MyFutureTask(Callable<String> c) { super(c); }

        @Override
        protected void done() { handleDone(); }

        private void handleDone() {
            try {
                System.out.println("Result: " + get().concat(" "+ Thread.currentThread().getName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}