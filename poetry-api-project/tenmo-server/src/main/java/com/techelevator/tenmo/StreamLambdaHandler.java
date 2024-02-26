package com.techelevator.tenmo;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import javax.ws.rs.core.Application;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamLambdaHandler implements RequestStreamHandler {

    //This is a static handler to manage lambda springboot requests which takes an
    // AwsProxyRequest from API Gateway, and returns an AwsProxyResponse back
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            // Initializes the 'handler' with the Spring Boot application class. This line effectively starts the Spring
            // application context and prepares the Spring Boot application to handle requests forwarded by AWS Lambda.
            // The SpringBootLambdaContainerHandler acts as a bridge between the AWS Lambda runtime and the Spring
            // application, allowing the application to handle web requests without being aware that it is running
            // within a Lambda function.
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(MuseMachineApplication.class);
        } catch (ContainerInitializationException e) {
            // Catches the ContainerInitializationException which may be thrown if the Spring application context
            // fails to start. This exception handling block logs the stack trace of the exception for debugging purposes
            e.printStackTrace();
            // Rethrows an exception, wrapping the original exception, to stop the initialization of the Lambda function.
            // This will cause AWS Lambda to discard the current execution environment and create a new one on the next
            // invocation (known as a "cold start"). This is a mechanism to prevent the Lambda from running in a
            // potentially invalid state.
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    // Overrides the 'handleRequest' method from the AWS Lambda Java core library. This method is the entry point
    // for AWS Lambda function invocations.
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        // Calls the 'proxyStream' method on the handler, passing the incoming 'inputStream' and 'outputStream'.
        // The 'proxyStream' method adapts the AWS Lambda Java input/output stream model to the Spring
        // framework's HTTP request/response model. This allows the Spring Boot application to handle the request
        // and write the response using its familiar model while running in the AWS Lambda environment.
        handler.proxyStream(inputStream, outputStream, context);
    }
}
