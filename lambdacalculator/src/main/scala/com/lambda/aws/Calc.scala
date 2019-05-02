package com.lambda.aws

import java.io.{InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper



/**
  * Class that implements AWS Lambda handler for API Gateway REST call
  */
class Calc //extends RequestHandler[InputStream, OutputStream] {
{
  /**
    * Calculator lambda function
    * @param input - String of the JSON format { "argument1":"5", "argument2":"10", "operation":"+"}
    * @param
    * @return Result of the operation requested
    */
    def handleRequest(input: InputStream, outputStream: OutputStream, context: Context): Unit = {
    context.getLogger.log("Operation requested");

    // parse the json string
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    val parsedJson = mapper.readValue(input,classOf[CalcArguments])

    //Get the arguments and operator
    val operator = parsedJson.operation
    val operand1 = parsedJson.argument1
    val operand2 = parsedJson.argument2

    //check for divide by zero
    if(operand2.toFloat==0.0 && operator.equals("/")){
      outputStream.write("Divide by zero error".getBytes("UTF-8"))
    }else {


      //Calculate result based on the operator
      val result = operator match {

        case "+" => operand1.toFloat + operand2.toFloat
        case "-" => operand1.toFloat - operand2.toFloat
        case "*" => operand1.toFloat * operand2.toFloat
        case "/" => operand1.toFloat / operand2.toFloat
        case _ => "Invalid operation"

      }

      context.getLogger.log("result = "+result);
      //Write to outputstream
      outputStream.write(result.toString.getBytes("UTF-8"))

    }
  }
}