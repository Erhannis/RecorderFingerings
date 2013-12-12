/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package recorderfingerings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author mewer
 */
public class MidiConversions {

    public static final String[] pitches = {"C(-1)", "Db(-1)", "D(-1)", "Eb(-1)", "E(-1)", "F(-1)", "Gb(-1)", "G(-1)", "Ab(-1)", "A(-1)", "Bb(-1)", "B(-1)", "C0", "Db0", "D0", "Eb0", "E0", "F0", "Gb0", "G0", "Ab0", "A0", "Bb0", "B0", "C1", "Db1", "D1", "Eb1", "E1", "F1", "Gb1", "G1", "Ab1", "A1", "Bb1", "B1", "C2", "Db2", "D2", "Eb2", "E2", "F2", "Gb2", "G2", "Ab2", "A2", "Bb2", "B2", "C3", "Db3", "D3", "Eb3", "E3", "F3", "Gb3", "G3", "Ab3", "A3", "Bb3", "B3", "C4", "Db4", "D4", "Eb4", "E4", "F4", "Gb4", "G4", "Ab4", "A4", "Bb4", "B4", "C5", "Db5", "D5", "Eb5", "E5", "F5", "Gb5", "G5", "Ab5", "A5", "Bb5", "B5", "C6", "Db6", "D6", "Eb6", "E6", "F6", "Gb6", "G6", "Ab6", "A6", "Bb6", "B6", "C7", "Db7", "D7", "Eb7", "E7", "F7", "Gb7", "G7", "Ab7", "A7", "Bb7", "B7", "C8", "Db8", "D8", "Eb8", "E8", "F8", "Gb8", "G8", "Ab8", "A8", "Bb8", "B8", "C9", "Db9", "D9", "Eb9", "E9", "F9", "Gb9", "G9"};
    public static final String[] recorderPitches = {"11Cn","11Cs","12Dn","12Ds","13En","14Fn","14Fs","15Gn","15Gs","16An","17Bb","17Bn","21Cn","21Cs","22Dn","22Ds","23En","24Fn","24Fs","25Gn","25Gs","26An","27Bb","27Bn","31Cn","31Cs","32Dn","32Ds","33En","34Fn","34Fs","35Gn","35Gs","36An","99xx"};
    
    public static Image[] firstImages = new Image[recorderPitches.length];
    public static Image[] firstImagesUpsideDown = new Image[recorderPitches.length];
    
    public static final String BASE_PITCH = "C5";
    public static int basePitchInt = 0;
    
    public void init() {
        for (int i = 0; i < pitches.length; i++) {
            if (BASE_PITCH.equals(pitches[i])) {
                basePitchInt = i;
                break;
            }
        }
        for (int i = 0; i < firstImages.length; i++) {
            Image image = firstImage(recorderPitches[i]);
            firstImages[i] = image;
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = bi.createGraphics();
            g.drawImage(image, width, height, -width, -height, null);
            firstImagesUpsideDown[i] = bi;
        }
    }
    
    public Image getImage(int note, boolean upsideDown) {
        int newIndex = note - basePitchInt;
        if (newIndex < 0 || newIndex >= recorderPitches.length) {
            newIndex = recorderPitches.length - 1;
        }
        if (upsideDown) {
            return firstImagesUpsideDown[newIndex];
        } else {
            return firstImages[newIndex];
        }
    }
    
    public int sidebarWidth = 39;
    
    private Image doTest() {
        int basePitch = 0;
        for (int i = 0; i < pitches.length; i++) {
            if (BASE_PITCH.equals(pitches[i])) {
                basePitch = i;
                break;
            }
        }
        
        Image[] docImgs = new Image[nBildTeile * nBilder];
        this.FuGriff("15Gs", "1st Octave", "C", "", "", docImgs);
        Image[] first = new Image[nBildTeile];
        
        for (int i = 0; i < nBildTeile; i++) {
            first[i] = docImgs[i];
        }

        int mid = first[0].getWidth(null);
        int width = mid + first[1].getWidth(null);
        int height = first[0].getHeight(null);
        
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = bi.createGraphics();
        g.drawImage(first[0], 0, 0, null);
        int y = 0;
        for (int i = 1; i < nBildTeile; i++) {
            g.drawImage(first[i], mid, y, null);
            y += first[i].getHeight(null);
        }
        
        return bi;
    }
    
    private Image firstImage(String note) {
        Image[] docImgs = new Image[nBildTeile * nBilder];
        this.FuGriff(note, "1st Octave", "C", "", "", docImgs);
        Image[] first = new Image[nBildTeile];
        
        for (int i = 0; i < nBildTeile; i++) {
            first[i] = docImgs[i];
        }

        int mid = first[0].getWidth(null);
        int width = mid + first[1].getWidth(null);
        int height = first[0].getHeight(null);
        
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = bi.createGraphics();
        if (note.equals("99xx")) {
            g.setColor(Color.red);
            g.fillRect(0, 0, width, height);
        }
        g.drawImage(first[0], 0, 0, null);
        int y = 0;
        for (int i = 1; i < nBildTeile; i++) {
            g.drawImage(first[i], mid, y, null);
            y += first[i].getHeight(null);
        }
        
        return bi;
    }
    
    public static class Fingering {

        public String note;
        public String id;
        public String msgA;
        public String msgB;
        public int h1;
        public int h2;
        public int h3;
        public int h4;
        public int h5;
        public int h6;
        public int h7;
        public int h8;
        public int h9;
        public int[] holes;

