package artopia;

import artopia.handler.ConnectionHandler;
import artopia.handler.ExceptionHandler;
import artopia.service.locator.ServiceLocator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Vehsamrak
 */
public class Launcher
{
    private ServiceLocator serviceLocator;

    /**
     * @param serviceLocator ServiceLocator
     * @return Launcher
     */
    public Launcher setServiceLocator(ServiceLocator serviceLocator)
    {
        this.serviceLocator = serviceLocator;

        return this;
    }

    public void run()
    {
        if (this.serviceLocator == null) {
            throw new RuntimeException("ServiceLocator must be set to run application.");
        }

        int port = 9000;

        System.out.println("Инициализация приложения ...");

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.printf("Сервер запущен на порту %s ...%n", port);

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);

                Runnable connectionHandler = new ConnectionHandler(
                        socket,
                        socketInput,
                        socketOutput,
                        this.serviceLocator
                );

                new Thread(connectionHandler).start();
            }
        } catch (Exception exception) {
            ExceptionHandler.handle(exception);
        }
    }
}