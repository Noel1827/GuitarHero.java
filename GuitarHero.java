public class GuitarHero {
    public static void main(String[] args) {

        String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int notes = 37;

        GuitarString[] strings = new GuitarString[notes];
        // For loop to calculate frequency and total strings
        for (int i = 0; i < notes; i++) {
            double frequency = 440 * Math.pow(2, ((i - 24) / 12.0)); // Formula given
            strings[i] = new GuitarString(frequency);

        }
        // the main input loop
        Keyboard keyboard = new Keyboard();
        while (true) {

            // check if the user has played a key; if so, process it
            if (keyboard.hasNextKeyPlayed()) {

                // the key the user played
                char key = keyboard.nextKeyPlayed();

                int character = keyboardString.indexOf(key);
                // pluck the corresponding string
                // When -1, that means no characters are typed and code can work
                if (character == -1) {
                    continue;
                }
                strings[character].pluck(); // Pluck x character from x array
            }
            double sample = 0;

            // Loop to calculate how many hertz used for the index array
            for (int i = 0; i < strings.length; i++) {
                sample += strings[i].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            // For loop made to sound tic with x string on x index array
            for (int i = 0; i < strings.length; i++) {
                strings[i].tic();
            }

        }
    }
}
