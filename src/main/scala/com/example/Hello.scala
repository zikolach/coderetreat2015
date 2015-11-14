package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  case class Cell(x: Int, y: Int)

  def evolve(gen: Set[Cell]): Set[Cell] = {
    Set.empty
  }

}
