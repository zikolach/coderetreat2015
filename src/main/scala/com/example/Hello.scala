package com.example

import scala.language.{implicitConversions, postfixOps}

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
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

  implicit class GenToStr(gen: Generation) {
    /**
      * Converts generation to string
      * @param rect rectangle
      * @return string representation
      */
    def toString(rect: Rect): String = {
      val set = gen.mat(rect)
      val xx = set.map(_._1)
      (xx.min to xx.max).map { x =>
        val yy = set.map(_._2)
        (yy.min to yy.max).map { y =>
          gen(x, y) match {
            case true => 'x'
            case _ => '.'
          }
        } mkString
      } mkString "\n"
    }
  }

}
