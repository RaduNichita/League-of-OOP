package gameoutput;


import players.AbstractPlayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

public final class GameOutput {

        private static PrintWriter writer;

        public GameOutput(final String fileName) throws FileNotFoundException {
            writer = new PrintWriter(fileName);
        }

        public void writeRoundBeginning(final int roundNumber) {
            writer.println("~~ Round " + (roundNumber + 1) + " ~~");
        }

        public void writeRoundEnding() {
            writer.println();
        }

        public static void writeRequestedMessage(final String requestMessage) {
            writer.println(requestMessage);
        }

        public void writeResults(final Collection<? extends AbstractPlayer> array) {
            writer.println("~~ Results ~~");
                for (Object o : array) {
                    writer.println(o);
                }
                writer.close();
        }


}
