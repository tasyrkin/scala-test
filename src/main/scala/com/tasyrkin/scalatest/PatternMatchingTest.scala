package com.tasyrkin.scalatest


object StringExpander {

  private[scalatest] val singleDigitToSting = Array("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
  private[scalatest] val teenDigitToSting = Array("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
  private[scalatest] val tensDigitToSting = Array("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
  private[scalatest] val numberCategoryToSting = Array("", "thousand", "million", "milliard", "trillion", "quadrillion", "quintillion")


  private[scalatest] def expandCategory(str: String, category: Int): String = {
    if (str.length == 0){
      ""
    } else {
      val currentInt = Integer.parseInt(str takeRight 3)
      val rest = str dropRight 3
      val categoryStr = numberCategoryToSting(category) + (if (category > 0 && currentInt > 1) "s" else "")

      val currentStr = currentInt match {
        case i if i == 0 => if(str.length == 1) parseTriple(i) else ""
        case i => parseTriple(i) + (if (categoryStr.length > 0) " " else "") + categoryStr

      }
      val nextStr = expandCategory(rest, category + 1)
      val spaceBetween = if(nextStr.length > 0 && currentStr.length > 0) " " else ""
      s"$nextStr$spaceBetween$currentStr"
    }
  }

  def expand(str: String): String = {
    if(!isAllDigits(str)) {
      throw new IllegalArgumentException(s"Not a number [$str]")
    } else {
      expandCategory(str, 0)
    }
  }

  private[scalatest] def isAllDigits(str: String): Boolean = str forall Character.isDigit

  private[scalatest] def parseTriple(number: Int): String = {
    if (number < 0 || number >= 1000){
      throw new IllegalArgumentException(s"The number is out of range [$number]")
    } else {
      val hundreds = number / 100
      val hundredsStr = hundreds match {
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
      val spaceBetween = if (hundreds > 0 && tens > 0) " " else ""
      s"$hundredsStr$spaceBetween$tensStr"
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
