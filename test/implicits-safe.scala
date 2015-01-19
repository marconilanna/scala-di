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
package implicitssafe

import service.Config

import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar

class ImplicitsTest extends FunSuite with MockitoSugar {
	test("Default service") {
		import ConfigModule.config

		val db = new Db

		assert(db.connect === "Connected as root:1234")
	}

	test("Alternative service") {
		object ProdConfig extends Config {
			def username = "prod"
			def password = "code"
		}

		object ProdConfigModule extends ConfigModule {
			implicit def config = ProdConfig
		}

		import ProdConfigModule.config

		val db = new Db

		assert(db.connect === "Connected as prod:code")
	}

	test("Alternative service - option 2") {
		object ProdConfigModule extends ConfigModule {
			implicit object config extends Config {
				def username = "prod"
				def password = "code"
			}
		}

		import ProdConfigModule.config

		val db = new Db

		assert(db.connect === "Connected as prod:code")
	}

	test("Mock test") {
		implicit val mockConfig = mock[Config]
		when(mockConfig.username) thenReturn "mockUser"
		when(mockConfig.password) thenReturn "mockPass"

		val db = new Db

		assert(db.connect === "Connected as mockUser:mockPass")
	}

	test("Mock test - option 2") {
		object MockConfigModule extends ConfigModule {
			implicit val config = mock[Config]
			when(config.username) thenReturn "mockUser"
			when(config.password) thenReturn "mockPass"
		}

		import MockConfigModule.config

		val db = new Db

		assert(db.connect === "Connected as mockUser:mockPass")
	}
}
