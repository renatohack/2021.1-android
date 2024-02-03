package renatohack.minhascompras.Model.GoogleAPI.Classes

data class Items (

	val kind : String,
	val title : String,
	val htmlTitle : String,
	val link : String,
	val displayLink : String,
	val snippet : String,
	val htmlSnippet : String,
	val cacheId : String,
	val formattedUrl : String,
	val htmlFormattedUrl : String,
	val mime : String,
	val fileFormat : String,
	val image : Image,
	val labels : List<Labels>,
	val cse_image : List<Cse_Image>
)