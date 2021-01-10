package projetal2020

import projetal2020.models.Mower
import projetal2020.utils.Parser

object Main extends App {
  val test = """5 5
  |1 2 N
  |GAGAGAGAA
  |3 3 E
  |AADAADADDA""".stripMargin

  val settings = Parser.parseMowerConfig(test)

  val mower = new Mower(2, 5, "B", "GFGAGAGAA")
  val otherMower = new Mower(2, 2, "B", "GFGAGAGAA")
  println(mower.actions.hashCode)
  println(mower.hashCode)
  println(otherMower.hashCode == mower.hashCode)
}
