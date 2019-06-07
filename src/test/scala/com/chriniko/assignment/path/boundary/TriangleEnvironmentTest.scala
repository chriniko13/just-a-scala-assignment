package com.chriniko.assignment.path.boundary

import org.scalatest.FunSuite

class TriangleEnvironmentTest extends FunSuite {

  test("testGenerateTriangleFromResource_small_triangle_case") {

    // when
    val root = TriangleEnvironment.generateTriangleFromResource("sample_file.txt")

    // then
    assert(Triangle.calculatePaths(root).size == 8)
  }

  test("testGenerateTriangleFromResource_normal_triangle_case_1") {

    // when
    val root = TriangleEnvironment.generateTriangleFromResource("sample_file_2.txt")

    // then
    val paths = Triangle.calculatePaths(root)
    assert(paths.size == 512)

  }

  test("testGenerateTriangleFromResource_malformed_triangle_case") {

    // when
    val thrown = intercept[IllegalStateException] {
      TriangleEnvironment.generateTriangleFromResource("malformed_file.txt")
    }

    // then
    assert(thrown.getMessage == "malformed file provided")

  }


  test("testGenerateTriangleFromResource_normal_triangle_case_2") {

    // when
    val root = TriangleEnvironment.generateTriangleFromResource("sample_file_3.txt")

    // then
    val paths = Triangle.calculatePaths(root)
    assert(paths.size == 16384)

  }
}
