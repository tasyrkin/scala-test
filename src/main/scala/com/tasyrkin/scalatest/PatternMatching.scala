package com.tasyrkin.scalatest


object StringExpander {

  private[scalatest] val singleDigitToSting = Array("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
  private[scalatest] val teenDigitToSting = Array("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
  private[scalatest] val tensDigitToSting = Array("", "", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety")


  def expand(str: String): Int = {
    0
  }

  private[scalatest] def isAllDigits(str: String): Boolean = str forall Character.isDigit

  private[scalatest] def parseTriple(number: Int): String = {
    if (number < 0 || number >= 1000){
      throw new IllegalArgumentException(s"The number is out of range [$number]")
    } else {
      val hundreds = number / 100
      val hunderdsStr = hundreds match {
        case 0 => ""
        case i => s"${singleDigitToSting(i)} hundred" + (if (hundreds > 1) "s" else "")
      }
      val tens = number % 100
      val tensStr = tens match {
        case 0 if hundreds > 0 => ""
        case i if i >= 0 && i <= 9 => singleDigitToSting(i)
        case i if i >= 10 && i <= 19 => teenDigitToSting(i - 10)
        case i => s"${tensDigitToSting(i/10)}" + (i%10 match {
          case 0 => ""
          case j => " " + singleDigitToSting(j)
        })
      }
      s"$hunderdsStr${if (hundreds > 0 && tens > 0) " " else ""}$tensStr"
    }
  }

}

object PatternMatchingTest extends App {

  if (args.length < 1) {
    throw new IllegalArgumentException("Program's argument is missing")
  }
  else {
    println(StringExpander.expand(args(0)))
  }

}
