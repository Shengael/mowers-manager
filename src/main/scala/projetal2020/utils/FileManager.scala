package projetal2020.utils
import better.files._

object FileManager {
  private def getFile(filePath: String): File = File(filePath)
  def getLines(filePath: String): List[String] = getFile(filePath).lines.toList
  def write(filePath: String, content: String): File =
    getFile(filePath).write(content)
  def getJsonPath(filePath: String): String =
    s"${getFile(filePath).path.getParent.toString}/result.json"
}
