package projetal2020.models

class Settings(val lawn: Lawn, val mowers: Array[Mower]) {
  override def equals(that: Any): Boolean =
    that match {
      case that: Settings =>
        this.hashCode == that.hashCode
      case _ => false
    }

  override def hashCode: Int = {

    def getMoversHashcode(mowersArray: Array[Mower]): Int =
      mowersArray.toList match {
        case el :: rest => el.hashCode + getMoversHashcode(rest.toArray)
        case Nil        => 0
      }

    val prime = 31
    val result = 1
    val lawnHashcode = prime * result + lawn.hashCode
    val moversHashcode = prime + lawnHashcode + getMoversHashcode(mowers)
    moversHashcode
  }
}
