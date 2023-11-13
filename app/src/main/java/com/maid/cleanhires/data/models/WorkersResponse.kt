package com.maid.cleanhires.data.models

data class WorkersResponse(
	val categories: List<CategoriesItem>
)

data class CategoriesItem(
	val duration: Int? = null,
	val charges: Int? = null,
	val reviews: List<ReviewsItem?>? = null,
	val joined: String? = null,
	val service: String? = null,
	val urlToImage: String? = null,
	val name: String? = null,
	val rating: Float? = null,
	val location: String? = null,
	val booking_count: Int? = null
)

data class ReviewsItem(
	val rated: Float? = null,
	val feedback: String? = null,
	val image: String? = null,
	val username: String? = null,
	val timestamp: String? = null
)

