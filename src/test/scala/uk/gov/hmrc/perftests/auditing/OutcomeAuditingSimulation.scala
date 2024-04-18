/*
 * Copyright 2024 HM Revenue & Customs
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

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import OutcomeAuditingRequests._

class OutcomeAuditingSimulation extends PerformanceTestRunner {

  setup("check-nino-outcome-auditing", "Check nino outcome Auditing API") withRequests checkNinoOutcomeAuditing
  setup("check-bank-account-outcome-auditing", "Check bank account outcome auditing API") withRequests checkBankAccountOutcomeAuditing

  runSimulation()
}
