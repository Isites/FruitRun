/*
 * RunnersHigh Settings 
 * _DESCRIPTION:
 * 	for global values
 */

package yixing.fruitrun;
// Download by http://www.codefans.net
public class Settings {	
	
	public static final String LOG_TAG = "RunnersHigh";
	
	public static final String URL_RUNNERSHIGH = "http://rh.fidrelity.at";
	public static final String HIGHSCORE_GET_URL = "http://rh.fidrelity.at/best.php";
	public static final String HIGHSCORE_POST_URL = "http://rh.fidrelity.at/post/post_highscore.php";
	
	public static final String URL_ANDRE = "http://fidrelity.at/";
	public static final String URL_ANDREAS = "http://www.andreasnagl.at";
	public static final String URL_CHRIS = "http://cwinkler.multimediatechnology.at/";
	public static final String URL_FRANCOIS = "http://www.weberdevelopment.de/";
	public static final String URL_HANS = "http://kookaburradesign.at";
	public static final String URL_MANUEL = "http://www.manuel-lehermayr.at";
//	static final String URL_MARIO = "http://www.manuel-lehermayr.at";
	public static final String URL_STEVE = "http://steve.multimediatechnology.at/";
	public static final String URL_WEBI = "http://mwebi.multimediatechnology.at/";
	
	public static final String URL_FH = "http://www.fh-salzburg.ac.at";
	public static final String URL_MMA = "http://multimediaart.at/";
	public static final String URL_MMT = "http://multimediatechnology.at/";
	
	public static final String URL_SONY = "http://www.sonydadc.com/";
	
	public static final boolean RHDEBUG = false;
	public static boolean SHOW_FPS = false;
	
	public static final boolean showHighscoreMarks = false;
	
	//gameplay settings
	static public float FirstBlockHeight = 70;
	
	public static final int TimeForLoadingScreenToBeVisible = 3500;
	
	public static final int TimeOfFirstSpeedIncrease = 30000;
	public static final int timeToFurtherSpeedIncreaseMillis = 10000;
	public static final int timeUntilLongBlocksStopMillis = 40000;

	public static final int onlineHighscoreLimit = 100;
	
	
	public static final String URL_DONATE = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=PSPCC2Z4PLL8W";
}