package au.com.realestate.hometime.service.models

import com.squareup.moshi.Json

data class Token(
		@Json(name = "DeviceToken")
		var value: String? = null
)
