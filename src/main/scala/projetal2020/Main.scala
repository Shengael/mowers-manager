package projetal2020

import projetal2020.controllers.MowersController
import projetal2020.utils.{FileManager, Parser}

@SuppressWarnings(Array("org.wartremover.warts.Throw"))
object Main extends App {
  if (args.length == 1) {
    val filename = args(0)
    val state = Parser.parseMowerConfig(FileManager.getLines(filename))

    val mowersController =
      new MowersController(state).launchMowers()
    println(mowersController.state.lawn)

    val result = FileManager.write(FileManager.getJsonPath(filename), "")
    if (result.exists) {
      println(s"process informations has been saved in ${result.toString}")
    }
  } else {
    throw new Exception("invalid file path")
  }
}
