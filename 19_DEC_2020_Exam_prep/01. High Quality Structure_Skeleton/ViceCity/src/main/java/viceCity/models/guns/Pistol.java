package viceCity.models.guns;

public class Pistol extends BaseGun {
    public static final int BULLETS_PER_BARREL = 10;
    public static final int TOTAL_BULLETS = 100;
    public static final int BULLETS_SHOT_WHEN_FIRE = 1;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {
        this.setTotalBullets(this.getTotalBullets() - BULLETS_SHOT_WHEN_FIRE);
        return BULLETS_SHOT_WHEN_FIRE;
    }

    @Override
    public boolean canFire(){
        return super.getTotalBullets() > BULLETS_SHOT_WHEN_FIRE;
    }
}
