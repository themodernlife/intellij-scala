package org.jetbrains.plugins.scala.failed.typeInference

import org.jetbrains.plugins.scala.PerfCycleTests
import org.jetbrains.plugins.scala.lang.typeConformance.TypeConformanceTestBase
import org.junit.experimental.categories.Category

/**
  * @author Nikolay.Tropin
  */
@Category(Array(classOf[PerfCycleTests]))
class PrivateTypeProjectionConformance extends TypeConformanceTestBase {
  def testSCL9506(): Unit = {
    doTest(
      s"""object TicketTester {
        |  trait A {
        |    private type This = A
        |
        |    def something: This = this
        |  }
        |
        |  class B extends A {
        |    private type This = B
        |
        |    ${caretMarker}val aThis: This = super.something
        |  }
        |}
        |//false
      """.stripMargin)
  }
}
