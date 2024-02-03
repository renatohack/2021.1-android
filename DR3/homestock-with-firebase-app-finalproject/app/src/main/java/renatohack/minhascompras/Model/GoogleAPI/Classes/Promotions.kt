package renatohack.minhascompras.Model.GoogleAPI.Classes

data class Promotions (

	val title : String,
	val htmlTitle : String,
	val link : String,
	val displayLink : String,
	val bodyLines : List<BodyLines>,
	val image : Image
)