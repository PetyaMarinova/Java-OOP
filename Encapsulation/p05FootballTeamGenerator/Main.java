package p05FootballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Team> allTeams = new LinkedHashMap<>();

        while (!input.equals("END")) {
            String[] tokens = input.split(";");
            try {
                switch (tokens[0]) {
                    case "Team":
                        String teamName = tokens[1];
                        Team team = new Team(teamName);
                        allTeams.putIfAbsent(team.getName(), team);
                        break;
                    case "Add":
                        String teamNameToAdd = tokens[1];
                        String playerName = tokens[2];
                        int endurance = Integer.parseInt(tokens[3]);
                        int spirit = Integer.parseInt(tokens[4]);
                        int dribble = Integer.parseInt(tokens[5]);
                        int passing = Integer.parseInt(tokens[6]);
                        int shooting = Integer.parseInt(tokens[7]);
                        if (!allTeams.containsKey(teamNameToAdd)) {
                            System.out.printf("Team %s does not exist.%n", teamNameToAdd);
                        } else {
                            Player player = new Player(playerName, endurance, spirit, dribble, passing, shooting);
                            allTeams.get(teamNameToAdd).addPlayer(player);
                        }
                        break;
                    case "Remove":
                        String teamNameToRemove = tokens[1];
                        String playerNameToRemove = tokens[2];
                        allTeams.get(teamNameToRemove).removePlayer(playerNameToRemove);
                        break;
                    case "Rating":
                        String ratingTeamName = tokens[1];
                        if (!allTeams.containsKey(ratingTeamName)) {
                            System.out.printf("Team %s does not exist.%n", ratingTeamName);
                        } else {
                            System.out.printf("%s - %.0f%n", ratingTeamName, allTeams.get(ratingTeamName).getRating());
                        }
                        break;
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
            input = scanner.nextLine();
        }
    }
}
