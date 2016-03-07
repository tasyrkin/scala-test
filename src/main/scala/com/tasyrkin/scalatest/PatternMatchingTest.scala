package com.tasyrkin.scalatest

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory


object StringExpander {

  private[scalatest] val minus = "minus"
  private[scalatest] val hundred = "hundred"
  private[scalatest] val dash = "-"
  private[scalatest] val empty = ""
  private[scalatest] val space = " "
  private[scalatest] val singleDigitToSting = Array("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
  private[scalatest] val teenDigitToSting = Array("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
  private[scalatest] val tensDigitToSting = Array(empty, empty, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
  private[scalatest] val numberCategoryToSting = Array(empty, "thousand", "million", "milliard", "trillion", "quadrillion", "quintillion")

  private[scalatest] val LOG = Logger(LoggerFactory.getLogger(StringExpander.getClass))

  /**
    * Expands a number into words. For example, "123" will be expanded into "one hundred twenty three"
    * The supported number range is [-10^21+1; 10^21-1]
    *
    * @param numberStr a positive or negative number, alpha characters are not allowed
    * @return a word representation of a numberStr
    */
  def expand(numberStr: String): String = {
    val isNegativeNumber = numberStr startsWith dash
    val positiveNumberStr = if (isNegativeNumber) numberStr drop 1 else numberStr

    if(!isValidNumber(positiveNumberStr)) {
      throw new IllegalArgumentException(s"Invalid number [$numberStr]")
    } else {
      stringOrEmpty(minus + space, isNegativeNumber) + expandCategory(positiveNumberStr, 0)
    }
  }

  private[scalatest] def isValidNumber(positiveNumberStr: String) = {
    val maxCategoryIdx = positiveNumberStr.length / 3 + (if(positiveNumberStr.length % 3 != 0) 1 else 0) - 1

    isAllDigits(positiveNumberStr) && maxCategoryIdx < numberCategoryToSting.length
  }

  private[scalatest] def spaceOrEmpty(flag: Boolean) = stringOrEmpty(space, flag)
  private[scalatest] def stringOrEmpty(str: String, flag: Boolean) = if (flag) str else empty

  private[scalatest] def expandCategory(numberStr: String, category: Int): String = {
    if (numberStr.length == 0){
      empty
    } else {
      val currentInt = Integer.parseInt(numberStr takeRight 3)

      val categoryStr = numberCategoryToSting(category) + stringOrEmpty("s", category > 0 && currentInt > 1)

      val currentStr = currentInt match {
        case i if i == 0 => if (numberStr.length == 1) parseTriple(i) else empty
        case i => parseTriple(i) + spaceOrEmpty(categoryStr.length > 0) + categoryStr

      }

      val restStr = numberStr dropRight 3
      val nextStr = expandCategory(restStr, category + 1)
      val spaceBetween = spaceOrEmpty(nextStr.length > 0 && currentStr.length > 0)

      s"$nextStr$spaceBetween$currentStr"
    }
  }

  private[scalatest] def isAllDigits(str: String): Boolean = str forall Character.isDigit

  private[scalatest] def parseTriple(number: Int): String = {
    if (number < 0 || number >= 1000){
      throw new IllegalArgumentException(s"The number is out of range [$number]")
    } else {
      val hundreds = number / 100
      val hundredsStr = hundreds match {
        case 0 => empty
        case i => s"${singleDigitToSting(i)} $hundred" + stringOrEmpty("s", hundreds > 1)
      }
      val tens = number % 100
      val tensStr = tens match {
        case 0 if hundreds > 0 => empty
        case i if i >= 0 && i <= 9 => singleDigitToSting(i)
        case i if i >= 10 && i <= 19 => teenDigitToSting(i - 10)
        case i => s"${tensDigitToSting(i/10)}" + (i%10 match {
          case 0 => empty
          case j => space + singleDigitToSting(j)
        })
      }

      val spaceBetween = spaceOrEmpty(hundreds > 0 && tens > 0)

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
