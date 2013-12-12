/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recorderfingerings;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.Track;
import mathnstuff.MeUtils;

/**
 *
 * @author mewer
 */
public class MidiUtilities {
    public static int findEventIndex(final Track track, final long tick) {
        return (int)MeUtils.binarySearch(new MeUtils.SearchKernel() {
            @Override
            public boolean beforeOrAt(long i) {
                MidiEvent event = track.get((int)i);
                return (tick <= event.getTick());
            }

            @Override
            public long size() {
                return track.size();
            }
        });
    }

    public static MidiEvent findEvent(final Track track, final long tick) {
        return track.get(findEventIndex(track, tick));
    }
}
