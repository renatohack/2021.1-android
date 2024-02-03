package renatohack.minhascompras.Model.GoogleAPI.Classes

data class ResponseGoogleAPI (

	val kind : String,
	val url : Url,
	val queries : Queries,
	val promotions : List<Promotions>,
	val context : Context,
	val searchInformation : SearchInformation,
	val spelling : Spelling,
	val items : List<Items>
)