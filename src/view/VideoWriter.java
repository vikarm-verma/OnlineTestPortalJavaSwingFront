package view;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import static com.mchange.v2.util.CollectionUtils.size;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.video.ConverterFactory;
import com.xuggle.xuggler.video.IConverter;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vikram
 */
public class VideoWriter {

    public static void main(String[] args) throws InterruptedException {
        VideoWriter videoWriter = new VideoWriter();
        videoWriter.startVideoRecording();
    }

    public void startVideoRecording() throws InterruptedException {
        File saveFile = new File("saved.mp4");
        IMediaWriter writer = ToolFactory.makeWriter(saveFile.getName());
        Dimension size = WebcamResolution.QQVGA.getSize();
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);
        long start = System.currentTimeMillis();
        Webcam webcam = openWebcam(size);
        for (int i = 0; i < 500; i++) {
            BufferedImage image = ConverterFactory.convertToType(webcam.getImage(), BufferedImage.TYPE_3BYTE_BGR);
            IConverter converter = ConverterFactory.createConverter(image, IPixelFormat.Type.YUV420P);
            IVideoPicture frame = converter.toPicture(image, (System.currentTimeMillis() - start) * 1000);
            frame.setKeyFrame(i == 0);
            frame.setQuality(100);
            writer.encodeVideo(0, frame);
            Thread.sleep(20);
            System.out.println("frame is " + i);
        }
        writer.close();
        System.out.println("video recorded in the file" + saveFile.getAbsolutePath());
    }

    private Webcam openWebcam(Dimension size) {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(size);
        webcam.open();
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setMirrored(true);
        JFrame frame = new JFrame();
        frame.add(webcamPanel);
        frame.dispose();
        frame.setUndecorated(true);
        frame.pack();
        frame.setVisible(true);
        return webcam;
    }
}
