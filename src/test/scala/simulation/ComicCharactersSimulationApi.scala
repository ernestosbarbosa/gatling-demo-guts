package simulation

import base.BaseSimulation
import comic_characters.{DC, Marvel}
import io.gatling.core.Predef._

import scala.concurrent.duration._

class ComicCharactersSimulationApi extends BaseSimulation {

	val scn = scenario("Testes na API de Quadrinhos")
		.exec(
			group("Universo Marvel"){
				Marvel.MarvelScenario
			}
		)
		.pause(5 seconds)
		.exec(
			group("Universo DC"){
				DC.DCScenario
			}
		)

	setUp(scn.inject(
			rampUsers(userCount) during (rampDuration seconds),
		//		constantUsersPerSec(600) during (2 seconds),
		//		nothingFor(5 seconds),
		//		atOnceUsers(1)
	).protocols(httpConf))
		.maxDuration(testDuration seconds)

}
