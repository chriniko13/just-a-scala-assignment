package com.chriniko.assignment.path.entity

case class Node(var parents: Set[Node] = Set.empty,
                value: Option[Int],
                var children: Set[Node] = Set.empty) {

  def +>(child: Node): (Node, Node) = {
    this.children += child
    child.parents += this
    (this, child)
  }

  def ++>(children: Set[Node]): (Node, Set[Node]) = (this, children.map(+>).map(_._2))

  def <+(parent: Node): (Node, Node) = {
    this.parents += parent
    parent.children += this
    (this, parent)
  }

  def <++(parents: Set[Node]): (Node, Set[Node]) = (this, parents.map(<+).map(_._2))

  def !(fallback: Int): Int = this.value.getOrElse(fallback)

  override def toString: String =
    s"[parents = ${stringify(parents)}, value = $value, children = ${stringify(children)}]"

  private def stringify(nodes: Set[Node]): String =
    Some(nodes.map(_.value).mkString(",")).filter(_.nonEmpty).getOrElse("{}")

}


object Node {
  def apply(v: Int): Node = Node(value = Some(v))

  def empty(): Node = Node(value = None)
}