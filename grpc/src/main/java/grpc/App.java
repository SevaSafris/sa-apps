package grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.opentracing.contrib.grpc.gen.GreeterGrpc;
import io.opentracing.contrib.grpc.gen.GreeterGrpc.GreeterBlockingStub;
import io.opentracing.contrib.grpc.gen.HelloReply;
import io.opentracing.contrib.grpc.gen.HelloRequest;
import java.util.concurrent.TimeUnit;

public class App {
  public static void main(String[] args) throws Exception {

    final Server server = ServerBuilder.forPort(8086)
        .addService(new GreeterImpl()).build().start();

    final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8086)
        .usePlaintext(true)
        .build();

    final GreeterBlockingStub greeterBlockingStub = GreeterGrpc.newBlockingStub(channel);

    final String message = greeterBlockingStub
        .sayHello(HelloRequest.newBuilder().setName("world").build()).getMessage();

    System.out.println(message);

    server.shutdownNow();

    TimeUnit.SECONDS.sleep(10);
  }

  private static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
      HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
  }
}
