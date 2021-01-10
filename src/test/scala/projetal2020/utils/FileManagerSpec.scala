package projetal2020.utils

import org.scalatest.funsuite.AnyFunSuite

class FileManagerSpec extends AnyFunSuite {
  test("getLines should return a list of strings") {
    val result = FileManager.getLines("config.mowers")
    val expected = """5 5
                     |1 2 N
                     |GAGAGAGAA
                     |3 3 E
                     |AADAADADDA""".stripMargin.split("\n").toList

    assert(result == expected)
  }

  test("getLines should throw java.nio.file.NoSuchFileException") {
    assertThrows[java.nio.file.NoSuchFileException](
      FileManager.getLines("config")
    )
  }

}
