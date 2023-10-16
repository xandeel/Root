package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * Created By LeandroSSJ
 * Created on 19/12/2022
 */

public abstract class PotionEffectEvent extends EntityEvent {

    private final PotionEffect effect;

    public PotionEffectEvent(LivingEntity what, PotionEffect effect) {
        super(what);
        this.effect = effect;
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    public PotionEffect getEffect() {
        return effect;
    }
}
