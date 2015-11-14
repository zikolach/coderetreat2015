package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  case class Cell(x: Int, y: Int)

  def evolve(gen: Set[Cell]): Set[Cell] = {
    if (gen.size > 1) ???
    Set.empty
  }

  def neighbours(cell: Cell): Set[Cell] = {
    Set(
      Cell(cell.x - 1, cell.y - 1),
      Cell(cell.x, cell.y - 1),
      Cell(cell.x + 1, cell.y - 1),
      Cell(cell.x - 1, cell.y),
      Cell(cell.x + 1, cell.y),
      Cell(cell.x - 1, cell.y + 1),
      Cell(cell.x, cell.y + 1),
      Cell(cell.x + 1, cell.y + 1)

    )
  }

  def getLivingNeighbours(gen: Set[Cell], cell: Cell): Set[Cell] = {
    gen.intersect(neighbours(cell))
  }
}
