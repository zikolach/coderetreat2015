package com.example

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  case class Cell(x: Int, y: Int)

  var generation = Set.empty[Cell]

  def evolve(): Unit = {
    var nextGeneration: Set[Cell] = Set.empty

    generation.foreach(cell => {
      nextGeneration += cell
      nextGeneration += Cell(cell.x - 1, cell.y - 1)
      nextGeneration += Cell(cell.x, cell.y - 1)
      nextGeneration += Cell(cell.x + 1, cell.y - 1)
      nextGeneration += Cell(cell.x - 1, cell.y)
      nextGeneration += Cell(cell.x + 1, cell.y)
      nextGeneration += Cell(cell.x - 1, cell.y + 1)
      nextGeneration += Cell(cell.x, cell.y + 1)
      nextGeneration += Cell(cell.x + 1, cell.y + 1)
    })

    nextGeneration.foreach(cell => {
      var cnt = 0
      cnt += generation.count(_ == Cell(cell.x - 1, cell.y - 1))
      cnt += generation.count(_ == Cell(cell.x, cell.y - 1))
      cnt += generation.count(_ == Cell(cell.x + 1, cell.y - 1))
      cnt += generation.count(_ == Cell(cell.x - 1, cell.y))
      cnt += generation.count(_ == Cell(cell.x + 1, cell.y))
      cnt += generation.count(_ == Cell(cell.x - 1, cell.y + 1))
      cnt += generation.count(_ == Cell(cell.x, cell.y + 1))
      cnt += generation.count(_ == Cell(cell.x + 1, cell.y + 1))

      if (cnt < 2) {
        nextGeneration -= cell
      } else if(cnt > 3) {
        nextGeneration -= cell
      }
    })

    generation = nextGeneration
  }

  var space: String = ""

  def print(): Unit = {

    if (generation.nonEmpty) {
      space = "x"
    }


//    println(space)
  }

}
