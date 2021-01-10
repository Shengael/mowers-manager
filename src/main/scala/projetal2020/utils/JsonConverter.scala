package projetal2020.utils

import play.api.libs.json.{JsValue, Json}
import projetal2020.models.{Lawn, Mower, Position, Settings}

object JsonConverter {

  def convertPositionToJson(position: Position): JsValue = Json.obj(
    "point"     -> Json.obj("x" -> position.x, "y" -> position.y),
    "direction" -> position.direction
  )

  def convertMowerToJson(mower: Mower): JsValue = Json.obj(
    "debut"        -> convertToJson(mower.initialPosition),
    "instructions" -> mower.actions.split(""),
    "fin"          -> convertToJson(mower.currentPosition)
  )

  def convertLawnToJson(lawn: Lawn): JsValue = Json.obj(
    "x" -> lawn.width,
    "y" -> lawn.height
  )

  def convertSettingsToJson(settings: Settings): JsValue = ???

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def convertToJson(obj: Any): JsValue = obj match {
    case position: Position => convertPositionToJson(position)
    case settings: Settings => convertSettingsToJson(settings)
    case mower: Mower       => convertMowerToJson(mower)
    case lawn: Lawn         => convertLawnToJson(lawn)
    case _                  => throw new Exception("bad object type")
  }

}
