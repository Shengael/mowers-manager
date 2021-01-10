package projetal2020.models

class Lawn(val width: Int, val height: Int) {
  def isValid: Boolean = {
    width > 0 && height > 0
  }

  override def hashCode: Int = {
    val prime = 31
    val result = 1
    val widthHashcode = prime * result + width
    val heightHashcode = prime * widthHashcode + height
    heightHashcode
  }

}
