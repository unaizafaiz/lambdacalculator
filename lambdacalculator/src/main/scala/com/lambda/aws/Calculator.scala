package com.lambda.aws

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler


class Calculator extends RequestHandler[String, String] {

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

    result.toString
  }
}