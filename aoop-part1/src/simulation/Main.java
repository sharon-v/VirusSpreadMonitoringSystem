package simulation;

import java.util.Random;

import country.City;
import country.Map;
import country.RamzorColor;
import country.Settlement;
import location.Location;
import location.Point;
import location.Size;
import population.Healthy;
import population.Sick;
import ui.MainWindow;
import ui.MapDrawing;
import virus.BritishVariant;
import virus.ChineseVariant;

/**
 * 
 * @author Yarden Hovav, Sharon Vazana
 *
 */
public class Main {

	public static void main(String[] args) {
		Map map = new Map();// Map instance
		MainWindow theWindow = new MainWindow(map);
		playSimu(map, theWindow.getMapDrawing());
	}



	
	private static void playSimu(Map myMap, MapDrawing draw) {
		while (true) {
			System.out.print("");
			if (playFlag == true && loadFlag == true) {
				try {
					System.out.println("ticks : " + Clock.now());
					myMap.executeSimulation(); // third stage
					Clock.nextTick();
					draw.repaint();
					Thread.sleep(sleepTime * 1000);
				} catch (Exception ex) {
					System.out.println("an unexpected ERROR has occurred :(");
					ex.printStackTrace();
				}
			}
		}
	}

	public static void setPlayFlag(boolean val) {
		playFlag = val;
	}

	public static void setLoadFlag(boolean val) {
		loadFlag = val;
	}

	public static boolean getPlayFlag() {
		return playFlag;
	}

	public static boolean getLoadFlag() {
		return loadFlag;
	}

	public static void setSleepTime(int val) {
		sleepTime = val;
	}


	private static boolean playFlag = true;
	private static boolean loadFlag = false;
	private static int sleepTime = 1;

}
