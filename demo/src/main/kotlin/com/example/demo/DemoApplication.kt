// snippet-sourcedescription:[PublishTopic.kt demonstrates how to publish an Amazon Simple Notification Service (Amazon SNS) topic.]
// snippet-keyword:[AWS SDK for Kotlin]
// snippet-keyword:[Amazon Simple Notification Service]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.demo

// snippet-start:[sns.kotlin.PublishTopic.import]
import aws.sdk.kotlin.services.sns.SnsClient
import aws.sdk.kotlin.services.sns.model.PublishRequest
// import aws.sdk.kotlin.services.lambda.LambdaClient
// import aws.sdk.kotlin.services.lambda.model.InvokeRequest
// import aws.sdk.kotlin.services.lambda.model.LogType
// import aws.sdk.kotlin.services.auth.ProfileCredentialsProvider
// import aws.sdk.kotlin.auth.ProfileCredentialsProvider
// import aws.smithy.kotlin.ProfileCredentialsProvider
// import software.amazon.awssdk.auth.BasicAWSCredentials
// import software.amazon.awssdk.auth.AWSStaticCredentialsProvider
// import kotlin.system.exitProcess
// snippet-end:[sns.kotlin.PublishTopic.import]

/**
Before running this Kotlin code example, set up your development environment,
including your credentials.

For more information, see the following documentation topic:
https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/setup.html
 */
suspend fun main(args: Array<String>) {
    // val usage = """
    //       Usage:
    //         <message> <topicArn>

    //     Where:
    //         message - The message text to send.
    //         topicArn - The ARN of the topic to publish.
    //         """
    // if (args.size != 2) {
    //     println(usage)
    //     exitProcess(0)
    // }
    // val message = args[0]
    // val topicArn = args[1]
    // pubTopic(topicArn, message)
    pubTopic()
    // invokeFunction()
}

// snippet-start:[sns.kotlin.PublishTopic.main]
// suspend fun pubTopic(topicArnVal: String, messageVal: String) {
suspend fun pubTopic() {
    val request = PublishRequest {
        message = "サンプル太郎"
        // ↓文法分からず、いったん断念
        // entry {
        //     name = "サンプル太郎"
        //     telNo= "09012345678"
        // }
        topicArn = "arn:aws:sns:ap-northeast-1:769877970277:TestTopic"
    }

    // val credentialsProvider = CredentialsProvider(BasicAWSCredentials("accessKey", "secretKey"))
    // val snsClient = SNSClient.builder()
    //     .withCredentials(credentialsProvider)
    //     .withRegion("ap-northeast-1")
    //     .build()
    // SnsClient snsClient = SnsClient.builder()
    //         .region("ap-northeast-1")
    //         .credentialsProvider(ProfileCredentialsProvider.create())
    //         .build();
    // val result = snsClient.publish(request)
    // println("${result.messageId} message sent.")
    SnsClient { region = "ap-northeast-1" }.use { snsClient ->
        val result = snsClient.publish(request)
        println("${result.messageId} message sent.")
    }
}
// suspend fun invokeFunction() {
//     val json = """{"inputValue":"1000"}"""
//     val byteArray = json.trimIndent().encodeToByteArray()
//     val request = InvokeRequest {
//         functionName = "testSnsQqs"
//         logType = LogType.Tail
//         payload = byteArray
//     }

//     LambdaClient { region = "ap-northeast-1" }.use { awsLambda ->
//         val res = awsLambda.invoke(request)
//         println("${res.payload?.toString(Charsets.UTF_8)}")
//         println("The log result is ${res.logResult}")
//     }
//     // credentialsProvider = ProfileCredentialsProvider(profileName = "AdministratorAccess-769877970277")
// }

// snippet-end:[sns.kotlin.PublishTopic.main]
