package com.laakarisimu.eduni.distributions;

/**
 * A random number generator based on the negexp distribution.
 * automatically generated by <code>MetaGenerator</code>
 *
 * @author F.Mallet from C.Simatos's original
 * @version 1.0, Thu Oct 03 10:57:43 BST 2002
 */

public class Negexp extends Generator implements ContinuousGenerator {
    private double mean;

    /**
     * the seed is aumatically provided by the <code>SeedGenerator</code>
     */
    public Negexp(double mean) {
        super();
        set(mean);
    }

    /**
     * The constructor with which a specific seed is set for the random
     * number generator
     *
     * @param seed The initial seed for the generator, two instances with
     *             the same seed will generate the same sequence of numbers
     */
    public Negexp(double mean, long seed) {
        super(seed);
        set(mean);
    }

    private void set(double mean) {
        if (mean <= 0)
            throw new ParameterException("Negexp: The mean must be greater than 0.");
        this.mean = mean;
    }

    /**
     * Generate a new random number.
     *
     * @return The next random number in the sequence
     */
    public double sample() {
        return distrib.negexp(mean);
    }
}
