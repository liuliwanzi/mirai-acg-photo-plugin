package org.photo.acg.photo

import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.util.ConsoleExperimentalApi
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeGroupMessages

@ConsoleExperimentalApi
object AcgPhotoPluginMain : KotlinPlugin(JvmPluginDescription.loadFromResource()) {

    private suspend inline fun send(group: Group, messageChain: String, bot: Bot) {
        launch { bot.getGroup(group.id)?.sendMessage(messageChain) }
    }

    private fun setupMsgHandle() {
        globalEventChannel().subscribeGroupMessages{
            always {
                if (message.contentToString() == "acg") {
                    send(group, "你好", bot)
                }
            }
        }
    }
    override fun onEnable() {
        super.onEnable()
        setupMsgHandle()
    }

    override fun onDisable() {
        super.onDisable()
    }
}