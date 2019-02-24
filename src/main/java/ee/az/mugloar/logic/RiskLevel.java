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
	
	/*
	 
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Sure thing' success 49/53 (92%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Hmmm....' success 70/112 (62%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Piece of cake' success 174/179 (97%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Walk in the park' success 98/122 (80%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Suicide mission' success 6/74 (8%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Rather detrimental' success 39/135 (28%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Gamble' success 58/112 (51%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Playing with fire' success 1/6 (16%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Impossible' success 0/31 (0%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Quite likely' success 101/132 (76%)
INFO	2019-02-24 03:07:39,441	426251	ee.az.mugloar.utils.DataCollector	[main]	Risk 'Risky' success 0/1 (0%)
	  */
	
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
