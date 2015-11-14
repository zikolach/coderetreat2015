package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  private val range = -1 to 1

  def evolve(gen: (Int, Int) => Boolean): (Int, Int) => Boolean = {
    (x: Int, y: Int) => range.flatMap(i => range.map(j => (i, j)))
      .filter(p => !(p._1 == 0 && p._2 == 0))
      .count(p => gen(p._1, p._2)) match {
      case 2 | 3 => true
      case _ => false
    }
  }
}
