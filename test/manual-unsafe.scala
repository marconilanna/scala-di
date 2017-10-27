/*
 * Copyright 2014 Marconi Lanna
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package manual

import service.Config

import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

class ManualTest extends FunSuite with MockitoSugar {
  test("Default service") {
    val db = new Db

    assert(db.connect === "Connected as root:1234")
  }

  test("Alternative service") {
    object ProdConfig extends Config {
      def username = "prod"
      def password = "code"
    }

    val db = new Db(ProdConfig)

    assert(db.connect === "Connected as prod:code")
  }

  test("Mock test") {
    val mockConfig = mock[Config]
    when(mockConfig.username) thenReturn "mockUser"
    when(mockConfig.password) thenReturn "mockPass"

    val db = new Db(mockConfig)

    assert(db.connect === "Connected as mockUser:mockPass")
  }
}
