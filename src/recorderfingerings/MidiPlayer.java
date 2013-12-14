/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recorderfingerings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;
import javax.swing.Timer;

/**
 *
 * @author mewer
 */
public class MidiPlayer {
    public static MidiDevice receivingDevice = null;
    
    public boolean useExternalSynth = false;
    public Sequence sequence;
    private Sequencer sequencer;
    
    private MidiEventListener listener;
    private Timer eventTimer;
    
    public static void ensureReceivingDevice() throws MidiUnavailableException {
        if (receivingDevice == null) {
            receivingDevice = getReceivingDevice();
            //TODO This should probably be closed later.
            receivingDevice.open();
        }
    }
    
    public MidiPlayer(Sequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        long time = System.currentTimeMillis();
        this.sequence = sequence;
        System.out.println("0 time " + (System.currentTimeMillis() - time));
        ensureReceivingDevice();
        System.out.println("1 time " + (System.currentTimeMillis() - time));
        System.out.println("2 time " + (System.currentTimeMillis() - time));

        sequencer = MidiSystem.getSequencer(false);
        System.out.println("3 time " + (System.currentTimeMillis() - time));
        Transmitter tx = sequencer.getTransmitter();
        System.out.println("4 time " + (System.currentTimeMillis() - time));
        Receiver rx = receivingDevice.getReceiver();
        System.out.println("5 time " + (System.currentTimeMillis() - time));
        tx.setReceiver(rx);
        System.out.println("6 time " + (System.currentTimeMillis() - time));

        sequencer.open();
        System.out.println("7 time " + (System.currentTimeMillis() - time));
        sequencer.setSequence(sequence);
        System.out.println("8 time " + (System.currentTimeMillis() - time));
    }
    
    public void hotswapSequence(Sequence sequence) throws InvalidMidiDataException {
        this.sequence = sequence;
        long pos = sequencer.getTickPosition();
        sequencer.setSequence(sequence);
        sequencer.setTickPosition(pos);
        listener.tracks = sequence.getTracks();
        listener.currentIndices = new int[listener.tracks.length];
        for (int i = 0; i < listener.tracks.length; i++) {
            listener.currentIndices[i] = MidiUtilities.findEventIndex(sequence.getTracks()[i], pos);
        }
    }

    public void setTempoFactor(float factor) {
        sequencer.setTempoFactor(factor);
    }
    
    public static MidiDevice getReceivingDevice() throws MidiUnavailableException {
        for (MidiDevice.Info mdi: MidiSystem.getMidiDeviceInfo()) {
            MidiDevice dev = MidiSystem.getMidiDevice(mdi);
            if (dev.getMaxReceivers() != 0) {
//                String lcName = StringUtils.defaultString(mdi.getName()).toLowerCase(Locale.ENGLISH);
                String lcName = mdi.getName().toLowerCase(Locale.ENGLISH);
//                if (lcName.contains(useExternalSynth ? "usb": "java")) {
                    return dev;
//                }
            }
        }
        return null;
    }    
    
    public boolean isRunning() {
        return sequencer.isRunning();
    }
    
    public void start() {
        if (eventTimer != null) {
            eventTimer.stop();
        }
        eventTimer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMidiEventListener(MidiPlayer.this.listener);
            }
        });
        eventTimer.start();
        sequencer.start();
    }

    public void stop() {
        if (eventTimer != null) {
            eventTimer.stop();
        }
        sequencer.stop();
    }

    /**
     * Currently just assumes the first track in the sequence.
     * @param listener 
     */
    public void setMidiEventListener(final MidiEventListener listener) {
        this.listener = listener;
        listener.tracks = sequence.getTracks();
        listener.currentIndices = new int[listener.tracks.length];
        long pos = sequencer.getTickPosition();
        for (int i = 0; i < listener.tracks.length; i++) {
            listener.currentIndices[i] = MidiUtilities.findEventIndex(sequence.getTracks()[i], pos);
        }
    }

    public boolean released = false;
    
    private synchronized void updateMidiEventListener(MidiEventListener listener) {
        //TODO Account for pausing.
        if (released) {
            eventTimer.stop();
            return;
        }
        
        int delay = 50; // Max delay
        
        boolean finished = true;
        for (int i = 0; i < listener.tracks.length; i++) {
            Track t = listener.tracks[i];

            long tick = sequencer.getTickPosition();
            
            int size = t.size();
            if (listener.currentIndices[i] >= size || listener.currentIndices[i] < 0) {
                continue;
            }
            finished = false;

            MidiEvent curEvent = null;
            curEvent = t.get(listener.currentIndices[i]);
            int count = 0;
            //TODO This happens to be slightly inefficient
            while (curEvent.getTick() <= tick && listener.currentIndices[i] < size) {
                curEvent = t.get(listener.currentIndices[i]);
                listener.onEvent(i, curEvent);
                listener.currentIndices[i]++;
                count++;
            }
            
            delay = Math.min(delay, (int)(((curEvent.getTick() - tick) * microsPerTick()) / 1000));
        }
        if (finished) {
            eventTimer.stop();
            return;
        }
        
        eventTimer.setDelay(delay);
    }
    
    public long microsPerTick() {
        if (sequence.getDivisionType() == Sequence.PPQ) {
            return (long)(60000000 / (sequence.getResolution() * sequencer.getTempoInBPM() * sequencer.getTempoFactor()));
        } else {
            System.err.println("Oh heck!  Weird resolution!");
        }
        return 10000;
    }
    
    public static abstract class MidiEventListener {
        public Track[] tracks;
        public int[] currentIndices;
        public abstract void onEvent(int track, MidiEvent event);
    }
}
