package comic_characters

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Character {

	val resource = "characters"

	def feedCharacter(universe: String) = {
		feed(csv(s"data/${universe}.csv").random.circular)
	}

	def getCharacterPrintString(name: String, sex: String): String = {
		if (sex == "Male") s"o ${name}" else s"a ${name}"
	}

	def listAllCharacters() = {
		exec(
			http("Chama todo mundo")
				.get(s"/${resource}")
				.check(status.is(200))
		)
	}

	def getCharacter(id: String) = {
		exec(
			http(s"Chama ${getCharacterPrintString("${name}", "${sex}")}")
				.get(s"/${resource}/${id}")
				.check(status.is(200))
		)
	}

	def createCharacter(universe: String) = {
		exec(
			http(s"Criando ${getCharacterPrintString("${name}", "${sex}")}")
				.post(s"/${resource}")
				.body(ElFileBody("bodies/comicCharacters.json"))
				.check(status.is(201))
				.check(jsonPath("$.id").exists.saveAs("id"))
		).exitHereIfFailed
	}

	def updateCharacter(id: String) = {
		exec(
			http(s"Alterando os dados d${getCharacterPrintString("${name}", "${sex}")}")
				.put(s"/${resource}/${id}")
				.body(ElFileBody("bodies/comicCharacters.json"))
				.check(status.is(204))
		)
	}

	def deleteCharacter(id: String) = {
		exec(
			http(s"Removendo ${getCharacterPrintString("${name}", "${sex}")}")
				.delete(s"/${resource}/${id}")
				.check(status.is(204))
		)
	}
}
