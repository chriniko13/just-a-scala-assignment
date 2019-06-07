package com.chriniko.assignment

import com.chriniko.assignment.path.boundary.PathCalculator

object App {

  def main(args: Array[String]): Unit = {

    val resourceName = "sample_file_4.txt"

    val displayResult = (minimumPath: (List[Int], Int)) => println(s"Minimum path is: ${minimumPath._1.mkString(" + ")} = ${minimumPath._2}")

    val minimumPath = PathCalculator.calculate(resourceName)
    displayResult(minimumPath)
  }

}