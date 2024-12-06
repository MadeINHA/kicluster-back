package org.example.madeinha.global.gRPC;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: algorithm_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AlgorithmServiceGrpc {

  private AlgorithmServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "algorithm.AlgorithmService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest,
      AlgorithmServiceOuterClass.AlgorithmResponse> getDbScanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DbScan",
      requestType = AlgorithmServiceOuterClass.AlgorithmRequest.class,
      responseType = AlgorithmServiceOuterClass.AlgorithmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest,
      AlgorithmServiceOuterClass.AlgorithmResponse> getDbScanMethod() {
    io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest, AlgorithmServiceOuterClass.AlgorithmResponse> getDbScanMethod;
    if ((getDbScanMethod = AlgorithmServiceGrpc.getDbScanMethod) == null) {
      synchronized (AlgorithmServiceGrpc.class) {
        if ((getDbScanMethod = AlgorithmServiceGrpc.getDbScanMethod) == null) {
          AlgorithmServiceGrpc.getDbScanMethod = getDbScanMethod =
              io.grpc.MethodDescriptor.<AlgorithmServiceOuterClass.AlgorithmRequest, AlgorithmServiceOuterClass.AlgorithmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DbScan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AlgorithmServiceOuterClass.AlgorithmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AlgorithmServiceOuterClass.AlgorithmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlgorithmServiceMethodDescriptorSupplier("DbScan"))
              .build();
        }
      }
    }
    return getDbScanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest,
      AlgorithmServiceOuterClass.AlgorithmResponse> getConvexHullMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "convexHull",
      requestType = AlgorithmServiceOuterClass.AlgorithmRequest.class,
      responseType = AlgorithmServiceOuterClass.AlgorithmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest,
      AlgorithmServiceOuterClass.AlgorithmResponse> getConvexHullMethod() {
    io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest, AlgorithmServiceOuterClass.AlgorithmResponse> getConvexHullMethod;
    if ((getConvexHullMethod = AlgorithmServiceGrpc.getConvexHullMethod) == null) {
      synchronized (AlgorithmServiceGrpc.class) {
        if ((getConvexHullMethod = AlgorithmServiceGrpc.getConvexHullMethod) == null) {
          AlgorithmServiceGrpc.getConvexHullMethod = getConvexHullMethod =
              io.grpc.MethodDescriptor.<AlgorithmServiceOuterClass.AlgorithmRequest, AlgorithmServiceOuterClass.AlgorithmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "convexHull"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AlgorithmServiceOuterClass.AlgorithmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AlgorithmServiceOuterClass.AlgorithmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlgorithmServiceMethodDescriptorSupplier("convexHull"))
              .build();
        }
      }
    }
    return getConvexHullMethod;
  }

  private static volatile io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest,
      AlgorithmServiceOuterClass.AlgorithmResponse> getPointInPolygonMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PointInPolygon",
      requestType = AlgorithmServiceOuterClass.AlgorithmRequest.class,
      responseType = AlgorithmServiceOuterClass.AlgorithmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest,
      AlgorithmServiceOuterClass.AlgorithmResponse> getPointInPolygonMethod() {
    io.grpc.MethodDescriptor<AlgorithmServiceOuterClass.AlgorithmRequest, AlgorithmServiceOuterClass.AlgorithmResponse> getPointInPolygonMethod;
    if ((getPointInPolygonMethod = AlgorithmServiceGrpc.getPointInPolygonMethod) == null) {
      synchronized (AlgorithmServiceGrpc.class) {
        if ((getPointInPolygonMethod = AlgorithmServiceGrpc.getPointInPolygonMethod) == null) {
          AlgorithmServiceGrpc.getPointInPolygonMethod = getPointInPolygonMethod =
              io.grpc.MethodDescriptor.<AlgorithmServiceOuterClass.AlgorithmRequest, AlgorithmServiceOuterClass.AlgorithmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PointInPolygon"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AlgorithmServiceOuterClass.AlgorithmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  AlgorithmServiceOuterClass.AlgorithmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AlgorithmServiceMethodDescriptorSupplier("PointInPolygon"))
              .build();
        }
      }
    }
    return getPointInPolygonMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AlgorithmServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlgorithmServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlgorithmServiceStub>() {
        @java.lang.Override
        public AlgorithmServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlgorithmServiceStub(channel, callOptions);
        }
      };
    return AlgorithmServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AlgorithmServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlgorithmServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlgorithmServiceBlockingStub>() {
        @java.lang.Override
        public AlgorithmServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlgorithmServiceBlockingStub(channel, callOptions);
        }
      };
    return AlgorithmServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AlgorithmServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AlgorithmServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AlgorithmServiceFutureStub>() {
        @java.lang.Override
        public AlgorithmServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AlgorithmServiceFutureStub(channel, callOptions);
        }
      };
    return AlgorithmServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void dbScan(AlgorithmServiceOuterClass.AlgorithmRequest request,
                        io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDbScanMethod(), responseObserver);
    }

    /**
     */
    default void convexHull(AlgorithmServiceOuterClass.AlgorithmRequest request,
                            io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConvexHullMethod(), responseObserver);
    }

    /**
     */
    default void pointInPolygon(AlgorithmServiceOuterClass.AlgorithmRequest request,
                                io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPointInPolygonMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AlgorithmService.
   */
  public static abstract class AlgorithmServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AlgorithmServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AlgorithmService.
   */
  public static final class AlgorithmServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AlgorithmServiceStub> {
    private AlgorithmServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlgorithmServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlgorithmServiceStub(channel, callOptions);
    }

    /**
     */
    public void dbScan(AlgorithmServiceOuterClass.AlgorithmRequest request,
                       io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDbScanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void convexHull(AlgorithmServiceOuterClass.AlgorithmRequest request,
                           io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConvexHullMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pointInPolygon(AlgorithmServiceOuterClass.AlgorithmRequest request,
                               io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPointInPolygonMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AlgorithmService.
   */
  public static final class AlgorithmServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AlgorithmServiceBlockingStub> {
    private AlgorithmServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlgorithmServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlgorithmServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public AlgorithmServiceOuterClass.AlgorithmResponse dbScan(AlgorithmServiceOuterClass.AlgorithmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDbScanMethod(), getCallOptions(), request);
    }

    /**
     */
    public AlgorithmServiceOuterClass.AlgorithmResponse convexHull(AlgorithmServiceOuterClass.AlgorithmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConvexHullMethod(), getCallOptions(), request);
    }

    /**
     */
    public AlgorithmServiceOuterClass.AlgorithmResponse pointInPolygon(AlgorithmServiceOuterClass.AlgorithmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPointInPolygonMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AlgorithmService.
   */
  public static final class AlgorithmServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AlgorithmServiceFutureStub> {
    private AlgorithmServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AlgorithmServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AlgorithmServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AlgorithmServiceOuterClass.AlgorithmResponse> dbScan(
        AlgorithmServiceOuterClass.AlgorithmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDbScanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AlgorithmServiceOuterClass.AlgorithmResponse> convexHull(
        AlgorithmServiceOuterClass.AlgorithmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConvexHullMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AlgorithmServiceOuterClass.AlgorithmResponse> pointInPolygon(
        AlgorithmServiceOuterClass.AlgorithmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPointInPolygonMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DB_SCAN = 0;
  private static final int METHODID_CONVEX_HULL = 1;
  private static final int METHODID_POINT_IN_POLYGON = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DB_SCAN:
          serviceImpl.dbScan((AlgorithmServiceOuterClass.AlgorithmRequest) request,
              (io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse>) responseObserver);
          break;
        case METHODID_CONVEX_HULL:
          serviceImpl.convexHull((AlgorithmServiceOuterClass.AlgorithmRequest) request,
              (io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse>) responseObserver);
          break;
        case METHODID_POINT_IN_POLYGON:
          serviceImpl.pointInPolygon((AlgorithmServiceOuterClass.AlgorithmRequest) request,
              (io.grpc.stub.StreamObserver<AlgorithmServiceOuterClass.AlgorithmResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getDbScanMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              AlgorithmServiceOuterClass.AlgorithmRequest,
              AlgorithmServiceOuterClass.AlgorithmResponse>(
                service, METHODID_DB_SCAN)))
        .addMethod(
          getConvexHullMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              AlgorithmServiceOuterClass.AlgorithmRequest,
              AlgorithmServiceOuterClass.AlgorithmResponse>(
                service, METHODID_CONVEX_HULL)))
        .addMethod(
          getPointInPolygonMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              AlgorithmServiceOuterClass.AlgorithmRequest,
              AlgorithmServiceOuterClass.AlgorithmResponse>(
                service, METHODID_POINT_IN_POLYGON)))
        .build();
  }

  private static abstract class AlgorithmServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AlgorithmServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AlgorithmServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AlgorithmService");
    }
  }

  private static final class AlgorithmServiceFileDescriptorSupplier
      extends AlgorithmServiceBaseDescriptorSupplier {
    AlgorithmServiceFileDescriptorSupplier() {}
  }

  private static final class AlgorithmServiceMethodDescriptorSupplier
      extends AlgorithmServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AlgorithmServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AlgorithmServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AlgorithmServiceFileDescriptorSupplier())
              .addMethod(getDbScanMethod())
              .addMethod(getConvexHullMethod())
              .addMethod(getPointInPolygonMethod())
              .build();
        }
      }
    }
    return result;
  }
}
