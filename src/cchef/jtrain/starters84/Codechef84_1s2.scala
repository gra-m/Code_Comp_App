package cchef.jtrain.starters84

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


object Codechef84_1s2 {
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

    while (x > 0) {
      x -= 1
      val kase = t - x
      println(s"CASE: $kase")
      val caseLine1 = input(kase)
      println(caseLine1)

      println(Util.gof("gof"))

    }

  }
}

private object Util {
  def gof(str: String): List[Int] = {
    var list = List(1, 2, 3)
    list
  }
}

private object GetterInner {
  private var list = List(1, 2, 3)
  private var arrayBuffer = ArrayBuffer(3, 2, 1)
  
  /*def chooseType(): Unit = {
    var offline = true
    if offline
      try
        list = List(3, 4, 5)
      catch

    
  }*/


}







