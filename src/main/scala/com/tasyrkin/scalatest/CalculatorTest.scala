package com.tasyrkin.scalatest

class CalculatorBase[A](brand: String) {
  def add(x: Int, y: Int) = x + y

  def f(x: A) = x.toString
}

case class Calculator(brand: String, model: String) extends CalculatorBase[Int](brand)

object CalculatorTest extends App {
  val calc = Calculator("HP", "20b")
  println(calc.add(10, 10))
  println(calc.f(10))
  println(calc)

  println(getType(calc))
  println(getType(Calculator("Huawei", "_")))

  def getType(calculator: Calculator): String = {
    calculator match {
      case Calculator("HP", "20b") => "scientific"
      case Calculator("Samsung", "1a") => "financial"
      case Calculator("Casio", "AB") => "simple"
      //rebinding matching value with another name
      case c@Calculator(brand, model) => s"$c is of unknown type"
    }
  }
}
