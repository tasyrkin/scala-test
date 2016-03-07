
object PatternMatchingTest extends App {
  if (args.size < 1)
    throw new IllegalArgumentException("Program's argument is missing")
  else{
    args.foreach(println(_))
    //val arg = args(1)
    //println(arg)
  }
}
