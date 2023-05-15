package com.myorg;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.apigateway.LambdaIntegration;
import software.amazon.awscdk.services.apigateway.LambdaRestApi;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

// import software.amazon.awscdk.Duration;
// import software.amazon.awscdk.services.sqs.Queue;

public class InfrastructureStack extends Stack {
    public InfrastructureStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public InfrastructureStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "InfrastructureQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();


        //create lambda to get innovations
       Function getInnovationFunction =
               Function.Builder.create(this, "hello_world_handler")
                .runtime(Runtime.JAVA_11)
                .handler("com.innovation.getInnovation.LambdaHandler")
                .memorySize(512)
                .timeout(Duration.seconds(10))
                .functionName("handleRequest")
                .code(Code.fromAsset("../assets/GetInnovation.jar"))
                .build();

        Function createInnovationFunction =
                Function.Builder.create(this,"lambdaCreate")
                .runtime(Runtime.JAVA_11)
                .handler("com.innovation.createInnovation.LamdaHandler")
                .memorySize(1024)
                .timeout(Duration.seconds(30))
                .functionName("lambdaCreate")
                .code(Code.fromAsset("../assets/SubmitInnovation.jar"))
                .build();

        LambdaRestApi gateway = LambdaRestApi.Builder.create(this, "gateway")
                .handler(getInnovationFunction)
                .build();

        gateway.getRoot().addResource("getInnovation").addMethod("GET", new LambdaIntegration(getInnovationFunction));

        //gateway.getRoot().addResource("submit").addMethod("GET", new LambdaIntegration(createInnovationFunction));

        gateway.getRoot().addResource("submit").addMethod("POST", new LambdaIntegration(createInnovationFunction));


    }
}
