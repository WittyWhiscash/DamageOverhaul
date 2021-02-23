package mod.wittywhiscash.damageoverhaul.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class DamageIndicatorParticle extends SpriteBillboardParticle {

    protected DamageIndicatorParticle(ClientWorld clientWorld, double x, double y, double z, double dx, double dy, double dz, SpriteProvider provider) {
        super(clientWorld, x, y, z, dx, dy, dz);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public static class DefaultFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider provider;

        public DefaultFactory(SpriteProvider provider) {
            this.provider = provider;
        }

        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            DamageIndicatorParticle particle = new DamageIndicatorParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.provider);
            particle.setSprite(provider);
            return particle;
        }
    }

}
