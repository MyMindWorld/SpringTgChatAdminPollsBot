import com.natpryce.konfig.*

fun getProp(key: String): String {
    val config = EnvironmentVariables() overriding
            ConfigurationProperties.fromResource("bot.local.properties") overriding
            ConfigurationProperties.fromResource("bot.properties")
    return config[Key(key, stringType)].encodeToByteArray().decodeToString()
}