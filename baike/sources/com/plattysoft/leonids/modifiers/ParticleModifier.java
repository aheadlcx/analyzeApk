package com.plattysoft.leonids.modifiers;

import com.plattysoft.leonids.Particle;

public interface ParticleModifier {
    void apply(Particle particle, long j);
}
