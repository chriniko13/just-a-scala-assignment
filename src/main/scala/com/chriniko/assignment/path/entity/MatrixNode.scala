package com.chriniko.assignment.path.entity

case class MatrixNode(var value: Int,
                      var previousValue: Option[Int],
                      var next: Option[MatrixNode]) {

  def +=(other: MatrixNode): MatrixNode = {
    previousValue = Some(value)
    next = Some(other)

    value += other.value
    this
  }

}

object MatrixNode {
  def apply(v: Int): MatrixNode = MatrixNode(v, None, None)
}
