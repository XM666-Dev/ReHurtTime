package com.xm666.rehurttime.mixin;

import com.xm666.rehurttime.Config;
import com.xm666.rehurttime.util.Util;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public class AppliesKnockbackMixin {
    @Mixin(LivingEntity.class)
    private static class LivingEntityMixin {
        @ModifyVariable(method = "hurt", at = @At(value = "STORE", ordinal = 1), name = "flag1", ordinal = 1)
        private boolean modifyKnockback(boolean knockback, DamageSource source, float amount) {
            return knockback || Util.test(Config.APPLIES_KNOCKBACK_PREDICATE.get(), (LivingEntity) (Object) this, source);
        }
    }
}