        public Fingering(String note, String id, String msgA, String msgB, int h1, int h2, int h3, int h4, int h5, int h6, int h7, int h8, int h9) {
            this.note = note;
            this.id = id;
            this.msgA = msgA;
            this.msgB = msgB;
            this.h1 = h1;
            this.h2 = h2;
            this.h3 = h3;
            this.h4 = h4;
            this.h5 = h5;
            this.h6 = h6;
            this.h7 = h7;
            this.h8 = h8;
            this.h9 = h9;
            this.holes = new int[]{h1, h2, h3, h4, h5, h6, h7, h8, h9};
        }
    }
    public boolean UpsideDown = false;
    public ArrayList aDocImg = new ArrayList();
    public int nDocImg = 0;
//    public void FuDocImg(xDocImg) {
//        aDocImg[nDocImg]=xDocImg;
//        nDocImg=nDocImg+1;
//    };
//    {
//        FuDocImg(document.images.length); // save infos for Diag
//    }
    public String PfadPrefix = "/";
    public String Host = "Main";
    public int maxLoDef = 9;
    public String ImapDopp = "";
    public int nFloeBild = 8;
    public boolean ZeigeKreisGriffe = true;
    public boolean Print = false;
    public boolean TestAlert = false;

    public void WBalert(int number, String Text) {
        Text = " Please report the error number and fingering you displayed to the Webmaster (see CONTACT). Thank you very much for helping me to correct this bug (Winfried Bauer).  ---- More information for the WEBMASTER: " + Text;
        System.err.println("Error " + number + " = " + Text);
    }
    public int nBildVor = 0;
    public int nBildTeile = 9;
    public int posImg0 = 8;
    public int enFT;
    public ArrayList<Image> FT = new ArrayList<Image>();
    public int[] FTx = new int[nBildTeile * nFloeBild];

