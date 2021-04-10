package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {

        Gun gun = mainPlayer.getGunRepository().getModels().stream().filter(Gun::canFire).findFirst().orElse(null);
        Player civilPlayer = civilPlayers.stream().filter(Player::isAlive).findFirst().orElse(null);

        while (civilPlayer != null && gun != null) {
            while (gun.canFire() && civilPlayer.isAlive()) {
                int bullets = gun.fire();
                civilPlayer.takeLifePoints(bullets);
            }
            if (!gun.canFire()) {
                mainPlayer.getGunRepository().remove(gun);
                gun = mainPlayer.getGunRepository().getModels().stream().filter(Gun::canFire).findFirst().orElse(null);
            }
            if (!civilPlayer.isAlive()) {
                civilPlayers.remove(civilPlayer);
                civilPlayer = civilPlayers.stream().filter(Player::isAlive).findFirst().orElse(null);
            }
        }

        boolean vercettiIsDead = false;

        for (Player player : civilPlayers) {
            Gun currentGun = player.getGunRepository().getModels().stream().filter(Gun::canFire).findFirst().orElse(null);
            while (currentGun != null) {
                while (currentGun .canFire() && mainPlayer.isAlive()) {
                    int fire = currentGun.fire();
                    mainPlayer.takeLifePoints(fire);
                }
                if (!currentGun.canFire()){
                    player.getGunRepository().remove(currentGun);
                    currentGun = player.getGunRepository().getModels().stream().filter(Gun::canFire).findFirst().orElse(null);
                }
                if (!mainPlayer.isAlive()){
                    vercettiIsDead = true;
                    break;
                }
            }
            if (vercettiIsDead){
                return;
            }
        }

    }
}
