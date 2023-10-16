package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

/**
 * Created By LeandroSSJ
 * Created on 19/12/2022
 */
public class PotionEffectExtendEvent extends PotionEffectAddEvent {

    private final PotionEffect oldEffect;

    public PotionEffectExtendEvent(LivingEntity what, PotionEffect effect,
                                   EffectAddReason reason, PotionEffect oldEffect) {
        super(what, effect, reason);
        this.oldEffect = oldEffect;
    }

    /**
     * Get the state of the potion effect prior to the change
     */
    public PotionEffect getOldEffect() {
        return oldEffect;
    }
}
