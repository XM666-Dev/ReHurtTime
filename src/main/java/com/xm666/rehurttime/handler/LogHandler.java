package com.xm666.rehurttime.handler;

import com.xm666.rehurttime.Config;
import com.xm666.rehurttime.ReHurtTime;
import com.xm666.rehurttime.util.Util;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

public class LogHandler {
    @EventBusSubscriber
    private static class LogHandlerClient {
        @SubscribeEvent
        static void onLivingIncomingDamage(LivingAttackEvent event) {
            if (!Config.LOG_ENABLED.get()) return;
            var result = Util.execute(Config.LOG_FUNCTION.get(), event.getEntity(), event.getSource());
            ReHurtTime.LOGGER.info(result.toString());
        }
    }

    //@EventBusSubscriber
    private static class LogHandlerConfig {
        @SubscribeEvent
        static void onConfigLoading(ModConfigEvent.Loading event) {
            toggle();
        }

        @SubscribeEvent
        static void onConfigReloading(ModConfigEvent.Reloading event) {
            toggle();
        }

        static void toggle() {
            if (Config.LOG_ENABLED.get()) {
                NeoForge.EVENT_BUS.register(LogHandlerClient.class);
            } else {
                NeoForge.EVENT_BUS.unregister(LogHandlerClient.class);
            }
        }
    }
}
