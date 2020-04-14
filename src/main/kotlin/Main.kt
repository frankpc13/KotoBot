import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDABuilder
import javax.security.auth.login.LoginException

fun main() {
    val builder: JDABuilder = JDABuilder(AccountType.BOT)
    val token = "Njk5NDIxMTU2NTI4MjI2MzE2.XpUMSA.SrsHG3hRgrCjJQDmzMcjfiwGXOM"
    builder.setToken(token)
    builder.addEventListeners(Handler())
    builder.build()


}