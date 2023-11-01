package com.maid.cleanhires.mercor.mData.models

data class ApiResponse(
	val results: List<ResultsItem>,
	val info: Info? = null
)

data class ResultsItem(
	val image: String? = null,
	val gender: String? = null,
	val species: String? = null,
	val created: String? = null,
	val origin: Origin? = null,
	val name: String? = null,
	val location: Location? = null,
	val episode: List<String?>? = null,
	val id: Int? = null,
	val type: String? = null,
	val url: String? = null,
	val status: String? = null
)

data class Origin(
	val name: String? = null,
	val url: String? = null
)

data class Info(
	val next: String? = null,
	val pages: Int? = null,
	val prev: Any? = null,
	val count: Int? = null
)

data class Location(
	val name: String? = null,
	val url: String? = null
)

