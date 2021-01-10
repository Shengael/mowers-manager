package projetal2020.controllers

import projetal2020.models.{Mower, State}

import scala.annotation.tailrec

class MowersController(val state: State) {
  def launchMowers(): MowersController = {
    @tailrec
    def moveMower(instructions: String, mower: Mower): Mower =
      instructions.toList match {
        case instruction :: rest =>
          moveMower(rest.mkString, mower.move(instruction, state.lawn))
        case Nil => mower
      }

    def launchAllMowers(mowersToLaunch: Array[Mower]): Array[Mower] =
      mowersToLaunch.toList match {
        case mower :: rest =>
          val m = moveMower(mower.actions, mower)
          println(s"Mower ended ${m.toString}")
          val ms = launchAllMowers(rest.toArray)
          m +: ms
        case Nil => Array[Mower]()
      }

    new MowersController(new State(state.lawn, launchAllMowers(state.mowers)))
  }
}
