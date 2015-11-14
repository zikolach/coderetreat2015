package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  def evolve(gen: (Int, Int) => Boolean): (Int, Int) => Boolean = {

    (x: Int, y: Int) => {

      val neighbourStates = for {
        i <- -1 to 1
        j <- -1 to 1
        if !(i == 0 && j == 0)
      } yield gen(i, j)

      val livingCnt = neighbourStates.count(state => state)

      livingCnt match {
        case 2 | 3 => true
        case _ => false
      }
    }
  }
}
