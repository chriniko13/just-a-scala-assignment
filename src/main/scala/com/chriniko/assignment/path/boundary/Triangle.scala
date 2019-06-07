package com.chriniko.assignment.path.boundary

import com.chriniko.assignment.path.entity.{MatrixNode, Node}

import scala.annotation.tailrec
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Triangle {

  def resolveMinimumPath(matrixNodes: Array[ArrayBuffer[MatrixNode]]): (List[Int], Int) = resolvePath(matrixNodes, _ < _)

  def resolvePath(matrixNodes: Array[ArrayBuffer[MatrixNode]], comparison: (Int, Int) => Boolean): (List[Int], Int) = {
    val rows = matrixNodes.length - 1
    var i = rows - 1

    while (i >= 0) {
      var j = 0
      while (j <= i) {

        if (comparison(matrixNodes(i + 1)(j).value, matrixNodes(i + 1)(j + 1).value)) {
          matrixNodes(i)(j) += matrixNodes(i + 1)(j)
        } else {
          matrixNodes(i)(j) += matrixNodes(i + 1)(j + 1)
        }

        j += 1
      }
      i -= 1
    }

    val root = matrixNodes(0)(0)
    (calculatePath(root), root.value)
  }

  private def calculatePath(root: MatrixNode): List[Int] = {

    @tailrec
    def helper(matrixNode: MatrixNode, acc: List[Int]): List[Int] = {
      matrixNode.next match {
        case Some(n) => helper(n, acc :+ matrixNode.previousValue.get)
        case None => acc :+ matrixNode.value
      }
    }

    helper(root, List())
  }

  // -------------------------------------------------------------------------------------------------------------------

  def resolveMinimumPath(paths: List[List[Node]]): (List[Int], Int) = resolveRequiredPath(paths)(_._2 < _._2)

  def resolveMaximumPath(paths: List[List[Node]]): (List[Int], Int) = resolveRequiredPath(paths)(_._2 > _._2)

  def resolveRequiredPath(paths: List[List[Node]])
                         (comparison: ((_, Int), (_, Int)) => Boolean): (List[Int], Int) =
    resolvePathsCost(paths, 0, _ + _).sortWith(comparison).head

  def resolvePathsCost(paths: List[List[Node]], acc: Int, cost: (Int, Int) => Int): List[(List[Int], Int)] =
    paths.map(p => (p.map(_.!(acc)), p.map(_.!(acc)).foldLeft(acc)(cost)))

  def calculatePaths(root: Node): List[List[Node]] = {

    def helper(n: Node, accumulator: ListBuffer[List[Node]], current: List[Node]): List[List[Node]] = {
      n.children match {
        case children if children.nonEmpty =>
          children.foreach { child =>
            helper(child, accumulator, current :+ child)
          }
        case _ => accumulator += current
      }
      accumulator.toList
    }

    helper(root, new ListBuffer[List[Node]](), List(root))
  }

}
