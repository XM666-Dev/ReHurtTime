package com.xm666.rehurttime;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.ConfigValue<String> PREDICATE = BUILDER
            .comment("Available variables: entity, source, sourceEntity, sourceDirectEntity, sourceWeaponItem, entityType, entityTags, sourceEntityType, sourceEntityTags, sourceDirectEntityType, sourceDirectEntityTags, sourceWeaponItemType, sourceWeaponItemTags, sourceType, sourceTags")
            .define("predicate", "entityType != 'minecraft:player' && !include(sourceTags, 'neoforge:is_environment')");

    public static final ModConfigSpec.BooleanValue LOG = BUILDER.define("log", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
