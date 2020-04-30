package simulation

import base.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class SimulationApi extends BaseSimulation {

	val feederDemo = csv("data/nameJobData.csv").random.circular

	val scn = scenario("Meu primeiro Teste")
		.feed(feederDemo)
		.exec(
			http("Cria um usuario")
				.post("/users")
				.body(ElFileBody("bodies/nameJobBody.json"))
				.check(status.is(201))
				.check(jsonPath("$.name").is("${name}"))
				.check(jsonPath("$.id").exists.saveAs("id"))
		)
		.exitHereIfFailed
		.exec { session =>
			println("Id: " + session("id").as[String])
			println("Name: " + session("name").as[String])
			session
		}
		.exec(
			http("Consulta o usuário criado")
				.get("/users/${id}")
				.check(status.is(200))
		)

	setUp(scn.inject(
		constantUsersPerSec(600) during (2 seconds),
		nothingFor(5 seconds),
		rampUsersPerSec(1) to (1000) during (10 seconds)
		//		rampUsersPerSec(1) to (10) during (20 seconds)
	).protocols(httpConf))

}
