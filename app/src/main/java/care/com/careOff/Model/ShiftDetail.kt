package care.com.careOff.Model

import com.google.gson.annotations.SerializedName

data class ShiftDetail(
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("id") val id: Int,
        @SerializedName("shift") val shift: Shift,
        @SerializedName("shift_cancelled_by") val canceledBy: String,
        @SerializedName("shift_id") val shiftId: Int,
        @SerializedName("status") val status: String,
        @SerializedName("updatedAt") val updatedAt: String,
        @SerializedName("user_id") val userId: Int
)

data class Shift(
        @SerializedName("id") val Id: Int,
        @SerializedName("facility_id") val facilityId: Int,
        @SerializedName("start_date") val startDate: String,
        @SerializedName("length_in_mins") val lengthInMins: Int,
        @SerializedName("address_id") val addressId: Int,
        @SerializedName("note") val note: String,
        @SerializedName("is_weekend") val isWeekend: Boolean,
        @SerializedName("approved_user") val approvedUser: String,
        @SerializedName("worker_payment_status") val workerPaymentStatus: String,
        @SerializedName("facility_payment_status") val facilityPaymentStatus: String,
        @SerializedName("status") val status: String,
        @SerializedName("checked_in") val checkedIn: String,
        @SerializedName("checked_out") val checkedOut: String,
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("updatedAt") val updatedAt: String
)