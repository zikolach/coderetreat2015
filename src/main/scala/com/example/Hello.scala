package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  trait Neighbourship

  case object ZeroNeighbours extends Neighbourship
  case object OneNeighbours extends Neighbourship

  trait State
  case object Dead extends State
  case object Alive extends State

  trait Cell

  case object AliveWithoutNeighbours extends Cell
  case object DeadWithoutNeighbours extends Cell
  case object AliveWith2Neighbours extends  Cell

  def evolve(cell: Cell): State = cell match {
    case AliveWithoutNeighbours => Dead
    case DeadWithoutNeighbours => Dead
    case AliveWith2Neighbours => Alive
    case _ => ???
  }

  def withNeighborship(s: State, n: Neighbourship): Cell = (s, n) match {
    case (Dead, ZeroNeighbours) => DeadWithoutNeighbours
    case _ => ???
  }
}
