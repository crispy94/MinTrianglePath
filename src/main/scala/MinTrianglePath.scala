import scala.collection.immutable.IntMap
import scala.io.Source
import scala.util.Try

//Using list over array because of inmutability
object MinTrianglePath {

  case class TriangleElem(elem: Int, elemPath: List[Int])

  private def readFile(filename: String): Option[List[List[Int]]] = {
    Try {
      val buffSrc = Source.fromFile(filename)
      val strList = buffSrc
        .getLines()
        .map(
          _.split(" ")
            .map(_.toInt)
            .toList
        )
        .toList

      buffSrc.close()

      strList
    }.toOption
  }

  private def calculeSubRows(upRow: List[Int],
                             bottomRow: List[TriangleElem]) = {
    upRow.zip(bottomRow.zip(bottomRow.tail)).map {
      case (upElemInt, (leftElem, rightElem)) => {
        val minElem =
          if (rightElem.elem < leftElem.elem) rightElem else leftElem
        TriangleElem(upElemInt + minElem.elem, upElemInt :: minElem.elemPath)
      }
    }
  }

  def main(args: Array[String]) {
    if (args.length == 0) {
      println("[Error] - Introduce a filename as parameter, please")
    } else {
      val filename = args(0)
      val parsedFileOpt = readFile(filename)
      val response = parsedFileOpt.map { parsedFile =>
        if (parsedFile.isEmpty) {
          "[Error] - Empty file read"
        } else {
          val initialEmptyRow = List.fill[TriangleElem](
            parsedFile.last.length + 1
          )(TriangleElem(0, List()))

          val result =
            parsedFile.foldRight(initialEmptyRow)(calculeSubRows).head

          s"Minimal path is: ${result.elemPath.mkString("+")} = ${result.elem}"
        }
      }.getOrElse("[Error] - Error reading file")
      println(response)
    }
  }
}
