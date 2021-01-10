package projetal2020

import projetal2020.controllers.MowersController
import projetal2020.utils.Parser

object Main extends App {
  val test = """5 5
  |1 2 N
  |GAGAGAGAA
  |3 3 E
  |AADAADADDA""".stripMargin

  val settings = Parser.parseMowerConfig(test)
  val mowersController =
    new MowersController(settings.mowers, settings.lawn).launchMowers()
}
