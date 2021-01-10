package projetal2020.utils

import projetal2020.exceptions.DonneesIncorectesException
import projetal2020.models.{Lawn, Mower, Position, Settings}

object Parser {
  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def parseMowerConfig(instructions: List[String]): Settings = {

    if (instructions.length % 2 == 1 && instructions.length >= 3) {
      val lawnConfig = instructions.headOption match {
        case Some(lawn) => lawn
        case None =>
          throw new DonneesIncorectesException("Lawn configuration is invalid")
      }
      val lawn = checkLawnDimension(lawnConfig) match {
        case Some(value) => value
        case None =>
          throw new DonneesIncorectesException("Lawn configuration is invalid")
      }
      val mowers = checkMowersConfig(
        instructions.slice(1, instructions.length),
        lawn
      ) match {
        case Some(value) => value
        case None =>
          throw new DonneesIncorectesException(
            "Mowers configuration is invalid"
          )
      }
      new Settings(lawn, mowers)
    } else {
      throw new DonneesIncorectesException("There is an invalid line number")
    }

  }

  private def checkLawnDimension(lawnConfig: String): Option[Lawn] = {
    val dimensionStr = lawnConfig.split(" ")
    if (dimensionStr.length == 2) {
      try {
        val lawn = new Lawn(dimensionStr(0).toInt, dimensionStr(1).toInt)
        if (lawn.isValid) Some(lawn) else None
      } catch {
        case _: java.lang.NumberFormatException => None
      }
    } else {
      None
    }
  }
  private def checkMowersConfig(
      mowerConfig: List[String],
      lawn: Lawn
  ): Option[Array[Mower]] = {

    def checkMower(positionConfig: String, actions: String): Option[Mower] = {

      val position = positionConfig.split(" ")
      if (position.length == 3) {
        try {
          val mower = new Mower(
            Position(position(0).toInt, position(1).toInt, position(2)),
            actions
          )
          if (mower.isValid(lawn)) Some(mower) else None
        } catch {
          case _: java.lang.NumberFormatException => None
        }
      } else {
        None
      }
    }

    def checkAllMowers(mowerConfig: List[String]): Option[Array[Mower]] =
      mowerConfig match {
        case position :: moves :: rest =>
          checkMower(position, moves) match {
            case Some(mover) =>
              checkAllMowers(rest) match {
                case Some(mowersArray) => Some(mover +: mowersArray)
                case None              => None
              }
            case None => None
          }
        case Nil => Some(Array[Mower]())
        case _   => None
      }

    checkAllMowers(mowerConfig)
  }

}
