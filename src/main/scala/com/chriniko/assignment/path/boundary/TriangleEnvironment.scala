package com.chriniko.assignment.path.boundary

import com.chriniko.assignment.file.boundary.Files
import com.chriniko.assignment.path.entity.{MatrixNode, Node}

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.language.postfixOps

object TriangleEnvironment {

  def generateTriangleFromFileLines(fileLines: List[String]): Array[ArrayBuffer[MatrixNode]] = {

    val lines: List[List[Int]] = fileLines.map(line => line.split(" ").map(_.toInt).toList)

    val maxColumns = lines.map(_.size).max

    val matrix: List[List[Int]] = lines.map(l => l ++: List.fill(maxColumns - l.size)(0))

    matrix.map { row => ArrayBuffer(row map (MatrixNode(_))) flatten }.toArray
  }

  def generateTriangleFromResource(resourceName: String): Node = {

    val lines = Files.extractLinesFromResource(resourceName)

    var aboveNodes = new ListBuffer[(Int, Node)]()
    var linesCount = 0

    var root = Node.empty()

    for (line <- lines) {

      linesCount += 1
      val nodeValues = line.split(" ")

      if (linesCount != nodeValues.size) {
        throw new IllegalStateException("malformed file provided")
      }

      linesCount match {
        case 1 =>
          root = Node(nodeValues(0).toInt)
          aboveNodes += ((0, root))

        case _ =>
          val nodes = Range(0, nodeValues.size).map { idx => (idx, Node(nodeValues(idx).toInt)) }.toList

          aboveNodes.foreach(aboveNode => {
            val idx = aboveNode._1

            val belowNode = nodes(idx)._2
            val belowNodeOneShiftRight = nodes(idx + 1)._2

            aboveNode._2 ++> Set(belowNode, belowNodeOneShiftRight)
            belowNode <+ aboveNode._2
            belowNodeOneShiftRight <+ aboveNode._2
          })

          aboveNodes.clear()
          aboveNodes ++= nodes
      }
    }

    root
  }

  def generateSampleTriangle(): Node = {
    val n1 = Node(7)

    val n2 = Node(6)
    val n3 = Node(3)

    val n4 = Node(3)
    val n5 = Node(8)
    val n6 = Node(5)

    val n7 = Node(11)
    val n8 = Node(2)
    val n9 = Node(10)
    val n10 = Node(9)

    n1 ++> Set(n2, n3)

    n2 ++> Set(n4, n5)
    n3 ++> Set(n5, n6)

    n4 ++> Set(n7, n8)
    n5 ++> Set(n8, n9)
    n6 ++> Set(n9, n10)

    n1
  }

}
