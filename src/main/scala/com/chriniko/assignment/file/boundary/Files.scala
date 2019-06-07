package com.chriniko.assignment.file.boundary

import scala.io.Source
import scala.language.reflectiveCalls

object Files {

  def extractLinesFromResource(resourceName: String): List[String] = {
    Control.using(Source.fromResource(resourceName)) {
      source => source.getLines().toList
    }
  }

  private object Control {
    def using[A <: {def close() : Unit}, B](resource: A)(f: A => B): B =
      try {
        f(resource)
      } finally {
        resource.close()
      }
  }

}
