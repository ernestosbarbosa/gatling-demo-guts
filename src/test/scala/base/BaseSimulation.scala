package base

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BaseSimulation extends Simulation {

	private def getProperty(propertyName: String, defaultValue: String) = {
		Option(System.getenv(propertyName))
			.orElse(Option(System.getProperty(propertyName)))
			.getOrElse(defaultValue)
	}

	def userCount: Int = getProperty("USERS", "5").toInt
	def rampDuration: Int = getProperty("RAMP_DURATION", "10").toInt
	def testDuration: Int = getProperty("DURATION", "60").toInt

	val httpConf = http
		.baseUrl("https://service-testing-crud-api.herokuapp.com")
		.header("Content-Type", "application/json; charset=utf-8")
		.inferHtmlResources()

	before {
		println("Iniciando os testes")
		println(s"Executando testes com ${userCount} usuários")
		println(s"Rampa de usuários em ${rampDuration} segundos")
		println(s"Duração máxima: ${testDuration} segundos")
	}

	after {
		println("Testes finalizados!")
	}

}
