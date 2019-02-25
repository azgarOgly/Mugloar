package ee.az.mugloar.logic;

public enum RiskLevel {
	CAKE("Piece of cake", 95, true),
	SURE("Sure thing", 90, true),
	PARK_WALK("Walk in the park", 80, true),
	LIKELY("Quite likely", 75, true),
	HMMM("Hmmm....", 65, true),
	GAMBLE("Gamble", 50, false),
	RISKY("Risky", 40, false),
	DETRIMENTAL("Rather detrimental", 30, true),
	FIRE("Playing with fire", 20, false), 
	SUICIDE("Suicide mission", 10, false),
	IMPOSSIBLE("Impossible", 5, false),
	UNKNOWN("Unknown", 0, false);
	
	boolean acceptable;
	String label;
	int score;
	
	private RiskLevel(String label, int score, boolean acceptable) {
		this.acceptable = acceptable;
		this.label = label;
		this.score = score;
	}
	
	public static RiskLevel getRiskLevel(String s) {
		for(RiskLevel r : values()) {
			if (r.label.equalsIgnoreCase(s)) {
				return r;
			}
		}
		return UNKNOWN;
	}
	
	public boolean isAcceptable() {
		return acceptable;
	}
	public int getScore() {
		return score;
	}
}
