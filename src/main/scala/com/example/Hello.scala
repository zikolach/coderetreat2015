package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  private val range = -1 to 1

  type Generation = (Int, Int) => Boolean

  def evolve(gen: Generation): Generation = (x: Int, y: Int) =>
    range.flatMap(i => range.map(j => (i, j)))
      .filter(_ !=(0, 0))
      .count(p => gen(p._1, p._2)) match {
      case 2 | 3 => true
      case _ => false
    }

}
