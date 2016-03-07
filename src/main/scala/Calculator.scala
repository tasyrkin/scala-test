
class Calculator[A](brand: String) {
  def add(x: Int, y: Int) = x + y

  def f(x: A) = x.toString
}

object CalculatorTest extends App {
  val calc = new Calculator[Int]("HP")
  println(calc.add(10, 10))
  println(calc.f(10))
}
