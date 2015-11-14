package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  private val range = -1 to 1

  val neighbours = for {
    x <- range
    y <- range
    pos = (x, y)
    if pos !=(0, 0)
  } yield pos

  type Generation = (Int, Int) => Boolean

  def evolve(gen: Generation): Generation = (x: Int, y: Int) =>
    neighbours.count(p => gen(x + p._1, y + p._2)) match {
      case 2 | 3 => true
      case _ => false
    }

}
