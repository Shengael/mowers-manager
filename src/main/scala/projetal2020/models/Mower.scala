package projetal2020.models

import scala.annotation.tailrec

case class Position(x: Int, y: Int, direction: String) {
  override def hashCode(): Int = {
    val prime = 31
    val result = 1
    val xHashcode = prime * result + x
    val yHashcode = prime * xHashcode + y
    val directionHashcode = prime * yHashcode + direction.hashCode
    directionHashcode
  }
}

class Mower(
    val initialPosition: Position,
    val currentPosition: Position,
    val actions: String
) {

  def this(positon: Position, actions: String) = {
    this(positon, positon, actions)
  }

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
          initialPosition,
          Position(
            currentPosition.x + forward(currentPosition.direction)._1,
            currentPosition.y + forward(currentPosition.direction)._2,
            currentPosition.direction
          ),
          actions
        )
      case action if action == 'G' =>
        new Mower(
          initialPosition,
          Position(
            currentPosition.x,
            currentPosition.y,
            possibleDirections(
              (possibleDirections.indexOf(currentPosition.direction) + 3) % 4
            )
          ),
          actions
        )
      case action if action == 'D' =>
        new Mower(
          initialPosition,
          Position(
            currentPosition.x,
            currentPosition.y,
            possibleDirections(
              (possibleDirections.indexOf(currentPosition.direction) + 1) % 4
            )
          ),
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
      case action :: rest =>
        Array('G', 'D', 'A').contains(action) && checkActions(rest.mkString)
      case Nil => true
    }

    0 <= currentPosition.x && currentPosition.x <= lawn.width && 0 <= currentPosition.y && currentPosition.y <= lawn.height && Array(
      "N",
      "E",
      "W",
      "S"
    ).contains(currentPosition.direction) && checkActions(actions)
  }

  override def toString: String =
    s"x: ${currentPosition.x.toString}, y: ${currentPosition.y.toString}, direction: ${currentPosition.direction}"

  override def equals(that: Any): Boolean = that match {
    case that: Mower =>
      this.hashCode == that.hashCode
    case _ => false
  }

  override def hashCode: Int = {
    val prime = 31
    val result = 1
    val initialHashcode = prime * result + initialPosition.hashCode
    val yHashcode = prime * initialHashcode + currentPosition.hashCode
    val actionsHashcode = prime * yHashcode + actions.hashCode
    actionsHashcode
  }
}
