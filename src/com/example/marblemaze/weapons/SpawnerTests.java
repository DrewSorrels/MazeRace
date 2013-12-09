package com.example.marblemaze.weapons;

/**
 * // -------------------------------------------------------------------------
 * /** Tests the (@link RocketSpawner) and (@link LaserSpawner) classes
 *
 * @author Drew Sorrels (amsorr)
 * @version 2013.12.08
 */
public class SpawnerTests
    extends student.TestCase
{
    private LaserSpawner  ls;
    private RocketSpawner rs;


    /**
     * Sets up the fields for tests.
     */
    public void setUp()
    {
        ls = new LaserSpawner(0, 0, 3000, 0);
        rs = new RocketSpawner(0, 0, 4000, 0);
    }


    /**
     * Tests the create bullet method.
     */
    public void testCreateBullet()
    {
        ls.createBullet();
        rs.createBullet();
        assertNotNull(ls);
    }
}
