package com.xm666.rehurttime.util;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

import java.util.Set;
import java.util.stream.Collectors;

public class Util {
    public static String getEntityType(Entity entity) {
        if (entity != null) {
            return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
        }
        return "";
    }

    public static Set<String> getEntityTags(Entity entity) {
        if (entity != null) {
            return entity.getTags();
        }
        return Set.of();
    }

    public static String getItemType(ItemStack stack) {
        if (stack != null) {
            return BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        }
        return "";
    }

    public static Set<String> getItemTags(ItemStack stack) {
        if (stack != null) {
            return stack.getTags().map(tag -> tag.location().toString()).collect(Collectors.toSet());
        }
        return Set.of();
    }

    public static String getHolderType(Holder<?> holder) {
        if (holder != null) {
            var key = holder.getKey();
            if (key != null) {
                return key.location().toString();
            }
        }
        return "";
    }

    public static Set<String> getHolderTags(Holder<?> holder) {
        if (holder != null) {
            return holder.tags().map(tag -> tag.location().toString()).collect(Collectors.toSet());
        }
        return Set.of();
    }
}
