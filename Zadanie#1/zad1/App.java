public class App {
    public static void main(String[] args) throws Exception {
        Thread newThread = new Thread() {

            @Override
            public void run() {
                System.out.println("Hello World");
            }

        };
        newThread.start();
        newThread.join();
    }
}
