package com.chriniko.assignment.path.entity

import org.scalatest.FunSuite

class NodeTest extends FunSuite {

  test("testToString_case_1") {

    // given
    val n = Node(Set(Node(1)), Some(2), Set(Node(3)))

    // when
    val s = n.toString

    // then
    assert(s == "[parents = Some(1), value = Some(2), children = Some(3)]")

  }


  test("testToString_case_2") {

    // given
    val n = Node(Set.empty, Some(2), Set(Node(3)))

    // when
    val s = n.toString

    // then
    assert(s == "[parents = {}, value = Some(2), children = Some(3)]")

  }

  test("test<++") {

    // given
    val n = Node(Set.empty, Some(2), Set(Node(3)))


    // when
    n <++ Set(Node(1), Node(2))

    // then
    assert(n.parents.size == 2)

  }
}
