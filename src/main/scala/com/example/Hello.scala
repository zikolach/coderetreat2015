package com.example

import scala.language.{implicitConversions, postfixOps}

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
  }

  case class Rect(x1: Int, y1: Int, x2: Int, y2: Int)

  object Rect {
    implicit def apply(p: (Int, Int, Int, Int)): Rect = Rect(p._1, p._2, p._3, p._4)
  }

  private val range = -1 to 1

  val neighbours = for {
    x <- range
    y <- range
    pos = (x, y)
    if pos !=(0, 0)
  } yield pos

  type Generation = (Int, Int) => Boolean

  implicit class GenToSet(gen: Generation) {
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
    def toString(r: Rect): String = {
      val set = gen.mat(r)
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

  def evolve(gen: Generation): Generation = (x: Int, y: Int) =>
    neighbours.count(p => gen(x + p._1, y + p._2)) match {
      case 2 => gen(x, y)
      case 3 => true
      case _ => false
    }
}
