package com.laakarisimu.eduni.distributions;

/**
 * represents classes which has a seed
 *
 * @see Generator
 * @see ContinuousGenerator
 * @see DiscreteGenerator
 */
public interface Seedable {
    /**
     * Get the random number generator's seed.
     *
     * @return The generator's seed
     */
    long getSeed();

    /**
     * Set the random number generator's seed.
     *
     * @param seed The new seed for the generator
     */
    void setSeed(long seed);

    /**
     * Get another seed well-spaced (from the default <code>SeedGenerator</code>)
     */
    void reseed();
}
