import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.*
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.logging.LogLevel
import com.natpryce.konfig.*
import java.util.*

fun startBot() {
    val bot = bot {


        token = getProp("pollsBot.token")
        timeout = 30
        logLevel = LogLevel.Network.Body

        dispatch {

            command("kick") {
                val textFromMessage = message.text

                if (textFromMessage.isNullOrBlank()) {
                    return@command
                }

                val messageId = getMessageId(textFromMessage)
                bot.sendMessage(
                    chatId = message.chat.id,
                    text = "Voting to give Yellow Card for message with id $messageId"
                )
                bot.sendPoll(
                    chatId = message.chat.id,
                    question = getPollMessage(),
                    options = getOptions(),
                    isAnonymous = true
                )

            }

            telegramError {
                println(error.getErrorMessage())
            }
        }
    }
    bot.startPolling()
}


fun main() {
    startBot()
}