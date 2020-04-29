package simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimulationApi extends Simulation {

	val httpConf = http
		.baseUrl("http://localhost:2345")
		.header("Content-Type", "application/json; charset=utf-8")
  	.inferHtmlResources()

	val feederDemo = csv("data/demoData.csv").random.circular

	val scn = scenario("Meu primeiro Teste")
  		.feed(feederDemo)
  		.exec(
				http("Cria um usuario")
					.post("/users")
  				.body(ElFileBody("bodies/demoBody.json"))
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
		constantUsersPerSec(600) during(2 seconds),
		nothingFor(5 seconds),
		rampUsersPerSec(1) to (1000) during (10 seconds)
//		rampUsersPerSec(1) to (10) during (20 seconds)
	).protocols(httpConf))

}
