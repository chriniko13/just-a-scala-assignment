package com.chriniko.assignment.path.boundary

import com.chriniko.assignment.file.boundary.Files
import com.chriniko.assignment.path.entity.MatrixNode

import scala.collection.mutable.ArrayBuffer

object PathCalculator {

  def calculate(resourceName: String): (List[Int], Int) = {

    val lines = Files.extractLinesFromResource(resourceName)

    lines.size match {
      case noOfLines if noOfLines <= 10 => /* Note: not so big input file, use simple recursive solution */
        val root = TriangleEnvironment.generateTriangleFromResource(resourceName)
        val paths = Triangle.calculatePaths(root)
        Triangle.resolveMinimumPath(paths)

      case _ => /* Note: big input file, use bottom up dynamic programming approach */
        val matrixNodes: Array[ArrayBuffer[MatrixNode]] = TriangleEnvironment.generateTriangleFromFileLines(lines)
        Triangle.resolveMinimumPath(matrixNodes)

    }

  }

}
