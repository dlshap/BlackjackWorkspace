package com.blackjack.strategy;

import com.blackjack.player.Play;

public class BuildPlayForHardHands {
	public static Play[][] build() {
		// play array index is total facevalue (3-21) -3
		// against dealer card:
		// p[] = {2,3,4,5,6,7,8,9,10,A}

		Play[][] play = new Play[19][10];

		{ // 17-Blackjack (14-18)
			Play p[] = { Play.STAND, Play.STAND, Play.STAND, Play.STAND,
					Play.STAND, Play.STAND, Play.STAND, Play.STAND, Play.STAND,
					Play.STAND };
			for (int i = 14; i < 19; i++)
				play[i] = p;
		}
		{// 13-16 [10-13]
			Play p[] = { Play.STAND, Play.STAND, Play.STAND, Play.STAND,
					Play.STAND, Play.HIT, Play.HIT, Play.HIT, Play.HIT,
					Play.HIT };
			for (int i = 10; i < 14; i++)
				play[i] = p;
		}
		{// 12 [9]
			Play p[] = { Play.HIT, Play.HIT, Play.STAND, Play.STAND,
					Play.STAND, Play.HIT, Play.HIT, Play.HIT, Play.HIT,
					Play.HIT };
			play[9] = p;
		}
		{// 11 [8]
			Play p[] = { Play.DOUBLE, Play.DOUBLE, Play.DOUBLE, Play.DOUBLE,
					Play.DOUBLE, Play.DOUBLE, Play.DOUBLE, Play.DOUBLE,
					Play.DOUBLE, Play.HIT };
			play[8] = p;
		}
		{// 10 [7]
			Play p[] = { Play.DOUBLE, Play.DOUBLE, Play.DOUBLE, Play.DOUBLE,
					Play.DOUBLE, Play.DOUBLE, Play.DOUBLE, Play.DOUBLE,
					Play.HIT, Play.HIT };
			play[7] = p;
		}
		{// 9 [6]
			Play p[] = { Play.DOUBLE, Play.DOUBLE, Play.DOUBLE, Play.DOUBLE,
					Play.DOUBLE, Play.DOUBLE, Play.HIT, Play.HIT, Play.HIT,
					Play.HIT };
			play[6] = p;
		}
		{// 3-8 [0-5]
			Play p[] = { Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT,
					Play.HIT, Play.HIT, Play.HIT, Play.HIT, Play.HIT };
			for (int i = 0; i < 6; i++)
				play[i] = p;
		}
		return play;

	}
}
