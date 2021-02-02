import java.text.SimpleDateFormat
import java.util.*

fun getDueDate(): String {
    val formatter = SimpleDateFormat("EEE, d MMM HH:mm")
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.MINUTE, +getProp("pollsBot.dueTimeInMinutes").toInt())

    return formatter.format(calendar.time)
}


fun getPollMessage(): String {
    return getProp("pollsBot.pollMessage")
        .replace("{user}", "ToBeImplemented")
        .replace("{date}", getDueDate())
}

fun getMessageId(message: String): String {
    val regex = Regex("""https:\/\/t\.me\/${getProp("pollsBot.groupName")}\/(\d*)""")

    return if (regex.containsMatchIn(message)) {
        regex.find(message)!!.groups.last()!!.value
    } else {
        throw IllegalStateException("Message is non-parsable")
    }
}

fun getOptions(): List<String> {
    return listOf("Желтая карта", "Ничего страшного", "Воздержусь/Я инициатор")
}