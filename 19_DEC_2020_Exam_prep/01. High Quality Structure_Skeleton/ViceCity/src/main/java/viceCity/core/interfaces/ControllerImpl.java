package viceCity.core.interfaces;

import viceCity.common.ConstantMessages;
import viceCity.models.guns.BaseGun;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;

import java.util.*;

import static viceCity.common.ConstantMessages.GUN_TYPE_INVALID;
import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller {
    private Queue<BaseGun> gunsInTheQueue;
    private Collection<Player> civilPlayers;
    private Player mainPlayer;
    private GangNeighbourhood neighbourhood;

    public ControllerImpl() {
        mainPlayer = new MainPlayer();
        this.gunsInTheQueue = new ArrayDeque<>();
        civilPlayers = new ArrayList<>();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        CivilPlayer civilPlayer = new CivilPlayer(name);
        this.civilPlayers.add(civilPlayer);
        return String.format(PLAYER_ADDED,name);
    }

    @Override
    public String addGun(String type, String name) {
        BaseGun gun;
        if (type.equals("Pistol")){
           gun = new Pistol(name);
        } else if (type.equals("Rifle")){
            gun = new Rifle(name);
        } else {
            return GUN_TYPE_INVALID;
        }
        this.gunsInTheQueue.offer(gun);
        return String.format(GUN_ADDED,name,type);
    }

    @Override
    public String addGunToPlayer(String name) {
        if (this.gunsInTheQueue.isEmpty()){
            return GUN_QUEUE_IS_EMPTY;
        }
        if (name.equals("Vercetti")){
            String gunName = this.gunsInTheQueue.peek().getName();
            this.mainPlayer.getGunRepository().add(this.gunsInTheQueue.poll());
            return String.format(GUN_ADDED_TO_MAIN_PLAYER,gunName,this.mainPlayer.getName());
        }
        Player civilPlayer = this.civilPlayers.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
        if (civilPlayer == null){
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        } else {
            String gunName = this.gunsInTheQueue.peek().getName();
            this.civilPlayers.stream()
                    .filter(p -> p.getName().equals(name))
                    .findFirst()
                    .get().getGunRepository().add(this.gunsInTheQueue.poll());
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER,gunName,name);
        }
    }

    @Override
    public String fight() {
        int mainPlayerPoints = mainPlayer.getLifePoints();
        int civilPlayersPoints = civilPlayers.stream().mapToInt(Player::getLifePoints).sum();
        int countPlayers = civilPlayers.size();
        neighbourhood.action(mainPlayer, civilPlayers);
        boolean pointsEquals = civilPlayersPoints == civilPlayers.stream().mapToInt(Player::getLifePoints).sum();

        if (mainPlayerPoints == mainPlayer.getLifePoints() && pointsEquals) {
            return FIGHT_HOT_HAPPENED;
        } else {
            StringBuilder output = new StringBuilder();
            output.append(FIGHT_HAPPENED)
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, mainPlayer.getLifePoints()))
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, countPlayers - civilPlayers.size()))
                    .append(System.lineSeparator())
                    .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size()));
            return output.toString();
        }
    }
}
