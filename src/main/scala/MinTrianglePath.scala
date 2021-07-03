import scala.io.Source
import scala.util.Try
import Errors._

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
                             bottomRow: List[TriangleElem]): List[TriangleElem] = {
    upRow.zip(bottomRow.zip(bottomRow.tail)).map {
      case (upElemInt, (leftElem, rightElem)) => {
        val minElem =
          if (rightElem.elem < leftElem.elem) rightElem else leftElem
        TriangleElem(upElemInt + minElem.elem, upElemInt :: minElem.elemPath)
      }
    }
  }

  def calculePath(filename: String): String = {
    val parsedFileOpt = readFile(filename)
    parsedFileOpt
      .map { parsedFile =>
        if (parsedFile.isEmpty) emptyFileError
        else {
          val initialEmptyRow = List.fill[TriangleElem](
            parsedFile.last.length + 1
          )(TriangleElem(0, List()))

          val result =
            parsedFile.foldRight(initialEmptyRow)(calculeSubRows).head

          s"Minimal path is: ${result.elemPath.mkString(" + ")} = ${result.elem}"
        }
      }
      .getOrElse(readFileError)
  }
  def main(args: Array[String]) {
    if (args.length == 0)
      println(mainArgsError)
    else
      println(calculePath(args(0)))
  }
}
