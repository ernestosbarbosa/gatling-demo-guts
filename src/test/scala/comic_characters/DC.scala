package comic_characters

import io.gatling.core.Predef._
import comic_characters.Character._

object DC {

	val create = exec(
		createCharacter("dc")
	)

	val DCScenario =
		feedCharacter("dc")
			.exec(
				listAllCharacters(),
				create,
				getCharacter("${id}"),
				updateCharacter("${id}"),
				deleteCharacter("${id}"),
			)

}
