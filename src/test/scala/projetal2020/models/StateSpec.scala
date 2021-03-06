package projetal2020.models

import org.scalatest.funsuite.AnyFunSuite

class StateSpec extends AnyFunSuite {
  test("Equals should return true when settings is the same") {
    val lawn = new Lawn(5, 5)
    val mowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val settings = new State(lawn, mowers)

    val otherLawn = new Lawn(5, 5)
    val otherMowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val otherSettings = new State(otherLawn, otherMowers)

    assert(settings.equals(otherSettings))
  }

  test(
    "Equals should return false when settings is not the same (different lawn)"
  ) {
    val lawn = new Lawn(6, 5)
    val mowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val settings = new State(lawn, mowers)

    val otherLawn = new Lawn(5, 5)
    val otherMowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val otherSettings = new State(otherLawn, otherMowers)

    assert(!settings.equals(otherSettings))
  }

  test(
    "Equals should return false when settings is not the same (different mowers)"
  ) {
    val lawn = new Lawn(5, 5)
    val mowers = Array(
      new Mower(Position(4, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "W"), "AADAADADDA")
    )
    val settings = new State(lawn, mowers)

    val otherLawn = new Lawn(5, 5)
    val otherMowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val otherSettings = new State(otherLawn, otherMowers)

    assert(!settings.equals(otherSettings))
  }

  test(
    "Equals should return false when settings is not the same (different number of mowers)"
  ) {
    val lawn = new Lawn(5, 5)
    val mowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA"),
      new Mower(Position(1, 1, "S"), "AADAADADDA")
    )
    val settings = new State(lawn, mowers)

    val otherLawn = new Lawn(5, 5)
    val otherMowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val otherSettings = new State(otherLawn, otherMowers)

    assert(!settings.equals(otherSettings))
  }
}
