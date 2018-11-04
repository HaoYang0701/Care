package care.com.careOff.Model


data class RegistrationResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val body: Body
)

data class Body(
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val cellphone_number: String,
    val user_account_status: String
)