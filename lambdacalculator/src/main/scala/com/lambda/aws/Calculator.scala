package com.lambda.aws

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

/**
  * Class that implements AWS Lambda handler
  */
class Calculator extends RequestHandler[String, String] {

  /**
    * Calculator lambda function
    * @param input - String of the form "<operand1><operator><operator2>" Eg. 5+8
    * @param context
    * @return Result of the operation requested
    */
  override def handleRequest(input: String, context: Context): String = {
    context.getLogger.log("Input: " + input)
    var regext = ""

    //Getting the operator
    if(input.contains("+")) {
      regext = "\\+"

    }else if(input.contains("-")){
      regext = "-"
    }
    else if(input.contains("*")){
      regext = "\\*"
    }
    else if(input.contains("/")){
      regext = "/"
    }

    //Split the string to get the operands
    val temp = input.split(regext)

    //check for divide by zero
    if(temp(1).toFloat==0.0 && regext.contains("/"))
      return "Divide by zero error"

    //Calculate result based on the operator
    val result = regext match {

      case "\\+" => temp(0).toFloat + temp(1).toFloat
      case "-"  => temp(0).toFloat - temp(1).toFloat
      case "\\*" => temp(0).toFloat * temp(1).toFloat
      case "/" => temp(0).toFloat / temp(1).toFloat
      case _ => "Invalid operation"

    }
    context.getLogger.log("result = "+result)
    result.toString
  }
}