/*******************************************************************************
 *
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 ******************************************************************************/

public class GuitarString {
    // YOUR INSTANCE VARIABLES HERE
    private final int RATE = 44100; // sampling
    private RingBuffer guitstring;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        // YOUR CODE HERE
        guitstring = new RingBuffer((int) Math.ceil(RATE / frequency)); // formula given
        System.out.println(guitstring); // To check how many frequencies
        while (!guitstring.isFull()) // If full, run an enqueue
            guitstring.enqueue(0);

    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        // YOUR CODE HERE
        guitstring = new RingBuffer(init.length);
        for (int i = 0; !guitstring.isFull(); i++) // Run a for loop to fill in array
            guitstring.enqueue(init[i]);
    }

    // returns the number of samples in the ring buffer
    public int length() {
        // YOUR CODE HERE
        int samples = guitstring.size();
        return samples; // Returns the array size
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        // YOUR CODE HERE
        int pick = guitstring.size();
        // Loop to pick x numbers inside of an array with the 2 values given
        for (int i = 0; i < pick; i++) {
            double x = StdRandom.uniform(-0.5, 0.5); // Picks between -0.5 through 0.5
            guitstring.dequeue();
            guitstring.enqueue(x);
        }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        // YOUR CODE HERE
        double deq = guitstring.dequeue();
        double peek = guitstring.peek();
        double enq = 0.996 * (0.5 * (deq + peek)); // Formula given in assignment doc
        guitstring.enqueue(enq);

    }

    // returns the current sample
    public double sample() {
        // YOUR CODE HERE
        return guitstring.peek();

    }

    // tests and calls every constructor and instance method in this class
    // Main args given by assignment doc
    public static void main(String[] args) {
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);
        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }

}

