package com.lambda.aws

import java.io.{InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper



/**
  * Class that implements AWS Lambda handler for API Gateway REST call
  */
class Calc
{

  def add(operand1: String, operand2: String) = {
     operand1.toFloat + operand2.toFloat
  }

  def sub(operand1: String, operand2: String) = {
    operand1.toFloat - operand2.toFloat
  }

  def multiply(operand1: String, operand2: String) = {
    operand1.toFloat * operand2.toFloat
  }

  def divide(operand1: String, operand2: String, operator:String) = {
    //check for divide by zero

    if(operand2.toFloat==0.0 && operator.equals("/")){
       "Divide by zero error"
    }else {
      (operand1.toFloat / operand2.toFloat).toString
    }
  }

  /**
    * Calculator lambda function
    *
    * @param input - String of the JSON format { "argument1":"5", "argument2":"10", "operation":"+"}
    * @param
    * @return Result of the operation requested
    */
    def handleRequest(input: InputStream, outputStream: OutputStream, context: Context): Unit = {
      context.getLogger.log("Operation requested");

      // parse the json string
      val mapper = new ObjectMapper() with ScalaObjectMapper
      mapper.registerModule(DefaultScalaModule)
      val parsedJson = mapper.readValue(input, classOf[CalcArguments])

      //Get the arguments and operator
      val operator = parsedJson.opnd
      val operand1 = parsedJson.op1
      val operand2 = parsedJson.op2

      //Calculate result based on the operator
      val result = operator match {

        case "+" => add(operand1, operand2)
        case "-" => sub(operand1, operand2)
        case "*" => multiply(operand1, operand2)
        case "/" => divide(operand1, operand2, "/")
        case _ => "Invalid operation"

      }

      context.getLogger.log("result = " + result);
      val jsonresult = "{ \"result\":" + result + "}"
      //Write to outputstream
      outputStream.write(jsonresult.toString.getBytes("UTF-8"))
    }

}