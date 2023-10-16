package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * Created By LeandroSSJ
 * Created on 19/12/2022
 */
public class PotionEffectExpireEvent extends PotionEffectRemoveEvent {

    private int duration = 0;

    public PotionEffectExpireEvent(LivingEntity entity, PotionEffect effect) {
        super(entity, effect);
    }

    /**
     * Get the new duration for the potion effect. This is initially 0.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set a new duration for the potion effect. Passing 0 to this method un-cancels
     * the event, and passing anything above 0 cancels it.
     */
    public void setDuration(int duration) {
        this.duration = Math.max(0, duration);
    }

    @Override
    public boolean isCancelled() {
        return duration > 0;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.duration = cancel ? Integer.MAX_VALUE : 0;
    }
}
