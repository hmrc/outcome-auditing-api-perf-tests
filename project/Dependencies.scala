import sbt._

object Dependencies {

  private val gatlingVersion = "3.5.1"

  val test = Seq(
    "uk.gov.hmrc"          %% "performance-test-runner"   % "6.2.0"         % Test
  )
}
