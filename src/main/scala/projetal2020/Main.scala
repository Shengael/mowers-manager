package projetal2020

import projetal2020.controllers.MowersController
import projetal2020.utils.{FileManager, JsonConverter, Parser}

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
object Main extends App {
  if (args.length == 1) {
    val filename = args(0)
    val state = Parser.parseMowerConfig(FileManager.getLines(filename))

    val mowersController =
      new MowersController(state).launchMowers()

    val result = FileManager.write(
      FileManager.getJsonPath(filename),
      JsonConverter.convertToJson(mowersController.state).beautify()
    )
    if (result.exists) {
      println(s"process informations has been saved in ${result.toString}")
    } else {
      println(s"process informations could not be saved")
    }
  } else {
    throw new Exception("invalid file path")
  }
}
