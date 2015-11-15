package com.example

import scala.language.{implicitConversions, postfixOps}
import scala.util.Random

object Hello {
  def main(args: Array[String]): Unit = {
    println("Game of Life - Global Day of Coderetreat 2015")

    val initial: Generation = (x, y) => Random.nextBoolean()
    var current = initial
    var rect = Rect(-11, -2, 11, 2)

    Stream.from(1).foreach(n => {
      println(s"gen $n")

      val mat = current.mat(rect)

      println(GenToStr(mat).toString(alive ="O", dead = " ", sep = " "))

      rect = Rect(
        mat.map(_._1).min - 1, mat.map(_._2).min - 1,
        mat.map(_._1).max + 1, mat.map(_._2).max + 1
      )
      current = evolve((x, y) => mat(x, y))

      Thread.sleep(100)
    })

  }

  private val range = -1 to 1

  private val neighbours = for {
    x <- range
    y <- range
    pos = (x, y)
    if pos !=(0, 0)
  } yield pos

  type Generation = (Int, Int) => Boolean

  /**
    * Implementation of GoL rules
    * @param gen previos generaton
    * @return new generation
    */
  def evolve(gen: Generation): Generation = (x: Int, y: Int) =>
    neighbours.count(p => gen(x + p._1, y + p._2)) match {
      case 2 => gen(x, y)
      case 3 => true
      case _ => false
    }

  // Following are utility classes
  case class Rect(x1: Int, y1: Int, x2: Int, y2: Int)

  object Rect {
    implicit def apply(p: (Int, Int, Int, Int)): Rect = Rect(p._1, p._2, p._3, p._4)
  }

  implicit class GenToSet(gen: Generation) {
    /**
      * Materialize generation
      * @param rect coordinate rectangle
      * @return set of coordinates living cells'
      */
    def mat(rect: Rect): Set[(Int, Int)] = {
      for {
        x <- rect.x1 to rect.x2
        y <- rect.y1 to rect.y2
        alive = gen(x, y)
        if alive
      } yield (x, y)
    } toSet
  }

  implicit class StrToGen(str: String) {
    /**
      * Constructs generation from string
      * @param dx offset x
      * @param dy offset y
      * @return generation
      */
    def gen(dx: Int = 0, dy: Int = 0): Generation = { (x: Int, y: Int) =>
      val cells = str.stripMargin.split('\n').map(_.toCharArray)
      try {
        cells(y - dy)(x - dx) == 'x'
      } catch {
        case _: Throwable => false
      }
    }
  }

  implicit class GenToStr(mat: Set[(Int, Int)]) {
    /**
      * Converts generation to string
      * @return string representation
      */
    def toString(alive: String = "x", dead: String = ".", sep: String = ""): String = {
      val yy = mat.map(_._2)
      (yy.min to yy.max).map { y =>
        val xx = mat.map(_._1)
        (xx.min to xx.max).map { x =>
          mat.contains(x, y) match {
            case true => s"$sep$alive$sep"
            case _ => s"$sep$dead$sep"
          }
        } mkString
      } mkString "\n"
    }

    override def toString = toString()
  }

}
