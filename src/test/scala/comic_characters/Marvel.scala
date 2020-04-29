package comic_characters

import io.gatling.core.Predef._
import comic_characters.Character._

object Marvel {

	val create = exec(
		createCharacter("marvel")
	)

	val MarvelScenario =
		feedCharacter("marvel")
			.exec(
				listAllCharacters(),
				create,
				getCharacter("${id}"),
				updateCharacter("${id}"),
				deleteCharacter("${id}"),
			)

}
