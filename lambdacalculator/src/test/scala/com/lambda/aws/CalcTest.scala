package com.lambda.aws

import org.junit.Test

/**
  * Testing calculator methods
  */
class CalcTest {
  val calculator = new Calc
    @Test def addTest(): Unit ={
      val expected = 25.0
      assert(calculator.add("10","15")==expected)
    }

  @Test def subTest(): Unit ={
    val expected = -5.0
    assert(calculator.sub("10","15")==expected)
  }

  @Test def multiplyTest(): Unit ={
    val expected = 150.0
    assert(calculator.multiply("10","15")==expected)
  }

  @Test def divideTest(): Unit ={
    val expected = "25.0"
    val actual = calculator.divide("250","10","/")
    print(actual)

    assert(actual.equals(expected))
  }

  @Test def divideByZeroTest(): Unit ={
    assert(calculator.divide("250","0","/").equals("Divide by zero error"))
  }
}