    {
        try {
            //if (nBildVor<0) nBildVor=document.images.length; // nBildVor has been pre-set for browser incompat. (see in Inc-Menu.php)
            String imgFile = "";
            if (UpsideDown) {
                imgFile = "misc/imgs/UpsideDown/";
            } else {
                imgFile = "misc/imgs/";
            }
            String[] imgs = new String[]{"leer.gif", "F00.gif", "F01.gif", "F02.gif", "F06.gif", "F07.gif", "F10.gif", "F11.gif", "F12.gif", "F16.gif", "F20.gif", "F21.gif", "F22.gif", "F26.gif", "F27.gif", "F30.gif", "F31.gif", "F32.gif", "F33.gif", "F34.gif", "F36.gif", "F40.gif", "F41.gif", "F42.gif", "F46.gif", "F47.gif", "F50.gif", "F51.gif", "F52.gif", "F53.gif", "F54.gif", "F56.gif", "F60.gif", "F61.gif", "F62.gif", "F63.gif", "F64.gif", "F66.gif", "F67.gif", "F70.gif", "F71.gif", "F72.gif", "F73.gif", "F74.gif", "F76.gif", "F77.gif", "F80.gif", "F81.gif", "F8b.gif"};
            int n = 0;
            for (int i = 0; i < imgs.length; i++) {
                FT.add(ImageIO.read(new File(imgFile + imgs[n++])));
            }
            enFT = FT.size() - 1;
            int a = 0;
            int x = 0;
            int b = 0;
            int c = 0;
            String text = " ";
            for (a = 0; a < nBildTeile * nFloeBild; a++) {
                FTx[a] = 0;
            }   //loe Ref Tab FTx
            for (x = 1; x < FT.size() - 1; x++) // gehe durch FT (ohne leer und F8b), baue FTx
            {
                text = imgs[x];
                a = text.length() - 4;
                c = Integer.valueOf(text.substring(a - 1, a));
                b = Integer.valueOf(text.substring(a - 2, a - 1));
                FTx[b * nFloeBild + c] = x;  //setze Pos von FT in FTx
                int xx = b * nFloeBild + c;
            }
        } ////////////////////////////////////////////////////////////////
        catch (IOException ex) {
            Logger.getLogger(MidiConversions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //public boolean ZeigeKreisGriffe=true;
    public int nBilder = 18;
    public String Instrument = "c";
    public String iMsg = "a";
    public int UnderConstr = 0;
    public String ImapGr = "Barock";
    public String ImapKZ = "Bar";
    public String Build = "Barock";
    ////////////////////////////////////////////////////////////////
    public Fingering[] Griffe = {
        new Fingering("11Cn", "11Cn", "", "", 1, 1, 1, 1, 1, 1, 1, 1, -1),
        new Fingering("11Cs", "11Cs", "", "", 1, 1, 1, 1, 1, 1, 1, 2, -1),
        new Fingering("12Dn", "12Dn", "", "", 1, 1, 1, 1, 1, 1, 1, 0, -1),
        new Fingering("12Ds", "12Ds", "", "", 1, 1, 1, 1, 1, 1, 2, 0, -1),
        new Fingering("13En", "13En", "", "", 1, 1, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("14Fn", "14Fn", "", "", 1, 1, 1, 1, 1, 0, 1, 1, -1),
        new Fingering("14Fn", "14FnC", "", "", 1, 1, 1, 1, 1, 0, 1, 0, -1),
        new Fingering("14Fs", "14Fs", "", "", 1, 1, 1, 1, 0, 1, 1, 0, -1),
        new Fingering("14Fs", "14FsC", "", "", 1, 1, 1, 1, 0, 1, 1, 2, -1),
        new Fingering("14Fs", "14FsC 1", "", "slightly flat", 1, 1, 1, 1, 0, 1, 1, 1, -1),
        new Fingering("15Gn", "15Gn", "", "", 1, 1, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("15Gs", "15Gs 1", "", "", 1, 1, 1, 0, 1, 1, 2, 0, -1),
        new Fingering("15Gs", "15GsB", "", "", 1, 1, 1, 0, 1, 1, 0, 0, -1),
        new Fingering("15Gs", "15GsB 1", "", "", 1, 1, 1, 0, 1, 1, 1, 0, -1),
        new Fingering("15Gs", "15GsB 2", "", "", 1, 1, 1, 0, 1, 0, 0, 0, -1),
        new Fingering("15Gs", "15GsB 3", "", "", 1, 1, 1, 0, 1, 1, 0, 1, -1),
        new Fingering("15Gs", "15GsB 4", "", "", 0, 1, 1, 0, 0, 1, 1, 1, -1),
        new Fingering("16An", "16An", "", "", 1, 1, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("16An", "16AnC", "slur with Bb", "", 1, 1, 0, 1, 1, 1, 2, 0, -1),
        new Fingering("16An", "16AnC 1", "", "", 1, 0, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("17Bb", "17Bb", "A#", "", 1, 1, 0, 1, 1, 0, 0, 0, -1),
        new Fingering("17Bb", "17Bb 6", "Bb", "", 1, 1, 0, 1, 0, 1, 1, 0, -1),
        new Fingering("17Bb", "17BbB", "", "", 1, 1, 0, 1, 0, 1, 1, 1, -1),
        new Fingering("17Bb", "17BbB 1", "", "", 1, 1, 0, 1, 1, 0, 1, 0, -1),
        new Fingering("17Bb", "17BbB 2", "", "", 1, 1, 0, 1, 0, 0, 0, 0, -1),
        new Fingering("17Bb", "17BbB 3", "", "", 1, 1, 0, 1, 1, 1, 0, 0, -1),
        new Fingering("17Bb", "17BbB 4", "", "", 1, 1, 0, 1, 1, 1, 1, 0, -1),
        new Fingering("17Bb", "17BbC 0a", "", "", 1, 1, 0, 1, 0, 1, 0, 0, -1),
        new Fingering("17Bb", "17BbC 1c", "", "", 1, 1, 0, 1, 2, 0, 1, 0, -1),
        new Fingering("17Bb", "17BbC 1d", "slur with A", "", 1, 1, 0, 1, 1, 6, 2, 0, -1),
        new Fingering("17Bb", "17BbC 2", "slur with C", "slightly sharp", 1, 0, 1, 1, 1, 0, 0, 0, -1),
        new Fingering("17Bb", "17BbC 3", "for piano", "slightly sharp", 1, 0, 1, 1, 1, 0, 1, 0, -1),
        new Fingering("17Bb", "17BbC 4", "", "blow gentle", 0, 1, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("17Bn", "17Bn", "", "", 1, 1, 0, 0, 0, 0, 0, 0, -1),
        new Fingering("17Bn", "17BnB", "slur with C", "", 1, 0, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("17Bn", "17BnC", "", "", 1, 0, 1, 1, 0, 1, 1, 1, -1),
        new Fingering("21Cn", "21Cn", "", "", 1, 0, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("21Cn", "21CnB", "slur with G", "", 0, 1, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("21Cn", "21CnC", "slur with Bb", "", 1, 0, 0, 1, 0, 1, 1, 0, -1),
        new Fingering("21Cn", "21CnC 1", "slur with Bb", "", 1, 0, 0, 1, 1, 0, 2, 0, -1),
        new Fingering("21Cn", "21CnC 2", "slur with Bb", "", 1, 0, 0, 1, 1, 0, 0, 0, -1),
        new Fingering("21Cn", "21CnC 3", "", "", 1, 0, 1, 0, 0, 1, 1, 0, -1),
        new Fingering("21Cn", "21CnC 4", "", "", 0, 0, 0, 1, 1, 1, 0, 0, -1),
        new Fingering("21Cs", "21Cs", "", "", 0, 1, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("21Cs", "21CsB 0a", "slur with B", "", 1, 0, 0, 0, 0, 0, 0, 0, -1),
        new Fingering("21Cs", "21CsB 1", "slur with Bb", "", 0, 1, 0, 1, 1, 0, 0, 0, -1),
        new Fingering("21Cs", "21CsB 2", "slur with D#", "", 0, 0, 1, 1, 1, 0, 0, 0, -1),
        new Fingering("21Cs", "21CsB 3", "slur with D#", "", 0, 0, 1, 1, 1, 0, 1, 0, -1),
        new Fingering("21Cs", "21CsB 4", "slur with Bb", "", 0, 1, 0, 1, 0, 1, 1, 0, -1),
        new Fingering("21Cs", "21CsB 5", "slur with Bb", "", 0, 1, 0, 1, 1, 0, 1, 0, -1),
        new Fingering("21Cs", "21CsC", "", "", 2, 1, 1, 1, 1, 1, 1, 1, -1),
        new Fingering("22Dn", "22Dn", "", "", 0, 0, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("22Dn", "22DnC", "slur with B", "slightly flat", 0, 1, 0, 0, 0, 0, 0, 0, -1),
        new Fingering("22Dn", "22DnC 1", "", "slightly sharp", 0, 1, 1, 1, 1, 1, 1, 1, -1),
        new Fingering("22Dn", "22DnC 2", "", "", 2, 1, 1, 1, 1, 1, 1, 2, -1),
        new Fingering("22Dn", "22DnC 3", "slur with Bb", "", 0, 0, 0, 1, 1, 0, 0, 0, -1),
        new Fingering("22Dn", "22DnC 4", "", "", 2, 0, 0, 1, 1, 1, 0, 0, -1),
        new Fingering("22Ds", "22Ds", "", "", 0, 0, 1, 1, 1, 1, 1, 0, -1),
        new Fingering("22Ds", "22DsB", "", "", 0, 0, 1, 1, 1, 1, 0, 1, -1),
        new Fingering("22Ds", "22DsB 1", "", "", 0, 1, 1, 1, 1, 1, 1, 0, -1),
        new Fingering("22Ds", "22DsC", "", "", 2, 1, 1, 1, 1, 1, 2, 0, -1),
        new Fingering("22Ds", "22DsC 1", "", "", 0, 0, 0, 1, 1, 1, 1, 0, -1),
        new Fingering("22Ds", "22DsC 2", "slur with Bb", "", 8, 1, 0, 1, 6, 1, 1, 0, -1),
        new Fingering("22Ds", "22DsC 3", "", "", 0, 0, 0, 0, 1, 1, 1, 0, -1),
        new Fingering("23En", "23En", "", "", 2, 1, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("23En", "23EnB", "", "", 0, 1, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("23En", "23EnB 1", "", "", 0, 0, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("23En", "23EnC", "", "", 1, 0, 1, 1, 1, 1, 0, 0, -1),
        new Fingering("23En", "23EnC 3", "", "", 2, 1, 1, 1, 1, 0, 1, 1, -1),
        new Fingering("24Fn", "24Fn", "", "", 2, 1, 1, 1, 1, 0, 1, 0, -1),
        new Fingering("24Fn", "24FnB", "slur with D#", "", 2, 1, 1, 1, 1, 0, 2, 0, -1),
        new Fingering("24Fn", "24FnB 1", "slur with C", "", 0, 1, 1, 1, 1, 0, 1, 0, -1),
        new Fingering("24Fn", "24FnB 2", "slur with F#", "", 2, 1, 1, 1, 0, 1, 2, 0, -1),
        new Fingering("24Fs", "24Fs", "", "", 2, 1, 1, 1, 0, 1, 0, 0, -1),
        new Fingering("24Fs", "24FsB 1", "slur with G + E", "", 2, 1, 1, 1, 1, 0, 0, 0, -1),
        new Fingering("24Fs", "24FsB 2", "slur with G + E", "", 2, 1, 1, 1, 2, 0, 0, 0, -1),
        new Fingering("24Fs", "24FsC", "", "", 2, 1, 1, 1, 1, 0, 2, 0, -1),
        new Fingering("25Gn", "25Gn", "", "", 2, 1, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("25Gn", "25GnB", "slur with F", "", 2, 1, 1, 1, 0, 0, 1, 0, -1),
        new Fingering("25Gs", "25Gs", "", "", 2, 1, 1, 0, 1, 0, 0, 0, -1),
        new Fingering("25Gs", "25GsB", "", "", 2, 1, 1, 1, 0, 1, 1, 1, -1),
        new Fingering("25Gs", "25GsC", "", "", 2, 1, 1, 1, 0, 0, 1, 1, -1),
        new Fingering("25Gs", "25GsC 1", "", "", 2, 1, 1, 1, 0, 0, 0, 1, -1),
        new Fingering("25Gs", "25GsC 2", "slur with<BR>F# /A", "", 2, 1, 1, 0, 0, 1, 0, 0, -1),
        new Fingering("25Gs", "25GsC 3", "", "", 2, 1, 1, 0, 1, 1, 0, 1, -1),
        new Fingering("25Gs", "25GsC 4", "slur with<BR>B / Bb", "", 2, 1, 1, 1, 1, 1, 1, 1, -1),
        new Fingering("26An", "26An", "", "", 2, 1, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("26An", "26AnC", "", "", 2, 1, 1, 0, 0, 0, 0, 1, -1),
        new Fingering("27Bb", "27Bb 1", "", "", 2, 1, 1, 0, 1, 1, 1, 0, -1),
        new Fingering("27Bb", "27BbC", "", "", 2, 1, 1, 0, 1, 1, 1, 2, -1),
        new Fingering("27Bb", "27BbC 1", "", "", 2, 1, 1, 0, 0, 0, 1, 2, -1),
        new Fingering("27Bb", "27BbC 2", "", "", 2, 1, 1, 0, 0, 1, 1, 0, -1),
        new Fingering("27Bb", "27BbC 3", "", "", 2, 1, 1, 0, 0, 1, 1, 1, -1),
        new Fingering("27Bb", "27BbC 5", "", "", 2, 1, 0, 1, 0, 1, 1, 1, -1),
        new Fingering("27Bb", "27BbC 6", "", "", 2, 1, 0, 1, 0, 1, 1, 0, -1),
        new Fingering("27Bn", "27Bn", "", "", 2, 1, 1, 0, 1, 1, 0, 0, -1),
        new Fingering("27Bn", "27BnC", "", "", 2, 1, 0, 0, 0, 0, 0, 0, -1),
        new Fingering("27Bn", "27BnC 1", "", "", 2, 1, 0, 0, 0, 1, 1, 1, -1),
        new Fingering("31Cn", "31Cn", "", "", 2, 1, 0, 0, 1, 1, 0, 0, -1),
        new Fingering("31Cn", "31CnB", "", "", 2, 1, 1, 1, 1, 1, 0, 0, 1),
        new Fingering("31Cn", "31CnC", "", "", 2, 1, 0, 0, 1, 0, 0, 0, -1),
        new Fingering("31Cn", "31CnC 1", "", "", 2, 1, 0, 1, 1, 0, 0, 0, -1),
        new Fingering("31Cn", "31CnC 2", "", "", 2, 1, 0, 1, 1, 1, 0, 0, -1),
        new Fingering("31Cn", "31CnC 3", "", "", 2, 1, 0, 1, 1, 1, 1, 0, 1),
        new Fingering("31Cs", "31Cs", "", "", 2, 1, 0, 1, 1, 0, 1, 2, 1),
        new Fingering("31Cs", "31Cs 1", "", "", 2, 1, 0, 1, 1, 0, 0, 1, -1),
        new Fingering("31Cs", "31CsB", "", "", 2, 1, 0, 0, 1, 1, 0, 0, 1),
        new Fingering("31Cs", "31CsB 0", "", "overblow", 1, 1, 2, 1, 1, 1, 2, 1, -1),
        new Fingering("31Cs", "31CsB 1", "", "", 2, 1, 0, 1, 1, 0, 1, 0, 1),
        new Fingering("31Cs", "31CsB 2", "", "overblow", 1, 1, 2, 1, 1, 1, 0, 1, -1),
        new Fingering("31Cs", "31CsC", "", "", 2, 1, 0, 0, 1, 1, 0, 1, -1),
        new Fingering("31Cs", "31CsC 2", "slur with<BR>B / C", "", 2, 0, 0, 0, 1, 1, 0, 0, -1),
        new Fingering("31Cs", "31CsC 3", "", "", 2, 1, 2, 1, 1, 1, 1, 1, -1),
        new Fingering("31Cs", "31CsC 4", "", "", 2, 1, 2, 1, 1, 2, 1, 1, -1),
        new Fingering("31Cs", "31CsC 5", "slur with D", "", 2, 1, 1, 1, 6, 1, 0, 1, -1),
        new Fingering("31Cs", "31CsC 6", "slur with D", "", 2, 1, 0, 1, 6, 1, 0, 1, -1),
        new Fingering("31Cs", "31CsC 7", "", "", 2, 1, 0, 1, 1, 1, 1, 1, -1),
        new Fingering("31Cs", "31CsC 8", "", "", 2, 1, 1, 1, 1, 1, 1, 1, -1),
        new Fingering("31Cs", "31CsC 90", "", "overblow", 1, 1, 0, 1, 1, 1, 0, 1, -1),
        new Fingering("31Cs", "31CsC 91", "", "overblow", 1, 1, 0, 1, 1, 1, 2, 1, -1),
        new Fingering("31Cs", "31CsC 92", "", "", 2, 1, 2, 1, 1, 1, 2, 1, -1),
        new Fingering("31Cs", "31CsC 93", "", "", 2, 1, 2, 1, 1, 0, 1, 1, -1),
        new Fingering("32Dn", "32Dn 1", "", "", 2, 1, 0, 1, 1, 0, 1, 2, -1),
        new Fingering("32Dn", "32DnB", "", "", 2, 1, 0, 1, 1, 0, 1, 1, -1),
        new Fingering("32Dn", "32DnB 1", "", "", 2, 1, 0, 1, 1, 0, 1, 0, -1),
        new Fingering("32Dn", "32DnB 2", "", "soft sound", 2, 1, 1, 1, 1, 0, 1, 0, -1),
        new Fingering("32Dn", "32DnC", "", "", 2, 1, 0, 1, 0, 0, 0, 0, 1),
        new Fingering("32Dn", "32DnC 1", "slur with C#", "", 2, 1, 0, 1, 6, 1, 0, 1, -1),
        new Fingering("32Dn", "32DnC 2", "slur with C#", "", 2, 1, 1, 1, 6, 1, 0, 1, -1),
        new Fingering("32Dn", "32DnC 3", "", "", 2, 1, 0, 0, 1, 0, 0, 1, -1),
        new Fingering("32Dn", "32DnC 4", "", "", 2, 1, 0, 0, 1, 0, 1, 1, -1),
        new Fingering("32Ds", "32Ds", "", "", 2, 0, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("32Ds", "32DsB", "slur with D", "", 2, 0, 1, 1, 0, 1, 1, 2, -1),
        new Fingering("32Ds", "32DsB 1a", "", "", 2, 0, 1, 1, 0, 1, 2, 0, -1),
        new Fingering("32Ds", "32DsB 1b", "", "", 2, 1, 0, 1, 0, 0, 1, 1, -1),
        new Fingering("32Ds", "32DsB 2", "", "", 2, 0, 1, 1, 0, 1, 1, 0, -1),
        new Fingering("32Ds", "32DsC", "slur with D", "", 2, 1, 0, 1, 0, 0, 1, 0, -1),
        new Fingering("32Ds", "32DsC 1", "slur with D", "", 2, 1, 0, 1, 0, 0, 0, 0, -1),
        new Fingering("33En", "33En", "", "", 2, 0, 1, 1, 0, 1, 1, 0, 1),
        new Fingering("33En", "33EnB", "", "", 2, 0, 1, 1, 0, 1, 2, 0, -1),
        new Fingering("33En", "33EnB 1", "", "", 2, 0, 1, 1, 0, 1, 2, 0, 1),
        new Fingering("33En", "33EnB 2", "", "", 2, 1, 1, 1, 1, 1, 2, 0, 1),
        new Fingering("33En", "33EnC", "", "good for Alto", 2, 0, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("33En", "33EnC 1", "", "", 1, 0, 1, 0, 1, 1, 0, 1, -1),
        new Fingering("33En", "33EnC 2", "", "", 2, 2, 1, 0, 0, 0, 0, 0, -1),
        new Fingering("34Fn", "34Fn", "", "", 2, 1, 1, 0, 1, 1, 0, 0, 1),
        new Fingering("34Fn", "34FnB", "", "", 2, 1, 1, 0, 1, 1, 1, 1, -1),
        new Fingering("34Fn", "34FnB 1", "", "", 2, 1, 1, 1, 1, 1, 1, 1, -1),
        new Fingering("34Fn", "34FnB 2", "", "good for Alto", 2, 1, 1, 2, 1, 1, 1, 1, -1),
        new Fingering("34Fn", "34FnB 3", "", "", 2, 1, 1, 0, 0, 1, 1, 1, -1),
        new Fingering("34Fn", "34FnB 4", "", "", 2, 1, 1, 0, 0, 1, 1, 0, 1),
        new Fingering("34Fn", "34FnC", "", "", 2, 1, 1, 0, 0, 1, 0, 0, 1),
        new Fingering("34Fn", "34FnC 1", "", "", 2, 0, 1, 0, 0, 0, 1, 0, 0),
        new Fingering("34Fn", "34FnC 2", "", "", 2, 1, 1, 0, 1, 1, 2, 0, 1),
        new Fingering("34Fs", "34Fs", "", "good for Alto", 2, 1, 1, 0, 1, 1, 0, 0, -1),
        new Fingering("34Fs", "34FsB", "", "", 2, 1, 1, 0, 1, 0, 0, 0, -1),
        new Fingering("34Fs", "34FsC", "", "Hole 1: close 3/4", 2, 2, 2, 0, 1, 1, 0, 0, -1),
        new Fingering("35Gn", "35Gn", "", "good for Alto", 2, 1, 0, 0, 1, 0, 0, 0, -1),
        new Fingering("35Gn", "35GnB", "", "", 2, 1, 0, 0, 0, 0, 0, 0, -1),
        new Fingering("35Gn", "35GnB 1", "", "", 2, 1, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("35Gn", "35GnB 2", "", "good for Alto", 2, 1, 1, 1, 0, 0, 0, 0, 1),
        new Fingering("35Gn", "35GnC", "", "Hole 4: shade", 2, 1, 1, 1, 0, 0, 0, 0, -1),
        new Fingering("35Gn", "35GnC 1", "", "", 2, 1, 1, 0, 1, 0, 0, 0, -1),
        new Fingering("35Gn", "35GnC 2", "", "Hole 1: close 3/4", 2, 2, 0, 0, 1, 0, 0, 0, -1),
        new Fingering("35Gs", "35Gs", "", "", 2, 1, 0, 1, 0, 0, 1, 1, 1),
        new Fingering("35Gs", "35Gs 1", "", "", 2, 1, 0, 1, 0, 0, 0, 0, 1),
        new Fingering("35Gs", "35Gs 2", "", "", 2, 1, 0, 0, 0, 0, 0, 1, 1),
        new Fingering("35Gs", "35GsB", "", "", 2, 1, 0, 1, 0, 2, 1, 0, 1),
        new Fingering("35Gs", "35GsB 1", "", "", 2, 1, 0, 1, 0, 1, 2, 0, 1),
        new Fingering("36An", "36An", "", "good for Alto", 2, 1, 0, 1, 0, 1, 0, 0, -1),
        new Fingering("99xx", "", "", "", 0, 0, 0, 0, 0, 0, 0, 0, -1)
    };
    ////////////////////////////////////////////////////////////////
    public int nGriffe = Griffe.length;
    public int nLoDef = 0;
    public int vorherigeBilder = nBilder;
    public int aBild = 0;
    public boolean isBlink = false;
    public boolean sBlinken = false;
    public int pBlinkTab = 0;
    public int nBlinkTab = 60;
    public int xBlinkTab = 0;
    public ArrayList<Integer> BlinkTab = new ArrayList<Integer>();
    {
        for (int i = 0; i < nBlinkTab; i++) {
            BlinkTab.add(0);
        }
    }

    public void stoppBlink() {
//        for (int pBlinkTab=0; pBlinkTab<nBlinkTab; pBlinkTab++) {
//            BlinkTab[pBlinkTab]=0;
//        }
//        pBlinkTab=0;
//        if (isBlink) {
//            clearTimeout(BlinkID);
//            isBlink=false;
//        }
    }

    public void startBlink() {
//        for (int a=0;a<xBlinkTab;a=a+3) {
//            document.images[BlinkTab.get(a)].src=FT.get(BlinkTab.get(a+2)).src;
//            int n=BlinkTab.get(a+1);
//            BlinkTab.set(a+1, BlinkTab.get(a+2));
//            BlinkTab.set(a+2, n);
//        }
//        isBlink=true;
//        BlinkID=setTimeout("startBlink()",600);
    }

// Function to write text to the top of the recorder into the <H5> tags (params: pos=Recorder number = H5 position, Txt=text to display in the box)
    public void WriteInfo(int pos, String Txt) {
//oH5=document.getElementsByTagName("H5")[pos];
//oH5.innerHTML=Txt;   //oH5.firstChild.nodeValue=Txt; //firstChild is better but does not show the Greek text
//if (Txt=="" || Txt==" " || Txt=="  " || Txt=="   " || Txt=="    ") {oH5.style.display="none"} 
//else {	if (UpsideDown)	oH5.style.marginLeft="-9px";oH5.style.marginTop="30px"; // Default for Upside Down: <H5> Left/Top (layed out for Barock)
//	oH5.style.display="inline";
//	switch (Build) {	// ---- Default for normal display: Left=20px Top=8px (set <H5> in CSS)    Upside-Down: Left= Top= (lines above) 
//		case "Ganassi" :	if (!UpsideDown) {oH5.style.marginLeft="30px";} break; 
//		case "Mol-Ta-Ae","Mol-Ta-Af":		if (UpsideDown) {	oH5.style.marginTop="44px";} break;	
//		case "Supercorder","Supercorder2":if (!UpsideDown) {oH5.style.marginLeft="-4px";
//									oH5.style.marginTop="20px";oH5.style.width="30px";} break;
//		case "Renaissance":	if (!UpsideDown) {oH5.style.marginLeft="4px"; oH5.style.marginTop="16px";}
//					else					    { oH5.style.marginTop="26px";}	 break;	
//		case "Kue-Su-B":	if (!UpsideDown) {oH5.style.marginLeft="6px"; oH5.style.marginTop="254px";}   // no BEM, Info at bottom of rec.
//					else					    { oH5.style.marginTop="26px";} break;	
//		case "Kue-Su-GB":	if (!UpsideDown) {oH5.style.marginLeft="4px"; oH5.style.marginTop="280px";} break;  // no BEM, Info at bottom
//		case "Bre-Eagle":	if (UpsideDown) {oH5.style.marginTop="80px";}else {oH5.style.marginTop="-40px";}
//}}
    }
// Possible BUILDs: Barock, Ganassi, Kue-Cl-B/S, Kue-Su-B/GB, Mol-He, Mol-Ta-Ae/Af, Paetzold (Bem only), Renaissance, Supercorder/..2

// ===  "WriteBem" writes text to a <H6> Tag, which are the "Comments" below the recorder picture (params: pos=H6 position, Txt=text)
    public void WriteBem(int pos, String Txt) {
//oH6=document.getElementsByTagName("H6")[pos];
//oH6.innerHTML=Txt;
//if (Txt=="" || Txt==" " || Txt=="  " || Txt=="   " || Txt=="    ") {oH6.style.display="none";}
//else {	if (UpsideDown)	oH6.style.marginLeft="-9px";oH6.style.marginTop="28px"; // Default for Upside Down: <H6> Left/Top (layed out for Barock)
//	oH6.style.display="inline";
//	switch (Build){   						 //default for BEM<H6>   Left = 10px   Top = 0 px (which is underneath image)
//  		case "Ganassi" : if (!UpsideDown) {oH6.style.marginTop="0px";}	;
//  						   oH6.style.marginLeft="24px"; break;	// Left gilt fuer beides (upside down/up)
//  		case "Barock"  : if (!UpsideDown) 	{oH6.style.marginLeft="18px";oH6.style.marginTop="-56px";} break;
//  		case "Kue-Cl-G": if (!UpsideDown) 	{			     oH6.style.marginTop="-14px";} break;
//  		case "Supercorder","Supercorder2":  oH6.style.marginTop="-7px"; break;    // some Bem in hole 8 place (valid for normal & UpsideDown
//		case "Paetzold":  oH6.style.marginTop="-30px"; break;
//  		case "Renaissance":if (!UpsideDown) 	{oH6.style.marginLeft="2px"; oH6.style.marginTop="-50px";}
//  				   else  		{			     oH6.style.marginTop="10px";}	 break;
//}}
    } // eof WriteBem

    public static int count = 0;
    
    public boolean FuGriff(String ToneID, String Octave, String Tone, String ToneType, String ToneX, Image[] docImgs) {
        //ToneID holds internal tone name, eg: 15Gn or 21Cs or  11Cn*12Dn (trill) and text in local lang
        //alert ("FuGriff:"+ToneID);
        //alert("FuGriff-Aufruf:"+document.images.length);
        String xGriffID = "Diag:"; // Diagnostics if manually or from the menu URL shows Info=Test or Info=uc (Diag shows original ID)
        int aBild = 0;
        boolean sGriffDa = false;
        stoppBlink();

        //	(Nopped 11/2008, activated 3/2012, see also code in Main-F.php "TonBox"							Display tone text in 4th line
        String ToneTextDisplay = "<span class='GriffText'>" + Octave + " &nbsp; <span class='Grifftone'>" + Tone
                + "</span> &nbsp; " + ToneType + " &nbsp; <span class='Grifftone'>" + ToneX + "</span></span>";
//    e=document.getElementById("TonBox");
//    e.innerHTML=ToneTextDisplay;
//    e=document.getElementById("DiagFingNo");
//    e.innerHTML=ToneID;  // Display tone ID for Diagnostic

        for (int pGriffe = 0; pGriffe < nGriffe; pGriffe++) {  						// === find fingering in array =======         
            if (ToneID.compareTo(Griffe[pGriffe].note) > 0) {
                continue;
            } // not yet found
            if (ToneID.compareTo(Griffe[pGriffe].note) < 0) {
                break;
            };    // passed     
            if (!ZeigeKreisGriffe && Griffe[pGriffe].h9 == 1) {
                continue;
            }; 	// don't show fingerings with bell closed
            sGriffDa = true;
            xGriffID = xGriffID + "(" + Griffe[pGriffe].id + ")"; 		// ID into diag msg
            if (nBilder <= aBild) {
                System.err.println("Coding error - please report to Webmaster (Error: Wrong nB in aBar.1S.3a = Barock/Bar for " + Griffe[pGriffe].note + ")");
                continue;
            }

// ==========================================================================================================================================
//														FINGERING FOUND	
// loop through all recorder parts and show the img (replace it by the new one) ==============================================================
            for (int aLoch = 0; aLoch < nBildTeile; aLoch++) // nBildTeile is defined in Build.js (Build = name of recorder images)
            {
                nLoDef = Griffe[pGriffe].holes[aLoch]; 					// = Hole (Loch) definition (open, closed etc. ..)    
                if (nLoDef > maxLoDef) {
                    System.err.println("Coding error - please report to Webmaster (Error: Incorr hole def in Barock/Bar for " + Griffe[pGriffe].note + " with: " + Tone + "Def=" + nLoDef + ")");
                    continue;
                }
                int pImage = nBildVor + aBild * nBildTeile;						//Pos first Image part of the recorder
                if (!UpsideDown) {
                    pImage = pImage + aLoch;
                } // ... upside up - normal recorder position
                else if (UpsideDown && posImg0 > 1 && aLoch == 0) {
                    pImage = pImage - aLoch + 1;	// ... upside down - image 0 format 2-8 (Barock,Ganassi)
                } else if (UpsideDown && posImg0 > 1 && aLoch >= posImg0) {
                    pImage = pImage + nBildTeile - aLoch - 1; 	// ... upside down - image parallel to img 0 
                } else if (UpsideDown && posImg0 > 1) {
                    pImage = pImage + nBildTeile - aLoch;		// all other upside down image parts
                }//	    else if (UpsideDown && posImg0==0)         		pImage=pImage+nBildTeile-aLoch-1;	// format 0 (Renaissance etc.)
                else {
                    pImage = pImage + nBildTeile - aLoch - 1;	// format 0+1 (Renaissance etc.)
                }
                int pFT = FTx[aLoch * nFloeBild + nLoDef]; 						// get image pointer from ref table FTx (hole*maxImg+Def)
                if (nLoDef < 0) {
                    pFT = enFT;
                } 							// if invalid hole definition = get an empty image (blank)
                if (nLoDef > 7) {
                    pFT = FTx[aLoch * nFloeBild];
                }					// if alternate blinking = start with an "open" img
                if (pFT == 0) {
                    System.err.println("Coding error - please report to Webmaster (ERROR: Image not available or not in Imap in aBar.1S.3a = Barock/Bar fing " + Griffe[pGriffe].note + " for this part of the recorder: F " + aLoch + " " + nLoDef);
                    continue;
                }
                System.out.println("count " + count);count++;
                docImgs[pImage] = FT.get(pFT);   					// ok, no error, replace the image
                if (nLoDef > 5) // trill fingerings (hole def 6,7,8)
                {
                    sBlinken = true;								// set BLINK on
                    try {
                    BlinkTab.set(pBlinkTab, pImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pBlinkTab++; 				//image position for BLINK function in Blink tab
                    BlinkTab.set(pBlinkTab, pFT);
                    pBlinkTab++;     				//current img
                    if (nLoDef > 7) // if alternate blinking
                    {
                        BlinkTab.set(pBlinkTab, FTx[aLoch * nFloeBild + nLoDef - 2]);
                        pBlinkTab++;
                    } // alt image = fingering def 8/9 blinks with 6/7
                    else {
                        BlinkTab.set(pBlinkTab, FTx[aLoch * nFloeBild]);
                        pBlinkTab++;
                    } // alt image = fingering Fx0-7
                }
            } //end  for (all holes shown)
            WriteBem(aBild, Griffe[pGriffe].msgB);  				//GriffBem contains full text (has been created in Inc-Fingerings.php)
            WriteInfo(aBild, Griffe[pGriffe].msgA);				//same for Info 

            aBild++;  								//next pic, set also for delete
        }  //end for (all fingerings avail)

        xBlinkTab = pBlinkTab;  								//save last used pos+1 in blink table


// ============== following coding clears the remaining recorder pictures (not used ones), but show at least one empty, if no fingering at all ====
        
/**/
        for (int n = aBild; n < vorherigeBilder; n++) {			// loop through all recorder images
            WriteBem(n, "");
            WriteInfo(n, "");				// clear info boxes
            int pImage = nBildVor + n * nBildTeile;				// pointer for screen image
            for (int x = 0; x < nBildTeile; x++) // nBildTeile = number of parts per recorder
            {
                if (n > 0) {
            docImgs[pImage+x] = FT.get(0);
                } // not last picture = clear part-image 
                else {
                    int nHole = x;								// last picture = empty recorder - set hole number		
                    if (UpsideDown) {
                        nHole = (nBildTeile - x - 1);					// correct hole number for upside down
                        if (posImg0 > 1 && (nHole) == (posImg0 - 1)) {
                            nHole = 0;	// ... thumb hole (hole 0) to be set here besides first displ hole
                        } else if (posImg0 > 1 && (nHole) < posImg0) {
                            nHole = nHole + 1;// parts behind the posImg0 + 0 part to be incremented (0 part was inserted)
                        }
                    }
                    int pFT = FTx[nHole * nFloeBild];			// Hole number, nFloeBild is max number of img in img table
                    if (nHole == 8) {
                        pFT = enFT;				// Hole 8 (bell) = dont show open hole but an empty image (enFT points to F8b.gif)
                    }		//if (pFT==0) pFT=enFT;				// no pointer, corr. to last image (this is F8b.gif = blank image for hole 8)
                    //alert ("x="+x+" nHole="+nHole);
		docImgs[pImage+x] = FT.get(pFT);	// replace the image
                }
            }
        }
/**/
        
// ------------------------------ nopped the Ton-Box (leave it, we might need it later again)
// if (UnderConstr==1) {ToneTextDisplay=ToneTextDisplay+" &nbsp; ("+xGriffID+")";}
// e=document.getElementById("TonBox");e.innerHTML=ToneTextDisplay;    

        // === Show  messages ====
//        e=document.getElementById("FingeringMsg");e.style.visibility="hidden";
//        if (sGriffDa == false) {
//            String Text = "Fingering not available";
//
//		e.innerHTML="<br>"+Text+"<br>&nbsp;"; e.style.visibility="visible";
//        }
        vorherigeBilder = aBild;
        if (sBlinken) {
            startBlink();
        }
        return true;
    }	// end of function FuGriff  =================
}
