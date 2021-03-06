package com.tasyrkin.scalatest

import org.scalatest.{Matchers, FlatSpec}

class StringExpanderSpec extends FlatSpec with Matchers {

  "A number" should "be expanded into a correct string" in {
    StringExpander.parseTriple(0) should be ("zero")
    StringExpander.parseTriple(1) should be ("one")
    StringExpander.parseTriple(2) should be ("two")
    StringExpander.parseTriple(3) should be ("three")
    StringExpander.parseTriple(4) should be ("four")
    StringExpander.parseTriple(5) should be ("five")
    StringExpander.parseTriple(6) should be ("six")
    StringExpander.parseTriple(7) should be ("seven")
    StringExpander.parseTriple(8) should be ("eight")
    StringExpander.parseTriple(9) should be ("nine")
    StringExpander.parseTriple(10) should be ("ten")
    StringExpander.parseTriple(11) should be ("eleven")
    StringExpander.parseTriple(12) should be ("twelve")
    StringExpander.parseTriple(18) should be ("eighteen")
    StringExpander.parseTriple(20) should be ("twenty")
    StringExpander.parseTriple(22) should be ("twenty two")
    StringExpander.parseTriple(27) should be ("twenty seven")
    StringExpander.parseTriple(30) should be ("thirty")
    StringExpander.parseTriple(35) should be ("thirty five")
    StringExpander.parseTriple(39) should be ("thirty nine")
    StringExpander.parseTriple(40) should be ("forty")
    StringExpander.parseTriple(41) should be ("forty one")
    StringExpander.parseTriple(50) should be ("fifty")
    StringExpander.parseTriple(53) should be ("fifty three")
    StringExpander.parseTriple(60) should be ("sixty")
    StringExpander.parseTriple(66) should be ("sixty six")
    StringExpander.parseTriple(70) should be ("seventy")
    StringExpander.parseTriple(72) should be ("seventy two")
    StringExpander.parseTriple(80) should be ("eighty")
    StringExpander.parseTriple(84) should be ("eighty four")
    StringExpander.parseTriple(90) should be ("ninety")
    StringExpander.parseTriple(99) should be ("ninety nine")
    StringExpander.parseTriple(100) should be ("one hundred")
    StringExpander.parseTriple(111) should be ("one hundred eleven")
    StringExpander.parseTriple(154) should be ("one hundred fifty four")
    StringExpander.parseTriple(188) should be ("one hundred eighty eight")
    StringExpander.parseTriple(200) should be ("two hundreds")
    StringExpander.parseTriple(208) should be ("two hundreds eight")
    StringExpander.parseTriple(290) should be ("two hundreds ninety")
    StringExpander.parseTriple(300) should be ("three hundreds")
    StringExpander.parseTriple(310) should be ("three hundreds ten")
    StringExpander.parseTriple(400) should be ("four hundreds")
    StringExpander.parseTriple(411) should be ("four hundreds eleven")
    StringExpander.parseTriple(418) should be ("four hundreds eighteen")
    StringExpander.parseTriple(500) should be ("five hundreds")
    StringExpander.parseTriple(555) should be ("five hundreds fifty five")
    StringExpander.parseTriple(600) should be ("six hundreds")
    StringExpander.parseTriple(605) should be ("six hundreds five")
    StringExpander.parseTriple(700) should be ("seven hundreds")
    StringExpander.parseTriple(789) should be ("seven hundreds eighty nine")
    StringExpander.parseTriple(800) should be ("eight hundreds")
    StringExpander.parseTriple(825) should be ("eight hundreds twenty five")
    StringExpander.parseTriple(900) should be ("nine hundreds")
    StringExpander.parseTriple(999) should be ("nine hundreds ninety nine")
  }

  "A number string" should "be expanded into correct string" in {
    StringExpander.expand("-999999999999999999999") should be ("minus nine hundreds ninety nine quintillions nine hundreds ninety nine quadrillions nine hundreds ninety nine trillions nine hundreds ninety nine milliards nine hundreds ninety nine millions nine hundreds ninety nine thousands nine hundreds ninety nine")
    StringExpander.expand("-9125005") should be ("minus nine millions one hundred twenty five thousands five")
    StringExpander.expand("-5005") should be ("minus five thousands five")
    StringExpander.expand("-200") should be ("minus two hundreds")
    StringExpander.expand("-19") should be ("minus nineteen")
    StringExpander.expand("-1") should be ("minus one")
    StringExpander.expand("0") should be ("zero")
    StringExpander.expand("10") should be ("ten")
    StringExpander.expand("100") should be ("one hundred")
    StringExpander.expand("678") should be ("six hundreds seventy eight")
    StringExpander.expand("1000") should be ("one thousand")
    StringExpander.expand("2000") should be ("two thousands")
    StringExpander.expand("5555") should be ("five thousands five hundreds fifty five")
    StringExpander.expand("15555") should be ("fifteen thousands five hundreds fifty five")
    StringExpander.expand("124124") should be ("one hundred twenty four thousands one hundred twenty four")
    StringExpander.expand("2124124") should be ("two millions one hundred twenty four thousands one hundred twenty four")
    StringExpander.expand("99124124") should be ("ninety nine millions one hundred twenty four thousands one hundred twenty four")
    StringExpander.expand("199124124") should be ("one hundred ninety nine millions one hundred twenty four thousands one hundred twenty four")
    StringExpander.expand("1000000000") should be ("one milliard")
    StringExpander.expand("9000000000") should be ("nine milliards")
    StringExpander.expand("10000000000") should be ("ten milliards")
    StringExpander.expand("1000000000000") should be ("one trillion")
    StringExpander.expand("5000000000001") should be ("five trillions one")
    StringExpander.expand("12345678910111213") should be ("twelve quadrillions three hundreds forty five trillions six hundreds seventy eight milliards nine hundreds ten millions one hundred eleven thousands two hundreds thirteen")
    StringExpander.expand("999999999999999999999") should be ("nine hundreds ninety nine quintillions nine hundreds ninety nine quadrillions nine hundreds ninety nine trillions nine hundreds ninety nine milliards nine hundreds ninety nine millions nine hundreds ninety nine thousands nine hundreds ninety nine")
  }

  it should "throw IllegalArgumentException when negative number is provided" in {
    a [IllegalArgumentException] should be thrownBy {
      StringExpander.parseTriple(-1)
    }
  }

  it should "throw IllegalArgumentException when out of range number is provided" in {
    a [IllegalArgumentException] should be thrownBy {
      StringExpander.parseTriple(1000)
    }
  }

  it should "throw IllegalArgumentException when a number is out of range" in {
    a [IllegalArgumentException] should be thrownBy {
      StringExpander.expand("1000000000000000000000")
    }
  }
}
