package p05FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public void removePlayer(String player) {
        boolean isInTheTeam = false;
        for (Player currentPlayer : this.players) {
            if (currentPlayer.getName().equals(player)) {
                isInTheTeam = true;
                this.players.remove(currentPlayer);
                break;
            }
        }
        if (!isInTheTeam) {
            throw new IllegalArgumentException(String.format("Player %s is not in %s team.", player, this.name));
        }
    }

    public void addPlayer(Player player){
       this.players.add(player);
    }

    public double getRating(){
        return this.players.stream().mapToDouble(Player::overallSkillLevel).average().orElse(0);

    }
}
