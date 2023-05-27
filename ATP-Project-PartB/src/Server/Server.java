package Server;

import Server.IServerStrategy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private boolean stop;
    //private ExecutorService pool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        //this.pool = Executors.newFixedThreadPool(2);
    }

    public void start() {
        new Thread(() -> {
            run();
        }).start();
    }


    private void run() {

        try {

            // Configurations
            Configurations config = Configurations.getInstance();
            int numberOfThreads = config.getThreadPoolSize();

            ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
            ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);

            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    Thread thread = new Thread(() -> {
                        handleClient(clientSocket);
                    });
                    pool.execute(thread);
                }
                catch (SocketTimeoutException e) {
                    e.getStackTrace();
                }
            }
            serverSocket.close();
            pool.shutdown();
        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            strategy.serverStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException e){

        }
    }


    public void stop(){
        stop = true;
    }
}


