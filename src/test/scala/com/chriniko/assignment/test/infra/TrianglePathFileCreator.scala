package com.chriniko.assignment.test.infra

import java.io.{BufferedWriter, File, FileWriter}

import scala.util.{Failure, Random, Success, Try}

/*
  Note: utility object in order to generate triangle path file.
 */
object TrianglePathFileCreator {


  def generate(filename: String, noOfRows: Int, randomValue: Boolean = false): Unit = {

    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    try {

      Range(1, noOfRows + 1) foreach { row =>

        Range(1, row + 1) foreach { column =>
          val value = if (!randomValue) column.toString else (Random.nextInt(250) + 1).toString

          val datum = if (column != row) value + " " else value
          bw.write(datum)
        }

        bw.newLine()
      }

    } finally {
      bw.close()
    }

  }


  def main(args: Array[String]): Unit = {
    Try {
      val filename = "/home/chriniko/Desktop/repositorer_capture"
      TrianglePathFileCreator.generate(filename, 500, randomValue = true)
    } match {
      case Success(_) => println("triangle path file generated successfully!")
      case Failure(exception) => println(s"could not generate triangle path file, error is: $exception")
    }
  }

}
