package projetal2020.utils

import org.scalatest.funsuite.AnyFunSuite
import projetal2020.exceptions.DonneesIncorectesException
import projetal2020.models.{Lawn, Mower, Settings}

class ParserSpec extends AnyFunSuite {

  test("Parser should return a Settings object") {
    val configStr = """5 5
                      |1 2 N
                      |GAGAGAGAA
                      |3 3 E
                      |AADAADADDA""".stripMargin

    val lawn = new Lawn(5, 5)
    val mowers = Array(
      new Mower(1, 2, "N", "GAGAGAGAA"),
      new Mower(3, 3, "E", "AADAADADDA")
    )

    val settings = new Settings(lawn, mowers)
    assert(settings.equals(Parser.parseMowerConfig(configStr)))
  }

  test(
    "Parser should throw an DonneesIncorectesException error on even line number"
  ) {
    val configStr = """5 5
              |1 2 N
              |1 2 N
              |GAGAGAGAA
              |3 3 E
              |AADAADADDA""".stripMargin

    assertThrows[DonneesIncorectesException](
      Parser.parseMowerConfig(configStr)
    )
  }

  test(
    "Parser should throw DonneesIncorectesException error on invalid lawn configuration (invalid number)"
  ) {
    val configStr = """5 N
              |1 2 N
              |GAGAGAGAA
              |3 3 E
              |AADAADADDA""".stripMargin

    assertThrows[DonneesIncorectesException](
      Parser.parseMowerConfig(configStr)
    )
  }

  test(
    "Parser should throw DonneesIncorectesException error on invalid lawn configuration (negative number)"
  ) {
    val configStr = """-1 4
              |1 2 N
              |GAGAGAGAA
              |3 3 E
              |AADAADADDA""".stripMargin

    assertThrows[DonneesIncorectesException](
      Parser.parseMowerConfig(configStr)
    )
  }

  test(
    "Parser should throw DonneesIncorectesException exception on invalid mover exception (invalid direction)"
  ) {
    val configStr = """5 5
                      |1 2 E
                      |GAGAGAGAA
                      |3 3 C
                      |AADAADADDA
                      |3 3 W
                      |AADAADADDA""".stripMargin

    assertThrows[DonneesIncorectesException](
      Parser.parseMowerConfig(configStr)
    )
  }

  test(
    "Parser should throw DonneesIncorectesException exception on invalid mover exception (invalid position: out of lawn)"
  ) {
    val configStr = """5 5
                      |1 2 E
                      |GAGAGAGAA
                      |3 2 S
                      |AADAADADDA
                      |6 3 W
                      |AADAADADDA""".stripMargin

    assertThrows[DonneesIncorectesException](
      Parser.parseMowerConfig(configStr)
    )
  }

  test(
    "Parser should throw DonneesIncorectesException exception on invalid mover exception (invalid actions)"
  ) {
    val configStr = """5 5
                      |1 2 E
                      |GAGAGAGAR
                      |3 2 S
                      |AADAADADDA
                      |6 3 W
                      |AADAADADDA""".stripMargin

    assertThrows[DonneesIncorectesException](
      Parser.parseMowerConfig(configStr)
    )
  }

}
