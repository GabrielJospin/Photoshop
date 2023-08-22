package photoshop.filters;

import photoshop.Image;

import photoshop.Point;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MedianFilterGray extends Filter{

    int radius;

    public MedianFilterGray(Image imageIn, Image imageOut, int radius) {
        super(imageIn, imageOut);
        this.radius = radius;
    }

    @Override
    public void apply() {
        BufferedImage original = this.imageIn.getBuffer();
        final BufferedImage filtered = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int x = 0; x < original.getWidth(); x++){
            for(int y=0; y < original.getHeight(); y++){
                Point point = new Point(x, y);
                List<Thread> treads = new ArrayList<>();

                treads.add(new Thread(new Runnable(){
                    @Override
                    public void run() {
                        List<Integer> list = new ArrayList();
                        for(int x_ = 0; x_ < original.getWidth(); x_++){
                            for(int y_ = 0; y_ < original.getHeight(); y_++){
                                if(point.distManhathan(new Point(x_, y_)) <= radius){
                                    Color c = new Color(original.getRGB(x_, y_));
                                    list.add(c.getRed());
                                }
                            }
                        }
                        
                        if(list.size() > 0){
                            list.sort(Comparator.naturalOrder());
                            int median = list.get((int) list.size()/2 + 1);
                            filtered.setRGB(point.x, point.y, new Color(median, median, median).getRGB());
                        }else{
                            filtered.setRGB(point.x, point.y, new Color(0, 0, 0).getRGB());
                        }
                    }

                }));

                for(Thread t: treads)
                    t.start();
                
                try{
                    for(Thread t: treads)
                        t.join();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        this.imageOut.setBuffer(filtered);
    }



    

    
}
