package com.chriniko.assignment.path.boundary

import org.scalatest.FlatSpec

class TriangleTest extends FlatSpec {

  "Triangle" should "resolve minimum path" in {

    // given
    val root = TriangleEnvironment.generateSampleTriangle()

    val calculatedPaths = Triangle.calculatePaths(root)
    assert(calculatedPaths.size == 8)

    // when
    val minimumPath = Triangle.resolveMinimumPath(calculatedPaths)

    // then
    assert(minimumPath === (List(7, 6, 3, 2), 18))
  }

  "Triangle" should "resolve minimum path read from file" in {

    // given
    val root = TriangleEnvironment.generateTriangleFromResource("sample_file.txt")

    val calculatedPaths = Triangle.calculatePaths(root)
    assert(calculatedPaths.size == 8)

    // when
    val minimumPath = Triangle.resolveMinimumPath(calculatedPaths)

    // then
    assert(minimumPath === (List(7, 6, 3, 2), 18))
  }

  "Triangle" should "resolve maximum path" in {

    // given
    val root = TriangleEnvironment.generateSampleTriangle()

    val calculatedPaths = Triangle.calculatePaths(root)
    assert(calculatedPaths.size == 8)

    // when
    val maximumPath = Triangle.resolveMaximumPath(calculatedPaths)

    // then
    assert(maximumPath === (List(7, 6, 8, 10), 31))
  }

  "Triangle" should "resolve path costs based on needs" in {

    // given
    val root = TriangleEnvironment.generateSampleTriangle()

    val calculatedPaths = Triangle.calculatePaths(root)
    assert(calculatedPaths.size == 8)


    // when
    val pathCosts = Triangle.resolvePathsCost(calculatedPaths, 1, _ * _)


    // then
    assert(pathCosts.size == 8)

    assert(
      pathCosts === List(
        (List(7, 6, 3, 11), 1386),
        (List(7, 6, 3, 2), 252),
        (List(7, 6, 8, 2), 672),
        (List(7, 6, 8, 10), 3360),
        (List(7, 3, 8, 2), 336),
        (List(7, 3, 8, 10), 1680),
        (List(7, 3, 5, 10), 1050),
        (List(7, 3, 5, 9), 945))
    )

  }


}
