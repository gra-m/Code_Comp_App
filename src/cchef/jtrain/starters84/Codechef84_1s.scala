package cchef.jtrain.starters84

import scala.io.Source

object Codechef84_1s {
  val inputPath = "/home/kali/Documents/001_CC/in.txt"
  val outputPath = "/home/kali/Documents/001_CC/out.txt"
  private val source = Source.fromFile(inputPath)
  private val input = source.getLines().toList

  def main(args: Array[String]): Unit = {

    source.close()
    printFileContents(input)

    def printFileContents(contents: List[String]): Unit =
      contents.foreach(println)

    val t = input.head.toInt
    var x = t

    while(x > 0) {
      x -= 1
      val kase = t-x
      println(s"CASE: $kase")
      val caseLine1 = input(kase)
      println(caseLine1)

    }

  }

  /*object util {
   // this is wrongly def at mom
    def returnIntList(str: String): List[Int] = {
      val strList = str.trim.split("\\s+").toList
      val intList = strList.foreach(str ⇒ str.toInt)
      intList
    }


  } */

}


