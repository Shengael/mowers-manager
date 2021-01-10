package projetal2020.utils

import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json.{JsValue, Json}
import projetal2020.models.{Lawn, Mower, Position, State}

case class ConvertResult(json: JsValue) {
  def beautify(): String = Json.prettyPrint(json)
}

object JsonConverter {

  def convertPositionToJson(position: Position): ConvertResult = ConvertResult(
    Json.obj(
      "point"     -> Json.obj("x" -> position.x, "y" -> position.y),
      "direction" -> position.direction
    )
  )

  def convertMowerToJson(mower: Mower): ConvertResult = ConvertResult(
    Json.obj(
      "debut"        -> convertToJson(mower.initialPosition).json,
      "instructions" -> mower.actions.split(""),
      "fin"          -> convertToJson(mower.currentPosition).json
    )
  )

  def convertLawnToJson(lawn: Lawn): ConvertResult = ConvertResult(
    Json.obj(
      "x" -> lawn.width,
      "y" -> lawn.height
    )
  )

  def convertMowersToJson(mowers: Array[Mower]): ConvertResult = ConvertResult(
    Json.arr(mapMowersArrayToJsonObjList(mowers): _*)
  )

  def convertSettingsToJson(state: State): ConvertResult = ConvertResult(
    Json.obj(
      "limite"    -> convertToJson(state.lawn).json,
      "tondeuses" -> convertToJson(state.mowers).json
    )
  )

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def convertToJson(obj: Any): ConvertResult = obj match {
    case position: Position   => convertPositionToJson(position)
    case settings: State      => convertSettingsToJson(settings)
    case mowers: Array[Mower] => convertMowersToJson(mowers)
    case mower: Mower         => convertMowerToJson(mower)
    case lawn: Lawn           => convertLawnToJson(lawn)
    case _                    => throw new Exception("bad object type")
  }

  private def mapMowersArrayToJsonObjList(
      mowers: Array[Mower]
  ): List[JsValueWrapper] =
    mowers.toList match {
      case mower :: rest =>
        val mowers = mapMowersArrayToJsonObjList(rest.toArray)
        convertToJson(mower).json :: mowers
      case Nil => List[JsValueWrapper]()
    }

}
