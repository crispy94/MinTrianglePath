import sbt._

object Version {
  final val ScalaTestVersion   = "3.0.7"

}
object Library {
  import Version._

  final val ScalaTest   = "org.scalatest" %% "scalatest"   % ScalaTestVersion   % Test
}