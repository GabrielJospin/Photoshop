package photoshop;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

public class Image {
    
    private String path;
    private File file;
    private BufferedImage buffer;
    private Boolean isOpen = false;

    public Image(){

    }

    public Image(String path) throws IOException{
        this.path = path;
        this.file = new File(path);
        this.buffer = file.exists() ? ImageIO.read(file) : null;
        this.isOpen = true;
        
    }

    public String getPath(){
        return this.path;
    }

    public File getFile(){
        return this.file;
    }

    public BufferedImage getBuffer(){
        return this.buffer;
    }

    public void setBuffer(BufferedImage bufferedImage){
        if(this.buffer != null)
            this.buffer.flush();
        this.buffer = bufferedImage;
    }

    public Boolean isOpen(){
        return this.isOpen;
    }

    public void setImage(String path) throws Exception{
        if(isOpen){
            throw new Exception("Image is open, you can't set the path");
        }
        this.path = path;
        this.file = new File(path);
        this.buffer = ImageIO.read(file);
        this.isOpen = true;
    }

    public void close() throws IOException{ 
        ImageIO.write(this.buffer, "jpg", this.file);
        this.buffer.flush();
        this.isOpen = false;
    }

    public void show(){

        ImageIcon imageIcon = new ImageIcon(this.buffer);

        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());        
        jFrame.setSize(this.buffer.getWidth(), this.buffer.getHeight(jFrame));

        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);
        jFrame.setVisible(true);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    


}
