package projetal2020.models

import scala.annotation.tailrec
// TODO: garder la position initial
class Mower(
    val x: Int,
    val y: Int,
    val direction: String,
    val actions: String
) {

  def move(instruction: Char, lawn: Lawn): Mower = {
    val possibleDirections = Array("N", "E", "S", "W")
    val forward =
      Map(
        "N" -> Tuple2(0, 1),
        "W" -> Tuple2(-1, 0),
        "S" -> Tuple2(0, -1),
        "E" -> Tuple2(1, 0)
      )
    val newMower = instruction match {
      case action if action == 'A' =>
        new Mower(
          x + forward(direction)._1,
          y + forward(direction)._2,
          direction,
          actions
        )
      case action if action == 'G' =>
        new Mower(
          x,
          y,
          possibleDirections((possibleDirections.indexOf(direction) + 3) % 4),
          actions
        )
      case action if action == 'D' =>
        new Mower(
          x,
          y,
          possibleDirections((possibleDirections.indexOf(direction) + 1) % 4),
          actions
        )
    }

    if (newMower.isValid(lawn)) {
      newMower
    } else {
      this
    }
  }

  def isValid(lawn: Lawn): Boolean = {
    @tailrec
    def checkActions(actionsStr: String): Boolean = actionsStr.toList match {
      case action :: rest => {
        Array('G', 'D', 'A').contains(action) && checkActions(rest.mkString)
      }
      case Nil => true
    }

    0 <= x && x <= lawn.width && 0 <= y && y <= lawn.height && Array(
      "N",
      "E",
      "W",
      "S"
    ).contains(direction) && checkActions(actions)
  }

  override def equals(that: Any): Boolean = that match {
    case that: Mower =>
      this.hashCode == that.hashCode
    case _ => false
  }

  override def hashCode: Int = {
    println("****************** start ******************")
    val prime = 31
    val result = 1
    val xHashcode = prime * result + x
    println(xHashcode)
    val yHashcode = prime * xHashcode + y
    println(yHashcode)
    val directionHashcode = prime * yHashcode + direction.hashCode
    println(directionHashcode)
    val actionsHashcode = prime * directionHashcode + actions.hashCode
    println(actionsHashcode)
    println("****************** end ******************")
    actionsHashcode
  }
}
