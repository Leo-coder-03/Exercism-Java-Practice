import java.util.*;

class Tournament {

    private static class Team {
        String name;
        int mp = 0;
        int w = 0; 
        int d = 0; 
        int l = 0; 
        int p = 0; 

        Team(String name) {
            this.name = name;
        }

        void recordResult(String result) {
            mp++;
            switch (result) {
                case "win" -> {
                    w++;
                    p += 3;
                }
                case "draw" -> {
                    d++;
                    p += 1;
                }
                case "loss" -> l++;
            }
        }
    }

    private final Map<String, Team> teams = new HashMap<>();

    void applyResults(String resultString) {
        if (resultString == null || resultString.isBlank()) return;

        String[] lines = resultString.split("\\n");
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length != 3) continue;

            String team1Name = parts[0];
            String team2Name = parts[1];
            String result = parts[2];

            Team team1 = teams.computeIfAbsent(team1Name, Team::new);
            Team team2 = teams.computeIfAbsent(team2Name, Team::new);

            switch (result) {
                case "win" -> {
                    team1.recordResult("win");
                    team2.recordResult("loss");
                }
                case "loss" -> {
                    team1.recordResult("loss");
                    team2.recordResult("win");
                }
                case "draw" -> {
                    team1.recordResult("draw");
                    team2.recordResult("draw");
                }
            }
        }
    }

    String printTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team                           | MP |  W |  D |  L |  P\n");

        List<Team> teamList = new ArrayList<>(teams.values());
        teamList.sort(Comparator.comparingInt((Team t) -> -t.p).thenComparing(t -> t.name));

        for (Team t : teamList) {
            sb.append(String.format("%-30s | %2d | %2d | %2d | %2d | %2d\n",
                    t.name, t.mp, t.w, t.d, t.l, t.p));
        }

        return sb.toString();
    }
}
