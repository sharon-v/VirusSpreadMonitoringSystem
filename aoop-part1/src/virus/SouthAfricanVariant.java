package virus;

import java.util.Random;

import population.Person;
import population.Sick;
import simulation.Clock;

/**
 * 
 * @author Yarden Hovav, Sharon Vazana
 *
 */
public class SouthAfricanVariant implements IVirus {
	@Override
	public String toString() {
		return "South African Variant";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SouthAfricanVariant))
			return false;
		SouthAfricanVariant s = (SouthAfricanVariant) o;
		return toString().equals(s.toString());
	}

	@Override
	public double contagionProbability(Person p) {
		double contagionProbability;
		if(p.getAge() <= 18)
			contagionProbability = contagionProbTo18;
		else
			contagionProbability = contagionProb18Above;
		return contagionProbability * p.contagionProbability();
	}
	
	@Override
	public boolean tryToContagion(Person p1, Person p2){
		Random ran = new Random();
		double randomNumber = ran.nextDouble(); // [0, 1)
		if (p1.healthCondition().equals("Sick"))
			if (Clock.calculateDays(((Sick) p1).getContagiousTime()) < 5)
				return false;
		if (!(p2.healthCondition().equals("Sick"))) {
			double d = p1.distance(p2); // distance between 2 people
			return (contagionProbability(p2) * Math.min(1, 0.14 * Math.exp(2 - 0.25 * d)) > randomNumber);
		}
		return false;
	}
	
	@Override
	public boolean tryToKill(Sick s) {
		Random ran = new Random();
		double randomNumber = ran.nextDouble();
		double p ; //the probability to die according to age
		if(s.getAge() <= 18)
			p = deathProbTo18;
		else
			p= deathProb18Above;
		long t = Clock.calculateDays(s.getContagiousTime()); // the time that passed since contagion
		return Math.max(0, p - 0.01 * p * Math.pow(t - 15, 2)) >= randomNumber;
	}

	// attributes
	private final double deathProbTo18 = 0.05;// death probability up to 18
	private final double deathProb18Above = 0.08;// death probability above 18
	private final double contagionProbTo18 = 0.6;// contagion probability up to 18
	private final double contagionProb18Above = 0.5;// contagion probability above 18

}
