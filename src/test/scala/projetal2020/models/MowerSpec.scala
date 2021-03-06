package projetal2020.models

import org.scalatest.funsuite.AnyFunSuite

class MowerSpec extends AnyFunSuite {

  test("Move should return a new Mower edited (Avancer)") {
    val lawn = new Lawn(5, 5)
    val mowers = new Mower(Position(1, 2, "N"), "GAGAGAGAA")
    val expected =
      new Mower(Position(1, 2, "N"), Position(1, 3, "N"), "GAGAGAGAA")
    assert(mowers.move('A', lawn).equals(expected))
  }

  test("Move should return a new Mower edited (Gauche)") {
    val lawn = new Lawn(5, 5)
    val mowers = new Mower(Position(1, 2, "N"), "GAGAGAGAA")
    val expected =
      new Mower(Position(1, 2, "N"), Position(1, 2, "W"), "GAGAGAGAA")
    assert(mowers.move('G', lawn).equals(expected))
  }

  test("Move should return a new Mower edited (Droite)") {
    val lawn = new Lawn(5, 5)
    val mowers = new Mower(Position(1, 2, "N"), "GAGAGAGAA")
    val expected =
      new Mower(Position(1, 2, "N"), Position(1, 2, "E"), "GAGAGAGAA")
    assert(mowers.move('D', lawn).equals(expected))
  }

  test("Move should not edit Mower when out of lawn") {
    val lawn = new Lawn(5, 5)
    val mower = new Mower(Position(5, 5, "N"), "GAGAGAGAA")
    val expected =
      new Mower(Position(5, 5, "N"), Position(5, 5, "N"), "GAGAGAGAA")
    assert(mower.move('A', lawn).equals(expected))
  }

  test("isValid should return true on valid configuration") {
    val lawn = new Lawn(5, 5)
    val mower = new Mower(Position(5, 5, "N"), Position(5, 5, "N"), "GAGAGAGAA")
    assert(mower.isValid(lawn))
  }

  test(
    "isValid should return false on invalid configuration (invalid position)"
  ) {
    val lawn = new Lawn(5, 5)
    val mower = new Mower(Position(5, 5, "N"), Position(6, 5, "N"), "GAGAGAGAA")
    assert(!mower.isValid(lawn))
  }

  test(
    "isValid should return false on invalid configuration (invalid direction)"
  ) {
    val lawn = new Lawn(5, 5)
    val mower = new Mower(Position(2, 5, "B"), "GAGAGAGAA")
    assert(!mower.isValid(lawn))
  }

  test("isValid should return false on invalid configuration (invalid actions)") {
    val lawn = new Lawn(5, 5)
    val mower = new Mower(Position(2, 5, "N"), "GFGAGAGAA")
    assert(!mower.isValid(lawn))
  }

  test("Equals should return true when mower is the same") {
    val mower = new Mower(Position(2, 5, "N"), Position(5, 4, "N"), "GAGAGAGAA")
    val otherMower =
      new Mower(Position(2, 5, "N"), Position(5, 4, "N"), "GAGAGAGAA")
    assert(mower.equals(otherMower))
  }

  test("Equals should return false when mower is not the same") {
    val mower = new Mower(Position(2, 5, "N"), "GAGAGAGAA")
    val otherMower = new Mower(Position(2, 2, "N"), "GAGAGAGAA")
    assert(!mower.equals(otherMower))
  }

}
