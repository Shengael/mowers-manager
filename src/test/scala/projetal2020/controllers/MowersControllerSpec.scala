package projetal2020.controllers

import org.scalatest.funsuite.AnyFunSuite
import projetal2020.models.{Lawn, Mower, Position}

class MowersControllerSpec extends AnyFunSuite {
  test("Mower Controller should move mowers in the right place") {
    val lawn = new Lawn(5, 5)
    val mowers = Array(
      new Mower(Position(1, 2, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), "AADAADADDA")
    )
    val mowersController = new MowersController(mowers, lawn)
    val movedMowersController = mowersController.launchMowers()

    val expectedMowers = Array(
      new Mower(Position(1, 2, "N"), Position(1, 3, "N"), "GAGAGAGAA"),
      new Mower(Position(3, 3, "E"), Position(5, 1, "E"), "AADAADADDA")
    )

    assert(expectedMowers.sameElements(movedMowersController.mowers))
  }
}
