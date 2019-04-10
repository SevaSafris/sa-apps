package thrift;

import java.util.concurrent.TimeUnit;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import thrift.generated.CustomService;

public class App {
  private static final int port = 8883;

  public static void main(String[] args) throws Exception {
    final TServer server = startNewThreadPoolServer();

    TTransport transport = new TSocket("localhost", port);
    transport.open();

    TProtocol protocol = new TBinaryProtocol(transport);

    CustomService.Client client = new CustomService.Client(protocol);
    String res = client.say("Good bye", "World");

    System.out.println(res);

    server.stop();

    TimeUnit.SECONDS.sleep(30);
    System.exit(0);


  }


  private static TServer startNewThreadPoolServer() throws Exception {
    TServerTransport transport = new TServerSocket(port);
    TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
    //TTransportFactory transportFactory = new TFramedTransport.Factory();

    CustomHandler customHandler = new CustomHandler();
    final TProcessor customProcessor = new CustomService.Processor<CustomService.Iface>(
        customHandler);

    TThreadPoolServer.Args args = new TThreadPoolServer.Args(transport)
        .processorFactory(new TProcessorFactory(customProcessor))
        .protocolFactory(protocolFactory)
        //.transportFactory(transportFactory)
        .minWorkerThreads(5)
        //.executorService(Executors.newCachedThreadPool())
        .maxWorkerThreads(10);

    TServer server = new TThreadPoolServer(args);

    new Thread(new Runnable() {
      @Override
      public void run() {
        server.serve();
      }
    }).start();

    return server;
  }
}
