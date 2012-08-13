package location.writer;

import java.io.IOException;
import java.util.List;

import location.ILocation;

public interface ILocationWriter {
    public boolean write(List<ILocation> locs) throws IOException;
}
