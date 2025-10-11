package com.xm666.rehurttime.mixin;

import com.googlecode.aviator.runtime.type.AviatorNil;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.xm666.rehurttime.Config;
import com.xm666.rehurttime.ReHurtTime;
import com.xm666.rehurttime.util.PredicateUtil;
import com.xm666.rehurttime.util.Util;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Map;

@Mixin(LivingEntity.class)
public class HurtTimeMixin {
    @WrapOperation(method = "hurt", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/world/entity/LivingEntity;lastHurt:F"))
    private float hurt(LivingEntity instance, Operation<Float> original, DamageSource source, float amount) {
        var predicate = PredicateUtil.compile(Config.PREDICATE.get());
        var sourceEntity = source.getEntity();
        var sourceDirectEntity = source.getDirectEntity();
        var sourceWeaponItem = source.getWeaponItem();
        var sourceTypeHolder = source.typeHolder();
        Map<String, Object> variables = Map.ofEntries(
                Map.entry("entity", instance),
                Map.entry("source", source),
                Map.entry("sourceEntity", sourceEntity != null ? sourceEntity : AviatorNil.NIL),
                Map.entry("sourceDirectEntity", sourceDirectEntity != null ? sourceDirectEntity : AviatorNil.NIL),
                Map.entry("sourceWeaponItem", sourceWeaponItem != null ? sourceWeaponItem : AviatorNil.NIL),
                Map.entry("entityType", Util.getEntityType(instance)),
                Map.entry("entityTags", Util.getEntityTags(instance)),
                Map.entry("sourceEntityType", Util.getEntityType(sourceEntity)),
                Map.entry("sourceEntityTags", Util.getEntityTags(sourceEntity)),
                Map.entry("sourceDirectEntityType", Util.getEntityType(sourceDirectEntity)),
                Map.entry("sourceDirectEntityTags", Util.getEntityTags(sourceDirectEntity)),
                Map.entry("sourceWeaponItemType", Util.getItemType(sourceWeaponItem)),
                Map.entry("sourceWeaponItemTags", Util.getItemTags(sourceWeaponItem)),
                Map.entry("sourceType", Util.getHolderType(sourceTypeHolder)),
                Map.entry("sourceTags", Util.getHolderTags(sourceTypeHolder))
        );
        if (Config.LOG.get()) {
            ReHurtTime.LOGGER.debug(variables.toString());
        }
        if (predicate.test(variables)) {
            return 0.0F;
        }
        return original.call(instance);
    }
}
