package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  case class Cell(x: Int, y: Int)

  def evolve(gen: Set[Cell]): Set[Cell] = {

    def cellEvolve(cell: Cell): Option[Cell] = {
      val ifCellAlive = gen.contains(cell)

      val aliveNeighbours = gen.intersect(neighbours(cell))
      if (aliveNeighbours.size == 2 || aliveNeighbours.size == 3) {
        Some(cell)
      } else {
        None
      }
    }

    val cellsToCheck = gen.flatMap(cell => neighbours(cell) + cell)

    val space = cellsToCheck.map { case cell: Cell => cellEvolve(cell) }

    space.filter(_.nonEmpty).map(_.get)
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

}
