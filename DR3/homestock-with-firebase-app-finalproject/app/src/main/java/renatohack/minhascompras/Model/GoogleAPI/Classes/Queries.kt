package renatohack.minhascompras.Model.GoogleAPI.Classes

data class Queries (

	val previousPage : List<PreviousPage>,
	val request : List<Request>,
	val nextPage : List<NextPage>
)