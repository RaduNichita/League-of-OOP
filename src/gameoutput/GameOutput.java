package gameoutput;

import players.AbstractPlayer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

public final class GameOutput {

    private PrintWriter writer;
        public GameOutput(final String fileName) throws FileNotFoundException {
            writer = new PrintWriter(fileName);
        }

        public void writeResults(final Collection<? extends AbstractPlayer> array) {
                for (Object o : array) {
                    writer.println(o);
                }
                writer.close();
        }
}
