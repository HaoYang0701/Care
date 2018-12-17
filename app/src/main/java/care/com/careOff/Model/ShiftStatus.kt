package care.com.careOff.Model

enum class ShiftStatus(val status : String) {
    NEW("NEW"),
    INTERESTED("PENDING_ASSIGNMENT"),
    ASSIGNED("ASSIGNED"),
    PAST("COMPLETED")
}