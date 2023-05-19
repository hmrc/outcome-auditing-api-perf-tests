/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.auditing

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object OutcomeAuditingRequests extends ServicesConfiguration {

  val baseUrl: String = baseUrlFor("outcome-auditing-proxy")
  val route: String   = "/report/outcome"
  val ninoResponseMessage = "The nino outcome from outcome-auditing-proxy processed"

  val checkOutcomeAuditing: HttpRequestBuilder =
    http("Check outcome auditing for a nino attribute")
      .post(s"$baseUrl$route")
      .header(HttpHeaderNames.ContentType, "application/json")
      .header(HttpHeaderNames.UserAgent, "test-user-agent")
      .body(StringBody(
        """
          |{
          |  "correlationId": "33df37a4-a535-41fe-8032-7ab718b45526",
          |  "submitter": "dfe",
          |  "submission": {
          |    "submissionType": "nino",
          |    "submissionAttribute": {
          |      "nino": "AB608580X"
          |    }
          |  },
          |  "outcome": {
          |    "outcomeType": "insights",
          |    "decision": "ACCEPTED",
          |    "reasons": "Some reason"
          |  }
          |}
          |""".stripMargin
      ))
      .asJson
      .check(status.is(200))
      .check(jsonPath("$.code").is("ok"))
      .check(jsonPath("$.message").is(ninoResponseMessage))
}
